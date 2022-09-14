package switchtwentyone.project.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.datamodel.SprintJPA;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class SprintJPATest {

    @Test
    void getSprintID() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        int sprintDuration = 1;
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, sprintDuration);

        //Act
        SprintID result = jpa.getSprintID();

        //Assert
        assertEquals(sprintID, result);
    }

    @Test
    void getSprintNumber() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        int sprintDuration = 1;
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, sprintDuration);

        //Act
        int result = jpa.getSprintNumber();

        //Assert
        assertEquals(sprintNumber, result);
    }

    @Test
    void getProjectID() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Act
        int result = jpa.getProjectID();

        //Assert
        assertEquals(projectID, result);
    }

    @Test
    void getStartDate() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Act
        LocalDate result = jpa.getStartDate();

        //Assert
        assertEquals(startDate, result);
    }

    @Test
    void setSprintID() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);
        SprintID sprintID2 = SprintID.ofDouble(1.002);

        //Act
        jpa.setSprintID(sprintID2);
        SprintID result = jpa.getSprintID();

        //Assert
        assertEquals(sprintID2, result);
    }

    @Test
    void setSprintNumber() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Act
        jpa.setSprintNumber(2);
        int result = jpa.getSprintNumber();

        //Assert
        assertEquals(2, result);
    }

    @Test
    void setProjectID() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Act
        jpa.setProjectID(2);
        int result = jpa.getProjectID();

        //Assert
        assertEquals(2, result);
    }

    @Test
    void setStartDate() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Act
        jpa.setStartDate(LocalDate.of(2022,2,1));
        LocalDate result = jpa.getStartDate();

        //Assert
        assertEquals(LocalDate.of(2022,2,1), result);
    }

    @Test
    void getSprintDuration() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Act
        int result = jpa.getSprintDuration();

        //Assert
        assertEquals(1, result);
    }

    @Test
    void setSprintDuration() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Act
        jpa.setSprintDuration(2);

        //Assert
        assertEquals(2, jpa.getSprintDuration());
    }

    @Test
    void testSameJPAAreEqual() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);
        SprintJPA jpa2 = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Act
        boolean result = jpa.equals(jpa2);

        //Assert
        assertTrue(result);
    }

    @Test
    void testSameJPAHaveSameHashCode() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);
        SprintJPA jpa2 = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Assert
        assertEquals(jpa.hashCode(), jpa2.hashCode());
    }

    @Test
    void testJPANotEqualToNull() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);

        //Act
        boolean result = jpa.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPANotEqualToOtherObject() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);
        PositiveNumber number = PositiveNumber.of(1);

        //Act
        boolean result = jpa.equals(number);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPEqualToItself() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);
        PositiveNumber number = PositiveNumber.of(1);

        //Act
        boolean result = jpa.equals(jpa);

        //Assert
        assertTrue(result);
    }

    @Test
    void testDifferentJPAshaveDifferentHashcodes() {
        //Arrange
        SprintID sprintID = SprintID.ofDouble(1.001);
        SprintID sprintID2 = SprintID.ofDouble(1.002);
        int sprintNumber = 1;
        int projectID = 1;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        SprintJPA jpa = new SprintJPA(sprintID, startDate, projectID, sprintNumber, 1);
        SprintJPA jpa2 = new SprintJPA(sprintID2, startDate, projectID, 2, 1);

        //Assert
        assertNotEquals(jpa.hashCode(), jpa2.hashCode());
    }

}