package switchtwentyone.project.datamodel;

import lombok.*;
import switchtwentyone.project.domain.aggregates.task.TaskID;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskJPA {

    @EmbeddedId
    TaskID taskID;

    private String taskName;
    private String taskDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private int effortEstimate;
    private String taskType;
    private String taskStatus;
    private int hoursSpent;
    private double percentageOfExecution;

    //containerID is either a SprintID or a UserStoryID (i.e. from another aggregate)
    private String containerID;

    @ElementCollection
    private List<String> effortUpdate = new ArrayList<>();

    //associatedResource is a ResourceID (i.e. from another aggregate)
    private String associatedResource;

    private String taskPrecedenceId;

    public TaskID getTaskID() {
        return taskID;
    }


    public String getTaskName() {
        return taskName;
    }


    public String getTaskDescription() {
        return taskDescription;
    }


    public LocalDate getStartDate() {
        return startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }


    public int getEffortEstimate() {
        return effortEstimate;
    }


    public String getTaskType() {
        return taskType;
    }


    public String getTaskStatus() {
        return taskStatus;
    }


    public int getHoursSpent() {
        return hoursSpent;
    }


    public double getPercentageOfExecution() {
        return percentageOfExecution;
    }


    public String getContainerID() {
        return containerID;
    }


    public List<String> getEffortUpdate() {
        return new ArrayList<>(effortUpdate);
    }


    public String getAssociatedResource() {
        return associatedResource;
    }

    public void setAssociatedResource(String associatedResource) {
        this.associatedResource = associatedResource;
    }

    public String getTaskPrecedenceId() {
        return taskPrecedenceId;
    }


    public TaskJPA(TaskID taskID, String taskName, String taskDescription, LocalDate startDate,
                   LocalDate endDate, int effortEstimate, String taskType,
                   String taskStatus, int hoursSpent, double percentageOfExecution, String containerID,
                   List<String> effortUpdates, String associatedResource, String taskPrecedenceId) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.effortEstimate = effortEstimate;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.hoursSpent = hoursSpent;
        this.percentageOfExecution = percentageOfExecution;
        this.containerID = containerID;
        this.effortUpdate = new ArrayList<>(effortUpdates);
        this.associatedResource = associatedResource;
        this.taskPrecedenceId = taskPrecedenceId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskJPA taskJPA = (TaskJPA) o;
        return Objects.equals(taskID, taskJPA.taskID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskID, taskName, taskDescription, startDate, endDate, effortEstimate, taskType, taskStatus, hoursSpent, percentageOfExecution, containerID, effortUpdate, associatedResource, taskPrecedenceId);
    }
}
