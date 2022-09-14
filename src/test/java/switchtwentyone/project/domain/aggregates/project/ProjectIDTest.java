package switchtwentyone.project.domain.aggregates.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class ProjectIDTest {

    @Test
    void projectIDWithSameIDAreEquals() {
        int projectIDCode = 2;
        ProjectID projectID = new ProjectID(projectIDCode);
        ProjectID projectIDTwo = new ProjectID(projectIDCode);

        assertEquals(projectID, projectIDTwo);
    }

    @Test
    void projectIDWithDifferentIDAreDifferent() {
        int projectIDCode = 2;
        int projectIDCodeTwo = 3;
        ProjectID projectID = new ProjectID(projectIDCode);
        ProjectID projectIDTwo = new ProjectID(projectIDCodeTwo);

        assertNotEquals(projectID, projectIDTwo);
    }

    @Test
    void projectIDEqualsToItself() {
        int projectIDCode = 2;
        ProjectID projectID = new ProjectID(projectIDCode);


        assertEquals(projectID, projectID);
    }


    @Test
    void projectsIDWithSameIDHaveSameHashCode() {
        int projectIDCode = 2;
        ProjectID projectID = new ProjectID(projectIDCode);
        ProjectID projectIDTwo = new ProjectID(projectIDCode);

        assertEquals(projectID.hashCode(), projectIDTwo.hashCode());
    }

    @Test
    void projectsWithDifferentIDHaveDifferentHashCode() {
        int projectIDCode = 2;
        int projectIDCodeTwo = 3;
        ProjectID projectID = new ProjectID(projectIDCode);
        ProjectID projectIDTwo = new ProjectID(projectIDCodeTwo);

        assertNotEquals(projectID.hashCode(), projectIDTwo.hashCode());
    }

    @Test
    void projectNotEqualsToOtherObject() {
        int projectIDCode = 2;
        ProjectID projectID = new ProjectID(projectIDCode);
        int comparator = 2;

        assertNotEquals(projectID, comparator);
    }


    @Test
    void SameIdentityAsNull() {
        int projectIDCode = 2;
        ProjectID projectID = new ProjectID(projectIDCode);

        boolean result = projectID.sameValueAs(null);

        assertFalse(result);
    }

    @Test
    void EqualsToNull() {
        int projectIDCode = 2;
        ProjectID projectID = new ProjectID(projectIDCode);

        assertNotEquals(projectID, null);
    }

    @Test
    void testToString() {
        ProjectID projectIDtest = new ProjectID(1);
        String expected = "2";
        String actual = projectIDtest.toString();
        assertNotEquals(expected, actual);
    }

    @Test
    void testToStringg() {
        ProjectID projectIDtest = new ProjectID(2);
        String expected = "2";
        String actual = projectIDtest.toString();
        assertEquals(expected, actual);
    }

    @Test
    void getProjectIDTest() {
        ProjectID id = new ProjectID(2);
        int expected = 2;

        int result = id.getProjectID();

        assertEquals(expected, result);

    }

    @Test
    void ensureCloneIsEqual() {
        ProjectID expected = new ProjectID(1);

        ProjectID actual = expected.clone();

        assertEquals(expected, actual);

    }

    @Test
    void ensureCloneIsNotTheSameObject() {
        ProjectID expected = new ProjectID(1);

        ProjectID actual = expected.clone();

        assertNotSame(expected, actual);
    }

    @Test
    void ensureCloneReturnsSameValue() {
        ProjectID original = new ProjectID(1);
        ProjectID clone = original.clone();
        int expected = original.getProjectID();

        int result = clone.getProjectID();

        assertEquals(expected, result);
    }
}