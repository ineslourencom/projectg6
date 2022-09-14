package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceFindUS;
import switchtwentyone.project.domain.services.ServiceValidateUserStoryWhenCreatingTask;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.USMinimalInfoDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.UserStoryController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceGetUserStoriesNotDoneFromRunningSprintTest {

    @Mock
    switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository sprintRepository;
    @Mock
    switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository projectRepository;
    @Mock
    ServiceValidateUserStoryWhenCreatingTask serviceValidateUserStoryWhenCreatingTask;
    @Mock
    switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository userStoryRepository;
    @Mock
    ServiceFindUS serviceFindUS;


    @InjectMocks
    ApplicationServiceGetUserStoriesNotDoneFromRunningSprint applicationServiceGetUserStoriesNotDoneFromRunningSprint;

    @Test
    void getUSNotDoneFromRunningSprint() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint runningSprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(runningSprint));

        List<Sprint> runningSprintInList = new ArrayList<>();
        runningSprintInList.add(runningSprint);

        List<UserStoryID> userStoriesIDInRunningSprint = new ArrayList<>();
        UserStoryID userStoryIDOne = mock(UserStoryID.class);
        UserStoryID userStoryIDTwo = mock(UserStoryID.class);

        userStoriesIDInRunningSprint.add(userStoryIDOne);
        when(serviceFindUS.findUSIDinSprint(Optional.of(runningSprint))).thenReturn(userStoriesIDInRunningSprint);

        List<UserStoryID> userStoriesIDDone = new ArrayList<>();
        userStoriesIDInRunningSprint.add(userStoryIDTwo);
        when(serviceFindUS.findDoneUSIDinSprint(runningSprintInList)).thenReturn(userStoriesIDDone);

        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);

        when(userStoryRepository.findByID(userStoryIDOne)).thenReturn(Optional.of(userStoryOne));
        when(userStoryRepository.findByID(userStoryIDTwo)).thenReturn(Optional.of(userStoryTwo));

        List<UserStory> listUserStoryInRunningSprint = new ArrayList<>();
        listUserStoryInRunningSprint.add(userStoryOne);

        List<UserStory> listUserStoryDone = new ArrayList<>();
        listUserStoryDone.add(userStoryTwo);

        List<USMinimalInfoDTO> listUSMinimalDTO = new ArrayList<>();

        when(userStoryOne.getUsID()).thenReturn(UserStoryID.ofDouble(1.001));
        when(userStoryOne.getStoryNumber()).thenReturn(PositiveNumber.of(1));
        when(userStoryOne.getStatement()).thenReturn(Text.write("One"));

        when(userStoryTwo.getUsID()).thenReturn(UserStoryID.ofDouble(1.002));
        when(userStoryTwo.getStoryNumber()).thenReturn(PositiveNumber.of(2));
        when(userStoryTwo.getStatement()).thenReturn(Text.write("Two"));

        USMinimalInfoDTO dto = UserStoryMapper.toMinimalDTO(userStoryOne);
        Link link = linkTo(methodOn(UserStoryController.class).getUserStoryById(projID, dto.usID)).withSelfRel();
        dto.add(link);

        USMinimalInfoDTO dtoTwo = UserStoryMapper.toMinimalDTO(userStoryTwo);
        Link linkTwo = linkTo(methodOn(UserStoryController.class).getUserStoryById(projID, dtoTwo.usID)).withSelfRel();
        dtoTwo.add(linkTwo);

        listUSMinimalDTO.add(dto);
        listUSMinimalDTO.add(dtoTwo);


        List<USMinimalInfoDTO> result = applicationServiceGetUserStoriesNotDoneFromRunningSprint.getUSNotDoneFromRunningSprint(projID);

        assertEquals(listUSMinimalDTO, result);

    }

    @Test
    void getUSNotDoneFromRunningSprint_NoRunningSprint() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> applicationServiceGetUserStoriesNotDoneFromRunningSprint.getUSNotDoneFromRunningSprint(projID));

    }


    @Test
    void getUSNotDoneFromRunningSprint_ProjectDoenstExist() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(false);


        assertThrows(IllegalArgumentException.class, () -> applicationServiceGetUserStoriesNotDoneFromRunningSprint.getUSNotDoneFromRunningSprint(projID));
    }


}