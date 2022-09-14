package switchtwentyone.project.domain.aggregates.sprint;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.annotation.DirtiesContext;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SprintBacklogItemIDTest {

    SprintBacklogItemID itemID;
    SprintBacklogItemID itemID1;

    @BeforeAll
    void arrange(){
        itemID = SprintBacklogItemID.createSprintBacklogItemID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");
        itemID1 = SprintBacklogItemID.createSprintBacklogItemID("61ead7c9-6930-4191-9841-46f8d45535a3");
    }


    @Test
    void idNotEqualToOtherObject(){
        //Arrange
        ProjectID projID = new ProjectID(1);

        //Act
        boolean result = itemID.equals(projID);

        //Assert
        assertFalse(result);

    }

    @Test
    void equalToItself() {
        //Act
        boolean result = itemID.equals(itemID);

        //Assert
        assertTrue(result);
    }

    @Test
    void testNotEqualToNull() {
        //Act
        boolean result = itemID.equals(null);

        //Assert
        assertFalse(result);
    }


    @Test
    void testDifferentIDsAreDifferent() {
        //Act
        boolean result = itemID.equals(itemID1);

        //Assert
        assertFalse(result);
    }

    @Test
    void testDifferentIDsHaveDifferentHashCode() {
        //Assert
        assertNotEquals(itemID.hashCode(), itemID1.hashCode());
    }


    @Test
    void notSameValueAsNull() {
        //Act
        boolean result = itemID.sameValueAs(null);

        //Assert
        assertFalse(result);

    }

    @Test
    void differentValueAs() {
        //Act
        boolean result = itemID.sameValueAs(itemID1);

        //Assert
        assertFalse(result);

    }

}