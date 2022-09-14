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
import switchtwentyone.project.dto.AddUSToSprintInfoDTO;
import switchtwentyone.project.dto.ChangeUsCategoryDTO;
import switchtwentyone.project.dto.NewSprintInfoDTO;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ScrumBoardControllerIntegrationTest {

    @Autowired
    ScrumBoardController scrumBoardController;

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    UserStoryRepository userStoryRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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

    @Test
    void updateUsStatus() throws Exception {

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

        SprintID sprintID=SprintID.ofDouble(1.001);
        LocalDate startDate=LocalDate.of(2022,6,23);
        PositiveNumber sprintNumber=PositiveNumber.of(1);

        Sprint sprint=new Sprint(sprintID,startDate,projectID,sprintNumber,sprintDuration);
        sprintRepository.saveSprint(sprint);

        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1=UserStoryID.ofDouble(1.001);

        int usPriority1;

        Text statement1;

        Text detail;

        statement1 = Text.write("This is statement 1");
        detail = Text.write("This is a detail");
        usPriority1 = 1;
        UserStory newUserStory1 = new UserStory(usID1, usNumber1, statement1, detail, usPriority1, projectID);
        userStoryRepository.save(newUserStory1);


        FibonacciNumber effortEstimate =FibonacciNumber.of(3);
        sprint.addUSToSprintBacklog(usID1,effortEstimate);
        sprintRepository.saveSprint(sprint);

        int projID =1;
        double usID=1.001;

        ChangeUsCategoryDTO categoryDTO=new ChangeUsCategoryDTO();
        categoryDTO.category= "In progress";

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.patch("/project/"+ projID+"/scrumBoard/userStory/"+ usID)
                        .content(objectMapper.writeValueAsString(categoryDTO))
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertEquals(result.getResponse().getStatus(), 200);

    }

    @Test
    void dontUpdateUsStatusNoSprintRunning() throws Exception {

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

        SprintID sprintID=SprintID.ofDouble(1.001);
        LocalDate startDate=LocalDate.of(2022,6,23);
        PositiveNumber sprintNumber=PositiveNumber.of(1);

        Sprint sprint=new Sprint(sprintID,startDate,projectID,sprintNumber,sprintDuration);


        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1=UserStoryID.ofDouble(1.001);

        int usPriority1;

        Text statement1;

        Text detail;

        statement1 = Text.write("This is statement 1");
        detail = Text.write("This is a detail");
        usPriority1 = 1;
        UserStory newUserStory1 = new UserStory(usID1, usNumber1, statement1, detail, usPriority1, projectID);
        userStoryRepository.save(newUserStory1);


        FibonacciNumber effortEstimate =FibonacciNumber.of(3);
        sprint.addUSToSprintBacklog(usID1,effortEstimate);


        int projID =1;
        double usID=1.001;

        ChangeUsCategoryDTO categoryDTO=new ChangeUsCategoryDTO();
        categoryDTO.category= "In progress";

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.patch("/project/"+ projID+"/scrumBoard/userStory/"+ usID)
                        .content(objectMapper.writeValueAsString(categoryDTO))
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

        assertEquals(result.getResponse().getStatus(), 404);

    }

    @Test
    void dontUpdateUsInvalidStatus() throws Exception {

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

        SprintID sprintID=SprintID.ofDouble(1.001);
        LocalDate startDate=LocalDate.of(2022,6,23);
        PositiveNumber sprintNumber=PositiveNumber.of(1);

        Sprint sprint=new Sprint(sprintID,startDate,projectID,sprintNumber,sprintDuration);
        sprintRepository.saveSprint(sprint);

        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1=UserStoryID.ofDouble(1.001);

        int usPriority1;

        Text statement1;

        Text detail;

        statement1 = Text.write("This is statement 1");
        detail = Text.write("This is a detail");
        usPriority1 = 1;
        UserStory newUserStory1 = new UserStory(usID1, usNumber1, statement1, detail, usPriority1, projectID);
        userStoryRepository.save(newUserStory1);


        FibonacciNumber effortEstimate =FibonacciNumber.of(3);
        sprint.addUSToSprintBacklog(usID1,effortEstimate);
        sprintRepository.saveSprint(sprint);

        int projID =1;
        double usID=1.001;

        ChangeUsCategoryDTO categoryDTO=new ChangeUsCategoryDTO();
        categoryDTO.category= "Done";

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.patch("/project/"+ projID+"/scrumBoard/userStory/"+ usID)
                        .content(objectMapper.writeValueAsString(categoryDTO))
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity()).andReturn();

        assertEquals(result.getResponse().getStatus(), 422);

    }

    @Test
    void viewScrumBoard_IntegrationTest() throws Exception{
        ProjectID projectIDOne = new ProjectID(9998);
        CustomerID customerID = CustomerID.of(NIF.of(249778360));
        PositiveNumber budget = PositiveNumber.of(2000);
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        Period projectPeriod = Period.starting(LocalDate.now().minusDays(2));
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

        UserStory userStoryOne = new UserStory(UserStoryID.ofDouble(9998.001), PositiveNumber.of(1),
                Text.write("statement"), Text.write("detail"),1, projectIDOne);
        userStoryRepository.saveUS(userStoryOne);

        NewSprintInfoDTO newSprintInfoDTO = new NewSprintInfoDTO(LocalDate.now().minusDays(1));

        mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/9998/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newSprintInfoDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 9998.001, 1);


        mockMvc.perform( MockMvcRequestBuilders
                        .post("/projects/9998/sprintBacklogItems")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Link linkOne = linkTo(methodOn(ProjectController.class).getProjectDetails(9998)).withSelfRel();
        Link linkTwo = linkTo(methodOn(UserStoryController.class).getUserStoryById(9998, 9998.001)).withSelfRel();
        Link linkThree = linkTo(methodOn(ScrumBoardController.class).getUSStatus(9998, 9998.001)).withRel("editCategory");

        String result = mockMvc.perform(get("/projects/9998/scrumBoard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].usID", is(9998.001)))
                .andExpect(jsonPath("$.[0].storyNumber", is(1)))
                .andExpect(jsonPath("$.[0].statement", is("statement")))
                .andExpect(jsonPath("$.[0].priority", is(1)))
                .andExpect(jsonPath("$.[0].usStatus", is("To do")))
                .andExpect(jsonPath("$.[0].sprintID", is(9998.001)))
                .andExpect(jsonPath("$.[0].links[0].href", is(linkOne.getHref())))
                .andExpect(jsonPath("$.[0].links[1].href", is(linkTwo.getHref())))
                .andExpect(jsonPath("$.[0].links[2].href", is(linkThree.getHref())))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //Assert
        assertNotNull(result);
        ;

    }

}
