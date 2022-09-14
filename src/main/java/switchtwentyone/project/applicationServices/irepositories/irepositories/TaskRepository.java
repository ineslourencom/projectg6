package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task saveTask(Task task);

    List<Task> findAllByContainerID(String containerId);

    Optional<Task> findTaskByID(String taskID);
}
