package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectFactory;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
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

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@ExtendWith(MockitoExtension.class)
class ApplicationServiceCreateProjectTest {

    @Mock
    ProjectRepository projectRepository;

    @Mock
    BusinessSectorRepository businessSectorRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    ProjectTypologyRepository projectTypologyRepository;

    @Mock
    ProjectFactory projectFactory;

    @InjectMocks
    ApplicationServiceCreateProject applicationServiceCreateProject;



    @Test
    void createProject() {
        ProjectInfoNecessaryToCreateDTO info = mock(ProjectInfoNecessaryToCreateDTO.class);
        info.name = "name";
        info.description = "description";
        info.startDate = LocalDate.of(2021,8,25);
        info.endDate = LocalDate.of(2021,10,25);
        info.sprintDuration = 5;
        info.budget = 5000;
        info.BusinessSectorID = "00000";
        info.CustomerID = 257715347;
        info.ProjectTypologyID = "PT";

        Nameable name = NoNumberNoSymbolString.of(info.name);
        Describable description = Text.write(info.description);
        Period period = Period.between(info.startDate, info.endDate);
        PositiveNumber sprintDuration = PositiveNumber.of(info.sprintDuration);
        PositiveNumber budget = PositiveNumber.of(info.budget);
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of(info.ProjectTypologyID));
        CustomerID customerID = CustomerID.of(NIF.of(info.CustomerID));
        BusinesSectorID businessID = BusinesSectorID.of(CAE.of(info.BusinessSectorID));

        ProjectTypology projectTypology = mock(ProjectTypology.class);
        Business business = mock(Business.class);
        Customer customer = mock(Customer.class);


        when(businessSectorRepository.findBusinessSectorByID(businessID)).thenReturn(Optional.of(business));
        when(customerRepository.findCustomerByID(customerID)).thenReturn(Optional.of(customer));
        when(projectTypologyRepository.findProjectTypologyByID(projectTypologyID)).thenReturn(Optional.of(projectTypology));

        when(projectRepository.getHighestProjectID()).thenReturn(2);
        ProjectID projectID = new ProjectID(3);

        Project project = mock(Project.class);
        when(projectFactory.createProject(projectID, name, description, period, sprintDuration,
                budget, customerID, businessID,  projectTypologyID)).thenReturn(project);

        Optional<Project> projectCreatedExpected = Optional.of(project);
        when(projectRepository.save(project)).thenReturn(project);

        ProjectInfoReturnedWhenCreatedDTO projectDTOExpected = ProjectMapper.toDTOReturnedUpoCreation(projectCreatedExpected.get());
        Link link = linkTo(methodOn(ProjectController.class).getProjectDetails(projectDTOExpected.projectID)).withSelfRel();
        projectDTOExpected.add(link);

        ProjectInfoReturnedWhenCreatedDTO projectCreatedResult = applicationServiceCreateProject.createProject(info);

        assertEquals(projectDTOExpected, projectCreatedResult);

    }

    @Test
    void createProject_CantCreate_customer() {
        ProjectInfoNecessaryToCreateDTO info = mock(ProjectInfoNecessaryToCreateDTO.class);
        info.name = "name";
        info.description = "description";
        info.startDate = LocalDate.of(2021,8,25);
        info.endDate = LocalDate.of(2021,10,25);
        info.sprintDuration = 5;
        info.budget = 5000;
        info.BusinessSectorID = "00000";
        info.CustomerID = 257715347;
        info.ProjectTypologyID = "PT";

        Nameable name = NoNumberNoSymbolString.of(info.name);
        Describable description = Text.write(info.description);
        Period period = Period.between(info.startDate, info.endDate);
        PositiveNumber sprintDuration = PositiveNumber.of(info.sprintDuration);
        PositiveNumber budget = PositiveNumber.of(info.budget);
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of(info.ProjectTypologyID));
        CustomerID customerID = CustomerID.of(NIF.of(info.CustomerID));
        BusinesSectorID businessID = BusinesSectorID.of(CAE.of(info.BusinessSectorID));

        ProjectTypology projectTypology = mock(ProjectTypology.class);
        Business business = mock(Business.class);
        Customer customer = mock(Customer.class);


        when(businessSectorRepository.findBusinessSectorByID(businessID)).thenReturn(Optional.of(business));
        when(customerRepository.findCustomerByID(customerID)).thenReturn(Optional.empty());
        when(projectTypologyRepository.findProjectTypologyByID(projectTypologyID)).thenReturn(Optional.of(projectTypology));

