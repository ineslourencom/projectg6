package switchtwentyone.project.domain.aggregates.task;

import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.valueObjects.*;

public interface TaskCreatable {

    Task createTask (TaskID taskID, NoNumberNoSymbolString taskName, Text taskDescription, Period taskPeriod,
                     FibonacciNumber effortEstimate, TaskType taskType, ContainerID containerID, ResourceID resourceID);


}
