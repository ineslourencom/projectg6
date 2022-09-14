package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.task.Task;
import switchtwentyone.project.dto.TaskCreationReturnedDTO;
import switchtwentyone.project.dto.TaskStatusDTO;

public class TaskMapper {

    private TaskMapper() {
    }

    public static TaskStatusDTO toDTOForStatus(Task task) {
        TaskStatusDTO dto = new TaskStatusDTO();
        dto.taskNumber = task.getTaskIdentityNumber().getTaskIdentityNumber();
        dto.taskName = task.getTaskNameAsString();
        dto.belongsTo = task.findBelongsTo();
        dto.containerId = task.getContainerID().getIDForTask();
        dto.status = task.getTaskStatusAsString();
        dto.percOfExec = task.getPercentageOfExecution();

        return dto;
    }

    public static TaskCreationReturnedDTO toDTOReturnedUponCreation(Task task) {
        TaskCreationReturnedDTO dto = new TaskCreationReturnedDTO();
        dto.taskID = task.getIDAsInt();
        dto.name = task.getTaskNameAsString();
        dto.description = task.getTaskDescriptionAsString();
        dto.startDate = task.getStartDate();
        dto.endDate = task.getEndDate();
        dto.effortEstimate = task.getEffortEstimateAsInt();
        dto.type = task.getTaskTypeAsString();
        dto.resourceID = task.getAssociatedResource();
        dto.containerID = task.getContainerID().getID();
        return dto;
    }

}
