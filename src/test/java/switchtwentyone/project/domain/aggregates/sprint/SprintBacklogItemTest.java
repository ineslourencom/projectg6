package switchtwentyone.project.domain.aggregates.sprint;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;

import static org.junit.jupiter.api.Assertions.*;

class SprintBacklogItemTest {

    @Test
    void testSameItemsAreEqual() {
       //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID, usID, effortEstimate);
        SprintBacklogItem item1 = new SprintBacklogItem(itemID, usID, effortEstimate);

        //Act
        boolean result = item.equals(item1);

        //Assert
        assertTrue(result);
    }

    @Test
    void testDifferentItemsAreDifferent() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItemID itemID2 = SprintBacklogItemID.createSprintBacklogItemID();

        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        SprintBacklogItem item1 = new SprintBacklogItem(itemID2, usID, effortEstimate);

        //Act
        boolean result = item.equals(item1);

        //Assert
        assertFalse(result);
    }


    @Test
    void testItemIsNotEqualToObjectOfDifferentClass() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);

        //Act
        boolean result = item.equals(new Object());

        //Assert
        assertFalse(result);

    }

    @Test
    void testItemIsEqualToItself(){
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        boolean result = item.equals(item);
        //Assert
        assertTrue(result);
    }

    @Test
    void testSameItemsHaveSameHashcode() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID, usID, effortEstimate);
        SprintBacklogItem item1 = new SprintBacklogItem(itemID, usID, effortEstimate);

        //Assert
        assertEquals(item.hashCode(), item1.hashCode());
    }

    @Test
    void testDifferentItemsHaveDifferentHashcode() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItemID itemID2 = SprintBacklogItemID.createSprintBacklogItemID();

        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        SprintBacklogItem item1 = new SprintBacklogItem(itemID2, usID, effortEstimate);

        //Assert
        assertNotEquals(item.hashCode(), item1.hashCode());
    }

    @Test
    void testItemIsNotEqualToNull(){
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        boolean result = item.equals(null);
        //Assert
        assertFalse(result);
    }

    @Test
    void testNewItemHasToDoCategory(){
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        ScrumBoardCategory result = item.category;
        //Assert
        assertEquals(ScrumBoardCategory.toDo(), result);
    }

    @Test
    void getItemID() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        SprintBacklogItemID result = item.getItemID();
        //Assert
        assertEquals(itemID1, result);
    }

    @Test
    void setItemID() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItemID itemID2 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        item.setItemID(itemID2);
        //Assert
        assertEquals(itemID2, item.getItemID());
    }

    @Test
    void getUsID() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        UserStoryID result = item.getUsID();
        //Assert
        assertEquals(usID, result);
    }

    @Test
    void setUsID() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        UserStoryID usID2 = UserStoryID.ofDouble(1.2);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItemID itemID2 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        item.setUsID(usID2);
        //Assert
        assertEquals(usID2, item.getUsID());
    }

    @Test
    void getCategory() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        ScrumBoardCategory result = item.getCategory();
        //Assert
        assertEquals(ScrumBoardCategory.toDo(), result);
    }

    @Test
    void setCategory() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        UserStoryID usID2 = UserStoryID.ofDouble(1.2);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        ScrumBoardCategory cat = ScrumBoardCategory.codeReview();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        item.setCategory(cat);
        //Assert
        assertEquals(cat, item.getCategory());
    }

    @Test
    void getEffortEstimate() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        FibonacciNumber result = item.getEffortEstimate();
        //Assert
        assertEquals(effortEstimate, result);
    }

    @Test
    void setEffortEstimate() {
        //Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        UserStoryID usID2 = UserStoryID.ofDouble(1.2);
        FibonacciNumber effortEstimate = FibonacciNumber.of(1);
        FibonacciNumber effortEstimate2 = FibonacciNumber.of(2);
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID();
        ScrumBoardCategory cat = ScrumBoardCategory.codeReview();
        SprintBacklogItem item = new SprintBacklogItem(itemID1, usID, effortEstimate);
        //Act
        item.setEffortEstimate(effortEstimate2);
        //Assert
        assertEquals(effortEstimate2, item.getEffortEstimate());    }

}