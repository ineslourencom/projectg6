package switchtwentyone.project.domain.aggregates.userStory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryIDTest {

    @Test
    void testSameIDsAreEqual() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        ProjectID projID = new ProjectID(1);
        UserStoryID newUSID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);
        UserStoryID newUSIDTwo = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);

        //Act
        boolean result = newUSID.equals(newUSIDTwo);

        //Assert
        assertTrue(result);
    }

    @Test
    void accountNotEqualToOtherObjectl(){
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        ProjectID projID = new ProjectID(1);
        UserStoryID newUSID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);

        //Act
        boolean result = newUSID.equals(20);

        //Assert
        assertFalse(result);

    }

    @Test
    void equalToItself() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        ProjectID projID = new ProjectID(1);
        UserStoryID newUSID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);

        //Act
        boolean result = newUSID.equals(newUSID);

        //Assert
        assertTrue(result);
    }

    @Test
    void testNotEqualToNull() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        ProjectID projID = new ProjectID(1);
        UserStoryID newUSID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);

        //Act
        boolean result = newUSID.equals(null);

        //Assert
        assertFalse(result);
    }


    @Test
    void testDifferentIDsAreDifferent() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        PositiveNumber usNumberTwo = PositiveNumber.of(2);

        ProjectID projID = new ProjectID(1);
        UserStoryID newUSID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);
        UserStoryID newUSIDTwo = UserStoryID.ofProjIDAndUsNumber(projID, usNumberTwo);


        //Act
        boolean result = newUSID.equals(newUSIDTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    void testDifferentIDsHaveDifferentHashCode() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        PositiveNumber usNumberTwo = PositiveNumber.of(2);

        ProjectID projID = new ProjectID(2);
        UserStoryID newUSID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);
        UserStoryID newUSIDTwo = UserStoryID.ofProjIDAndUsNumber(projID, usNumberTwo);

        //Assert
        assertNotEquals(newUSID.hashCode(), newUSIDTwo.hashCode());
    }



    @Test
    void sameValueAs() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        ProjectID projID = new ProjectID(2);
        UserStoryID newUSID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);
        UserStoryID newUSIDTwo = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);
        //Act
        boolean result = newUSID.sameValueAs(newUSIDTwo);

        //Assert
        assertTrue(result);

    }

    @Test
    void notSameValueAsNull() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        ProjectID projID = new ProjectID(2);
        UserStoryID newUSID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);

        //Act
        boolean result = newUSID.sameValueAs(null);

        //Assert
        assertFalse(result);

    }

    @Test
    void differentValueAs() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        PositiveNumber usNumberTwo = PositiveNumber.of(2);

        ProjectID projID = new ProjectID(2);
        UserStoryID newUSID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);
        UserStoryID newUSIDTwo = UserStoryID.ofProjIDAndUsNumber(projID, usNumberTwo);

        //Act
        boolean result = newUSID.sameValueAs(newUSIDTwo);

        //Assert
        assertFalse(result);

    }

    @Test
    void generateUSIDforUSNumberLowerThan10(){
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1);
        ProjectID projID = new ProjectID(1);

        //Act
        UserStoryID usID = UserStoryID.ofProjIDAndUsNumber(projID , usNumber);
        Double result = usID.getID();

        //Assert
        assertEquals(1.001, result);
    }

    @Test
    void generateUSIDforUSNumberTen(){
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(10);
        ProjectID projID = new ProjectID(1);

        //Act
        UserStoryID usID = UserStoryID.ofProjIDAndUsNumber(projID , usNumber);
        Double result = usID.getID();

        //Assert
        assertEquals( 1.010, result);
    }

    @Test
    void generateUSIDforUSNumber999(){
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(999);
        ProjectID projID = new ProjectID(1);

        //Act
        UserStoryID usID = UserStoryID.ofProjIDAndUsNumber(projID , usNumber);
        Double result = usID.getID();

        //Assert
        assertEquals( 1.999, result);
    }
    

    @Test
    void ofProjIDAndUsNumber() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(999);
        ProjectID projID = new ProjectID(1);

        //Act
        UserStoryID usID = UserStoryID.ofProjIDAndUsNumber(projID , usNumber);
        Double result = usID.getID();

        //Assert
        assertEquals( 1.999, result);
    }

    @Test
    void ofProjIDAndUsNumberAboveLimit() {
        //Arrange
        PositiveNumber usNumber = PositiveNumber.of(1000);
        ProjectID projID = new ProjectID(1);

        //Act Assert
        assertThrows(IllegalArgumentException.class, () -> {
            UserStoryID.ofProjIDAndUsNumber(projID , usNumber);
        });

    }

    @Test
    void ofDouble() {
        //Act
        UserStoryID usID = UserStoryID.ofDouble(1.999);
        Double result = usID.getID();

        //Assert
        assertEquals( 1.999, result);

    }

    @Test
    void setId() {
        //Act
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        usID.setID(1.2);

        //Assert
        assertEquals(1.2, usID.getID());
    }

    @Test
    void getIDForTask() {
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        String expected = "US-1.1";

        String result = usID.getIDForTask();

        assertEquals(expected, result);
    }
}