//        when(projectRepository.getHighestProjectID()).thenReturn(2);
        ProjectID projectID = new ProjectID(3);

        Project project = mock(Project.class);
//        when(projectFactory.createProject(projectID, name, description, period, sprintDuration,
//                budget, customerID, businessID,  projectTypologyID)).thenReturn(project);

        Optional<Project> projectCreatedExpected = Optional.of(project);
//        when(projectRepository.save(project)).thenReturn(project);

        Optional<ProjectInfoReturnedWhenCreatedDTO> projectDTOExpected = Optional.of(ProjectMapper.toDTOReturnedUpoCreation(projectCreatedExpected.get()));

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateProject.createProject(info));

    }


    @Test
    void createProject_CantCreate_business() {
        ProjectInfoNecessaryToCreateDTO info = mock(ProjectInfoNecessaryToCreateDTO.class);
        info.name = "name";
        info.description = "description";
        info.startDate = LocalDate.of(2021,8,25);
        info.endDate = LocalDate.of(2021,10,25);
        info.sprintDuration = 5;
        info.budget = 5000;
        info.BusinessSectorID = "00000";
        info.CustomerID = 257715347;
        info.ProjectTypologyID = "PT";

        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of(info.ProjectTypologyID));
        CustomerID customerID = CustomerID.of(NIF.of(info.CustomerID));
        BusinesSectorID businessID = BusinesSectorID.of(CAE.of(info.BusinessSectorID));

        ProjectTypology projectTypology = mock(ProjectTypology.class);
        Business business = mock(Business.class);
        Customer customer = mock(Customer.class);


        when(businessSectorRepository.findBusinessSectorByID(businessID)).thenReturn(Optional.empty());
        when(customerRepository.findCustomerByID(customerID)).thenReturn(Optional.of(customer));
        when(projectTypologyRepository.findProjectTypologyByID(projectTypologyID)).thenReturn(Optional.of(projectTypology));


        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateProject.createProject(info));

    }

    @Test
    void createProject_CantCreate_typology() {
        ProjectInfoNecessaryToCreateDTO info = mock(ProjectInfoNecessaryToCreateDTO.class);
        info.name = "name";
        info.description = "description";
        info.startDate = LocalDate.of(2021,8,25);
        info.endDate = LocalDate.of(2021,10,25);
        info.sprintDuration = 5;
        info.budget = 5000;
        info.BusinessSectorID = "00000";
        info.CustomerID = 257715347;
        info.ProjectTypologyID = "PT";

        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of(info.ProjectTypologyID));
        CustomerID customerID = CustomerID.of(NIF.of(info.CustomerID));
        BusinesSectorID businessID = BusinesSectorID.of(CAE.of(info.BusinessSectorID));

        ProjectTypology projectTypology = mock(ProjectTypology.class);
        Business business = mock(Business.class);
        Customer customer = mock(Customer.class);


        when(businessSectorRepository.findBusinessSectorByID(businessID)).thenReturn(Optional.of(business));
        when(customerRepository.findCustomerByID(customerID)).thenReturn(Optional.of(customer));
        when(projectTypologyRepository.findProjectTypologyByID(projectTypologyID)).thenReturn(Optional.empty());


        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateProject.createProject(info));

    }
}