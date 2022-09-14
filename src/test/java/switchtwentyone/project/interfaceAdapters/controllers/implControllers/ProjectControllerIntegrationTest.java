package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateProject;
import switchtwentyone.project.applicationServices.irepositories.irepositories.*;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.businesSector.BusinessSectorFactory;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerFactory;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
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
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.*;
import switchtwentyone.project.dto.*;
import switchtwentyone.project.dto.mapper.ProjectMapper;
import switchtwentyone.project.externaldto.ExternalProjectDTO;
import switchtwentyone.project.infrastructure.webclient.irestrepositories.IProjectRestRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ProjectControllerIntegrationTest {

    @Autowired
    ApplicationServiceCreateProject appServCreateProj;

    @Autowired
    BusinessSectorFactory businessSectorFactory;

    @Autowired
    CustomerFactory cusFac;

    @Autowired
    ProjectTypologyFactory projTypFac;

    @Autowired
    ProjectFactory projFac;

    @Autowired
    BusinessSectorRepository busRep;

    @Autowired
    CustomerRepository cusRep;

    @Autowired
    ProjectTypologyRepository projTypRep;

    @Autowired
    ProjectController projectController;

    @Autowired
    private ProjectRepository repository;

    @MockBean
    private IProjectRestRepository restRepository;

    @MockBean
    ResourceRepository resourceRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createProjectSuccess_Integration_Test() throws Exception {
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


        ProjectInfoNecessaryToCreateDTO projectInfo = new ProjectInfoNecessaryToCreateDTO();
        projectInfo.name = "Banana";
        projectInfo.description = "Split";
        projectInfo.startDate = LocalDate.of(2002, 8, 24);
        projectInfo.endDate = LocalDate.of(2002, 10, 24);
        projectInfo.sprintDuration = 2;
        projectInfo.budget = 50000;
        projectInfo.CustomerID = 257715347;
        projectInfo.ProjectTypologyID = "project typology";
        projectInfo.BusinessSectorID = "00000";

        ProjectID projectID = new ProjectID(1);
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("Banana");
        Text projectDescription = Text.write("Split");
        Period period = Period.between(LocalDate.of(2002, 8, 24), LocalDate.of(2002, 10, 24));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(50000);
        NIF projectNif = NIF.of(257715347);
        CustomerID projectCustomerID = CustomerID.of(projectNif);
        NoNumberNoSymbolString projectProjectTypologyName = NoNumberNoSymbolString.of("project typology");
        ProjectTypologyID projectProjectTypologyID = ProjectTypologyID.of(projectProjectTypologyName);
        CAE projectCAE = CAE.of("00000");
        BusinesSectorID projectBusinesSectorID = BusinesSectorID.of(projectCAE);

        Project projectResult = projFac.createProject(projectID, projectName, projectDescription, period, sprintDuration,
                budget, projectCustomerID, projectBusinesSectorID, projectProjectTypologyID);
        ProjectInfoReturnedWhenCreatedDTO projectInfoDTOResult = ProjectMapper.toDTOReturnedUpoCreation(projectResult);

        Link link = linkTo(methodOn(ProjectController.class).getProjectDetails(projectInfoDTOResult.projectID)).withSelfRel();
        projectInfoDTOResult.add(link);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(projectInfo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.projectID", is(1)))
                .andExpect(jsonPath("$.description", is("Split")))
                .andExpect(jsonPath("$.startDate", is("2002-08-24")))
                .andExpect(jsonPath("$.endDate", is("2002-10-24")))
                .andExpect(jsonPath("$.sprintDuration", is(2)))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/projects/1")));

    }

    @Test
    void getProjectDetailsProjectNotFound() throws Exception {


        this.mockMvc
                .perform(get("/projects/9980"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProjectDetails() throws Exception {
        ProjectID pid = new ProjectID(1234);
        //Criar projeto
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = CustomerID.of(NIF.of(249778360));
        BusinesSectorID business = BusinesSectorID.of(CAE.of("12345"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("Fixed cost"));
        Project project = new Project(pid, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        repository.save(project);

        //Criar retorno esperado
        ProjectInfoDTO expected = new ProjectInfoDTO();
        expected.plannedSprints = 0;
        expected.sprintDuration = 2;
        expected.endDate = null;
        expected.startDate = LocalDate.of(2022, 1, 1);
        expected.status = "PLANNED";
        expected.description = "description";
        expected.code = 1234;


        //Comparar o esperado com o resultado

        MvcResult result = this.mockMvc
                .perform(get("/projects/1234"))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();



        assertNotNull(resultContent);

        assertEquals(objectMapper.writeValueAsString(expected), resultContent);
    }


    @Test
    void editProject() throws Exception {
        ProjectID pid = new ProjectID(1234);
        //Criar projeto
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = CustomerID.of(NIF.of(249778360));
        BusinesSectorID business = BusinesSectorID.of(CAE.of("12345"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("Fixed cost"));
        Project project = new Project(pid, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        repository.save(project);
        ProjectInfoDTO dto = new ProjectInfoDTO();
        dto.plannedSprints = 10;
        dto.sprintDuration = 3;
        dto.endDate = LocalDate.of(2023, 1, 1);
        dto.startDate = LocalDate.of(2022, 1, 1);
        dto.status = "planned";
        dto.description = "description";
        dto.code = 1234;
        ProjectInfoDTO expected = new ProjectInfoDTO();
        expected.plannedSprints = 10;
        expected.sprintDuration = 3;
        expected.endDate = LocalDate.of(2023, 1, 1);
        expected.startDate = LocalDate.of(2022, 1, 1);
        expected.status = "PLANNED";
        expected.description = "description";
        expected.code = 1234;

        String resultContent = this.mockMvc
                .perform(patch("/projects/1234")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        assertNotNull(resultContent);

        assertEquals(objectMapper.writeValueAsString(expected), resultContent);
    }


    @Test
    void getListOfProjectsByRoleTest() throws Exception {

        this.mockMvc.perform(get("/projects?uid=salome.rp@hotmail.com&role=PROJECT_MANAGER"))
                .andExpect(status().isNotFound());


    }

    @Test
    void getListOfProjectsBadEmail() throws Exception {

        this.mockMvc
                .perform(get("/projects?uid=salome.rphotmail.com&role=DEVELOPER"))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    void getListOfProjectsBadRole() throws Exception {

        this.mockMvc
                .perform(get("/projects?uid=andre@isep.ipp.ptrole=DEV"))
                .andExpect(status().isUnprocessableEntity());


    }

    @Test
    void getListOfAllProjectsWithAuthSuccess() throws Exception{
        ProjectID pid = new ProjectID(1234);
        //Create Project
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = CustomerID.of(NIF.of(249778360));
        BusinesSectorID business = BusinesSectorID.of(CAE.of("12345"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("Fixed cost"));
        Project project = new Project(pid, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        repository.save(project);

        //Create expected return
        ProjectDTO projectDTOOne = new ProjectDTO();
        projectDTOOne.code = "1234";
        projectDTOOne.name = "name";
        projectDTOOne.description = "description";
        Link link = linkTo(methodOn(ProjectController.class).getProjectDetails(project.getIDAsInt())).withSelfRel();
        Link linkSprints = linkTo(methodOn(SprintController.class).getAllSprintsByProjectID(project.getIDAsInt())).withRel("allSprints");
        Link linkProductBacklog = linkTo(methodOn(ProductBacklogController.class).getProductBacklog(project.getIDAsInt(), "andre@isep.ipp.pt")).withRel("productBacklog");
        Link linkResources = linkTo(methodOn(ResourceController.class).getResourcesByProjectID(project.getIDAsInt(), "active")).withRel("activeResources");
        Link linkResourcesAll = linkTo(methodOn(ResourceController.class).getResourcesByProjectID(project.getIDAsInt(), "all")).withRel("allResources");
        Link linkUserStories = linkTo(methodOn(UserStoryController.class).viewUSStatusFromProject(project.getIDAsInt())).withRel("userStories");
        Link linkSprintBacklogItems = linkTo(methodOn(UserStoryController.class).getAllSprintBacklogItemsByProject(project.getIDAsInt())).withRel("sprintBacklogItems");
        Link linkProductOwner = linkTo(methodOn(ResourceController.class).defineProductOwner(new NewProductOwnerDTO(), project.getIDAsInt()) ).withRel("productOwner");
        Link linkScrumMaster = linkTo(methodOn(ResourceController.class).newScrumMaster(new NewScrumMasterDTO(), project.getIDAsInt()) ).withRel("scrumMaster");
        Link linkScrumBoard = linkTo(methodOn(ScrumBoardController.class).viewScrumBoard(project.getIDAsInt())).withRel("scrumBoard");
        Link linkCreateProject = linkTo(methodOn(ProjectController.class).getListOfAllProjects("andre@isep.ipp.pt")).withRel("createProject");
        Link linkCreateTypology = linkTo(methodOn(ProjectTypologyController.class).getAllProjectTypologies()).withRel("createTypology");
        Link linkCustomers = linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("allCustomers");
        Link linkTypologies = linkTo(methodOn(ProjectTypologyController.class).getAllProjectTypologies()).withRel("allTypologies");
        Link linkBusinessSectors = linkTo(methodOn(BusinessSectorController.class).getAllBusinessSectors()).withRel("allBusinessSectors");
        Link linkRunningSprint = linkTo(methodOn(SprintController.class).gerRunningSprintByProjectID(project.getIDAsInt())).withRel("runningSprint");
        Link linkUSNotDoneInRunningSprint = linkTo(methodOn(SprintController.class).getUserStoriesDoneInRunningSprint(project.getIDAsInt())).withRel("notDoneUS");
        Link linkTasks = linkTo(methodOn(TaskController.class).viewTaskSatusFromProject(project.getIDAsInt())).withRel("tasks");
        Link linkCreateTask = linkTo(methodOn(TaskController.class).createTask(project.getIDAsInt(), new TaskCreationDTO())).withRel("createTask");
        Link createResource = linkTo(methodOn(ResourceController.class).createResource(new NewResourceDTO(), project.getIDAsInt())).withRel("resources");

        projectDTOOne.add(link);
        projectDTOOne.add(linkSprints);
        projectDTOOne.add(linkProductBacklog);
        projectDTOOne.add(createResource);
        projectDTOOne.add(linkResources);
        projectDTOOne.add(linkResourcesAll);
        projectDTOOne.add(linkProductOwner);
        projectDTOOne.add(linkScrumMaster);
        projectDTOOne.add(linkUserStories);
        projectDTOOne.add(linkSprintBacklogItems);

        projectDTOOne.add(linkScrumBoard);
        projectDTOOne.add(linkCreateProject);
        projectDTOOne.add(linkCreateTypology);
        projectDTOOne.add(linkCustomers);
        projectDTOOne.add(linkTypologies);
        projectDTOOne.add(linkBusinessSectors);
        projectDTOOne.add(linkRunningSprint);
        projectDTOOne.add(linkTasks);
        projectDTOOne.add(linkUSNotDoneInRunningSprint);
        projectDTOOne.add(linkCreateTask);
        List<ProjectDTO> expected = new ArrayList<>();
        expected.add(projectDTOOne);

        ExternalProjectDTO externalProject = new ExternalProjectDTO();
        externalProject.name ="Name";
        externalProject.description = "Desc";
        externalProject.id = "A123";
        externalProject.status= "Planned";
        externalProject.sprintDuration =2;
        externalProject.plannedSprints = 3;
        externalProject.budgetAmount=123;
        externalProject.budgetCurrency= "EUR";
        externalProject.businessSector ="IT";
        externalProject.customerName ="PTH";
        externalProject.customerVat ="12345";
        externalProject.typology ="Fixed Cost";
        externalProject.startDate ="01/03/2021" ;
        externalProject.endDate ="31/07/2021";

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.code= "EXT_A123";
        projectDTO.name = externalProject.name;
        projectDTO.description = externalProject.description;
        expected.add(projectDTO);


        //Compare expected with result
        when(restRepository.getAllProjects()).thenReturn(CollectionModel.of(List.of(externalProject)));

        MvcResult result = mockMvc.perform(get("/projects?uid=andre@isep.ipp.pt"))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(expected), resultContent);
    }

    @Test
    void getListOfAllProjectsFailure() throws Exception{

        //Create expected return
        List<ProjectDTO> projectDTOList = new ArrayList<>();


        //Compare expected with result
        List<ExternalProjectDTO> list = new ArrayList<>();
        when(restRepository.getAllProjects()).thenReturn(CollectionModel.of(list));
        MvcResult result = mockMvc.perform(get("/projects?uid=andre@isep.ipp.pt"))
                .andExpect(status().isNotFound()).andReturn();

        String resultContent = result.getResponse().getContentAsString();


        assertEquals(objectMapper.writeValueAsString(projectDTOList), resultContent);
    }

    @Test
    void getProjectByEmailAndRoleSuccess() throws Exception{
        Role r = new Role(Erole.PROJECT_MANAGER, NoNumberNoSymbolString.of(Erole.PROJECT_MANAGER.getDescription()));
        Email e = Email.of("salome.rp@hotmail.com");

        AccountID accountID = AccountID.of(e);
        ResourceID resourceIDOne = new ResourceID(1);
        ResourceID resourceIDTwo = new ResourceID(2);

        Period period = Period.between(LocalDate.of(2020, 3, 7), LocalDate.of(2020, 4, 8));
        LimitedPercentage percentage = LimitedPercentage.percentage(30);
        ProjectID projectIDOne = new ProjectID(1);
        ProjectID projectIDTwo = new ProjectID(2);
        ProjectID projectIDThree = new ProjectID(3);
        Monetary cost = Monetary.of(12, Currency.getInstance("EUR"));
        Resource resourceOne = new Resource(accountID, resourceIDOne, r, period, percentage, projectIDOne, cost);
        Resource resourceTwo = new Resource(accountID, resourceIDTwo, r, period, percentage, projectIDTwo, cost);
        List<Resource> resources = new ArrayList<>();
        resources.add(resourceOne);
        resources.add(resourceTwo);

        when(resourceRepository.findAllActiveResourcesByEmailAndRole(e, r, LocalDate.now())).thenReturn(resources);

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
        Project projectTwo = new Project(projectIDTwo, NoNumberNoSymbolString.of("nameTwo"),
                Text.write("descTwo"),
                projectPeriod,
                sprintDuration,
                budget,
                customerID,
                businesSectorID,
                typologyID);
        Project projectThree = new Project(projectIDThree, NoNumberNoSymbolString.of("nameThree"),
                Text.write("descThree"),
                projectPeriod,
                sprintDuration,
                budget,
                customerID,
                businesSectorID,
                typologyID);

        repository.save(projectOne);
        repository.save(projectTwo);
        repository.save(projectThree);


        List<ProjectDTO> expected = new ArrayList<>();

        ProjectDTO dtoOne = new ProjectDTO();
        dtoOne.code = "1";
        dtoOne.name = "nameOne";
        dtoOne.description = "descOne";
        expected.add(dtoOne);
        Link linkOne = linkTo(methodOn(ProjectController.class).getProjectDetails(projectOne.getIDAsInt())).withSelfRel();
        Link linkSprints = linkTo(methodOn(SprintController.class).getAllSprintsByProjectID(projectOne.getIDAsInt())).withRel("allSprints");
        Link linkProductBacklog = linkTo(methodOn(ProductBacklogController.class).getProductBacklog(projectOne.getIDAsInt(), e.getEmailData())).withRel("productBacklog");
        Link linkResources = linkTo(methodOn(ResourceController.class).getResourcesByProjectID(projectOne.getIDAsInt(),"active")).withRel("activeResources");
        Link linkResourcesAll = linkTo(methodOn(ResourceController.class).getResourcesByProjectID(projectOne.getIDAsInt(),"all")).withRel("allResources");
        Link linkUserStories = linkTo(methodOn(UserStoryController.class).viewUSStatusFromProject(projectOne.getIDAsInt())).withRel("userStories");
        Link linkSprintBacklogItems = linkTo(methodOn(UserStoryController.class).getAllSprintBacklogItemsByProject(projectOne.getIDAsInt())).withRel("sprintBacklogItems");
        Link linkProductOwner = linkTo(methodOn(ResourceController.class).defineProductOwner(new NewProductOwnerDTO(), projectOne.getIDAsInt()) ).withRel("productOwner");
        Link linkScrumMaster = linkTo(methodOn(ResourceController.class).newScrumMaster(new NewScrumMasterDTO(), projectOne.getIDAsInt()) ).withRel("scrumMaster");
        Link linkScrumBoard = linkTo(methodOn(ScrumBoardController.class).viewScrumBoard(projectOne.getIDAsInt())).withRel("scrumBoard");
        Link linkCreateProjectTwo = linkTo(methodOn(ProjectController.class).getListOfAllProjects(e.getEmailData())).withRel("createProject");
        Link linkCreateTypology = linkTo(methodOn(ProjectTypologyController.class).getAllProjectTypologies()).withRel("createTypology");
        Link linkCustomers = linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("allCustomers");
        Link linkTypologies = linkTo(methodOn(ProjectTypologyController.class).getAllProjectTypologies()).withRel("allTypologies");
        Link linkBusinessSectors = linkTo(methodOn(BusinessSectorController.class).getAllBusinessSectors()).withRel("allBusinessSectors");
        Link linkRunningSprint = linkTo(methodOn(SprintController.class).gerRunningSprintByProjectID(projectOne.getIDAsInt())).withRel("runningSprint");
        Link linkTasks = linkTo(methodOn(TaskController.class).viewTaskSatusFromProject(projectOne.getIDAsInt())).withRel("tasks");
        Link linkUSNotDoneInRunningSprint = linkTo(methodOn(SprintController.class).getUserStoriesDoneInRunningSprint(projectOne.getIDAsInt())).withRel("notDoneUS");
        Link linkCreateTask = linkTo(methodOn(TaskController.class).createTask(projectOne.getIDAsInt(), new TaskCreationDTO())).withRel("createTask");
        Link createResource = linkTo(methodOn(ResourceController.class).createResource(new NewResourceDTO(), projectOne.getIDAsInt())).withRel("resources");

        dtoOne.add(linkOne);
        dtoOne.add(linkSprints);
        dtoOne.add(linkProductBacklog);
        dtoOne.add(createResource);
        dtoOne.add(linkResources);
        dtoOne.add(linkResourcesAll);
        dtoOne.add(linkProductOwner);
        dtoOne.add(linkScrumMaster);
        dtoOne.add(linkUserStories);
        dtoOne.add(linkSprintBacklogItems);
        dtoOne.add(linkScrumBoard);
        dtoOne.add(linkCreateProjectTwo);
        dtoOne.add(linkCreateTypology);
        dtoOne.add(linkCustomers);
        dtoOne.add(linkTypologies);
        dtoOne.add(linkBusinessSectors);
        dtoOne.add(linkRunningSprint);
        dtoOne.add(linkTasks);
        dtoOne.add(linkUSNotDoneInRunningSprint);
        dtoOne.add(linkCreateTask);


        ProjectDTO dtoTwo = new ProjectDTO();
        dtoTwo.code = "2";
        dtoTwo.name = "nameTwo";
        dtoTwo.description = "descTwo";
        expected.add(dtoTwo);
        Link linkTwo = linkTo(methodOn(ProjectController.class).getProjectDetails(projectTwo.getIDAsInt())).withSelfRel();
        Link linkSprintsTwo = linkTo(methodOn(SprintController.class).getAllSprintsByProjectID(projectTwo.getIDAsInt())).withRel("allSprints");
        Link linkProductBacklogTwo = linkTo(methodOn(ProductBacklogController.class).getProductBacklog(projectTwo.getIDAsInt(), e.getEmailData())).withRel("productBacklog");
        Link linkResourcesTwo = linkTo(methodOn(ResourceController.class).getResourcesByProjectID(projectTwo.getIDAsInt(),"active")).withRel("activeResources");
        Link linkResourcesAllTwo = linkTo(methodOn(ResourceController.class).getResourcesByProjectID(projectTwo.getIDAsInt(),"all")).withRel("allResources");
        Link linkUserStoriesTwo = linkTo(methodOn(UserStoryController.class).viewUSStatusFromProject(projectTwo.getIDAsInt())).withRel("userStories");
        Link linkSprintBacklogItemsTwo = linkTo(methodOn(UserStoryController.class).getAllSprintBacklogItemsByProject(projectTwo.getIDAsInt())).withRel("sprintBacklogItems");
        Link linkProductOwnerTwo = linkTo(methodOn(ResourceController.class).defineProductOwner(new NewProductOwnerDTO(), projectTwo.getIDAsInt()) ).withRel("productOwner");
        Link linkScrumMasterTwo = linkTo(methodOn(ResourceController.class).newScrumMaster(new NewScrumMasterDTO(), projectTwo.getIDAsInt()) ).withRel("scrumMaster");
        Link linkScrumBoardTwo = linkTo(methodOn(ScrumBoardController.class).viewScrumBoard(projectTwo.getIDAsInt())).withRel("scrumBoard");
        Link linkCreateProject = linkTo(methodOn(ProjectController.class).getListOfAllProjects(e.getEmailData())).withRel("createProject");
        Link linkCreateTypologyTwo = linkTo(methodOn(ProjectTypologyController.class).getAllProjectTypologies()).withRel("createTypology");
        Link linkCustomersTwo = linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("allCustomers");
        Link linkTypologiesTwo = linkTo(methodOn(ProjectTypologyController.class).getAllProjectTypologies()).withRel("allTypologies");
        Link linkBusinessSectorsTwo = linkTo(methodOn(BusinessSectorController.class).getAllBusinessSectors()).withRel("allBusinessSectors");
        Link linkRunningSprintTwo = linkTo(methodOn(SprintController.class).gerRunningSprintByProjectID(projectTwo.getIDAsInt())).withRel("runningSprint");
        Link linkTasksTwo = linkTo(methodOn(TaskController.class).viewTaskSatusFromProject(projectTwo.getIDAsInt())).withRel("tasks");
        Link linkUSNotDoneInRunningSprintTwo = linkTo(methodOn(SprintController.class).getUserStoriesDoneInRunningSprint(projectTwo.getIDAsInt())).withRel("notDoneUS");
        Link linkCreateTaskTwo = linkTo(methodOn(TaskController.class).createTask(projectTwo.getIDAsInt(), new TaskCreationDTO())).withRel("createTask");
        Link createResourceTwo = linkTo(methodOn(ResourceController.class).createResource(new NewResourceDTO(), projectTwo.getIDAsInt())).withRel("resources");


        dtoTwo.add(linkTwo);
        dtoTwo.add(linkSprintsTwo);
        dtoTwo.add(linkProductBacklogTwo);
        dtoTwo.add(createResourceTwo);
        dtoTwo.add(linkResourcesTwo);
        dtoTwo.add(linkResourcesAllTwo);
        dtoTwo.add(linkProductOwnerTwo);
        dtoTwo.add(linkScrumMasterTwo);
        dtoTwo.add(linkUserStoriesTwo);
        dtoTwo.add(linkSprintBacklogItemsTwo);
        dtoTwo.add(linkScrumBoardTwo);
        dtoTwo.add(linkCreateProject);
        dtoTwo.add(linkCreateTypologyTwo);
        dtoTwo.add(linkCustomersTwo);
        dtoTwo.add(linkTypologiesTwo);
        dtoTwo.add(linkBusinessSectorsTwo);
        dtoTwo.add(linkRunningSprintTwo);
        dtoTwo.add(linkTasksTwo);
        dtoTwo.add(linkUSNotDoneInRunningSprintTwo);
        dtoTwo.add(linkCreateTaskTwo);


        String result = this.mockMvc.perform(get("/projects?uid=salome.rp@hotmail.com&role=PROJECT_MANAGER"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(result);

        assertEquals(objectMapper.writeValueAsString(expected), result);

    }
    @Test
    void getListOfAllProjectsSuccess() throws Exception{
        ProjectID pid = new ProjectID(1234);
        //Create Project
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = CustomerID.of(NIF.of(249778360));
        BusinesSectorID business = BusinesSectorID.of(CAE.of("12345"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("Fixed cost"));
        Project project = new Project(pid, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        repository.save(project);

        //Create expected return
        ProjectDTO expected = new ProjectDTO();
        expected.code = "1234";
        expected.name = "name";
        expected.description = "description";

        List<ProjectDTO> projectDTOList = new ArrayList<>();
        projectDTOList.add(expected);

        //Compare expected with result

        MvcResult result = mockMvc.perform(get("/local/projects"))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        assertEquals(objectMapper.writeValueAsString(projectDTOList), resultContent);
    }
}