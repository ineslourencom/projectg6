package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.irepositories.irepositories.*;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.businesSector.BusinessSectorFactory;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerFactory;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectFactory;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyFactory;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.aggregates.sprint.*;
import switchtwentyone.project.domain.aggregates.task.ContainerID;
import switchtwentyone.project.domain.aggregates.task.Task;
import switchtwentyone.project.domain.aggregates.task.TaskID;
import switchtwentyone.project.domain.aggregates.task.TaskType;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.*;
import switchtwentyone.project.dto.TaskCreationDTO;
import switchtwentyone.project.dto.TaskStatusDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class TaskControllerIntegrattionTest {

    @Autowired
    CustomerFactory cusFac;
    @Autowired
    ProjectTypologyFactory projTypFac;
    @Autowired
    BusinessSectorFactory businessSectorFactory;
    @Autowired
    ProjectFactory projectFactory;
    @Autowired
    AccountFactory accountFactory;
    @Autowired
    CustomerRepository cusRep;
    @Autowired
    ProjectTypologyRepository projTypRep;
    @Autowired
    BusinessSectorRepository busRep;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    UserStoryRepository userStoryRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    TaskController taskController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void createTaskSuccess_Integration_Test_Sprint() throws Exception {

        Nameable name = NoNumberNoSymbolString.of("customer");
        NIF nif = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(nif);
        Customer customer = cusFac.createCustomer(customerID, name);
        cusRep.saveCustomer(customer);

        NoNumberNoSymbolString nameProjTyp = NoNumberNoSymbolString.of("project typology");
        Describable description = Text.write("description");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(nameProjTyp);
        ProjectTypology projectTypology = projTypFac.createProjectTypology(projectTypologyID, description);
        projTypRep.saveProjectTypology(projectTypology);

        CAE cae = CAE.of("00000");
        Nameable nameBuss = NoNumberNoSymbolString.of("example");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business business = businessSectorFactory.createBusinessSector(businesSectorID, nameBuss);
        busRep.saveBusinessSector(business);

        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text projDescription = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(20);
        PositiveNumber budget = PositiveNumber.of(2000);
        ProjectID projectID = new ProjectID(1);
        Project proj = projectFactory.createProject(projectID, projectName, description, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(proj);


        SprintID sprintID = SprintID.ofDouble(001.001);
        LocalDate sprintStartDate = LocalDate.of(2022, 06, 01);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        Sprint sprint = SprintCreatable.createSprint(sprintID, sprintStartDate, projectID, sprintNumber, sprintDuration);
        sprintRepository.saveSprint(sprint);

        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();
        taskCreationDTO.name = "name";
        taskCreationDTO.description = "description";
        taskCreationDTO.startDate = LocalDate.of(2022, 06, 19);
        taskCreationDTO.type = "Meeting";
        taskCreationDTO.option = "Sprint";
        taskCreationDTO.containerID = 1.001;

        mockMvc
                .perform(MockMvcRequestBuilders.post("/project/1/tasks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("description")))
                .andExpect(jsonPath("$.startDate", is("2022-06-19")))
                .andExpect(jsonPath("$.type", is("Meeting")))
                .andExpect(jsonPath("$.containerID", is(1.001)))
        ;

    }

    @Test
    void createTaskSuccess_Integration_Test_UserStory() throws Exception {

        Nameable name = NoNumberNoSymbolString.of("customer");
        NIF nif = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(nif);
        Customer customer = cusFac.createCustomer(customerID, name);
        cusRep.saveCustomer(customer);

        NoNumberNoSymbolString nameProjTyp = NoNumberNoSymbolString.of("project typology");
        Describable description = Text.write("description");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(nameProjTyp);
        ProjectTypology projectTypology = projTypFac.createProjectTypology(projectTypologyID, description);
        projTypRep.saveProjectTypology(projectTypology);

        CAE cae = CAE.of("00000");
        Nameable nameBuss = NoNumberNoSymbolString.of("example");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business business = businessSectorFactory.createBusinessSector(businesSectorID, nameBuss);
        busRep.saveBusinessSector(business);

        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text projDescription = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(20);
        PositiveNumber budget = PositiveNumber.of(2000);
        ProjectID projectID = new ProjectID(1);
        Project proj = projectFactory.createProject(projectID, projectName, description, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(proj);

        UserStoryID userStoryID = UserStoryID.ofDouble(001.001);
        PositiveNumber userStoryNumber = PositiveNumber.of(1);
        Text statement = Text.write("statment");
        Text detail = Text.write("detail");
        UserStory userStory = new UserStory(userStoryID, userStoryNumber, statement, detail, 1, projectID);
        userStoryRepository.save(userStory);

        SprintID sprintID = SprintID.ofDouble(001.001);
        LocalDate sprintStartDate = LocalDate.of(2022,6,1);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        Sprint sprint = SprintCreatable.createSprint(sprintID,sprintStartDate,projectID,sprintNumber,sprintDuration);
        FibonacciNumber effortUS = FibonacciNumber.of(1);
        sprint.addUSToSprintBacklog(userStoryID, effortUS);
        sprintRepository.saveSprint(sprint);


        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();
        taskCreationDTO.name = "name";
        taskCreationDTO.description = "description";
        taskCreationDTO.startDate = LocalDate.of(2022, 06, 19);
        taskCreationDTO.type = "Meeting";
        taskCreationDTO.option = "User Story";
        taskCreationDTO.containerID = 1.001;

        mockMvc
                .perform(MockMvcRequestBuilders.post("/project/1/tasks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("description")))
                .andExpect(jsonPath("$.startDate", is("2022-06-19")))
                .andExpect(jsonPath("$.type", is("Meeting")))
                .andExpect(jsonPath("$.containerID", is(1.001)))
        ;

    }

    @Test
    void createTaskSuccess_Integration_Test_UserStory_All_AtributesDefined() throws Exception {

        Nameable name = NoNumberNoSymbolString.of("customer");
        NIF nif = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(nif);
        Customer customer = cusFac.createCustomer(customerID, name);
        cusRep.saveCustomer(customer);

        NoNumberNoSymbolString nameProjTyp = NoNumberNoSymbolString.of("project typology");
        Describable description = Text.write("description");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(nameProjTyp);
        ProjectTypology projectTypology = projTypFac.createProjectTypology(projectTypologyID, description);
        projTypRep.saveProjectTypology(projectTypology);

        CAE cae = CAE.of("00000");
        Nameable nameBuss = NoNumberNoSymbolString.of("example");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business business = businessSectorFactory.createBusinessSector(businesSectorID, nameBuss);
        busRep.saveBusinessSector(business);

        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text projDescription = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(20);
        PositiveNumber budget = PositiveNumber.of(2000);
        ProjectID projectID = new ProjectID(1);
        Project proj = projectFactory.createProject(projectID, projectName, projDescription, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(proj);

        UserStoryID userStoryID = UserStoryID.ofDouble(001.001);
        PositiveNumber userStoryNumber = PositiveNumber.of(1);
        Text statement = Text.write("statment");
        Text detail = Text.write("detail");
        UserStory userStory = new UserStory(userStoryID, userStoryNumber, statement, detail, 1, projectID);
        userStoryRepository.save(userStory);

        SprintID sprintID = SprintID.ofDouble(001.001);
        LocalDate sprintStartDate = LocalDate.of(2022,6,1);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        Sprint sprint = SprintCreatable.createSprint(sprintID,sprintStartDate,projectID,sprintNumber,sprintDuration);
        FibonacciNumber effortUS = FibonacciNumber.of(1);
        sprint.addUSToSprintBacklog(userStoryID, effortUS);
        sprintRepository.saveSprint(sprint);

        Email email = Email.of("123@isep.ipp.pt");
        AccountID accountID = AccountID.of(email);
        NoNumberNoSymbolString accountName = NoNumberNoSymbolString.of("name");
        NoNumberNoSymbolString accountFunction = NoNumberNoSymbolString.of("function");
        Photo photo = Photo.of("photo");
        Password password = Password.of("password", 1);
        ProfileID profileID = ProfileID.ofProfileType("User");
        accountRepository.createAndSaveAccount(accountID, email, accountName, accountFunction, photo, password, profileID);

        ResourceID resourceID = ResourceID.of(1);
        Erole erole = Erole.DEVELOPER;
        NoNumberNoSymbolString resourceDescription = NoNumberNoSymbolString.of("user");
        Role role = new Role(erole, resourceDescription);
        Period allocatedPeriod = Period.between(LocalDate.of(2022, 2, 2), LocalDate.of(2022,11,15));
        LimitedPercentage percentageOfAllocation = LimitedPercentage.percentage(10.0);
        Currency currency = Currency.getInstance("EUR");
        Monetary monCost = Monetary.of(50.0, currency);
        Resource resource = new Resource(accountID, resourceID, role, allocatedPeriod, percentageOfAllocation, projectID, monCost);
        resourceRepository.save(resource);

        TaskCreationDTO taskCreationDTO = new TaskCreationDTO();
        taskCreationDTO.name = "name";
        taskCreationDTO.description = "description";
        taskCreationDTO.startDate = LocalDate.of(2022, 06, 19);
        taskCreationDTO.endDate = LocalDate.of(2022, 07, 31);
        taskCreationDTO.type = "Meeting";
        taskCreationDTO.option = "User Story";
        taskCreationDTO.containerID = 1.001;
        taskCreationDTO.resourceID = "1";
        taskCreationDTO.effortEstimate = "1";

        mockMvc
                .perform(MockMvcRequestBuilders.post("/project/1/tasks")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskCreationDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.description", is("description")))
                .andExpect(jsonPath("$.startDate", is("2022-06-19")))
                .andExpect(jsonPath("$.type", is("Meeting")))
                .andExpect(jsonPath("$.containerID", is(1.001)))
        ;

    }


    @Test
    void viewTaskSatusFromProject_IntegrationTest() throws Exception {
        ProjectID projectIDOne = new ProjectID(1);
        CustomerID customerID = CustomerID.of(NIF.of(249778360));
        PositiveNumber budget = PositiveNumber.of(2000);
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        Period projectPeriod = Period.starting(LocalDate.of(2020, 1, 1));
        BusinesSectorID businesSectorID = BusinesSectorID.of(CAE.of("12345"));
        ProjectTypologyID typologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("tip"));


        Project projectOne = new Project(projectIDOne, NoNumberNoSymbolString.of("nameOne"),
                Text.write("descOne"),
                projectPeriod,
                sprintDuration,
                budget,
                customerID,
                businesSectorID,
                typologyID);
        projectRepository.save(projectOne);

        UserStory userStoryOne = new UserStory(UserStoryID.ofDouble(1.001), PositiveNumber.of(1),
                Text.write("statement"), Text.write("detail"), 1, projectIDOne);
        userStoryRepository.saveUS(userStoryOne);

        Sprint sp1 = new Sprint(SprintID.ofDouble(1.001), LocalDate.of(2022, 01, 01), projectIDOne, PositiveNumber.of(1), PositiveNumber.of(2));
        sprintRepository.saveSprint(sp1);

        TaskID taskID = TaskID.createTaskID("60f63a6d-6d27-4189-b728-a5212563c0b4");
        NoNumberNoSymbolString taskName = NoNumberNoSymbolString.of("Task One");
        Text description = Text.write("description");
        TaskType type = TaskType.meeting();
        ContainerID contID = UserStoryID.ofDouble(1.001);
        Task task1 = new Task.TaskBuilder(taskID, taskName, description, type, contID).build();

        TaskID taskID2 = TaskID.createTaskID("80f63a6d-6d27-4189-b728-a5212563c0b4");
        NoNumberNoSymbolString taskName2 = NoNumberNoSymbolString.of("Task Two");
        Text description2 = Text.write("description");
        TaskType type2 = TaskType.meeting();
        ContainerID contID2 = SprintID.ofDouble(1.001);
        Task task2 = new Task.TaskBuilder(taskID2, taskName2, description2, type2, contID2).build();

        taskRepository.saveTask(task1);
        taskRepository.saveTask(task2);

        //Expected Outcomes
        TaskStatusDTO dto = new TaskStatusDTO();
        dto.taskNumber = "60f63a6d-6d27-4189-b728-a5212563c0b4";
        dto.taskName = "Task One";
        dto.belongsTo = "User Story";
        dto.containerId = "US-1.001";
        dto.status = "Planned";
        dto.percOfExec = 0.0;
        dto.add(linkTo(methodOn(ProjectController.class).getProjectDetails(projectOne.getIDAsInt())).withSelfRel());
        dto.add(linkTo(methodOn(TaskController.class).getTaskById(taskID.getTaskIdentityNumber())).withSelfRel());


        TaskStatusDTO dto2 = new TaskStatusDTO();
        dto2.taskNumber = "80f63a6d-6d27-4189-b728-a5212563c0b4";
        dto2.taskName = "Task Two";
        dto2.belongsTo = "Sprint";
        dto2.containerId = "Sp-1.001";
        dto2.status = "Planned";
        dto2.percOfExec = 0.0;
        dto2.add(linkTo(methodOn(ProjectController.class).getProjectDetails(projectOne.getIDAsInt())).withSelfRel());
        dto2.add(linkTo(methodOn(TaskController.class).getTaskById(taskID2.getTaskIdentityNumber())).withSelfRel());


        List<TaskStatusDTO> expected = new ArrayList<>();
        expected.add(dto);
        expected.add(dto2);

        String result = this.mockMvc.perform(get("/projects/1/tasks"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        assertEquals(objectMapper.writeValueAsString(expected), result);

    }
}
