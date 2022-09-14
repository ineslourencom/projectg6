package switchtwentyone.project.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.datamodel.SprintJPA;
import switchtwentyone.project.datamodel.SprintbacklogItemJPA;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItemID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import static org.junit.jupiter.api.Assertions.*;

class SprintbacklogItemJPATest {

    @Test
    void getItemID() {
        //arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);

        //act
        SprintBacklogItemID result = jpa.getItemID();

        //assert
        assertEquals(itemID, result);

    }

    @Test
    void getUsID() {
        //arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        //act
        double result = jpa.getUsID();

        //assert
        assertEquals(usID, result);
    }

    @Test
    void getCategory() {
        //arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        //act
        String result = jpa.getCategory();

        //assert
        assertEquals(category, result);
    }

    @Test
    void getEffortEstimate() {
        //arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        //act
        int result = jpa.getEffortEstimate();

        //assert
        assertEquals(effortEstimate, result);
    }

    @Test
    void setItemID() {
        //arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID("5a8b03d0-ead7-4a1c-8e52-69ec78481a87");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        //act
        jpa.setItemID(itemID1);

        //assert
        assertEquals(itemID1, jpa.getItemID());
    }

    @Test
    void setUsID() {
        //arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        //act
        jpa.setUsID(1.002);

        //assert
        assertEquals(1.002, jpa.getUsID());
    }

    @Test
    void setCategory() {
        //arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        //act
        jpa.setCategory("done");

        //assert
        assertEquals("done", jpa.getCategory());
    }

    @Test
    void setEffortEstimate() {
        //arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        //act
        jpa.setEffortEstimate(2);

        //assert
        assertEquals(2, jpa.getEffortEstimate());
    }

    @Test
    void testSameJPAAreEqual() {
        //Arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        SprintbacklogItemJPA jpa2 = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);

        //Act
        boolean result = jpa.equals(jpa2);

        //Assert
        assertTrue(result);
    }
    @Test
    void testSameJPAAreEqual_return() {
        //Arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        SprintbacklogItemJPA jpa2 = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);

        //Act
        boolean result = jpa.equals(jpa2);

        //Assert
        assertEquals(jpa, jpa2 );
    }

    @Test
    void testSameJPAHaveSameHashCode() {
        //Arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        SprintbacklogItemJPA jpa2 = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);

        //Assert
        assertEquals(jpa.hashCode(), jpa2.hashCode());
    }

    @Test
    void testJPANotEqualToNull() {
        //Arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        //Act
        boolean result = jpa.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPANotEqualToOtherObject() {
        //Arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        PositiveNumber number = PositiveNumber.of(1);

        //Act
        boolean result = jpa.equals(number);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPEqualToItself() {
        //Arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        //Act
        boolean result = jpa.equals(jpa);

        //Assert
        assertTrue(result);
    }

    @Test
    void testDifferentJPAshaveDifferentHashcodes() {
        //Arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        SprintBacklogItemID itemID1 = SprintBacklogItemID.createSprintBacklogItemID("5a8b03d0-ead7-4a1c-8e52-69ec78481a87");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);
        SprintbacklogItemJPA jpa1 = new SprintbacklogItemJPA(itemID1, usID, category, effortEstimate, sprintJPA);


        //Assert
        assertNotEquals(jpa.hashCode(), jpa1.hashCode());
    }
    
    @Test
    void testToString(){
        //Arrange
        SprintBacklogItemID itemID = SprintBacklogItemID.createSprintBacklogItemID("02e22a94-5895-4c85-9f5d-673207c11f5b");
        double usID = 1.001;
        String category = "toDo";
        int effortEstimate = 1;
        SprintJPA sprintJPA = new SprintJPA();
        SprintbacklogItemJPA jpa = new SprintbacklogItemJPA(itemID, usID, category, effortEstimate, sprintJPA);


        String expected = "SprintbacklogItemJPA{" +
                "itemID=" + itemID.toString() +
                ", usID=" + "1.001" +
                ", category='" + "toDo" + '\'' +
                ", effortEstimate=" + "1" +
                '}';
        //act
        String result = jpa.toString();

        assertEquals(expected, result);

    }

}