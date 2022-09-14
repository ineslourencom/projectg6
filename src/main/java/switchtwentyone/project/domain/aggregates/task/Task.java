package switchtwentyone.project.domain.aggregates.task;

import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.shared.Entity;
import switchtwentyone.project.domain.valueObjects.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Task implements Entity<Task> {

    private TaskID taskIdentityNumber;
    private NoNumberNoSymbolString taskName;
    private Text taskDescription;
    private Period taskPeriod;
    private FibonacciNumber effortEstimate;
    private TaskType taskType;
    private TaskStatus taskStatus;
    private NonNegativeNumber hoursSpent;
    private LimitedPercentage percentageOfExecution;
    private ContainerID containerID;
    private List<EffortUpdate> effortUpdates;
    private ResourceID associatedResource;
    //TODO: TaskPrecedence covered by TaskId?
    private TaskID taskPrecedenceId;


    private Task(TaskBuilder builder) {
        this.taskIdentityNumber = builder.taskIdentityNumber;
        this.taskName = builder.taskName;
        this.taskDescription = builder.taskDescription;
        this.taskPeriod = builder.taskPeriod;
        this.effortEstimate = builder.effortEstimate;
        this.taskType = builder.taskType;
        this.taskStatus = builder.taskStatus;
        this.hoursSpent = builder.hoursSpent;
        this.percentageOfExecution = builder.percentageOfExecution;
        this.effortUpdates = new ArrayList<>();
        this.associatedResource = builder.associatedResource;
        this.containerID = builder.containerID;
        this.taskPrecedenceId = builder.taskPrecedenceId;
    }

    public int getIDAsInt(){
        return this.taskIdentityNumber.hashCode();
    }

    public TaskID getTaskIdentityNumber() {
        return taskIdentityNumber;
    }

    public String getTaskNameAsString() {
        return taskName.getValue();
    }

    public String getTaskDescriptionAsString() {
        return taskDescription.getValue();
    }

    public LocalDate getStartDate() {
        if (this.taskPeriod == null) {
            return null;
        } else {
            return taskPeriod.getStartingDate();
        }
    }

    public LocalDate getEndDate() {
        if (this.taskPeriod == null) {
            return null;
        } else {
        return taskPeriod.getEndingDate();
        }
    }

    public int getEffortEstimateAsInt() {
        if (this.effortEstimate == null) {
            return 0;
        } else {
            return effortEstimate.getNumber();
        }
    }

    public String getTaskTypeAsString() {
        return taskType.getTaskTypeAsString();
    }

    public String getTaskStatusAsString() {
        return taskStatus.getTaskStatusAsString();
    }

    public int getHoursSpentAsInt() {
        return hoursSpent.getNumber();
    }

    public double getPercentageOfExecution() {
        return percentageOfExecution.getPercentValue();
    }

    public ContainerID getContainerID() {
        return this.containerID;
    }

    public String getContainerIDAsString() {
        return this.containerID.getIDForTask();
    }

    public List<EffortUpdate> getEffortUpdates() {
        return new ArrayList<>(effortUpdates);
    }

    public String getTaskPrecedenceId() {
        if (taskPrecedenceId != null) {
            return taskPrecedenceId.getTaskIdentityNumber();
        } else return null;
    }

    public int getAssociatedResourceAsInt() {
        return associatedResource.getResourceID();
    }

    public String getAssociatedResource() {
        if (associatedResource != null) {
            return String.valueOf(associatedResource.getResourceID());
        } else return null;
    }

    /**
     * Setter method for EffortUpdate.
     * It substitutes the current content of the attribute effort update list by
     * the new given EffortUpdate list.
     *
     * @param effortUpdate new list of effort updates
     * @return the updated Task
     */
    public Task setEffortUpdate(List<EffortUpdate> effortUpdate) {
        this.effortUpdates = new ArrayList<>(effortUpdate);
        return this;
    }

    public void setHoursSpent(NonNegativeNumber hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public void setPercentageOfExecution(LimitedPercentage percentageOfExecution) {
        this.percentageOfExecution = percentageOfExecution;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * Method for adding new EffortUpdates to the attribute list of Task.
     *
     * @param effortUpdate new effort update to be added
     * @return Optional with updated task if successful, empty optional if otherwise
     */
    public Optional<Task> addEffortUpdate(EffortUpdate effortUpdate) {
        Optional<Task> answer = Optional.empty();
        boolean unique = true;
        for (EffortUpdate e : this.getEffortUpdates()) {
            if (e.equals(effortUpdate)) {
                unique = false;
            }
        }
        if (unique) {
            this.effortUpdates.add(effortUpdate);
            answer = Optional.of(this);
        }
        return answer;
    }


    public String findBelongsTo() {

        String id = this.containerID.getIDForTask();
        String result;
        if (id.contains("Sp-")) {
            result = "Sprint";
        } else {
            result = "User Story";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return task.taskIdentityNumber != null && this.taskIdentityNumber.sameValueAs(task.taskIdentityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskIdentityNumber);
    }

    @Override
    public boolean sameIdentityAs(Task other) {
        return other != null && this.taskIdentityNumber.sameValueAs(other.taskIdentityNumber);
    }

    public static class TaskBuilder {
        // mandatory attributes
        private TaskID taskIdentityNumber;
        private NoNumberNoSymbolString taskName;
        private Text taskDescription;
        private TaskType taskType;
        private TaskStatus taskStatus;
        private NonNegativeNumber hoursSpent;
        private LimitedPercentage percentageOfExecution;
        private ContainerID containerID;
        private TaskID taskPrecedenceId;

        // optional attributes
        private Period taskPeriod;
        private FibonacciNumber effortEstimate;
        private ResourceID associatedResource;


        public TaskBuilder(TaskID number, NoNumberNoSymbolString name, Text description, TaskType taskType, ContainerID containerID) {
            this.taskIdentityNumber = number;
            this.taskName = name;
            this.taskDescription = description;
            this.taskType = taskType;
            this.containerID = containerID;
            this.taskStatus = TaskStatus.planned();
            this.hoursSpent = NonNegativeNumber.of(0);
            this.percentageOfExecution = LimitedPercentage.percentage(0.0);
        }

        public TaskBuilder period(Period period) {
            this.taskPeriod = period;
            return this;
        }

        public TaskBuilder effortEstimate(FibonacciNumber effortEstimate) {
            this.effortEstimate = effortEstimate;
            return this;
        }

        public TaskBuilder associatedResource(ResourceID associatedResource) {
            this.associatedResource = associatedResource;
            return this;
        }

        public TaskBuilder parentTask(TaskID parentTask) {
            this.taskPrecedenceId = parentTask;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
