package switchtwentyone.project.domain.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.sprint.ScrumBoardCategory;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItem;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ServiceUpdateUsStatusTest {

    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToInProgressFromToDo() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        SprintBacklogItem sprintBacklogItemTwo = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItemTwo);


        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItemTwo.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("In_progress");


        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);

        when(sprintBacklogItemTwo.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("To_do");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();
        Sprint result = serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID, sprint, category);

        assertEquals(result, sprint);
    }

    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToInProgressFromCodeReview() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        SprintBacklogItem sprintBacklogItemTwo = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItemTwo);


        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItemTwo.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("In_progress");


        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);

        when(sprintBacklogItemTwo.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("Code_review");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        Sprint result = serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID, sprint, category);

        assertEquals(result, sprint);
    }

    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToCodeReviewFromInProgress() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        SprintBacklogItem sprintBacklogItemTwo = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItemTwo);


        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItemTwo.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Code_review");


        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);

        when(sprintBacklogItemTwo.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("In_progress");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        Sprint result = serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID, sprint, category);

        assertEquals(result, sprint);
    }
    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToCodeReviewFromToDo() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        SprintBacklogItem sprintBacklogItemTwo = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItemTwo);


        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItemTwo.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Code_review");


        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);

        when(sprintBacklogItemTwo.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("To_do");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        assertThrows(IllegalArgumentException.class, () -> serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID,sprint,category));

    }
    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToInProgressFromDone() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        SprintBacklogItem sprintBacklogItemTwo = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItemTwo);


        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItemTwo.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Code_review");


        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);

        when(sprintBacklogItemTwo.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("Done");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        assertThrows(IllegalArgumentException.class, () -> serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID,sprint,category));

    }
    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToInProgressFromRejected() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        SprintBacklogItem sprintBacklogItemTwo = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItemTwo);


        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItemTwo.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Code_review");


        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);

        when(sprintBacklogItemTwo.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("Rejected");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        assertThrows(IllegalArgumentException.class, () -> serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID,sprint,category));

    }

    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToDoneFromCodeReview() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        SprintBacklogItem sprintBacklogItemTwo = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItemTwo);


        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItemTwo.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Done");


        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);

        when(sprintBacklogItemTwo.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("Code_review");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        Sprint result = serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID, sprint, category);

        assertEquals(result, sprint);
    }

    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToRejectedFromCodeReview() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        SprintBacklogItem sprintBacklogItemTwo = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItemTwo);


        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItemTwo.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Rejected");


        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);

        when(sprintBacklogItemTwo.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("Code_review");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        Sprint result = serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID, sprint, category);

        assertEquals(result, sprint);
    }

    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToRejectedFromInProgress() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();
        SprintBacklogItem sprintBacklogItemTwo = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItemTwo);


        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItemTwo.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Rejected");


        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);

        when(sprintBacklogItemTwo.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("In_progress");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        Sprint result = serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID, sprint, category);

        assertEquals(result, sprint);
    }

    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusToRejectedFromToDo() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();

        SprintBacklogItem sprintBacklogItem = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItem);

        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItem.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Rejected");

        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);
        when(sprintBacklogItem.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("To_do");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        Sprint result = serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID, sprint, category);

        assertEquals(result, sprint);

    }
    @Test
    void testUsIDExistOnRunningSprintAndUpdateNewStatusInvalid() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();

        SprintBacklogItem sprintBacklogItem = mock(SprintBacklogItem.class);
        sprintBacklogItemList.add(sprintBacklogItem);

        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItem.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Rejected");

        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);
        when(sprintBacklogItem.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("Done");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        assertThrows(IllegalArgumentException.class, () -> serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID,sprint,category));
    }
    @Test
    void testUsIDDoesntExistOnRunningSprint() {

        List<SprintBacklogItem> sprintBacklogItemList = new ArrayList<>();

        SprintBacklogItem sprintBacklogItem = mock(SprintBacklogItem.class);

        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintBacklogItems()).thenReturn(sprintBacklogItemList);

        UserStoryID userStoryID = mock(UserStoryID.class);
        when(sprintBacklogItem.getUsID()).thenReturn(userStoryID);

        ScrumBoardCategory category = mock(ScrumBoardCategory.class);
        when(category.toString()).thenReturn("Rejected");

        ScrumBoardCategory oldCategory = mock(ScrumBoardCategory.class);
        when(sprintBacklogItem.getCategory()).thenReturn(oldCategory);
        when(oldCategory.toString()).thenReturn("Done");
        ServiceUpdateUsStatus serviceUpdateUsStatus = new ServiceUpdateUsStatus();

        assertThrows(IllegalArgumentException.class, () -> serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID,sprint,category));
    }
}