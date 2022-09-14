package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectFactory;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProjectInfoNecessaryToCreateDTO;
import switchtwentyone.project.dto.ProjectInfoReturnedWhenCreatedDTO;
import switchtwentyone.project.dto.mapper.ProjectMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.BusinessSectorRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.CustomerRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectTypologyRepository;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ProjectController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceCreateProject {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectTypologyRepository projectTypologyRepository;

    @Autowired
    BusinessSectorRepository businessSectorRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProjectFactory projectFactory;


    public ProjectInfoReturnedWhenCreatedDTO createProject(ProjectInfoNecessaryToCreateDTO info) {

        Nameable name = NoNumberNoSymbolString.of(info.name);
        Describable description = Text.write(info.description);
        Period period = Period.between(info.startDate, info.endDate);
        PositiveNumber sprintDuration = PositiveNumber.of(info.sprintDuration);
        PositiveNumber budget = PositiveNumber.of(info.budget);
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of(info.ProjectTypologyID));
        CustomerID customerID = CustomerID.of(NIF.of(info.CustomerID));
        BusinesSectorID businessID = BusinesSectorID.of(CAE.of(info.BusinessSectorID));

        boolean projectTypologyExists = projectTypologyRepository.findProjectTypologyByID(projectTypologyID).isPresent();
        boolean businessSectorExists = businessSectorRepository.findBusinessSectorByID(businessID).isPresent();
        boolean customerExists = customerRepository.findCustomerByID(customerID).isPresent();

        ProjectInfoReturnedWhenCreatedDTO projectDTOCreated;

        if (projectTypologyExists && businessSectorExists && customerExists) {
            ProjectID newProjectID = new ProjectID(projectRepository.getHighestProjectID() + 1);
            Project newProject = projectFactory.createProject(newProjectID, name, description,
                    period, sprintDuration, budget, customerID,
                    businessID, projectTypologyID);
            Project projectCreated = projectRepository.save(newProject);
            projectDTOCreated = ProjectMapper.toDTOReturnedUpoCreation(projectCreated);

            Link link = linkTo(methodOn(ProjectController.class).getProjectDetails(projectDTOCreated.projectID)).withSelfRel();
            projectDTOCreated.add(link);

        } else {
            throw new IllegalArgumentException("Project Typology/Business Sector/Customer does not exist");
        }
        return projectDTOCreated;
    }



}