package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceFindUS;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.USInfoAndStatusDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceGetAllUserStoryStatusTest {

    @Mock
    UserStoryRepository userStoryRepository;
    @Mock
    SprintRepository sprintRepository;
    @Mock
    ServiceFindUS serviceFindUS;
    @InjectMocks
    ApplicationServiceGetAllUserStoryStatus appService;

    @Test
    void getAllUserStoryStatus() {
        //Arrange
        ProjectID proj = new ProjectID(1);

        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        UserStory us3 = mock(UserStory.class);

        UserStoryID userStoryID1= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(1));
        PositiveNumber us1Number = PositiveNumber.of(1);
        Text us1Detail = Text.write("detail1");
        Text statement1 = Text.write("statement");

        UserStoryID userStoryID2= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(2));
        PositiveNumber us2Number = PositiveNumber.of(2);
        Text us2Detail = Text.write("detail2");
        Text statement2 = Text.write("statement");


        UserStoryID userStoryID3= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(3));
        PositiveNumber us3Number = PositiveNumber.of(3);
        Text us3Detail = Text.write("detail3");
        Text statement3 = Text.write("statement");




        when(us1.getStoryNumber()).thenReturn(us1Number);
        when(us1.getPriority()).thenReturn(1);
        when(us1.getUsID()).thenReturn(userStoryID1);
        when(us1.getDetail()).thenReturn(us1Detail);
        when(us1.getStatement()).thenReturn(statement1);

        when(us2.getStoryNumber()).thenReturn(us2Number);
        when(us2.getPriority()).thenReturn(2);
        when(us2.getUsID()).thenReturn(userStoryID2);
        when(us2.getDetail()).thenReturn(us2Detail);
        when(us2.getStatement()).thenReturn(statement2);

        when(us3.getStoryNumber()).thenReturn(us3Number);
        when(us3.getPriority()).thenReturn(3);
        when(us3.getUsID()).thenReturn(userStoryID3);
        when(us3.getDetail()).thenReturn(us3Detail);
        when(us3.getStatement()).thenReturn(statement3);


        List<UserStory> listUS = new ArrayList<>();
        listUS.add(us1);
        listUS.add(us2);
        listUS.add(us3);

        when(userStoryRepository.findAllUSByProjectID(proj)).thenReturn(listUS);

        //Sprints
        Sprint sprint1 = mock(Sprint.class);
        Optional<Sprint> sprintOptional = Optional.of(sprint1);
        Sprint sprint2 = mock(Sprint.class);

        List<Sprint> listPreviousSprints= new ArrayList<>();
        listPreviousSprints.add(sprint2);

        when(sprintRepository.findAllSprintsByProjectID(proj)).thenReturn(listPreviousSprints);
        when(sprintRepository.findRunningSprintByProjectID(proj)).thenReturn(sprintOptional);

        TreeMap<UserStory, String> usInfoStatus  = new TreeMap<>();
        usInfoStatus.put(us1, "To_do");
        usInfoStatus.put(us2, "Code_Review");
        usInfoStatus.put(us3, "done");


        when(serviceFindUS.getAllUserStoriesInfo(listUS,sprint1,listPreviousSprints)).thenReturn(usInfoStatus);

        //Act
        List<USInfoAndStatusDTO> result = appService.getAllUserStoryStatus(1);


        //Assert
        assertEquals(3, result.size());

    }


}