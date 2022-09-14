package switchtwentyone.project.domain.aggregates.task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class TaskIDTest {

    @Test
    void taskIDWithSameIDAreEquals(){
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);
        TaskID idTwo = TaskID.createTaskID(taskID);

        assertEquals(idOne, idTwo);
    }

    @Test
    void taskIDWithDifferentIDAreDifferent() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        String taskIDTwo = "61ead7c9-6930-4191-9841-46f8d45535a3";
        TaskID idOne = TaskID.createTaskID(taskID);
        TaskID idTwo = TaskID.createTaskID(taskIDTwo);

        assertNotEquals(idOne, idTwo);
    }

    @Test
    void taskIDEqualsToItself() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);
        assertEquals(idOne, idOne);
    }

    @Test
    void taskIDWithSameIDHaveSameHashCode() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);
        assertEquals(idOne.hashCode(), idOne.hashCode());

    }

    @Test
    void taskWithDifferentIDHaveDifferentHashCode() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        String taskIDTwo = "61ead7c9-6930-4191-9841-46f8d45535a3";
        TaskID idOne = TaskID.createTaskID(taskID);
        TaskID idTwo = TaskID.createTaskID(taskIDTwo);

        assertNotEquals(idOne.hashCode(), idTwo.hashCode());

    }

    @Test
    void taskIDNotEqualsToOtherObject() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);
        Object object = new Object();

        assertNotEquals(idOne, object);
    }

    @Test
    void SameIdentityAsNull() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);

        boolean result = idOne.sameValueAs(null);

        assertFalse(result);

    }

    @Test
    void EqualsToNull() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);

        assertNotEquals(taskID, null);
    }

}