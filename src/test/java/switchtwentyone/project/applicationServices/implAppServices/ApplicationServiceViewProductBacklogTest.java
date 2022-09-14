package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import switchtwentyone.project.applicationServices.irepositories.irepositories.*;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceFindUS;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.USShortDTO;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.UserStoryController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceViewProductBacklogTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private UserStoryRepository userStoryRepository;
    @Mock
    private SprintRepository sprintRepository;
    @Mock
    private ServiceFindUS serviceFindUS;
    @InjectMocks
    ApplicationServiceViewProductBacklog service;

    @Test
    void getProductBacklog_Test_Success() {
        //Arrange
        int testProjIDint = 1;
        String stringEmail = "andre@isep.ipp.pt";

        ProjectID testProjectID = new ProjectID(testProjIDint);
        Email testEmail = Email.of(stringEmail);

        List<UserStory> testProductBacklog = new ArrayList<>();
        List<USShortDTO> expected = new ArrayList<>();

        AccountID accountID = AccountID.of(testEmail);
        when(projectRepository.existsById(testProjectID)).thenReturn(true);
        when(resourceRepository.existsByAssociatedAccountAndStartDateBeforeAndEndDateAfterAndProjectID(accountID, testProjectID, LocalDate.now())).thenReturn(true);
        Account testAccount = mock(Account.class);
        Optional<Account> testAccountOpt = Optional.of(testAccount);
        when(accountRepository.findByAccountID(accountID)).thenReturn(testAccountOpt);
        when(testAccount.existsProfile(ProfileID.ofProfileType("Director"))).thenReturn(true);
        when(testAccount.existsProfile(ProfileID.ofProfileType("Administrator"))).thenReturn(true);


        UserStory testUs = mock(UserStory.class);
        testProductBacklog.add(testUs);

        when(userStoryRepository.findAllByProjectIDNotDecomposed(testProjectID)).thenReturn(testProductBacklog);

        Sprint runningSprint = mock(Sprint.class);
        Optional<Sprint> runningSprintOpt = Optional.of(runningSprint);
        when(sprintRepository.findRunningSprintByProjectID(testProjectID)).thenReturn(runningSprintOpt);
        List<UserStoryID> emptyUSIDListA = new ArrayList<>();
        when(serviceFindUS.findUSIDinSprint(runningSprintOpt)).thenReturn(emptyUSIDListA);
        Optional<UserStory> usOptEmpty = Optional.empty();
//        when(userStoryRepository.findByID(any(UserStoryID.class))).thenReturn(usOptEmpty);

        List<Sprint> sprintList = new ArrayList<>();
        when(sprintRepository.findAllSprintsByProjectID(testProjectID)).thenReturn(sprintList);
        List<UserStoryID> emptyUSIDListB = new ArrayList<>();
        when(serviceFindUS.findDoneUSIDinSprint(sprintList)).thenReturn(emptyUSIDListB);

        when(serviceFindUS.compileProductBacklog(anyList(), anyList(), anyList())).thenReturn(testProductBacklog);

        double testUsID = 1;
        Text testStatement = Text.write("statement");
        Text testDetail = Text.write("detail");
        PositiveNumber testUsNumber = PositiveNumber.of(1);
        int testPriority = 1;

        USShortDTO testDTO = new USShortDTO();
        testDTO.usID = testUsID;
        testDTO.storyNumber = testUsNumber.getNumber();
        testDTO.statement = testStatement.getValue();
        testDTO.detail = testDetail.getValue();
        testDTO.priority = testPriority;
        testDTO.projID = testProjIDint;

        when(testUs.getUsID()).thenReturn(UserStoryID.ofDouble(testUsID));
        when(testUs.getStoryNumber()).thenReturn(testUsNumber);
        when(testUs.getStatement()).thenReturn(testStatement);
        when(testUs.getDetail()).thenReturn(testDetail);
        when(testUs.getDetail()).thenReturn(testDetail);
        when(testUs.getPriority()).thenReturn(testPriority);
        when(testUs.getProjectID()).thenReturn(testProjectID);

        Link link = linkTo(methodOn(UserStoryController.class).getUserStoryById(testDTO.projID, testDTO.usID)).withSelfRel();
        testDTO.add(link);
        expected.add(testDTO);

        //Act
        List<USShortDTO> actual = service.getProductBacklog(testProjIDint, stringEmail);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getProductBacklog_Test_Fail_RequestNotValid() {
        //Arrange
        int testProjIDint = 1;
        String stringEmail = "andre@isep.ipp.pt";

        ProjectID testProjectID = new ProjectID(testProjIDint);
        Email testEmail = Email.of(stringEmail);

        List<UserStory> testProductBacklog = new ArrayList<>();
        List<USShortDTO> expected = new ArrayList<>();

        AccountID accountID = AccountID.of(testEmail);
        when(projectRepository.existsById(testProjectID)).thenReturn(true);
        when(resourceRepository.existsByAssociatedAccountAndStartDateBeforeAndEndDateAfterAndProjectID(accountID, testProjectID, LocalDate.now())).thenReturn(true);

        Account testAccount = mock(Account.class);
        Optional<Account> testAccountOpt = Optional.of(testAccount);
        when(accountRepository.findByAccountID(accountID)).thenReturn(testAccountOpt);
        when(testAccount.existsProfile(ProfileID.ofProfileType("Director"))).thenReturn(true);
        when(testAccount.existsProfile(ProfileID.ofProfileType("Administrator"))).thenReturn(true);

        //Act
        List<USShortDTO> result = service.getProductBacklog(testProjIDint, stringEmail);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getProductBacklog_Test_Success_Empty() {
        //Arrange
        int testProjIDint = 1;
        String stringEmail = "andre@isep.ipp.pt";

        ProjectID testProjectID = new ProjectID(testProjIDint);
        Email testEmail = Email.of(stringEmail);

        List<UserStory> testProductBacklog = new ArrayList<>();
        List<USShortDTO> expected = new ArrayList<>();

        AccountID accountID = AccountID.of(testEmail);
        when(projectRepository.existsById(testProjectID)).thenReturn(false);
        when(resourceRepository.existsByAssociatedAccountAndStartDateBeforeAndEndDateAfterAndProjectID(accountID, testProjectID, LocalDate.now())).thenReturn(true);

        Account testAccount = mock(Account.class);
        Optional<Account> testAccountOpt = Optional.of(testAccount);

        when(accountRepository.findByAccountID(accountID)).thenReturn(testAccountOpt);
        when(testAccount.existsProfile(ProfileID.ofProfileType("Director"))).thenReturn(true);
        when(testAccount.existsProfile(ProfileID.ofProfileType("Administrator"))).thenReturn(true);

//        when(userStoryRepository.findAllByProjectIDNotDecomposed(testProjectID)).thenReturn(testProductBacklog);

        Sprint runningSprint = mock(Sprint.class);
        Optional<Sprint> runningSprintOpt = Optional.of(runningSprint);
//        when(sprintRepository.findRunningSprintByProjectID(testProjectID)).thenReturn(runningSprintOpt);
        List<UserStoryID> emptyUSIDListA = new ArrayList<>();
//        when(serviceFindUS.findUSIDinSprint(runningSprintOpt)).thenReturn(emptyUSIDListA);
        Optional<UserStory> usOptEmpty = Optional.empty();
//        when(userStoryRepository.findByID(any(UserStoryID.class))).thenReturn(usOptEmpty);

        List<Sprint> sprintList = new ArrayList<>();
//        when(sprintRepository.findAllSprintsByProjectID(testProjectID)).thenReturn(sprintList);
        List<UserStoryID> emptyUSIDListB = new ArrayList<>();
//        when(serviceFindUS.findDoneUSIDinSprint(sprintList)).thenReturn(emptyUSIDListB);

//        when(serviceFindUS.compileProductBacklog(anyList(), anyList(), anyList())).thenReturn(testProductBacklog);

        //Act
        List<USShortDTO> result = service.getProductBacklog(testProjIDint, stringEmail);

        //Assert
        assertEquals(expected, result);
    }

}
