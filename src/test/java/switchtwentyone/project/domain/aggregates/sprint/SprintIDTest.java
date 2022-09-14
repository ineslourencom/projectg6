package switchtwentyone.project.domain.aggregates.sprint;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import static org.junit.jupiter.api.Assertions.*;


class SprintIDTest {

    @Test
    void sprintIDWithSameIDAreEquals() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintNumberTest = PositiveNumber.of(2);
        SprintID sprintID = new SprintID(projIDTest,sprintNumberTest);
        SprintID sprintID1 = new SprintID(projIDTest,sprintNumberTest);

        assertEquals(sprintID, sprintID1);

    }

    @Test
    void sprintIDWithDifferentIDAreEquals() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        PositiveNumber sprintIDTest1 = PositiveNumber.of(3);
        SprintID sprintID = new SprintID(projIDTest,sprintIDTest);
        SprintID sprintID1 = new SprintID(projIDTest,sprintIDTest1);

        assertNotEquals(sprintID, sprintID1);

    }

    @Test
    void sprintIDEqualsToItself() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        SprintID sprintID = new SprintID(projIDTest,sprintIDTest);


        assertEquals(sprintIDTest, sprintIDTest);
    }

    @Test
    void sprintsIDWithSameIDHaveSameHashCode() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        SprintID sprintID = new SprintID(projIDTest,sprintIDTest);
        SprintID sprintID1 = new SprintID(projIDTest,sprintIDTest);

        assertEquals(sprintID.hashCode(), sprintID1.hashCode());
    }

    @Test
    void sprintsWithDifferentIDHaveDifferentHashCode() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        PositiveNumber sprintIDTest1 =PositiveNumber.of(3);
        SprintID sprintID = new SprintID(projIDTest,sprintIDTest);
        SprintID sprintID1 = new SprintID(projIDTest,sprintIDTest1);

        assertNotEquals(sprintID.hashCode(), sprintID1.hashCode());
    }

    @Test
    void sprintNotEqualsToOtherObject() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        SprintID sprintID = new SprintID(projIDTest,sprintIDTest);
        double comparator = 2;

        assertNotEquals(sprintID, comparator);
    }

    @Test
    void SameIdentityAsNull() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        SprintID sprintID = new SprintID(projIDTest,sprintIDTest);

        boolean result = sprintID.sameValueAs(null);

        assertFalse(result);
    }

    @Test
    void EqualsToNull() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        SprintID sprintID = new SprintID(projIDTest,sprintIDTest);

        assertNotEquals(sprintID, null);
    }

    @Test
    void testTooString() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        SprintID test1 = new SprintID(projIDTest,sprintIDTest);
        String expected = "2.001";
        String actual = test1.toString();
        assertNotEquals(expected, actual);
    }
    @Test
    void testToString() {
        ProjectID projIDTest= new ProjectID(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        SprintID test1 = new SprintID(projIDTest,sprintIDTest);
        String expected = "2.002";
        String actual = test1.toString();
        assertEquals(expected, actual);
    }


    @Test
    void ofDouble() {
        //Act
        SprintID sprintID = SprintID.ofDouble(1.999);
        Double result = sprintID.getSprintNumber();

        //Assert
        assertEquals(result, 1.999);

    }

    @Test
    void getIDForTask() {
        SprintID sprintID = SprintID.ofDouble(1.999);
        String expected = "Sp-1.999";

        String result = sprintID.getIDForTask();

        assertEquals(expected, result);
    }
}