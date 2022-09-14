package switchtwentyone.project.datamodel;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.task.TaskID;
import switchtwentyone.project.dto.ProjectDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskJPATest {

    @Test
    void ensureEqualsItself() {
        List<String> effort = new ArrayList<>();

        TaskJPA task = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        assertEquals(task, task);
    }

    @Test
    void ensureEqualsEqualObject() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        TaskJPA taskTwo = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        assertEquals(taskOne, taskTwo);

    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        TaskJPA taskTwo = new TaskJPA(TaskID.createTaskID("61ead7c9-6930-4191-9841-46f8d45535a3"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");


        assertNotEquals(taskOne, taskTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        Object somethingElse = new Object();

        assertNotEquals(taskOne, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");


        assertNotEquals(taskOne, null);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        TaskJPA taskTwo = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        int hashOne = taskOne.hashCode();
        int hashTwo = taskTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        TaskJPA taskTwo = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        int hashOne = taskOne.hashCode();
        int hashTwo = taskTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }


    @Test
    void getTaskID() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        TaskID expected = TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");

        TaskID result = taskOne.getTaskID();

        assertEquals(expected, result);
    }

    @Test
    void getTaskName() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        String expected = "name1";

        String result = taskOne.getTaskName();

        assertEquals(expected, result);
    }

    @Test
    void getTaskDescription() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        String expected = "description";

        String result = taskOne.getTaskDescription();

        assertEquals(expected, result);
    }

    @Test
    void getStartDate() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        LocalDate expected = LocalDate.of(2022, 5, 22);

        LocalDate result = taskOne.getStartDate();

        assertEquals(expected, result);
    }

    @Test
    void getEndDate() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        LocalDate expected = LocalDate.of(2022, 6, 22);

        LocalDate result = taskOne.getEndDate();

        assertEquals(expected, result);
    }

    @Test
    void getEffortEstimate() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        int expected = 1;

        int result = taskOne.getEffortEstimate();

        assertEquals(expected, result);
    }

    @Test
    void getTaskType() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        String expected = "Type";

        String result = taskOne.getTaskType();

        assertEquals(expected, result);
    }

    @Test
    void getTaskStatus() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        String expected = "Status";

        String result = taskOne.getTaskStatus();

        assertEquals(expected, result);
    }

    @Test
    void getHoursSpent() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status",
                2, 0.5, "123", effort, "resource", "id");

        int expected = 2;

        int result = taskOne.getHoursSpent();

        assertEquals(expected, result);
    }

    @Test
    void getPercentageOfExecution() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status",
                2, 0.5, "123", effort, "resource", "id");

        double expected = 0.5;

        double result = taskOne.getPercentageOfExecution();

        assertEquals(expected, result);

    }

    @Test
    void getContainerID() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        String expected = "123";

        String result = taskOne.getContainerID();

        assertEquals(expected, result);
    }

    @Test
    void getEffortUpdate() {
        List<String> effort = new ArrayList<>();
        String One = "One";
        effort.add(One);

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");


        List<String> result = taskOne.getEffortUpdate();

        assertEquals(effort, result);
    }

    @Test
    void getAssociatedResource() {

        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        String expected = "resource";

        String result = taskOne.getAssociatedResource();

        assertEquals(expected, result);

    }

    @Test
    void setAssociatedResource() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        taskOne.setAssociatedResource("321");

        TaskJPA taskTwo = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "321", "id");


        assertEquals(taskOne, taskTwo);

    }

    @Test
    void getTaskPrecedenceId() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        String expected = "id";

        String result = taskOne.getTaskPrecedenceId();

        assertEquals(expected, result);

    }

    @Test
    void testEmptyConstructor() {
        TaskJPA taskOne = new TaskJPA();
        TaskJPA taskTwo = new TaskJPA();

        assertEquals(taskOne, taskTwo);

    }


    @Test
    void testToString() {
        List<String> effort = new ArrayList<>();

        TaskJPA taskOne = new TaskJPA(TaskID.createTaskID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4"), "name1", "description",
                LocalDate.of(2022, 5, 22), LocalDate.of(2022, 6, 22), 1, "Type", "Status", 2, 0.5, "123", effort, "resource", "id");

        String expected = "TaskJPA(taskID=switchtwentyone.project.domain.aggregates.task.TaskID@ecd39420, taskName=name1, taskDescription=description, startDate=2022-05-22, endDate=2022-06-22, effortEstimate=1, taskType=Type, taskStatus=Status, hoursSpent=2, percentageOfExecution=0.5, containerID=123, effortUpdate=[], associatedResource=resource, taskPrecedenceId=id)";

        String result = taskOne.toString();

        assertEquals(expected, result);
    }
}