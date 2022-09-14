package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ResourceRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.valueObjects.*;
import switchtwentyone.project.dto.NewResourceDTO;
import switchtwentyone.project.dto.NewProductOwnerDTO;
import switchtwentyone.project.dto.ResourceDTO;


import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ResourceControllerITTest {
    @Autowired
    ResourceController controller;
    @MockBean
    ProjectRepository projRepo;
    @MockBean
    SprintRepository sprintRepo;
    @MockBean
    AccountRepository accountRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createResourceSuccessTest() throws Exception {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "salome@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.costPerHourValue = 12.5;
        dto.startDate = LocalDate.of(2022, 2, 3);
        dto.endDate = LocalDate.of(2022, 3, 3);
        dto.percentageOfAllocation = 1;
        dto.currency = "EUR";

        ResourceDTO expected = new ResourceDTO();
        expected.resourceID = 2081023314;
        expected.associatedAccount = "salome@isep.ipp.pt";
        expected.associatedRole = "DEVELOPER";
        expected.costPerHourValue = 12.5;
        expected.startDate = LocalDate.of(2022, 2, 3);
        expected.endDate = LocalDate.of(2022, 3, 3);
        expected.percentageOfAllocation = 1;
        expected.projectID = 1;
        expected.currency = "EUR";

        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 3));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration,
                budget, customer, business, projectTypologyID);
        UUID uuid = UUID.fromString("ac9f90d6-82a0-42c7-aa51-1a2af8672d69");
        when(accountRepository.existsByAccountID(AccountID.of(Email.of("salome@isep.ipp.pt")))).thenReturn(true);

        MockedStatic<UUID> mock = mockStatic(UUID.class);

        when(UUID.randomUUID()).thenReturn(uuid);

        when(projRepo.findById(new ProjectID(1))).thenReturn(Optional.of(project));

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/1/resources")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        mock.close();

        String resultContent = result.getResponse().getContentAsString();
        //Assert
        assertNotNull(resultContent);

        assertEquals(objectMapper.writeValueAsString(expected), resultContent);


    }

    @Test
    void createResourceUnuccessfulTest() throws Exception {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "salome@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.costPerHourValue = 12.5;
        dto.startDate = LocalDate.of(2022, 2, 3);
        dto.endDate = LocalDate.of(2022, 3, 3);
        dto.percentageOfAllocation = 1;
        dto.currency = "EUR";

        BusinessErrorMessage expected = new BusinessErrorMessage("Project not found", BusinessErrorMessage.NOT_FOUND);



        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/1/resources")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        //Assert
        assertNotNull(resultContent);

        assertEquals(objectMapper.writeValueAsString(expected), resultContent);


    }

    @Test
    void defineProductOwnerUnsuccessfulTest() throws Exception {

        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 3));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration,
                budget, customer, business, projectTypologyID);
        when(projRepo.findById(new ProjectID(1))).thenReturn(Optional.of(project));

        when(accountRepository.existsByAccountID(AccountID.of(Email.of("salome@isep.ipp.pt")))).thenReturn(true);
        Resource dev = new Resource(AccountID.of(Email.of("salome@isep.ipp.pt")),
                ResourceID.create(),
                new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("dev")),
                Period.between(LocalDate.of(2022, 2, 3), LocalDate.of(2022, 3, 3)),
                LimitedPercentage.percentage(50),
                new ProjectID(1),
                Monetary.of(13, Currency.getInstance("EUR")));
        resourceRepository.save(dev);

        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "salome@isep.ipp.pt";
        dto.costPerHourValue = 12.5;
        dto.startDate = LocalDate.of(2022, 2, 3);
        dto.endDate = LocalDate.of(2022, 3, 3);
        dto.percentageOfAllocation = 0.5;
        dto.currency = "EUR";

        BusinessErrorMessage errorMessage = new BusinessErrorMessage("Resource is already allocated to project", BusinessErrorMessage.INVALID_ENTRY);
        String expected = objectMapper.writeValueAsString(errorMessage);

        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/projects/1/resources/productOwner")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        //Assert
        assertNotNull(resultContent);
        assertEquals(expected, resultContent);



    }

    @Test
    void defineProductOwnerSuccessTestWhenAnotherProductOwnerIsStillActive() throws Exception {

        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 3));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration,
                budget, customer, business, projectTypologyID);
        when(projRepo.findById(new ProjectID(1))).thenReturn(Optional.of(project));

        when(accountRepository.existsByAccountID(AccountID.of(Email.of("salome@isep.ipp.pt")))).thenReturn(true);
        Resource po = new Resource(AccountID.of(Email.of("ricardo@isep.ipp.pt")),
                ResourceID.create(),
                new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription())),
                Period.between(LocalDate.of(2022, 1, 3), LocalDate.of(2022, 3, 3)),
                LimitedPercentage.percentage(50),
                new ProjectID(1),
                Monetary.of(13, Currency.getInstance("EUR")));
        resourceRepository.save(po);


        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "salome@isep.ipp.pt";
        dto.costPerHourValue = 12.5;
        dto.startDate = LocalDate.of(2022, 2, 3);
        dto.endDate = LocalDate.of(2022, 3, 3);
        dto.percentageOfAllocation = 0.5;
        dto.currency = "EUR";

        ResourceDTO expected = new ResourceDTO();
        expected.resourceID = 2081023314;
        expected.associatedAccount = "salome@isep.ipp.pt";
        expected.associatedRole = "PRODUCT_OWNER";
        expected.costPerHourValue = 12.5;
        expected.startDate = LocalDate.of(2022, 2, 3);
        expected.endDate = LocalDate.of(2022, 3, 3);
        expected.percentageOfAllocation = 0.5;
        expected.projectID = 1;
        expected.currency = "EUR";

        UUID uuid = UUID.fromString("ac9f90d6-82a0-42c7-aa51-1a2af8672d69");
        MockedStatic<UUID> mock = mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(uuid);
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/projects/1/resources/productOwner")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        mock.close();
        String resultContent = result.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(expected), resultContent);

        //We verify there are now two resource records associated with this project
        List<Resource> resources = resourceRepository.findAllByProjectID(projectID);
        assertEquals(2, resources.size());

        //Additionally, we verify that the end date of the previous PO was correctly defined
        Optional<Resource> opPreviousPO = resourceRepository.findActiveResource
                ( new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription())),
                        projectID,
                        LocalDate.of(2022, 2, 2));
        Resource previousPO = opPreviousPO.get();
        LocalDate endDate = previousPO.getAllocationPeriod().getEndingDate();

        assertEquals(LocalDate.of(2022, 2, 2),endDate);


    }

    @Test
    void defineProductOwnerSuccessTestWhenOtherProductOwnersAreDesignatedInTheSamePeriod() throws Exception {
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 3));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration,
                budget, customer, business, projectTypologyID);
        when(projRepo.findById(new ProjectID(1))).thenReturn(Optional.of(project));

        when(accountRepository.existsByAccountID(AccountID.of(Email.of("salome@isep.ipp.pt")))).thenReturn(true);
        Resource firstPO = new Resource(AccountID.of(Email.of("ricardo@isep.ipp.pt")),
                ResourceID.create(),
                new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription())),
                Period.between(LocalDate.of(2022, 2, 3), LocalDate.of(2022, 2, 15)),
                LimitedPercentage.percentage(50),
                new ProjectID(1),
                Monetary.of(13, Currency.getInstance("EUR")));
        Resource secondPO = new Resource(AccountID.of(Email.of("luis@isep.ipp.pt")),
                ResourceID.create(),
                new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription())),
                Period.between(LocalDate.of(2022, 2, 16), LocalDate.of(2022, 3, 10)),
                LimitedPercentage.percentage(50),
                new ProjectID(1),
                Monetary.of(13, Currency.getInstance("EUR")));
        resourceRepository.save(firstPO);
        resourceRepository.save(secondPO);


        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "salome@isep.ipp.pt";
        dto.costPerHourValue = 12.5;
        dto.startDate = LocalDate.of(2022, 2, 3);
        dto.endDate = LocalDate.of(2022, 3, 3);
        dto.percentageOfAllocation = 0.5;
        dto.currency = "EUR";

        ResourceDTO expected = new ResourceDTO();
        expected.resourceID = 2081023314;
        expected.associatedAccount = "salome@isep.ipp.pt";
        expected.associatedRole = "PRODUCT_OWNER";
        expected.costPerHourValue = 12.5;
        expected.startDate = LocalDate.of(2022, 2, 3);
        expected.endDate = LocalDate.of(2022, 3, 3);
        expected.percentageOfAllocation = 0.5;
        expected.projectID = 1;
        expected.currency = "EUR";

        UUID uuid = UUID.fromString("ac9f90d6-82a0-42c7-aa51-1a2af8672d69");
        MockedStatic<UUID> mock = mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(uuid);
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/projects/1/resources/productOwner")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        mock.close();

        List<Resource> resources = resourceRepository.findAllByProjectID(projectID);
        assertEquals(1, resources.size());

        String resultContent = result.getResponse().getContentAsString();
        assertEquals(objectMapper.writeValueAsString(expected), resultContent);

    }


    @Test
    void getResourcesByProjectID_All() throws Exception {

        //Arrange
        int pid = 1;
        String type = "all";

        ProjectID projectID = new ProjectID(pid);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Project name");
        Text description = Text.write("description");
        Period projectPeriod = Period.between(LocalDate.now().minusMonths(6),LocalDate.now().plusMonths(6));
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        PositiveNumber budget = PositiveNumber.of(5000);
        CustomerID costumerID = CustomerID.of(NIF.of(257715347));
        BusinesSectorID businesSectorID = BusinesSectorID.of(CAE.of("11022"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("Fixed Cost"));

        Project project = new Project(projectID,name,description,projectPeriod,
                sprintDuration,budget,costumerID,businesSectorID,projectTypologyID);

        when(projRepo.findById(projectID)).thenReturn(Optional.of(project));

        UUID scrumUuid = UUID.fromString("ac9f90d6-82a0-42c7-aa51-1a2af8672d69");
        UUID actualDevUuid = UUID.fromString("46b65618-9aff-4e6f-924b-202c240eb3d0");
        UUID formerDevUuid = UUID.fromString("83408ee5-8a20-45b7-b948-06a10ec8d8a2");
        int scrumUuidInt = Math.abs(scrumUuid.hashCode());
        int actualDevUuidInt = Math.abs(actualDevUuid.hashCode());
        int formerDevUuidInt = Math.abs(formerDevUuid.hashCode());

        AccountID andreID = AccountID.of(Email.of("andre@isep.ipp.pt"));
        ResourceID actualScrumID = ResourceID.of(scrumUuidInt);
        Role associetdScrum = new Role(Erole.SCRUM_MASTER,NoNumberNoSymbolString.of(Erole.SCRUM_MASTER.getDescription()));
        Period actualPeriod = Period.between(LocalDate.now().minusMonths(4),LocalDate.now().plusMonths(1));
        LimitedPercentage allocation = LimitedPercentage.decimal(0.5);
        Monetary cost = Monetary.of(15.5,Currency.getInstance("EUR"));
        Resource actualScrum = new Resource(andreID,actualScrumID,associetdScrum, actualPeriod,allocation,projectID,cost);
        resourceRepository.save(actualScrum);
        AccountID joaoID = AccountID.of(Email.of("joao@isep.ipp.pt"));
        ResourceID actualDevID = ResourceID.of(actualDevUuidInt);
        Role associetdDev = new Role(Erole.DEVELOPER,NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription()));
        Resource actualDev = new Resource(joaoID,actualDevID,associetdDev, actualPeriod,allocation,projectID,cost);
        resourceRepository.save(actualDev);
        AccountID luisID = AccountID.of(Email.of("luis@isep.ipp.pt"));
        ResourceID formerDevID = ResourceID.of(formerDevUuidInt);
        Period finishedAllocation = Period.between(LocalDate.now().minusMonths(5),LocalDate.now().minusMonths(4));
        Resource formerDev = new Resource(luisID,formerDevID,associetdDev,finishedAllocation,allocation,projectID,cost);
        resourceRepository.save(formerDev);


        MockedStatic<UUID> mock = mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(scrumUuid,actualDevUuid,formerDevUuid);
        ResourceDTO actualScrumDTO = new ResourceDTO();
        actualScrumDTO.associatedAccount = "andre@isep.ipp.pt";
        actualScrumDTO.resourceID = scrumUuidInt;
        actualScrumDTO.associatedRole = "SCRUM_MASTER";
        actualScrumDTO.startDate = LocalDate.now().minusMonths(4);
        actualScrumDTO.endDate = LocalDate.now().plusMonths(1);
        actualScrumDTO.percentageOfAllocation = 0.5;
        actualScrumDTO.projectID = 1;
        actualScrumDTO.costPerHourValue = 15.5;
        actualScrumDTO.currency = "EUR";

        ResourceDTO actualDevDTO = new ResourceDTO();
        actualDevDTO.associatedAccount = "joao@isep.ipp.pt";
        actualDevDTO.resourceID = actualDevUuidInt;
        actualDevDTO.associatedRole = "DEVELOPER";
        actualDevDTO.startDate = LocalDate.now().minusMonths(4);
        actualDevDTO.endDate = LocalDate.now().plusMonths(1);
        actualDevDTO.percentageOfAllocation = 0.5;
        actualDevDTO.projectID = 1;
        actualDevDTO.costPerHourValue = 15.5;
        actualDevDTO.currency = "EUR";

        ResourceDTO formerDevDTO = new ResourceDTO();
        formerDevDTO.associatedAccount = "luis@isep.ipp.pt";
        formerDevDTO.resourceID = formerDevUuidInt;
        formerDevDTO.associatedRole = "DEVELOPER";
        formerDevDTO.startDate = LocalDate.now().minusMonths(5);
        formerDevDTO.endDate = LocalDate.now().minusMonths(4);
        formerDevDTO.percentageOfAllocation = 0.5;
        formerDevDTO.projectID = 1;
        formerDevDTO.costPerHourValue = 15.5;
        formerDevDTO.currency = "EUR";

        List<ResourceDTO> answer = new ArrayList<>();
        answer.add(actualScrumDTO);
        answer.add(actualDevDTO);
        answer.add(formerDevDTO);

       // String expected =objectMapper.writeValueAsString(answer);

       // Act and Assert
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/projects/" + pid + "/resources?type=all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].associatedAccount", is("luis@isep.ipp.pt")))
                .andExpect(jsonPath("$.[0].resourceID", is(1092610735)))
                .andExpect(jsonPath("$.[0].associatedRole", is("DEVELOPER")))
                .andExpect(jsonPath("$.[0].startDate", is(formerDevDTO.startDate.toString())))
                .andExpect(jsonPath("$.[0].endDate", is(LocalDate.now().minusMonths(4).toString())))
                .andExpect(jsonPath("$.[0].percentageOfAllocation", is(0.5)))
                .andExpect(jsonPath("$.[0].projectID", is(1)))
                .andExpect(jsonPath("$.[0].costPerHourValue", is(15.5)))
                .andExpect(jsonPath("$.[0].currency", is("EUR")))
                .andExpect(jsonPath("$.[1].associatedAccount", is("joao@isep.ipp.pt")))
                .andExpect(jsonPath("$.[1].resourceID", is(1779207051)))
                .andExpect(jsonPath("$.[1].associatedRole", is("DEVELOPER")))
                .andExpect(jsonPath("$.[1].startDate", is(LocalDate.now().minusMonths(4).toString())))
                .andExpect(jsonPath("$.[1].endDate", is(LocalDate.now().plusMonths(1).toString())))
                .andExpect(jsonPath("$.[1].percentageOfAllocation", is(0.5)))
                .andExpect(jsonPath("$.[1].projectID", is(1)))
                .andExpect(jsonPath("$.[1].costPerHourValue", is(15.5)))
                .andExpect(jsonPath("$.[1].currency", is("EUR")))
                .andExpect(jsonPath("$.[2].associatedAccount", is("andre@isep.ipp.pt")))
                .andExpect(jsonPath("$.[2].resourceID", is(2081023314)))
                .andExpect(jsonPath("$.[2].associatedRole", is("SCRUM_MASTER")))
                .andExpect(jsonPath("$.[2].startDate", is(LocalDate.now().minusMonths(4).toString())))
                .andExpect(jsonPath("$.[2].endDate", is(LocalDate.now().plusMonths(1).toString())))
                .andExpect(jsonPath("$.[2].percentageOfAllocation", is(0.5)))
                .andExpect(jsonPath("$.[2].projectID", is(1)))
                .andExpect(jsonPath("$.[2].costPerHourValue", is(15.5)))
                .andExpect(jsonPath("$.[2].currency", is("EUR")))
                .andReturn()
                .getResponse()
                .getContentAsString();
        mock.close();




}

    @Test
    void getResourcesByProjectID_Active() throws Exception {

        //Arrange
        int pid = 1;
        String type = "all";

        ProjectID projectID = new ProjectID(pid);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Project name");
        Text description = Text.write("description");
        Period projectPeriod = Period.between(LocalDate.now().minusMonths(6),LocalDate.now().plusMonths(6));
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        PositiveNumber budget = PositiveNumber.of(5000);
        CustomerID costumerID = CustomerID.of(NIF.of(257715347));
        BusinesSectorID businesSectorID = BusinesSectorID.of(CAE.of("11022"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("Fixed Cost"));

        Project project = new Project(projectID,name,description,projectPeriod,
                sprintDuration,budget,costumerID,businesSectorID,projectTypologyID);

        when(projRepo.findById(projectID)).thenReturn(Optional.of(project));

        UUID scrumUuid = UUID.fromString("ac9f90d6-82a0-42c7-aa51-1a2af8672d69");
        UUID actualDevUuid = UUID.fromString("46b65618-9aff-4e6f-924b-202c240eb3d0");
        UUID formerDevUuid = UUID.fromString("83408ee5-8a20-45b7-b948-06a10ec8d8a2");
        int scrumUuidInt = Math.abs(scrumUuid.hashCode());
        int actualDevUuidInt = Math.abs(actualDevUuid.hashCode());
        int formerDevUuidInt = Math.abs(formerDevUuid.hashCode());

        AccountID andreID = AccountID.of(Email.of("andre@isep.ipp.pt"));
        ResourceID actualScrumID = ResourceID.of(scrumUuidInt);
        Role associetdScrum = new Role(Erole.SCRUM_MASTER,NoNumberNoSymbolString.of(Erole.SCRUM_MASTER.getDescription()));
        Period actualPeriod = Period.between(LocalDate.now().minusMonths(4),LocalDate.now().plusMonths(1));
        LimitedPercentage allocation = LimitedPercentage.decimal(0.5);
        Monetary cost = Monetary.of(15.5,Currency.getInstance("EUR"));
        Resource actualScrum = new Resource(andreID,actualScrumID,associetdScrum, actualPeriod,allocation,projectID,cost);
        resourceRepository.save(actualScrum);
        AccountID joaoID = AccountID.of(Email.of("joao@isep.ipp.pt"));
        ResourceID actualDevID = ResourceID.of(actualDevUuidInt);
        Role associetdDev = new Role(Erole.DEVELOPER,NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription()));
        Resource actualDev = new Resource(joaoID,actualDevID,associetdDev, actualPeriod,allocation,projectID,cost);
        resourceRepository.save(actualDev);
        AccountID luisID = AccountID.of(Email.of("luis@isep.ipp.pt"));
        ResourceID formerDevID = ResourceID.of(formerDevUuidInt);
        Period finishedAllocation = Period.between(LocalDate.now().minusMonths(5),LocalDate.now().minusMonths(4));
        Resource formerDev = new Resource(luisID,formerDevID,associetdDev,finishedAllocation,allocation,projectID,cost);
        resourceRepository.save(formerDev);


        MockedStatic<UUID> mock = mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(scrumUuid,actualDevUuid,formerDevUuid);
        ResourceDTO actualScrumDTO = new ResourceDTO();
        actualScrumDTO.associatedAccount = "andre@isep.ipp.pt";
        actualScrumDTO.resourceID = scrumUuidInt;
        actualScrumDTO.associatedRole = "SCRUM_MASTER";
        actualScrumDTO.startDate = LocalDate.now().minusMonths(4);
        actualScrumDTO.endDate = LocalDate.now().plusMonths(1);
        actualScrumDTO.percentageOfAllocation = 0.5;
        actualScrumDTO.projectID = 1;
        actualScrumDTO.costPerHourValue = 15.5;
        actualScrumDTO.currency = "EUR";

        ResourceDTO actualDevDTO = new ResourceDTO();
        actualDevDTO.associatedAccount = "joao@isep.ipp.pt";
        actualDevDTO.resourceID = actualDevUuidInt;
        actualDevDTO.associatedRole = "DEVELOPER";
        actualDevDTO.startDate = LocalDate.now().minusMonths(4);
        actualDevDTO.endDate = LocalDate.now().plusMonths(1);
        actualDevDTO.percentageOfAllocation = 0.5;
        actualDevDTO.projectID = 1;
        actualDevDTO.costPerHourValue = 15.5;
        actualDevDTO.currency = "EUR";

        ResourceDTO formerDevDTO = new ResourceDTO();
        formerDevDTO.associatedAccount = "luis@isep.ipp.pt";
        formerDevDTO.resourceID = formerDevUuidInt;
        formerDevDTO.associatedRole = "DEVELOPER";
        formerDevDTO.startDate = LocalDate.now().minusMonths(5);
        formerDevDTO.endDate = LocalDate.now().minusMonths(4);
        formerDevDTO.percentageOfAllocation = 0.5;
        formerDevDTO.projectID = 1;
        formerDevDTO.costPerHourValue = 15.5;
        formerDevDTO.currency = "EUR";

        List<ResourceDTO> answer = new ArrayList<>();
        answer.add(actualScrumDTO);
        answer.add(actualDevDTO);


       // String expected =objectMapper.writeValueAsString(answer);

        // Act and Assert
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/projects/" + pid + "/resources?type=all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[1].associatedAccount", is("joao@isep.ipp.pt")))
                .andExpect(jsonPath("$.[1].resourceID", is(1779207051)))
                .andExpect(jsonPath("$.[1].associatedRole", is("DEVELOPER")))
                .andExpect(jsonPath("$.[1].startDate", is(LocalDate.now().minusMonths(4).toString())))
                .andExpect(jsonPath("$.[1].endDate", is(LocalDate.now().plusMonths(1).toString())))
                .andExpect(jsonPath("$.[1].percentageOfAllocation", is(0.5)))
                .andExpect(jsonPath("$.[1].projectID", is(1)))
                .andExpect(jsonPath("$.[1].costPerHourValue", is(15.5)))
                .andExpect(jsonPath("$.[1].currency", is("EUR")))
                .andExpect(jsonPath("$.[2].associatedAccount", is("andre@isep.ipp.pt")))
                .andExpect(jsonPath("$.[2].resourceID", is(2081023314)))
                .andExpect(jsonPath("$.[2].associatedRole", is("SCRUM_MASTER")))
                .andExpect(jsonPath("$.[2].startDate", is(LocalDate.now().minusMonths(4).toString())))
                .andExpect(jsonPath("$.[2].endDate", is(LocalDate.now().plusMonths(1).toString())))
                .andExpect(jsonPath("$.[2].percentageOfAllocation", is(0.5)))
                .andExpect(jsonPath("$.[2].projectID", is(1)))
                .andExpect(jsonPath("$.[2].costPerHourValue", is(15.5)))
                .andExpect(jsonPath("$.[2].currency", is("EUR")))
                .andReturn()
                .getResponse()
                .getContentAsString();
        mock.close();



    }
}