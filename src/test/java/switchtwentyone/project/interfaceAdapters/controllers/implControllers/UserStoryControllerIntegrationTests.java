package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceAddUSToSprintBacklog;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateUserStory;
import switchtwentyone.project.applicationServices.irepositories.irepositories.*;
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
import switchtwentyone.project.domain.aggregates.sprint.*;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//these tests are inspired by https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
/**
 * These tests are inspired in https://spring.io/guides/gs/testing-web/
 * Not all web layers have been tested due to time restrictions.
 * Another useful approach is to not start the server at all but to test only
 * the layer below that, where Spring handles the incoming HTTP request and
 * hands it off to your controller. That way, almost of the full stack is used,
 * and your code will be called in exactly the same way as if it were processing a real
 * HTTP request but without the cost of starting the server. To do that, use Spring’s
 * MockMvc and ask for that to be injected for you by using the @AutoConfigureMockMvc
 * annotation on the test case.
 */

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class UserStoryControllerIntegrationTests {
    @Autowired
    CustomerFactory cusFac;

    @Autowired
    CustomerRepository cusRep;

    @Autowired
    ProjectTypologyFactory projTypFac;

    @Autowired
    ProjectTypologyRepository projTypRep;

    @Autowired
    BusinessSectorFactory businessSectorFactory;

    @Autowired
    BusinessSectorRepository busRep;

    @Autowired
    ProjectFactory projectFactory;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ApplicationServiceCreateUserStory applicationServiceCreateUserStory;

    @Autowired
    ApplicationServiceAddUSToSprintBacklog appServcAddUS;

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    UserStoryRepository userStoryRepository;

    @Autowired
    private ObjectMapper objectMapper;


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void createUSSuccessfull() throws Exception {
        //Arrange
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
        Period period = Period.starting(LocalDate.now().minusDays(2));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        ProjectID projectID = new ProjectID(9997);
        Project proj = projectFactory.createProject(projectID, projectName, description, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(proj);

        NewUserStoryInfoDTO newUSInfo = new NewUserStoryInfoDTO("statement", "detail");

        //Act and assert
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/projects/9997/userStories")
                        .content(asJsonString(newUSInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usID", is(9997.001)))
                .andExpect(jsonPath("$.storyNumber", is(1)))
                .andExpect(jsonPath("$.statement", is("statement")))
                .andExpect(jsonPath("$.detail", is("detail")))
                .andExpect(jsonPath("$.isDecomposed", is(false)))
                .andExpect(jsonPath("$.priority", is(1)))
                .andExpect(jsonPath("$.projID", is(9997)))
        ;
    }


    @Test
    void createUSUnsuccessfull() throws Exception {
        //Arrange
        NewUserStoryInfoDTO newUSInfo = new NewUserStoryInfoDTO("statement", "detail");

        //Act and assert
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/projects/1/userStories")
                        .content(asJsonString(newUSInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.usID").doesNotExist());

    }

    @Test
    void getUSByIDUnSuccessful() throws Exception {
        //Act and assert
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/userStories/" + 1.008)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    void getUSByIDSuccessful() throws Exception {
        //Arrange
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
        ProjectID projectID = new ProjectID(3);
        Project proj = projectFactory.createProject(projectID, projectName, description, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(proj);

        NewUserStoryInfoDTO newUSInfo = new NewUserStoryInfoDTO("statement", "detail");

        //Act and assert
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/projects/3/userStories")
                        .content(asJsonString(newUSInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.usID").exists());

        //Act and assert
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/projects/3/userStories/" + 3.001)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
        .andExpect(status().isOk());
    }



    @Test
    void changePriorityOfUserStoryIntegrationTest() throws Exception {
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
        ProjectID projectID = new ProjectID(10);
        Project proj = projectFactory.createProject(projectID, projectName, description, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(proj);

        UserStoryID userStoryIDOne = UserStoryID.ofDouble(10.001);
        PositiveNumber userStoryNumberOne = PositiveNumber.of(1);
        Text statmentOne = Text.write("statmentOne");
        Text detailOne = Text.write("detailOne");
        UserStory userStoryOne = new UserStory(userStoryIDOne, userStoryNumberOne, statmentOne, detailOne, 1, projectID);
        userStoryRepository.save(userStoryOne);

        UserStoryID userStoryIDTwo = UserStoryID.ofDouble(10.002);
        PositiveNumber userStoryNumberTwo = PositiveNumber.of(2);
        Text statmentTwo = Text.write("statmentTwo");
        Text detailTwo = Text.write("detailTwo");
        UserStory userStoryTwo = new UserStory(userStoryIDTwo, userStoryNumberTwo, statmentTwo, detailTwo, 2, projectID);
        userStoryRepository.save(userStoryTwo);

        UserStoryID userStoryIDThree = UserStoryID.ofDouble(10.003);
        PositiveNumber userStoryNumberThree = PositiveNumber.of(3);
        Text statmentThree = Text.write("statmentThree");
        Text detailThree = Text.write("detailThree");
        UserStory userStoryThree = new UserStory(userStoryIDThree, userStoryNumberThree, statmentThree, detailThree, 3, projectID);
        userStoryRepository.save(userStoryThree);

        UserStoryID userStoryIDFour = UserStoryID.ofDouble(10.004);
        PositiveNumber userStoryNumberFour = PositiveNumber.of(4);
        Text statmentFour = Text.write("statmentFour");
        Text detailfour = Text.write("detailfour");
        UserStory userStoryFour = new UserStory(userStoryIDFour, userStoryNumberFour, statmentFour, detailfour, 4, projectID);
        userStoryRepository.save(userStoryFour);

        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.newPriority = 4;

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/projects/10/userStories/10.002")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(priorityDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usID", is(10.002)))
                .andExpect(jsonPath("$.projeID", is(10)))
                .andExpect(jsonPath("$.statment", is("statmentTwo")))
                .andExpect(jsonPath("$.priority", is(4)))
        ;
    }


    @Test
    void decomposeUserStorySuccessTest() throws Exception {
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
        ProjectID projectID = new ProjectID(9999);
        Project project = projectFactory.createProject(projectID, projectName, description, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(project);

        Text statement = Text.write("First user story");
        Text detail = Text.write("To be decomposed");

        applicationServiceCreateUserStory.createUserStory(statement, detail, projectID);


        List<ChildUserStoryDTO> newUserStories =new ArrayList<>() ;
        ChildUserStoryDTO dto1 = new ChildUserStoryDTO();
        dto1.statement = "First child";
        dto1.detail = "I was born";
        ChildUserStoryDTO dto2 = new ChildUserStoryDTO();
        dto2.statement = "Second child";
        dto2.detail = "I was born as well";
        newUserStories.add(dto1);
        newUserStories.add(dto2);
        List<USDTO> expected = new ArrayList<>();
        USDTO usdto1 = new USDTO();
        usdto1.usID = 9999.001;
        usdto1.storyNumber = 1;
        usdto1.statement = "First user story";
        usdto1.detail = "To be decomposed";
        usdto1.isDecomposed= true;
        usdto1.parent = 0;
        usdto1.priority = 1;
        usdto1.projID = 9999;
        USDTO usdto2 = new USDTO();
        usdto2.usID = 9999.002;
        usdto2.storyNumber = 2;
        usdto2.statement = "First child";
        usdto2.detail = "I was born";
        usdto2.isDecomposed= false;
        usdto2.parent = 9999.001;
        usdto2.priority = 2;
        usdto2.projID = 9999;
        USDTO usdto3 = new USDTO();
        usdto3.usID = 9999.003;
        usdto3.storyNumber = 3;
        usdto3.statement = "Second child";
        usdto3.detail = "I was born as well";
        usdto3.isDecomposed= false;
        usdto3.parent = 9999.001;
        usdto3.priority = 3;
        usdto3.projID = 9999;
        expected.add(usdto1);
        expected.add(usdto2);
        expected.add(usdto3);
        for (USDTO usdto : expected){
            Link link = linkTo(methodOn(UserStoryController.class).getUserStoryById(usdto.projID, usdto.usID)).withSelfRel();
            usdto.add(link);

        }
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/projects/9999/userStories/9999.001/childs")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newUserStories)) // or newCountryInfoMap
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        //Assert
        assertNotNull(resultContent);

        assertEquals(objectMapper.writeValueAsString(expected), resultContent);

    }


    @Test
    void addUSToSpringBacklogSuccessfull() throws Exception {
        //Create Project
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
        Period period = Period.starting(LocalDate.now().minusDays(2));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        ProjectID projectID = new ProjectID(9995);
        Project proj = projectFactory.createProject(projectID, projectName, description, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(proj);

        //Create Sprint
        LocalDate startDate =LocalDate.now().minusDays(1);
        NewSprintInfoDTO newSprintInfoDTO = new NewSprintInfoDTO(startDate);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/9995/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newSprintInfoDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //Create US
        NewUserStoryInfoDTO newUSInfo = new NewUserStoryInfoDTO("statement", "detail");

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/projects/9995/userStories")
                        .content(asJsonString(newUSInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 9995.001, 1);

        //Act and assert
        MvcResult response = mockMvc.perform( MockMvcRequestBuilders
                        .post("/projects/9995/sprintBacklogItems")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals("US successfully added.", response.getResponse().getContentAsString());
    }

    @Test
    void addUSToSpringBacklogUnSuccessfull() throws Exception {

        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 9995.001, 1);

        //Act and assert
        MvcResult responseResult = mockMvc.perform( MockMvcRequestBuilders
                        .post("/projects/9994/sprintBacklogItems")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        BusinessErrorMessage expected = new BusinessErrorMessage("Project not found.", 2);
        assertEquals(objectMapper.writeValueAsString(expected), responseResult.getResponse().getContentAsString());
    }

    @Test
    void addUSToSpringBacklog_Unsuccessful_SprintNotRunning() throws Exception {
        //Create Project
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
        Period period = Period.starting(LocalDate.now().minusDays(2));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        ProjectID projectID = new ProjectID(9990);
        Project proj = projectFactory.createProject(projectID, projectName, description, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(proj);

        //Create Sprint
        LocalDate startDate =LocalDate.now().plusDays(10);
        NewSprintInfoDTO newSprintInfoDTO = new NewSprintInfoDTO(startDate);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/9990/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newSprintInfoDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //Create US
        NewUserStoryInfoDTO newUSInfo = new NewUserStoryInfoDTO("statement", "detail");

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/projects/9990/userStories")
                        .content(asJsonString(newUSInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 9990.001, 1);

        //Act and assert
        MvcResult response = mockMvc.perform( MockMvcRequestBuilders
                        .post("/projects/9990/sprintBacklogItems")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        BusinessErrorMessage expected = new BusinessErrorMessage("There isn't a sprint currently running.", 1);
        assertEquals(objectMapper.writeValueAsString(expected), response.getResponse().getContentAsString());

    }


    @Test
    void viewUSStatusFromProject_IntegrationTest() throws Exception{
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
                Text.write("statement"), Text.write("detail"),1, projectIDOne);
        userStoryRepository.saveUS(userStoryOne);

        UserStory userStoryTwo = new UserStory(UserStoryID.ofDouble(1.002), PositiveNumber.of(2),
                Text.write("statement2"), Text.write("detail2"),2, projectIDOne);
        userStoryRepository.saveUS(userStoryTwo);

        Sprint sp1 = new Sprint(SprintID.ofDouble(1.001), LocalDate.of(2022,01, 01), projectIDOne, PositiveNumber.of(1),PositiveNumber.of(2));
        SprintBacklogItem sprintBacklogItem1 = new SprintBacklogItem(SprintBacklogItemID.createSprintBacklogItemID("74969509-2f7d-4b7e-a5cf-753dae784e57"),
                UserStoryID.ofDouble(1.001), FibonacciNumber.of(3));
        sprintBacklogItem1.setCategory(ScrumBoardCategory.toDo());

        SprintBacklogItem sprintBacklogItem2 = new SprintBacklogItem(SprintBacklogItemID.createSprintBacklogItemID("74969509-2f7d-4b7e-a5cf-753dae784e58"),
                UserStoryID.ofDouble(1.002), FibonacciNumber.of(3));
        sprintBacklogItem2.setCategory(ScrumBoardCategory.toDo());
        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        sprintBacklogItemList.add(sprintBacklogItem1);
        sprintBacklogItemList.add(sprintBacklogItem2);
        sp1.setSprintBacklogItems(sprintBacklogItemList);

        sprintRepository.saveSprint(sp1);


        //Expected outcome
        USInfoAndStatusDTO dto = new USInfoAndStatusDTO();
        dto.usID = 1.001;
        dto.storyNumber = 1;
        dto.statement = "statement";
        dto.detail = "detail";
        dto.priority = 1;
        dto.usStatus = "To do";
        dto.add(linkTo(methodOn(ProjectController.class).getProjectDetails(projectOne.getIDAsInt())).withSelfRel());
        dto.add(linkTo(methodOn(UserStoryController.class).getUserStoryById(projectOne.getIDAsInt(),userStoryOne.getUsID().getID())).withSelfRel());

        USInfoAndStatusDTO dto2 = new USInfoAndStatusDTO();
        dto2.usID = 1.002;
        dto2.storyNumber = 2;
        dto2.statement = "statement2";
        dto2.detail = "detail2";
        dto2.priority = 2;
        dto2.usStatus = "To do";
        dto2.add(linkTo(methodOn(ProjectController.class).getProjectDetails(projectOne.getIDAsInt())).withSelfRel());
        dto2.add(linkTo(methodOn(UserStoryController.class).getUserStoryById(projectOne.getIDAsInt(),userStoryTwo.getUsID().getID())).withSelfRel());




        List<USInfoAndStatusDTO> expected = new ArrayList<>();
        expected.add(dto2);
        expected.add(dto);

        String result = this.mockMvc.perform(get("/projects/1/userStories"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(objectMapper.writeValueAsString(expected), result);

    }

}
