package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.*;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.services.ServiceValidateResource;
import switchtwentyone.project.domain.services.SprintValidationService;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.dto.*;
import switchtwentyone.project.dto.mapper.ResourceMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ResourceRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class ApplicationServiceCreateResource {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    ResourceMapper mapper;
    @Autowired
    ServiceValidateResource resourceService;

    @Autowired
    SprintValidationService sprintService;

    @Autowired
    ResourceCreatable resourceCreatable;

    @Autowired
    AccountRepository accountRepository;

    /**
     * Creates a new resource
     * @param dto contains all required information to perform the operation
     * @return DTO containing information of the newly created resource, if operation is successful
     */
    public ResourceDTO createResource(NewResourceDTO dto, int pid) {
        Email email = Email.of(dto.associatedAccount);
        ProjectID pID = new ProjectID(pid);
        AccountID accountID = AccountID.of(email);
        Role role = new Role(Erole.valueOf(dto.associatedRole),
                NoNumberNoSymbolString.of(Erole.valueOf(dto.associatedRole).getDescription()));
        ResourceID resourceID = ResourceID.create();
        Period period = Period.between(dto.startDate, dto.endDate);
        LimitedPercentage percentage = LimitedPercentage.decimal(dto.percentageOfAllocation);
        Monetary cost = Monetary.of(dto.costPerHourValue, Currency.getInstance(dto.currency));

        makeCommonResourceValidations(email, pID, period, percentage, accountID);
        List<Resource> resourcesOfProject = resourceRepository.findAllByProjectID(pID);
        if (!resourceService.validateResourceIsNotRepeated(resourcesOfProject, period, email)) {
            throw new IllegalArgumentException("Resource is already allocated to project");
        }

        if (!Erole.valueOf(dto.associatedRole).equals(Erole.DEVELOPER)
                && !resourceService.validateRoleIsUniqueInPeriod(resourcesOfProject, period, role)) {
            throw new IllegalArgumentException("Role is already taken in period");
        }

        if (Erole.valueOf(dto.associatedRole).equals(Erole.SCRUM_MASTER)
                || Erole.valueOf(dto.associatedRole).equals(Erole.DEVELOPER)) {

            List<Sprint> sprints = sprintRepository.findAllSprintsByProjectID(pID);
            if (!sprintService.validateSprintRules(period, sprints)) {
                throw new IllegalArgumentException("Resource cannot be allocated in the course of a Sprint");
            }

        }


        Resource resource = resourceCreatable.createResource(accountID, resourceID, role, period, percentage, pID, cost);

        Resource savedResource = resourceRepository.save(resource);


        return mapper.toDTO(savedResource);
    }

    //TODO this should be made in a domain service
    private void makeCommonResourceValidations(Email email, ProjectID pID, Period period, LimitedPercentage percentage, AccountID accountID) {
        Optional<Project> opProject = projectRepository.findById(pID);
        if (opProject.isEmpty()) {
            throw new EntityNotFoundException("Project not found");
        }
        if (!accountRepository.existsByAccountID(accountID)) {
            throw new EntityNotFoundException("Account not found");
        }
        Project project = opProject.get();
        if (!project.containsPeriod(period)) {
            throw new IllegalArgumentException("Invalid allocation period");

        }
        List<Resource> resourcesOfAccount = resourceRepository.findAllByAccountID(email);

        if (!resourceService.validatePercentageOfAllocation(resourcesOfAccount, period, percentage)) {
            throw new IllegalArgumentException("Allocation exceeds maximum");
        }
    }

    /**
     * Creates or redefines a project's Product Owner
     *
     * @param dto       contains all required information to perform the operation
     * @param projectID the ID of the project to which the Product Owner will be allocated
     * @return DTO containing information of the newly created resource, if operation is successful
     */

    public ResourceDTO defineProductOwner(NewProductOwnerDTO dto, int projectID) {
        ProjectID pID = new ProjectID(projectID);
        Email email = Email.of(dto.associatedAccount);
        AccountID accountID = AccountID.of(email);
        Role role = new Role(Erole.PRODUCT_OWNER,
                NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription()));
        ResourceID resourceID = ResourceID.create();
        Period period = Period.between(dto.startDate, dto.endDate);
        LimitedPercentage percentage = LimitedPercentage.decimal(dto.percentageOfAllocation);
        Monetary cost = Monetary.of(dto.costPerHourValue, Currency.getInstance(dto.currency));

        makeCommonResourceValidations(email, pID, period, percentage, accountID);

        List<Resource> resourcesOfProject = resourceRepository.findAllByProjectID(pID);
        if (!resourceService.validateResourceIsNotRepeated(resourcesOfProject, period, email)) {
            throw new IllegalArgumentException("Resource is already allocated to project");
        }
        resourceRepository.deleteResources(role, pID, period);

        if (!resourceService.validateRoleIsUniqueInPeriod(resourcesOfProject, period, role)) {
            Optional<Resource> opResource = resourceRepository.findActiveResource(role, pID, dto.startDate);
            if (opResource.isPresent()) {
                Resource currentProductOwner = opResource.get();
                currentProductOwner.changeEndDate(dto.startDate.minusDays(1));
                resourceRepository.save(currentProductOwner);
            }
        }
        Resource newProductOwner = resourceCreatable.createResource(accountID, resourceID, role, period, percentage, pID, cost);

        Resource savedProductOwner= resourceRepository.save(newProductOwner);

        return mapper.toDTO(savedProductOwner);

    }

    public ResourceDTO updateScrumMaster(NewScrumMasterDTO dto, int projectID) {
        ProjectID id = new ProjectID(projectID);
        Role scrumMaster = new Role(Erole.SCRUM_MASTER, NoNumberNoSymbolString.of(Erole.SCRUM_MASTER.getDescription()));
        Role developer = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription()));
        Email newSMAccount = Email.of(dto.associatedAccount);
        Period newPeriod = Period.between(LocalDate.parse(dto.startDate),LocalDate.parse( dto.endDate));
        LocalDate date = newPeriod.getStartingDate();

        Optional<Project> oPproject = projectRepository.findById(id);


        if (oPproject.isEmpty()) {

            throw new EntityNotFoundException("No project found");
        }
        Project project = oPproject.get();
        if (!project.containsPeriod(newPeriod)){
            throw new IllegalArgumentException("Invalid allocation period");
        }

        Optional<Resource> actualSM = resourceRepository.findActiveResource(scrumMaster, id, date);

        if (actualSM.isPresent()) {
            Resource endSM = actualSM.get();
            scrumMasterToDev(id, developer, newPeriod, endSM);
        }
        Optional<Resource> actualDev = resourceRepository.findActiveResourceByAccountAndProjectID(AccountID.of(newSMAccount), id, date);

        Resource endDev = actualDev.get();
        Resource newScrumMaster = devToScrumMaster(id, scrumMaster, newPeriod, date, endDev);

        ResourceDTO result = mapper.toDTO(newScrumMaster);

        return result;
    }

    private Resource devToScrumMaster(ProjectID id, Role scrumMaster, Period newPeriod, LocalDate date,Resource endDev) {

        endDev.changeEndDate(date.minusDays(1));
        resourceRepository.save(endDev);

        List<Resource> resourcesOfAccount = resourceRepository.findAllByAccountID(endDev.getAssociatedAccount().getID());

        if (!resourceService.validatePercentageOfAllocation(resourcesOfAccount, newPeriod, endDev.getPercentageOfAllocation())) {
            throw new IllegalArgumentException("Allocation exceeds maximum");
        }
        List<Sprint> sprints = sprintRepository.findAllSprintsByProjectID(id);
        if (!sprintService.validateSprintRules(newPeriod, sprints)) {
            throw new IllegalArgumentException("Resource cannot be allocated in the course of a Sprint");
        }

        ResourceID newScrumID = ResourceID.create();
        Resource newScrumMaster = resourceCreatable.createResource(endDev.getAssociatedAccount(), newScrumID,
                scrumMaster, newPeriod, endDev.getPercentageOfAllocation(), id, endDev.getCostPerHour());
        resourceRepository.save(newScrumMaster);

        return newScrumMaster;
    }


    private void scrumMasterToDev(ProjectID id, Role developer, Period newPeriod, Resource endSM) {

        endSM.changeEndDate(newPeriod.getStartingDate().minusDays(1));
        resourceRepository.save(endSM);

        List<Resource> resourcesOfAccount = resourceRepository.findAllByAccountID(endSM.getAssociatedAccount().getID());

        if (!resourceService.validatePercentageOfAllocation(resourcesOfAccount, newPeriod, endSM.getPercentageOfAllocation())) {
            throw new IllegalArgumentException("Allocation exceeds maximum");
        }
        List<Sprint> sprints = sprintRepository.findAllSprintsByProjectID(id);
        if (!sprintService.validateSprintRules(newPeriod, sprints)) {
            throw new IllegalArgumentException("Resource cannot be allocated in the course of a Sprint");
        }
        ResourceID newDevID = ResourceID.create();
        Resource newDev = resourceCreatable.createResource(endSM.getAssociatedAccount(), newDevID,
                developer, newPeriod, endSM.getPercentageOfAllocation(), id, endSM.getCostPerHour());
        resourceRepository.save(newDev);
    }
    public List<ResourceDTO> getAllActiveResourcesByProjectID(int id, LocalDate date){
        ProjectID projectID = new ProjectID(id);
        List <Resource> resource = resourceRepository.findAllActiveResourcesByProjectID(projectID,date);
        List<ResourceDTO> resourceDTOS = new ArrayList<>();
        for(Resource rsc : resource){
            ResourceDTO dto  = mapper.toDTO(rsc);
            //Link link = linkTo(methodOn(ResourceController.class).(p.getIDAsInt())).withSelfRel();
            resourceDTOS.add(dto);
        }
        return resourceDTOS;
    }

    /*
    This method returns all the resources of a project, that att some
    time played a role.
     */
    public List<ResourceDTO> getAllResourcesByProjectID(int pid){
        ProjectID projectID = new ProjectID(pid);
        List<Resource> resources = resourceRepository.findAllByProjectID(projectID);
        List<ResourceDTO> resourcesDTO = new ArrayList<>();
        for (Resource rsc : resources){
            ResourceDTO resourceDTO = mapper.toDTO(rsc);
            resourcesDTO.add(resourceDTO);
        }
        return resourcesDTO;
    }
}

