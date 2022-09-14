package switchtwentyone.project.domain.aggregates.sprint;


import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class SprintTest {

    @Test
    void isCodeNotHigher() {
        PositiveNumber sID = PositiveNumber.of(1);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        int code = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sID);
        Sprint sprint1 = new Sprint(sprintID, start, projectID, sID, sprintDurantion);

        boolean result = sprint1.isCodeHigher(1);
        assertFalse(result);
    }

    @Test
    void isCodeHigher() {
        PositiveNumber sID = PositiveNumber.of(1);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        int code = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sID);
        Sprint sprint1 = new Sprint(sprintID, start, projectID, sID, sprintDurantion);

        boolean result = sprint1.isCodeHigher(2);
        assertTrue(result);
    }

    @Test
    void sprintWithSameIDAreEquals() {
        PositiveNumber sID = PositiveNumber.of(1);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sID);

        Sprint sprint1 = new Sprint(sprintID, start, projectID, sID, sprintDurantion);
        Sprint sprint2 = new Sprint(sprintID, start, projectID, sID, sprintDurantion);

        assertEquals(sprint1, sprint2);
    }

    @Test
    void sprintWithDifferentAreNotEquals() {
        PositiveNumber sIDTest = PositiveNumber.of(2);
        PositiveNumber sIDTest1 = PositiveNumber.of(3);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID1 = new SprintID(projectID, sIDTest);
        SprintID sprintID2 = new SprintID(projectID, sIDTest1);


        Sprint sprint1 = new Sprint(sprintID1, start, projectID, sIDTest, sprintDurantion);
        Sprint sprint2 = new Sprint(sprintID2, start, projectID, sIDTest1, sprintDurantion);

        assertNotEquals(sprint1, sprint2);
    }

    @Test
    void sprintEqualsToItself() {
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID1 = new SprintID(projectID, sprintIDTest);


        Sprint sprint = new Sprint(sprintID1, start, projectID, sprintIDTest, sprintDurantion);


        assertEquals(sprint, sprint);
    }

    @Test
    void sprintsHaveSameHashCode() {
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        SprintID sprintID1 = new SprintID(projectID, sprintIDTest);


        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);
        Sprint sprint1 = new Sprint(sprintID1, start, projectID, sprintIDTest, sprintDurantion);

        assertEquals(sprint.hashCode(), sprint1.hashCode());
    }

    @Test
    void sprintsDifferentHaveDifferentHashCode() {
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintIDTest1 = PositiveNumber.of(3);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        SprintID sprintID1 = new SprintID(projectID, sprintIDTest1);


        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);
        Sprint sprint1 = new Sprint(sprintID1, start, projectID, sprintIDTest1, sprintDurantion);


        assertNotEquals(sprint.hashCode(), sprint1.hashCode());
    }

    @Test
    void sprintNotEqualsToOtherObject() {
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        int comparator = 2;
        SprintID sprintID = new SprintID(projectID, sprintIDTest);


        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        assertNotEquals(sprint, comparator);
    }

    @Test
    void SameIdentityAsNull() {
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);


        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        boolean result = sprint.sameIdentityAs(null);

        assertFalse(result);
    }

    @Test
    void EqualsToNull() {
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);


        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        assertNotEquals(sprint, null);
    }

    /**
     * @Test void getProjectID() {
     * //Act
     * ProjectID result = newSprint1.getProjectID();
     * <p>
     * //Assert
     * assertEquals(result, projID1);
     * <p>
     * }
     */
    @Test
    void hasIDTest() {
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        boolean result = sprint.hasID(sprintID);

        assertTrue(result);

    }

    @Test
    void doesNotHaveIDTest() {
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintIDTest1 = PositiveNumber.of(1);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        int pId1 = 2;
        ProjectID projectID = new ProjectID(pId);
        ProjectID projectID1 = new ProjectID(pId1);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        SprintID sprintID1 = new SprintID(projectID1, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest1, sprintDurantion);

        boolean result = sprint.hasID(sprintID1);
        assertFalse(result);

    }

    @Test
    void doesNotHaveIDWhenIdIsNullTest() {
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.of(2022, 10, 25);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        boolean result = sprint.hasID(null);
        assertFalse(result);
    }


    @Test
    void addUSToSprintBacklog() {
        SprintID sprintID = SprintID.ofDouble(1.001);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        ProjectID projectID = new ProjectID(1);
        LocalDate startDate =  LocalDate.of(2022,1,1);
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        Sprint sprint = new Sprint(sprintID, startDate, projectID, sprintNumber, sprintDuration);

        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effort = FibonacciNumber.of(1);
        SprintBacklogItemID newItemID20 = SprintBacklogItemID.createSprintBacklogItemID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");

        MockedStatic<SprintBacklogItemID> sprintBacklogItemID = mockStatic(SprintBacklogItemID.class);

        sprintBacklogItemID.when(SprintBacklogItemID::createSprintBacklogItemID)
                .thenReturn(newItemID20);

        SprintBacklogItem expected = SprintBacklogItemCreator.createSprintBacklogItem(newItemID20, usID, effort);

        //Act
        SprintBacklogItem result = sprint.addUSToSprintBacklog(usID, effort);
        sprintBacklogItemID.close();


        //Assert
        assertEquals(expected, result);
    }

    @Test
    void isRunning() {
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().minusDays(1);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        boolean result = sprint.isRunning();

        assertTrue(result);

    }

    @Test
    void isRunningAndStartedToday() {
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now();
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        boolean result = sprint.isRunning();

        assertTrue(result);

    }

    @Test
    void isNotRunningAndEndedToday() {
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().minusDays(14);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        boolean result = sprint.isRunning();

        assertFalse(result);

    }

    @Test
    void isNotRunningAndStartsInTheFuture() {
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().plusDays(14);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        boolean result = sprint.isRunning();

        assertFalse(result);

    }

    @Test
    void isNotRunningAndEndedInThePast() {
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().minusDays(140);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        boolean result = sprint.isRunning();

        assertFalse(result);

    }

    @Test
    void belongsToProject() {
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().minusDays(140);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        boolean result = sprint.belongsToProject(projectID);

        assertTrue(result);
    }

    @Test
    void doesNotBelongToProject() {
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        ProjectID projectID2 = new ProjectID(2);
        LocalDate start = LocalDate.now().minusDays(140);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        boolean result = sprint.belongsToProject(projectID2);

        assertFalse(result);
    }

    @Test
    void getSprintID(){
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().minusDays(140);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        double result = sprint.getSprintIDDouble();

        assertEquals(sprintID.getID(), result);

    }

    @Test
    void getSprintNumber(){
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().minusDays(140);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        double result = sprint.getSprintNumber();

        assertEquals(sprintIDTest.getNumber(), result);

    }

    @Test
    void getProjectID(){
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().minusDays(140);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        double result = sprint.getProjectID();

        assertEquals(projectID.getProjectID(), result);

    }

    @Test
    void getStartDate(){
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().minusDays(140);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        LocalDate result = sprint.getStartDate();

        assertEquals(start, result);
    }

    @Test
    void getSprintDuration(){
        //Arrange
        PositiveNumber sprintIDTest = PositiveNumber.of(2);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        LocalDate start = LocalDate.now().minusDays(140);
        SprintID sprintID = new SprintID(projectID, sprintIDTest);
        Sprint sprint = new Sprint(sprintID, start, projectID, sprintIDTest, sprintDurantion);

        //Act
        PositiveNumber result = sprint.getSprintDuration();

        assertEquals(sprintDurantion, result);
    }

    @Test
    void getSprintBacklogItems() {
        SprintID sprintID = SprintID.ofDouble(1.001);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        ProjectID projectID = new ProjectID(1);
        LocalDate startDate =  LocalDate.of(2022,1,1);
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        Sprint sprint = new Sprint(sprintID, startDate, projectID, sprintNumber, sprintDuration);

        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effort = FibonacciNumber.of(1);
        SprintBacklogItemID newItemID20 = SprintBacklogItemID.createSprintBacklogItemID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");

        MockedStatic<SprintBacklogItemID> sprintBacklogItemID = mockStatic(SprintBacklogItemID.class);

        sprintBacklogItemID.when(SprintBacklogItemID::createSprintBacklogItemID)
                .thenReturn(newItemID20);

        SprintBacklogItem expectedItem = SprintBacklogItemCreator.createSprintBacklogItem(newItemID20, usID, effort);

        sprint.addUSToSprintBacklog(usID, effort);
        sprintBacklogItemID.close();

        //Act
        List<SprintBacklogItem> result = sprint.getSprintBacklogItems();
        List<SprintBacklogItem> expected = new ArrayList<>();
        expected.add(expectedItem);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void setSprintBacklogItems() {
        SprintID sprintID = SprintID.ofDouble(1.001);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        ProjectID projectID = new ProjectID(1);
        LocalDate startDate =  LocalDate.of(2022,1,1);
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        Sprint sprint = new Sprint(sprintID, startDate, projectID, sprintNumber, sprintDuration);

        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effort = FibonacciNumber.of(1);
        SprintBacklogItemID newItemID20 = SprintBacklogItemID.createSprintBacklogItemID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");

        SprintBacklogItem expectedItem = SprintBacklogItemCreator.createSprintBacklogItem(newItemID20, usID, effort);

        List<SprintBacklogItem> expected = new ArrayList<>();
        expected.add(expectedItem);

        //Act
        sprint.setSprintBacklogItems(expected);

        //Assert
        assertEquals(expected, sprint.getSprintBacklogItems());
    }

    @Test
    void isPeriodInsideProject_SuccessWithLimites() {
        SprintID sprintID = SprintID.ofDouble(1.001);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        ProjectID projectID = new ProjectID(1);
        LocalDate startDate =  LocalDate.of(2022,1,1);
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        Sprint sprint = new Sprint(sprintID, startDate, projectID, sprintNumber, sprintDuration);
        Period period = mock(Period.class);
        when(period.getStartingDate()).thenReturn(LocalDate.of(2022,1,1));
        when(period.getEndingDate()).thenReturn( LocalDate.of(2022,1,8));

        assertTrue(sprint.isPeriodInsideProject(period));
    }

    @Test
    void isPeriodInsideProject_SuccessEndDateNull() {
        SprintID sprintID = SprintID.ofDouble(1.001);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        ProjectID projectID = new ProjectID(1);
        LocalDate startDate =  LocalDate.of(2022,1,1);
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        Sprint sprint = new Sprint(sprintID, startDate, projectID, sprintNumber, sprintDuration);
        Period period = mock(Period.class);
        when(period.getStartingDate()).thenReturn(LocalDate.of(2022,1,1));
        when(period.getEndingDate()).thenReturn( null);

        assertTrue(sprint.isPeriodInsideProject(period));
    }

    @Test
    void isPeriodInsideProject_SuccessPeriodIsNull() {
        SprintID sprintID = SprintID.ofDouble(1.001);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        ProjectID projectID = new ProjectID(1);
        LocalDate startDate =  LocalDate.of(2022,1,1);
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        Sprint sprint = new Sprint(sprintID, startDate, projectID, sprintNumber, sprintDuration);
        Period period = null;

        assertTrue(sprint.isPeriodInsideProject(period));
    }

    @Test
    void isPeriodInsideProject_UnSuccessStartDate() {
        SprintID sprintID = SprintID.ofDouble(1.001);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        ProjectID projectID = new ProjectID(1);
        LocalDate startDate =  LocalDate.of(2022,1,1);
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        Sprint sprint = new Sprint(sprintID, startDate, projectID, sprintNumber, sprintDuration);
        Period period = mock(Period.class);
        when(period.getStartingDate()).thenReturn(LocalDate.of(2021,12,31));
        when(period.getEndingDate()).thenReturn( LocalDate.of(2022,1,8));

        assertFalse(sprint.isPeriodInsideProject(period));
    }

    @Test
    void isPeriodInsideProject_UnSuccessEndDate() {
        SprintID sprintID = SprintID.ofDouble(1.001);
        PositiveNumber sprintNumber = PositiveNumber.of(1);
        ProjectID projectID = new ProjectID(1);
        LocalDate startDate =  LocalDate.of(2022,1,1);
        PositiveNumber sprintDuration = PositiveNumber.of(1);
        Sprint sprint = new Sprint(sprintID, startDate, projectID, sprintNumber, sprintDuration);
        Period period = mock(Period.class);
        when(period.getStartingDate()).thenReturn(LocalDate.of(2021,12,31));
        when(period.getEndingDate()).thenReturn( LocalDate.of(2022,1,9));


        assertFalse(sprint.isPeriodInsideProject(period));
    }
}
