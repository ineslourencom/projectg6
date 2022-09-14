package switchtwentyone.project.datamodel.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.aggregates.task.*;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.*;
import switchtwentyone.project.datamodel.TaskJPA;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskDomainDataAssembler {

    @Autowired
    TaskFactory taskFactory;

    public TaskJPA toData(Task task) {
        List<String> effortUpdates = new ArrayList<>();
        String delimiter = "|";

        TaskID taskID = task.getTaskIdentityNumber();
        String taskName = task.getTaskNameAsString();
        String taskDescription = task.getTaskDescriptionAsString();
        LocalDate startDate = task.getStartDate();
        LocalDate endDate = task.getEndDate();
        int effortEstimate = task.getEffortEstimateAsInt();
        String taskType = task.getTaskTypeAsString();
        String taskStatus = task.getTaskStatusAsString();
        int hoursSpent = task.getHoursSpentAsInt();
        Double percentageOfExecution = task.getPercentageOfExecution();
        String containerID = task.getContainerID().getIDForTask();
        for (EffortUpdate e : task.getEffortUpdates()) {
            effortUpdates.add(e.getDateTimeAsString()
                    + delimiter
                    + e.getHoursSpentAsString()
                    + delimiter
                    + e.getComment()
                    + delimiter
                    + e.getSubmitterAsString());
        }
        String associatedResource = task.getAssociatedResource();
        String taskPrecedenceId = task.getTaskPrecedenceId();

        return new TaskJPA(taskID, taskName, taskDescription, startDate,
                endDate, effortEstimate, taskType, taskStatus, hoursSpent,
                percentageOfExecution, containerID, effortUpdates, associatedResource, taskPrecedenceId);
    }

    public Task toDomain(TaskJPA taskJPA) {
        List<EffortUpdate> effortUpdates = new ArrayList<>();
        String[] stringArray;


        TaskID taskID = taskJPA.getTaskID();

        String name = taskJPA.getTaskName();
        NoNumberNoSymbolString taskName = NoNumberNoSymbolString.of(name);

        String description = taskJPA.getTaskDescription();
        Text taskDescription = Text.write(description);

        LocalDate startDate = taskJPA.getStartDate();
        LocalDate endDate = taskJPA.getEndDate();

        Period taskPeriod = null;
        if (startDate != null && endDate == null) {
            taskPeriod = Period.starting(startDate);
        } else if (startDate != null) {
            taskPeriod = Period.between(startDate, endDate);
        }


        int effortEstimate = taskJPA.getEffortEstimate();

        FibonacciNumber taskEffortEstimate = null;
        if (effortEstimate != 0) {
            taskEffortEstimate = FibonacciNumber.of(effortEstimate);
        }

        String type = taskJPA.getTaskType();
        TaskType taskType = TaskType.dbConverter(type);

        String status = taskJPA.getTaskStatus();
        TaskStatus taskStatus = TaskStatus.dbConverter(status);

        int hoursSpent = taskJPA.getHoursSpent();
        NonNegativeNumber taskHoursSpent = NonNegativeNumber.of(hoursSpent);

        double percentageOfExecution = taskJPA.getPercentageOfExecution();
        LimitedPercentage taskPercentageOfExecution = LimitedPercentage.percentage(percentageOfExecution);

        ContainerID containerID;
        if ("Sp".equals(taskJPA.getContainerID().substring(0, 2))) {
            containerID = SprintID.ofDouble(Double.parseDouble(taskJPA.getContainerID().substring(3)));
        } else {
            containerID = UserStoryID.ofDouble(Double.parseDouble(taskJPA.getContainerID().substring(3)));
        }

        for (String e : taskJPA.getEffortUpdate()) {
            stringArray = e.split("\\|");
            EffortUpdate effortUpdate = EffortUpdate.of(LocalDateTime.parse(stringArray[0]),
                    Double.parseDouble(stringArray[1]),
                    stringArray[2],
                    ResourceID.of(Integer.parseInt(stringArray[3])));
            effortUpdates.add(effortUpdate);
        }

        ResourceID associatedResource = null;
        if (taskJPA.getAssociatedResource() != null) {
            associatedResource = ResourceID.of(Integer.parseInt(taskJPA.getAssociatedResource()));
        }

        TaskID taskPrecedenceId = null;
        if (taskJPA.getTaskPrecedenceId() != null) {
            taskPrecedenceId = TaskID.createTaskID(taskJPA.getTaskPrecedenceId());
        }

        Task newTask = new Task.TaskBuilder(taskID, taskName, taskDescription, taskType, containerID).parentTask(taskPrecedenceId).period(taskPeriod).
                associatedResource(associatedResource).effortEstimate(taskEffortEstimate).build();

        newTask.setHoursSpent(taskHoursSpent);
        newTask.setPercentageOfExecution(taskPercentageOfExecution);
        newTask.setEffortUpdate(effortUpdates);
        newTask.setTaskStatus(taskStatus);

        return newTask;

    }

}
