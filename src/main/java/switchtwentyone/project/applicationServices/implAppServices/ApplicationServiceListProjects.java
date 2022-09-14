package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.iwebrepositories.ProjectWebRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ResourceRepository;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.*;
import switchtwentyone.project.externaldto.assemblers.ExternalProjectMapper;
import switchtwentyone.project.dto.mapper.ProjectMapper;
import switchtwentyone.project.externaldto.ExternalProjectDTO;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceListProjects {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectWebRepository projectWebRepository;

    @Autowired
    private ExternalProjectMapper mapper;

    public List<ProjectDTO> getListOfAllocatedProjects(
            String uid, String role) {
        Email email = Email.of(uid);
        Role resourceRole = new Role(Erole.valueOf(role),
                NoNumberNoSymbolString.of(Erole.valueOf(role).getDescription()));

        List<ProjectDTO> listOfProjects = new ArrayList<>();
        List<Resource> resources = resourceRepository
                .findAllActiveResourcesByEmailAndRole(email, resourceRole, LocalDate.now());

        return getProjectDTOS(listOfProjects, resources, email);
    }


    public List<ProjectDTO> getListOfAllocatedProjects(Email email) {
        List<ProjectDTO> listOfProjects = new ArrayList<>();
        List<Resource> resources = resourceRepository.findAllActiveResourcesByEmail(email, LocalDate.now());
        return getProjectDTOS(listOfProjects, resources, email);
    }


    public List<ProjectDTO> getAllProjects(String uid) {
        Email email = Email.of(uid);
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (Project p : projects) {
            ProjectDTO dto = ProjectMapper.toDTO(p);
            generateAndAddLinks(email, p, dto);

            projectDTOS.add(dto);

        }
        List<ExternalProjectDTO> externalProjects = projectWebRepository.getProjects();
        for (ExternalProjectDTO externalDTO : externalProjects){
            ProjectDTO dto = mapper.toDTO(externalDTO);
            projectDTOS.add(dto);
        }

        return projectDTOS;
    }


    private void generateAndAddLinks(Email email, Project p, ProjectDTO dto) {
        Link link = linkTo(methodOn(ProjectController.class).getProjectDetails(p.getIDAsInt())).withSelfRel();
        Link linkSprints = linkTo(methodOn(SprintController.class).getAllSprintsByProjectID(p.getIDAsInt())).withRel("allSprints");
        Link linkProductBacklog = linkTo(methodOn(ProductBacklogController.class).getProductBacklog(p.getIDAsInt(), email.getEmailData())).withRel("productBacklog");
        Link createResource = linkTo(methodOn(ResourceController.class).createResource(new NewResourceDTO(), p.getIDAsInt())).withRel("resources");
        Link linkResources = linkTo(methodOn(ResourceController.class).getResourcesByProjectID(p.getIDAsInt(),"active")).withRel("activeResources");
        Link linkResourcesAll = linkTo(methodOn(ResourceController.class).getResourcesByProjectID(p.getIDAsInt(),"all")).withRel("allResources");
        Link linkProductOwner = linkTo(methodOn(ResourceController.class).defineProductOwner(new NewProductOwnerDTO(), p.getIDAsInt())).withRel("productOwner");
        Link linkScrumMaster = linkTo(methodOn(ResourceController.class).newScrumMaster(new NewScrumMasterDTO(), p.getIDAsInt())).withRel("scrumMaster");
        Link linkUserStories = linkTo(methodOn(UserStoryController.class).viewUSStatusFromProject(p.getIDAsInt())).withRel("userStories");
        Link linkSprintBacklogItems = linkTo(methodOn(UserStoryController.class).getAllSprintBacklogItemsByProject(p.getIDAsInt())).withRel("sprintBacklogItems");
        Link linkScrumBoard = linkTo(methodOn(ScrumBoardController.class).viewScrumBoard(p.getIDAsInt())).withRel("scrumBoard");
        Link linkCreateProject = linkTo(methodOn(ProjectController.class).getListOfAllProjects(email.getEmailData())).withRel("createProject");
        Link linkCreateTypology = linkTo(methodOn(ProjectTypologyController.class).getAllProjectTypologies()).withRel("createTypology");
        Link linkCustomers = linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("allCustomers");
        Link linkTypologies = linkTo(methodOn(ProjectTypologyController.class).getAllProjectTypologies()).withRel("allTypologies");
        Link linkBusinessSectors = linkTo(methodOn(BusinessSectorController.class).getAllBusinessSectors()).withRel("allBusinessSectors");
        Link linkRunningSprint = linkTo(methodOn(SprintController.class).gerRunningSprintByProjectID(p.getIDAsInt())).withRel("runningSprint");
        Link linkTasks = linkTo(methodOn(TaskController.class).viewTaskSatusFromProject(p.getIDAsInt())).withRel("tasks");
        Link linkUSNotDoneInRunningSprint = linkTo(methodOn(SprintController.class).getUserStoriesDoneInRunningSprint(p.getIDAsInt())).withRel("notDoneUS");
        Link linkCreateTask = linkTo(methodOn(TaskController.class).createTask(p.getIDAsInt(), new TaskCreationDTO())).withRel("createTask");

        dto.add(link);
        dto.add(linkSprints);
        dto.add(linkProductBacklog);
        dto.add(createResource);
        dto.add(linkResources);
        dto.add(linkResourcesAll);
        dto.add(linkProductOwner);
        dto.add(linkScrumMaster);
        dto.add(linkUserStories);
        dto.add(linkSprintBacklogItems);
        dto.add(linkScrumBoard);
        dto.add(linkCreateProject);
        dto.add(linkCreateTypology);
        dto.add(linkCustomers);
        dto.add(linkTypologies);
        dto.add(linkBusinessSectors);
        dto.add(linkRunningSprint);
        dto.add(linkTasks);
        dto.add(linkUSNotDoneInRunningSprint);
        dto.add(linkCreateTask);
    }

    private List<ProjectDTO> getProjectDTOS(List<ProjectDTO> listOfProjects, List<Resource> resources, Email email) {
        for (Resource r : resources) {
            ProjectID id = r.getProjectID();
            Optional<Project> opProject = projectRepository.findById(id);

            if (opProject.isPresent()) {
                Project project = opProject.get();
                ProjectDTO dto = ProjectMapper.toDTO(project);
                generateAndAddLinks(email, project, dto);
                listOfProjects.add(dto);
            }
        }

        return listOfProjects;
    }

    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (Project p : projects) {
            ProjectDTO dto = ProjectMapper.toDTO(p);
            projectDTOS.add(dto);
        }
        return projectDTOS;
    }

}
