package switchtwentyone.project.domain.aggregates.task;

import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class TaskID implements ValueObject<TaskID> {

    private UUID taskIdentityNumber;


    private TaskID() {
        this.taskIdentityNumber = UUID.randomUUID();
    }


    /**
     * Empty constructor for TaskID.
     * Required by @Embeddable annotation.
     */

    public static TaskID generate(){
        return new TaskID();
    }

    public static TaskID createTaskID(final String identity) {
        TaskID itemID = new TaskID();
        itemID.taskIdentityNumber = UUID.fromString(identity);
        return itemID;
    }

    public String getTaskIdentityNumber() {
        return taskIdentityNumber.toString();
    }

    @Override
    public boolean sameValueAs(TaskID other) {
         return other != null && this.taskIdentityNumber.equals(other.taskIdentityNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskID taskID1 = (TaskID) o;
        return this.sameValueAs(taskID1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskIdentityNumber);
    }
}
