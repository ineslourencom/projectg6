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
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.irepositories.irepositories.*;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IAccountJPARepository;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateUserStory;
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
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.*;
import switchtwentyone.project.dto.USShortDTO;
import switchtwentyone.project.datamodel.AccountJPA;
import switchtwentyone.project.datamodel.assembler.AccountDomainDataAssembler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductBacklogControllerIntegrationTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    IAccountJPARepository accountJPARepo;
    @Autowired
    AccountDomainDataAssembler accountDomainDataAssembler;
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
    ApplicationServiceCreateUserStory appServcCreateUS;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getProductBacklog() throws Exception {
        //Arrange ACCOUNTS
        AccountCreatable accountFactory = new AccountFactory();
        Email emailTestOne = Email.of("luis@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Luis");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestOne = Photo.of(":)");
        Password passwordTestOne = Password.of("testing01", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactory.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);

        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Accounting");
        Photo photoTestTwo = Photo.of("XD");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactory.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);

        Email emailTestThree = Email.of("salome@isep.ipp.pt");
        AccountID accountIDTestThree = AccountID.of(emailTestThree);
        NoNumberNoSymbolString nameTestThree = NoNumberNoSymbolString.of("Salome");
        NoNumberNoSymbolString functionTestThree = NoNumberNoSymbolString.of("Artsy programmer");
        Photo photoTestThree = Photo.of(";)");
        Password passwordTestThree = Password.of("testing10", 2);
        ProfileID profileIDTestThree = ProfileID.ofProfileType("Director");
        Account testAccountThree = accountFactory.createAccount(accountIDTestThree, emailTestThree, nameTestThree,
                functionTestThree, photoTestThree, passwordTestThree, profileIDTestThree);

        Email emailTestFour = Email.of("andreia@isep.ipp.pt");
        AccountID accountIDTestFour = AccountID.of(emailTestFour);
        NoNumberNoSymbolString nameTestFour = NoNumberNoSymbolString.of("Andreia");
        NoNumberNoSymbolString functionTestFour = NoNumberNoSymbolString.of("Developer");
        Photo photoTestFour = Photo.of(":p");
        Password passwordTestFour = Password.of("testing10", 2);
        ProfileID profileIDTestFour = ProfileID.ofProfileType("User");
        Account testAccountFour = accountFactory.createAccount(accountIDTestFour, emailTestFour, nameTestFour,
                functionTestFour, photoTestFour, passwordTestFour, profileIDTestFour);

        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        Optional<AccountJPA> testAccountThreeJPA = Optional.of(accountDomainDataAssembler.toData(testAccountThree));
        Optional<AccountJPA> testAccountFourJPA = Optional.of(accountDomainDataAssembler.toData(testAccountFour));
        accountJPARepo.save(testAccountOneJPA.get());
        accountJPARepo.save(testAccountTwoJPA.get());
        accountJPARepo.save(testAccountThreeJPA.get());
        accountJPARepo.save(testAccountFourJPA.get());


        //Arrange PROJECT
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

        int id = 1;
        ProjectID projectID = new ProjectID(id);
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("Banana");
        Text projectDescription = Text.write("Split");
        Period period = Period.between(LocalDate.now().minusMonths(6), LocalDate.now().plusMonths(6));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(50000);
        NIF projectNif = NIF.of(257715347);
        CustomerID projectCustomerID = CustomerID.of(projectNif);
        NoNumberNoSymbolString projectProjectTypologyName = NoNumberNoSymbolString.of("project typology");
        ProjectTypologyID projectProjectTypologyID = ProjectTypologyID.of(projectProjectTypologyName);
        CAE projectCAE = CAE.of("00000");
        BusinesSectorID projectBusinesSectorID = BusinesSectorID.of(projectCAE);

        Project testProject = projFac.createProject(projectID, projectName, projectDescription, period, sprintDuration,
                budget, projectCustomerID, projectBusinesSectorID, projectProjectTypologyID);

        projectRepository.save(testProject);


        //Arrange RESOURCE
        Resource po = new Resource(AccountID.of(Email.of("luis@isep.ipp.pt")),
                ResourceID.create(),
                new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription())),
                Period.between(LocalDate.now().minusMonths(6), LocalDate.now().minusMonths(4)),
                LimitedPercentage.percentage(50),
                new ProjectID(1),
                Monetary.of(13, Currency.getInstance("EUR")));

        Resource dev = new Resource(AccountID.of(Email.of("andreia@isep.ipp.pt")),
                ResourceID.create(),
                new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription())),
                Period.between(LocalDate.now().minusMonths(6), LocalDate.now().plusMonths(3)),
                LimitedPercentage.percentage(50),
                new ProjectID(1),
                Monetary.of(13, Currency.getInstance("EUR")));

        resourceRepository.save(po);
        resourceRepository.save(dev);


        //Arrange USER STORIES
        UserStoryID userStoryIDOne = UserStoryID.ofDouble(1.001);
        UserStoryID userStoryIDTwo = UserStoryID.ofDouble(1.002);
        UserStoryID userStoryIDThree = UserStoryID.ofDouble(1.003);
        UserStoryID userStoryIDFour = UserStoryID.ofDouble(1.004);
        UserStoryID userStoryIDFive = UserStoryID.ofDouble(1.005);
        UserStoryID userStoryIDSix = UserStoryID.ofDouble(1.006);
        UserStoryID userStoryIDSeven = UserStoryID.ofDouble(1.007);

        UserStory testUSOne = testProject.createUserStory(Text.write("statement 1"), Text.write("detail 1"), userStoryIDOne, 1, PositiveNumber.of(1));
        UserStory testUSTwo = testProject.createUserStory(Text.write("statement 2"), Text.write("detail 2"), userStoryIDTwo, 2, PositiveNumber.of(2));
        UserStory testUSThree = testProject.createUserStory(Text.write("statement 3"), Text.write("detail 3"), userStoryIDThree, 3, PositiveNumber.of(3));
        UserStory testUSFour = testProject.createUserStory(Text.write("statement 4"), Text.write("detail 4"), userStoryIDFour, 4, PositiveNumber.of(4));
        UserStory testUSFive = testProject.createUserStory(Text.write("statement 5"), Text.write("detail 5"), userStoryIDFive, 5, PositiveNumber.of(5));
        UserStory testUSSix = testProject.createUserStory(Text.write("statement 6"), Text.write("detail 6"), userStoryIDSix, 6, PositiveNumber.of(6));
        testUSSix.markAsDecomposed();
        UserStory testUSSeven = testProject.createUserStory(Text.write("statement 7"), Text.write("detail 7"), userStoryIDSeven, 7, PositiveNumber.of(7));


        userStoryRepository.saveUS(testUSOne);
        userStoryRepository.saveUS(testUSTwo);
        userStoryRepository.saveUS(testUSThree);
        userStoryRepository.saveUS(testUSFour);
        userStoryRepository.saveUS(testUSFive);
        userStoryRepository.saveUS(testUSSix);
        userStoryRepository.saveUS(testUSSeven);


        //Arrange SPRINT
        Sprint testSprint1 = testProject.createSprint(SprintID.ofDouble(1.001), LocalDate.now().minusWeeks(3), PositiveNumber.of(1));
        SprintBacklogItemID itemID01 = SprintBacklogItemID.createSprintBacklogItemID("54e3c9ff-a09f-4aa4-ad7a-7a7953159882");
        SprintBacklogItemID itemID02 = SprintBacklogItemID.createSprintBacklogItemID("3f132133-732c-4416-9311-7562a01cd7a9");
        SprintBacklogItemID itemID03 = SprintBacklogItemID.createSprintBacklogItemID("b0f98824-3220-4eba-96d1-b3b609e40aa5");
        SprintBacklogItem sbli01 = new SprintBacklogItem(itemID01, userStoryIDOne, FibonacciNumber.of(13));
        sbli01.setCategory(ScrumBoardCategory.inProgress());
        SprintBacklogItem sbli02 = new SprintBacklogItem(itemID02, userStoryIDTwo, FibonacciNumber.of(5));
        sbli02.setCategory(ScrumBoardCategory.done());
        SprintBacklogItem sbli03 = new SprintBacklogItem(itemID03, userStoryIDThree, FibonacciNumber.of(8));
        sbli03.setCategory(ScrumBoardCategory.inProgress());
        List<SprintBacklogItem> sBLItems1 = new ArrayList<>();
        sBLItems1.add(sbli01);
        sBLItems1.add(sbli02);
        sBLItems1.add(sbli03);
        testSprint1.setSprintBacklogItems(sBLItems1);
        sprintRepository.saveSprint(testSprint1);

        Sprint testSprint2 = testProject.createSprint(SprintID.ofDouble(1.002), LocalDate.now().minusWeeks(1), PositiveNumber.of(2));
        testSprint2.addUSToSprintBacklog(userStoryIDOne, FibonacciNumber.of(3));
        testSprint2.addUSToSprintBacklog(userStoryIDThree, FibonacciNumber.of(13));
        testSprint2.addUSToSprintBacklog(userStoryIDFour, FibonacciNumber.of(3));
        SprintBacklogItemID itemID04 = SprintBacklogItemID.createSprintBacklogItemID("4d90ca6b-c8af-4122-8de1-a87088cb088b");
        SprintBacklogItemID itemID05 = SprintBacklogItemID.createSprintBacklogItemID("e3f5e08c-f0fe-421a-8747-bc6735d3ccee");
        SprintBacklogItemID itemID06 = SprintBacklogItemID.createSprintBacklogItemID("04254fc0-cfd5-4a05-b2b6-1f514ff2b000");
        SprintBacklogItem sbli04 = new SprintBacklogItem(itemID04, userStoryIDFour, FibonacciNumber.of(5));
        sbli04.setCategory(ScrumBoardCategory.done());
        SprintBacklogItem sbli05 = new SprintBacklogItem(itemID05, userStoryIDFive, FibonacciNumber.of(13));
        sbli05.setCategory(ScrumBoardCategory.inProgress());
        SprintBacklogItem sbli06 = new SprintBacklogItem(itemID06, userStoryIDOne, FibonacciNumber.of(3));
        sbli06.setCategory(ScrumBoardCategory.inProgress());
        List<SprintBacklogItem> sBLItems2 = new ArrayList<>();
        sBLItems2.add(sbli04);
        sBLItems2.add(sbli05);
        sBLItems2.add(sbli06);
        testSprint2.setSprintBacklogItems(sBLItems2);
        sprintRepository.saveSprint(testSprint2);


        //Arrange FINAL
        USShortDTO testUSFiveDTO = new USShortDTO();
        testUSFiveDTO.usID = 1.003;
        testUSFiveDTO.storyNumber = 3;
        testUSFiveDTO.statement = "statement 3";
        testUSFiveDTO.detail = "detail 3";
        testUSFiveDTO.projID = id;
        Link linkFive = linkTo(methodOn(UserStoryController.class).getUserStoryById(id, testUSFiveDTO.usID)).withSelfRel();
        testUSFiveDTO.add(linkFive);

        USShortDTO testUSSevenDTO = new USShortDTO();
        testUSSevenDTO.usID = 1.007;
        testUSSevenDTO.storyNumber = 7;
        testUSSevenDTO.statement = "statement 7";
        testUSSevenDTO.detail = "detail 7";
        testUSSevenDTO.projID = id;
        Link linkSeven = linkTo(methodOn(UserStoryController.class).getUserStoryById(id, testUSSevenDTO.usID)).withSelfRel();
        testUSSevenDTO.add(linkSeven);

        List<USShortDTO> userStoryShortDTOS = new ArrayList<>();
        userStoryShortDTOS.add(testUSFiveDTO);
        userStoryShortDTOS.add(testUSSevenDTO);

        String expected = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userStoryShortDTOS);

        //Act & Assert
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/projects/1/productBacklog?email=salome@isep.ipp.pt")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].usID", is(1.003)))
                .andExpect(jsonPath("$.[0].storyNumber", is(3)))
                .andExpect(jsonPath("$.[0].statement", is("statement 3")))
                .andExpect(jsonPath("$.[0].detail", is("detail 3")))
                .andExpect(jsonPath("$.[0].priority", is(1)))
                .andExpect(jsonPath("$.[0].projID", is(1)))
                .andExpect(jsonPath("$.[0].links[0].href", is(linkFive.getHref())))
                .andExpect(jsonPath("$.[1].usID", is(1.007)))
                .andExpect(jsonPath("$.[1].storyNumber", is(7)))
                .andExpect(jsonPath("$.[1].statement", is("statement 7")))
                .andExpect(jsonPath("$.[1].detail", is("detail 7")))
                .andExpect(jsonPath("$.[1].priority", is(2)))
                .andExpect(jsonPath("$.[1].projID", is(1)))
                .andExpect(jsonPath("$.[1].links[0].href", is(linkSeven.getHref())))
                .andReturn();

//        String response = result.getResponse().getContentAsString();
    }
}
