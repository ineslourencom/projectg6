package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.task.ContainerID;
import switchtwentyone.project.domain.aggregates.task.Task;
import switchtwentyone.project.domain.aggregates.task.TaskID;
import switchtwentyone.project.dto.TaskStatusDTO;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskMapperTest {

    @Test
    void toDTOForStatus() {
        TaskStatusDTO expected = new TaskStatusDTO();
        expected.taskNumber = "6530fce2-ed9a-11ec-8ea0-0242ac120002";
        expected.taskName = "task one";
        expected.belongsTo =  "Sprint";
        expected.containerId = "Sp-1.100";
        expected.status = "Planned";
        expected.percOfExec = 80.0;

        Task task = mock(Task.class);
        ContainerID containerID = mock(ContainerID.class);
        when(containerID.getID()).thenReturn(1.100);
        TaskID taskId = TaskID.createTaskID("6530fce2-ed9a-11ec-8ea0-0242ac120002");
        when(task.getTaskIdentityNumber()).thenReturn(taskId);
        when(task.getTaskNameAsString()).thenReturn("task one");
        when(task.findBelongsTo()).thenReturn("Sprint");
        when(task.getContainerID()).thenReturn(containerID);
        when(task.getTaskStatusAsString()).thenReturn("Planned");
        when(task.getPercentageOfExecution()).thenReturn(80.0);

        TaskStatusDTO result = TaskMapper.toDTOForStatus(task);
        assertEquals(expected, result);


    }
}