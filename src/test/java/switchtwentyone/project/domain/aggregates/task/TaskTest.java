package switchtwentyone.project.domain.aggregates.task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.valueObjects.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskTest {

    @Test
    void taskWithSameIDAreEquals() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Text taskDescriptionTwo = Text.write("Example Two");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        //TODO - change this test to unitary - equals cannot be mocked
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);;
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        Task taskTwo = new Task.TaskBuilder(taskID, taskName, taskDescriptionTwo, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).build();

        assertEquals(taskOne, taskTwo);
    }

    @Test
    void taskWithDifferentIDAreDifferent() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        //TODO - change this test to unitary - equals cannot be mocked
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);
        String idTwo = "61ead7c9-6930-4191-9841-46f8d45535a3";
        TaskID taskIDTwo = TaskID.createTaskID(idTwo);
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        Task taskTwo = new Task.TaskBuilder(taskIDTwo, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).build();

        assertNotEquals(taskOne, taskTwo);
    }

    @Test
    void taskEqualsToItself() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        //TODO - change this test to unitary - equals cannot be mocked
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);;
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        assertEquals(taskOne, taskOne);
    }

    @Test
    void taskWithSameIDHaveSameHashCode() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Text taskDescriptionTwo = Text.write("Example Two");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        //TODO - change this test to unitary - equals cannot be mocked
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);;
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        Task taskTwo = new Task.TaskBuilder(taskID, taskName, taskDescriptionTwo, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).build();

        assertEquals(taskOne.hashCode(), taskTwo.hashCode());
    }

    @Test
    void tasksWithDifferentIDHaveDifferentHashCode() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        //TODO - change this test to unitary - equals cannot be mocked
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);
        String idTwo = "61ead7c9-6930-4191-9841-46f8d45535a3";
        TaskID taskIDTwo = TaskID.createTaskID(idTwo);
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        Task taskTwo = new Task.TaskBuilder(taskIDTwo, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).build();

        assertNotEquals(taskOne.hashCode(), taskTwo.hashCode());
    }

    @Test
    void taskNotEqualsToOtherObject() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        //TODO - change this test to unitary - equals cannot be mocked
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        Object object = new Object();

        assertNotEquals(taskOne, object);
    }

    @Test
    void SameIdentityAsNull() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        //TODO - change this test to unitary - equals cannot be mocked
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);
        String idTwo = "61ead7c9-6930-4191-9841-46f8d45535a3";
        TaskID taskIDTwo = TaskID.createTaskID(idTwo);
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        boolean expected = taskOne.sameIdentityAs(null);

        assertFalse(expected);
    }

    @Test
    void EqualsToNull() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        //TODO - change this test to unitary - equals cannot be mocked
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);
        String idTwo = "61ead7c9-6930-4191-9841-46f8d45535a3";
        TaskID taskIDTwo = TaskID.createTaskID(idTwo);
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        assertNotEquals(null, taskOne);
    }

    @Test
    void setEffortUpdate_Test_success() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effortUpdate = EffortUpdate.of(time, hoursSpent, comment, submitter);
        List<EffortUpdate> expected = new ArrayList<>();
        expected.add(effortUpdate);
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        taskOne.addEffortUpdate(effortUpdate);
        List<EffortUpdate> result = taskOne.getEffortUpdates();

        assertEquals(expected, result);
    }

    @Test
    void setEffortUpdate_Test_fail() {
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);
        LocalDateTime timeOne = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effortUpdateOne = EffortUpdate.of(timeOne, hoursSpent, comment, submitter);
        LocalDateTime timeTwo = LocalDateTime.now().minusSeconds(2);
        EffortUpdate effortUpdateTwo = EffortUpdate.of(timeTwo, hoursSpent, comment, submitter);
        List<EffortUpdate> expected = new ArrayList<>();
        expected.add(effortUpdateTwo);
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        taskOne.addEffortUpdate(effortUpdateOne);
        boolean result = expected.equals(taskOne.getEffortUpdates());

        assertFalse(result);
    }

    @Test
    void addEffortUpdate_Test_success() {
        //arrange
        //task data
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);;
        //effortUpdate data
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effortUpdateOne = EffortUpdate.of(time, hoursSpent, comment, submitter);
        LocalDateTime timeTwo = LocalDateTime.now().minusSeconds(2);
        EffortUpdate effortUpdateTwo = EffortUpdate.of(timeTwo, hoursSpent, comment, submitter);
        //expected build
        List<EffortUpdate> expected = new ArrayList<>();
        expected.add(effortUpdateOne);
        expected.add(effortUpdateTwo);
        //task build
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        //act
        taskOne.addEffortUpdate(effortUpdateOne);
        taskOne.addEffortUpdate(effortUpdateTwo);
        List<EffortUpdate> result = taskOne.getEffortUpdates();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void addEffortUpdate_Test_success_effortTwoRepeatedNotAdded() {
        //arrange
        //task data
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);;
        //effortUpdate data
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effortUpdateOne = EffortUpdate.of(time, hoursSpent, comment, submitter);
        EffortUpdate effortUpdateTwo = effortUpdateOne;
        LocalDateTime timeThree = LocalDateTime.now().minusSeconds(4);
        EffortUpdate effortUpdateThree = EffortUpdate.of(timeThree, hoursSpent, comment, submitter);
        //expected build
        List<EffortUpdate> expected = new ArrayList<>();
        expected.add(effortUpdateOne);
        expected.add(effortUpdateThree);
        //task build
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        //act
        taskOne.addEffortUpdate(effortUpdateOne);
        taskOne.addEffortUpdate(effortUpdateTwo);
        taskOne.addEffortUpdate(effortUpdateThree);
        boolean result = expected.equals(taskOne.getEffortUpdates());

        //assert
        assertTrue(result);
    }

    @Test
    void addEffortUpdate_Test_fail_effortRepeatedNotAdded() {
        //arrange
        //task data
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        ResourceID associatedResource = mock(ResourceID.class);
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);;
        //effortUpdate data
        LocalDateTime timeOne = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effortUpdateOne = EffortUpdate.of(timeOne, hoursSpent, comment, submitter);
        EffortUpdate effortUpdateTwo = effortUpdateOne;
        //expected build
        List<EffortUpdate> expected = new ArrayList<>();
        expected.add(effortUpdateOne);
        expected.add(effortUpdateTwo);
        //task build
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        //act
        Optional<Task> addOne = taskOne.addEffortUpdate(effortUpdateOne);
        Optional<Task> addTwo = taskOne.addEffortUpdate(effortUpdateTwo);
        boolean result = expected.equals(taskOne.getEffortUpdates());

        //assert
        assertFalse(result);
        assertFalse(addOne.isEmpty());
        assertFalse(addTwo.isPresent());
    }


    @Test
    void findBelongsToSprint(){
        NoNumberNoSymbolString taskName = mock(NoNumberNoSymbolString.class);
        Text taskDescriptionOne = Text.write("Example One");
        Period taskPeriod = mock(Period.class);
        FibonacciNumber effortEstimate = FibonacciNumber.of(5);
        TaskType taskType = TaskType.meeting();
        ContainerID containerID = mock(ContainerID.class);
        when(containerID.getIDForTask()).thenReturn("Sp-1.100");
        ResourceID associatedResource = mock(ResourceID.class);
        String idOne = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID taskID = TaskID.createTaskID(idOne);;
        Task taskOne = new Task.TaskBuilder(taskID, taskName, taskDescriptionOne, taskType, containerID)
                .period(taskPeriod).associatedResource(associatedResource).effortEstimate(effortEstimate).build();

        String expected = "Sprint";
        String result = taskOne.findBelongsTo();

        assertEquals(expected, result);


    }


}