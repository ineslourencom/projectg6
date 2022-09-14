package switchtwentyone.project.domain.aggregates.task;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.Text;

@Component
public class TaskFactory implements TaskCreatable {
    @Override
    public Task createTask(TaskID taskID, NoNumberNoSymbolString taskName, Text taskDescription, Period taskPeriod, FibonacciNumber effortEstimate, TaskType taskType, ContainerID containerID, ResourceID resourceID) {

        return new Task.TaskBuilder(taskID, taskName, taskDescription, taskType, containerID).period(taskPeriod).
                effortEstimate(effortEstimate).associatedResource(resourceID).build();

    }
}
