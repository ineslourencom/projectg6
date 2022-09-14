package switchtwentyone.project.domain.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceViewProductBacklog;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.ScrumBoardCategory;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItem;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ServiceFindUSTest {

    @Test
    void getAllUserStoriesInfo() {
        //Arrange
        ProjectID proj = new ProjectID(1);
        //US
        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        UserStory us3 = mock(UserStory.class);
        UserStoryID userStoryID1= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(1));
        UserStoryID userStoryID2= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(2));
        UserStoryID userStoryID3= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(3));

        List<UserStory> listUS = new ArrayList<>();
        listUS.add(us1);
        listUS.add(us2);
        listUS.add(us3);

        //Sprints
        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);

        List<Sprint> listPreviousSprints= new ArrayList<>();
        listPreviousSprints.add(sprint2);

        //SprintBacklogItems
        SprintBacklogItem sbItem1 = mock(SprintBacklogItem.class);
        SprintBacklogItem sbItem2 = mock(SprintBacklogItem.class);
        SprintBacklogItem sbItem3 = mock(SprintBacklogItem.class);

        List<SprintBacklogItem> sBacklogS1 = new ArrayList<>();
        sBacklogS1.add(sbItem1);
        sBacklogS1.add(sbItem2);
        List<SprintBacklogItem> sBacklogS2 = new ArrayList<>();
        sBacklogS2.add(sbItem3);

        when(sprint1.getSprintBacklogItems()).thenReturn(sBacklogS1);
        when(sprint2.getSprintBacklogItems()).thenReturn(sBacklogS2);

        when(sbItem1.getUsID()).thenReturn(userStoryID1);
        when(sbItem2.getUsID()).thenReturn(userStoryID2);
        when(sbItem3.getUsID()).thenReturn(userStoryID3);

        when(us1.hasID(userStoryID1)).thenReturn(true);
        when(us1.hasID(userStoryID2)).thenReturn(false);
        when(us1.hasID(userStoryID3)).thenReturn(false);

        when(us2.hasID(userStoryID1)).thenReturn(false);
        when(us2.hasID(userStoryID2)).thenReturn(true);
        when(us2.hasID(userStoryID3)).thenReturn(false);

        when(us3.hasID(userStoryID1)).thenReturn(false);
        when(us3.hasID(userStoryID2)).thenReturn(false);
        when(us3.hasID(userStoryID3)).thenReturn(true);

        when(sbItem1.getCategory()).thenReturn(ScrumBoardCategory.done());
        when(sbItem2.getCategory()).thenReturn(ScrumBoardCategory.codeReview());
        when(sbItem3.getCategory()).thenReturn(ScrumBoardCategory.rejected());
        ServiceFindUS serviceFindUS = new ServiceFindUS();
        //Act
        TreeMap<UserStory, String> result = serviceFindUS.getAllUserStoriesInfo(listUS, sprint1, listPreviousSprints);

        //Assert

        assertEquals(3, result.size());
    }

    @Test
    void getUSFromRunningSprint() {
        //Arrange
        ProjectID proj = new ProjectID(1);

        //US
        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        UserStory us3 = mock(UserStory.class);
        UserStoryID userStoryID1= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(1));
        UserStoryID userStoryID2= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(2));
        UserStoryID userStoryID3= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(3));

        List<UserStory> listUS = new ArrayList<>();
        listUS.add(us1);
        listUS.add(us2);
        listUS.add(us3);

        //Sprint
        Sprint sprint1 = mock(Sprint.class);

        //SprintBacklogItems
        SprintBacklogItem sbItem1 = mock(SprintBacklogItem.class);
        SprintBacklogItem sbItem2 = mock(SprintBacklogItem.class);
        SprintBacklogItem sbItem3 = mock(SprintBacklogItem.class);

        List<SprintBacklogItem> sBacklogS1 = new ArrayList<>();
        sBacklogS1.add(sbItem1);
        sBacklogS1.add(sbItem2);
        sBacklogS1.add(sbItem3);

        when(sprint1.getSprintBacklogItems()).thenReturn(sBacklogS1);
        when(sbItem1.getUsID()).thenReturn(userStoryID1);
        when(sbItem2.getUsID()).thenReturn(userStoryID2);
        when(sbItem3.getUsID()).thenReturn(userStoryID3);

        when(us1.hasID(userStoryID1)).thenReturn(true);
        when(us1.hasID(userStoryID2)).thenReturn(false);
        when(us1.hasID(userStoryID3)).thenReturn(false);

        when(us2.hasID(userStoryID1)).thenReturn(false);
        when(us2.hasID(userStoryID2)).thenReturn(true);
        when(us2.hasID(userStoryID3)).thenReturn(false);

        when(us3.hasID(userStoryID1)).thenReturn(false);
        when(us3.hasID(userStoryID2)).thenReturn(false);
        when(us3.hasID(userStoryID3)).thenReturn(true);

        when(sbItem1.getCategory()).thenReturn(ScrumBoardCategory.done());
        when(sbItem2.getCategory()).thenReturn(ScrumBoardCategory.codeReview());
        when(sbItem3.getCategory()).thenReturn(ScrumBoardCategory.rejected());
        ServiceFindUS serviceFindUS = new ServiceFindUS();

        //Act
        TreeMap<UserStory, String> result = serviceFindUS.getUSFromRunningSprint(listUS,sprint1 );

        //Assert
        assertEquals(3, result.size());
    }

    @Test
    void getAllUSFromPreviousSprints() {
        //Arrange
        ProjectID proj = new ProjectID(1);

            //US
        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        UserStory us3 = mock(UserStory.class);
        UserStoryID userStoryID1= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(1));
        UserStoryID userStoryID2= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(2));
        UserStoryID userStoryID3= UserStoryID.ofProjIDAndUsNumber(proj , PositiveNumber.of(3));

        List<UserStory> listUS = new ArrayList<>();
        listUS.add(us1);
        listUS.add(us2);
        listUS.add(us3);

            //Sprints
        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);

        List<Sprint> listPreviousSprints= new ArrayList<>();
        listPreviousSprints.add(sprint1);
        listPreviousSprints.add(sprint2);

            //SprintBacklogItems
        SprintBacklogItem sbItem1 = mock(SprintBacklogItem.class);
        SprintBacklogItem sbItem2 = mock(SprintBacklogItem.class);
        SprintBacklogItem sbItem3 = mock(SprintBacklogItem.class);

        List<SprintBacklogItem> sBacklogS1 = new ArrayList<>();
        sBacklogS1.add(sbItem1);
        sBacklogS1.add(sbItem2);
        List<SprintBacklogItem> sBacklogS2 = new ArrayList<>();
        sBacklogS2.add(sbItem3);

        when(sprint1.getSprintBacklogItems()).thenReturn(sBacklogS1);
        when(sprint2.getSprintBacklogItems()).thenReturn(sBacklogS2);

        when(sbItem1.getUsID()).thenReturn(userStoryID1);
        when(sbItem2.getUsID()).thenReturn(userStoryID2);
        when(sbItem3.getUsID()).thenReturn(userStoryID3);

        when(us1.hasID(userStoryID1)).thenReturn(true);
        when(us1.hasID(userStoryID2)).thenReturn(false);
        when(us1.hasID(userStoryID3)).thenReturn(false);

        when(us2.hasID(userStoryID1)).thenReturn(false);
        when(us2.hasID(userStoryID2)).thenReturn(true);
        when(us2.hasID(userStoryID3)).thenReturn(false);

        when(us3.hasID(userStoryID1)).thenReturn(false);
        when(us3.hasID(userStoryID2)).thenReturn(false);
        when(us3.hasID(userStoryID3)).thenReturn(true);

        when(sbItem1.getCategory()).thenReturn(ScrumBoardCategory.done());
        when(sbItem2.getCategory()).thenReturn(ScrumBoardCategory.codeReview());
        when(sbItem3.getCategory()).thenReturn(ScrumBoardCategory.rejected());
        ServiceFindUS serviceFindUS = new ServiceFindUS();

        //Act
        TreeMap<UserStory, String> result = serviceFindUS.getAllUSFromPreviousSprints(listUS,listPreviousSprints);

        //Assert
        assertEquals(3, result.size());

    }

    @Test
    void getDecomposedUSTest() {
        //Arrange
        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        UserStory us3 = mock(UserStory.class);

        when(us1.getIsDecomposed()).thenReturn(true);
        when(us2.getIsDecomposed()).thenReturn(true);
        when(us3.getIsDecomposed()).thenReturn(false);

        List<UserStory> listUS = new ArrayList<>();
        listUS.add(us1);
        listUS.add(us2);
        listUS.add(us3);
        ServiceFindUS serviceFindUS = new ServiceFindUS();

        //Act
        TreeMap<UserStory, String> result = serviceFindUS.getDecomposedUS(listUS);

        //Assert
        assertEquals(2, result.size());
    }

    @Test
    void orderListOfUSByPriority_Test_Success() {
        ApplicationServiceViewProductBacklog service = new ApplicationServiceViewProductBacklog();

        // Arrange
        UserStory newUserStory1 = mock(UserStory.class);
        when(newUserStory1.getPriority()).thenReturn(1);
        UserStory newUserStory2 = mock(UserStory.class);
        when(newUserStory2.getPriority()).thenReturn(3);
        UserStory newUserStory3 = mock(UserStory.class);
        when(newUserStory3.getPriority()).thenReturn(4);
        UserStory newUserStory4 = mock(UserStory.class);
        when(newUserStory4.getPriority()).thenReturn(2);
        UserStory newUserStory5 = mock(UserStory.class);
        when(newUserStory5.getPriority()).thenReturn(5);
        UserStory newUserStory6 = mock(UserStory.class);
        when(newUserStory6.getPriority()).thenReturn(3);

        List<UserStory> usList = new ArrayList<>();
        usList.add(newUserStory1);
        usList.add(newUserStory2);
        usList.add(newUserStory3);
        usList.add(newUserStory4);
        usList.add(newUserStory5);
        usList.add(newUserStory6);
        ServiceFindUS serviceFindUS = new ServiceFindUS();

        // Act
        serviceFindUS.orderListOfUSByPriority(usList);


        // Arrange & Assert
        int actualSize = usList.size();
        int expectedSize = 6;
        assertEquals(expectedSize, actualSize);

        int actual0 = usList.indexOf(newUserStory1);
        int expected0 = 0;
        assertEquals(expected0, actual0);

        int actual1 = usList.indexOf(newUserStory4);
        int expected1 = 1;
        assertEquals(expected1, actual1);

        int actual2 = usList.indexOf(newUserStory2);
        int expected2 = 2;
        assertEquals(expected2, actual2);

        int actual3 = usList.indexOf(newUserStory6);
        int expected3 = 3;
        assertEquals(expected3, actual3);

        int actual4 = usList.indexOf(newUserStory3);
        int expected4 = 4;
        assertEquals(expected4, actual4);

        int actual5 = usList.indexOf(newUserStory5);
        int expected5 = 5;
        assertEquals(expected4, actual4);
    }
}