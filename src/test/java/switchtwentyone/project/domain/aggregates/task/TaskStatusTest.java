package switchtwentyone.project.domain.aggregates.task;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.sprint.ScrumBoardCategory;

import static org.junit.jupiter.api.Assertions.*;

class TaskStatusTest {
    @Test
    void testSameObjectIsEqual() {
        TaskStatus taskStatus = TaskStatus.planned();

        assertEquals(taskStatus, taskStatus);
    }

    @Test
    void testObjectsAreEqualSameCategory() {
        TaskStatus taskStatusOne = TaskStatus.planned();
        TaskStatus taskStatusTwo = TaskStatus.planned();

        assertEquals(taskStatusOne, taskStatusTwo);

    }

    @Test
    void testObjectsAreNotEqualDifferentCategory() {
        TaskStatus taskStatusOne = TaskStatus.blocked();
        TaskStatus taskStatusTwo = TaskStatus.finished();

        assertNotEquals(taskStatusOne, taskStatusTwo);
    }
    @Test
    void testNotEqualToDifferentObject() {

        TaskStatus taskStatusOne = TaskStatus.running();
        ResourceID testTwo = new ResourceID(1);


        assertNotEquals(taskStatusOne, testTwo);
    }

    @Test
    void testNotEqualToNull() {
        TaskStatus taskStatusOne = TaskStatus.running();
      assertNotEquals(taskStatusOne, null);
    }

    @Test
    void testSameObjectHasSameHash() {
        TaskStatus taskStatusOne = TaskStatus.planned();
        TaskStatus taskStatusTwo = TaskStatus.planned();

        assertEquals(taskStatusOne.hashCode(), taskStatusTwo.hashCode());

    }

    @Test
    void testDifferentObjectsHaveDifferentHash() {
        TaskStatus taskStatusOne = TaskStatus.planned();
        TaskStatus taskStatusTwo = TaskStatus.blocked();

        assertNotEquals(taskStatusOne.hashCode(), taskStatusTwo.hashCode());
    }


    @Test
    void getTaskStatusAsString() {
        TaskStatus taskStatusOne = TaskStatus.planned();

        String expected = "Planned";

        String result = taskStatusOne.getTaskStatusAsString();

        assertEquals(expected, result);
    }
    @Test
    void getTaskStatusRunningAsString() {
        TaskStatus taskStatusOne = TaskStatus.running();

        String expected = "Running";

        String result = taskStatusOne.getTaskStatusAsString();

        assertEquals(expected, result);
    }

    @Test
    void getTaskStatusFinishedAsString() {
        TaskStatus taskStatusOne = TaskStatus.finished();

        String expected = "Finished";

        String result = taskStatusOne.getTaskStatusAsString();

        assertEquals(expected, result);
    }
    @Test
    void getTaskStatusBlockedAsString() {
        TaskStatus taskStatusOne = TaskStatus.blocked();

        String expected = "Blocked";

        String result = taskStatusOne.getTaskStatusAsString();

        assertEquals(expected, result);
    }

    @Test
    void getTaskStatusAsStringError() {
        TaskStatus taskStatusOne = TaskStatus.blocked();

        String expected = "Bananas";

        String result = taskStatusOne.getTaskStatusAsString();

        assertThrows(IllegalArgumentException.class, () -> TaskStatus.dbConverter("Banana"));
    }
}