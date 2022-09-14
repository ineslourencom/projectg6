package switchtwentyone.project.domain.aggregates.task;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;

import static org.junit.jupiter.api.Assertions.*;

class TaskTypeTest {

    @Test
    void testSameObjectIsEqual() {
        TaskType taskType = TaskType.meeting();

        assertEquals(taskType, taskType);
    }

    @Test
    void testObjectsAreEqualSameCategory() {
        TaskType taskTypeOne = TaskType.deployment();
        TaskType taskTypeTwo = TaskType.deployment();

        assertEquals(taskTypeOne, taskTypeTwo);

    }

    @Test
    void testObjectsAreNotEqualDifferentCategory() {
        TaskType taskTypeOne = TaskType.design();
        TaskType taskTypeTwo = TaskType.documentation();

        assertNotEquals(taskTypeOne, taskTypeTwo);
    }
    @Test
    void testNotEqualToDifferentObject() {

        TaskType taskTypeOne = TaskType.testing();
        ResourceID testTwo = new ResourceID(1);


        assertNotEquals(taskTypeOne, testTwo);
    }

    @Test
    void testNotEqualToNull() {
        TaskType taskTypeOne = TaskType.implementation();
        assertNotEquals(taskTypeOne, null);
    }

    @Test
    void testSameObjectHasSameHash() {
        TaskType taskTypeOne = TaskType.deployment();
        TaskType taskTypeTwo = TaskType.deployment();

        assertEquals(taskTypeOne.hashCode(), taskTypeTwo.hashCode());

    }

    @Test
    void testDifferentObjectsHaveDifferentHash() {
        TaskType taskTypeOne = TaskType.deployment();
        TaskType taskTypeTwo = TaskType.testing();

        assertNotEquals(taskTypeOne.hashCode(), taskTypeTwo.hashCode());
    }


    @Test
    void getTaskTypeAsString() {
        TaskType taskTypeOne = TaskType.deployment();
        String expected = "Deployment";

        String result = taskTypeOne.getTaskTypeAsString();

        assertEquals(expected, result);
    }
}