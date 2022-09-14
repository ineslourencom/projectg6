package switchtwentyone.project.domain.aggregates.task;

import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

public class TaskStatus implements ValueObject<TaskStatus> {
    Status taskStatus;

    private TaskStatus(Status status) {
        this.taskStatus = status;
    }

    public static TaskStatus planned() {
        return new TaskStatus(Status.Planned);
    }

    public static TaskStatus running() {
        return new TaskStatus(Status.Running);
    }

    public static TaskStatus finished() {
        return new TaskStatus(Status.Finished);
    }

    public static TaskStatus blocked() {
        return new TaskStatus(Status.Blocked);
    }

    public static TaskStatus dbConverter(String dbName){
        return new TaskStatus(Status.fromDbName(dbName));
    }

    public String getTaskStatusAsString(){
        return this.taskStatus.toString();
    }

    @Override
    public boolean sameValueAs(TaskStatus other) {
        return other != null && this.taskStatus.equals(other.taskStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStatus that = (TaskStatus) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskStatus);
    }


}
