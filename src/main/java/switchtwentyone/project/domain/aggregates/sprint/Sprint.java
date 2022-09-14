package switchtwentyone.project.domain.aggregates.sprint;

import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.shared.Entity;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sprint implements Entity<Sprint> {

    private SprintID sprintID;
    private PositiveNumber sprintNumber;
    private ProjectID projectID;
    private LocalDate startDate;
    private PositiveNumber sprintDuration;
    private List<SprintBacklogItem> sprintBacklogItems;

    public Sprint() {
        //this is an empty constructor
    }

    public Sprint(SprintID sprintID, LocalDate startDate, ProjectID projectID, PositiveNumber sprintNumber, PositiveNumber sprintDuration) {
        this.sprintID = sprintID;
        this.startDate = startDate;
        this.projectID = projectID;
        this.sprintNumber = sprintNumber;
        this.sprintDuration = sprintDuration;
        this.sprintBacklogItems = new ArrayList<>();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return this.startDate.plusWeeks(sprintDuration.getNumber());
    }

    public double getSprintIDDouble() {
        return this.sprintID.getID();
    }

    public int getSprintNumber() {
        return this.sprintNumber.getNumber();
    }

    public int getProjectID() {
        return this.projectID.getProjectID();
    }

    public PositiveNumber getSprintDuration() {
        return sprintDuration;
    }

    public SprintID getSprintID() {
        return SprintID.ofDouble(this.getSprintIDDouble());
    }

    public List<SprintBacklogItem> getSprintBacklogItems() {
        return List.copyOf(this.sprintBacklogItems);
    }

    public void setSprintBacklogItems(List<SprintBacklogItem> sprintBacklogItems) {
        this.sprintBacklogItems = new ArrayList<>(sprintBacklogItems);
    }

    public boolean hasID(SprintID id) {
        return this.sprintID.sameValueAs(id);
    }

    /**
     * Method to check if the code passed is higher that the Sprint's.
     */
    public boolean isCodeHigher(int code) {
        boolean result = false;
        if (this.sprintNumber.getNumber() < code) {
            result = true;
        }
        return result;
    }

    public SprintBacklogItem addUSToSprintBacklog(UserStoryID usID, FibonacciNumber effortEstimate) {
        SprintBacklogItemID newItemID = SprintBacklogItemID.createSprintBacklogItemID();
        SprintBacklogItem sbi = SprintBacklogItemCreator.createSprintBacklogItem(newItemID, usID, effortEstimate);
        this.sprintBacklogItems.add(sbi);
        return sbi;
    }

    private boolean hasStarted() {
        boolean result = false;
        if (this.startDate.isBefore(LocalDate.now()) || this.startDate.isEqual(LocalDate.now())) {
            result = true;
        }
        return result;
    }

    private boolean hasEnded() {
        boolean result = false;
        if (this.startDate.plusDays(this.sprintDuration.getNumber() * 7L).isBefore(LocalDate.now()) || this.startDate.plusDays(this.sprintDuration.getNumber() * 7L).isEqual(LocalDate.now())) {
            result = true;
        }
        return result;
    }

    public boolean isRunning() {
        boolean result = false;
        if (this.hasStarted() && !this.hasEnded()) {
            result = true;
        }
        return result;
    }

    public boolean belongsToProject(ProjectID projectID) {
        boolean result = false;
        if (this.projectID.equals(projectID)) {
            result = true;
        }
        return result;
    }


    public boolean isPeriodInsideProject(Period period) {
        boolean periodIsInsideProject = true;
        if (period != null) {
            LocalDate periodStartDate = period.getStartingDate();
            LocalDate periodEndDate = period.getEndingDate();

            if ((periodStartDate.isBefore(this.startDate) ||
                    (periodEndDate != null && periodEndDate.isAfter(this.startDate.plusDays(this.sprintDuration.getNumber() * 7L))))) {
                periodIsInsideProject = false;
            }
        }
        return periodIsInsideProject;
    }
    public void setNewStartDate(LocalDate newStartDate) {
       this.startDate = newStartDate;
         }



    @Override
    public boolean sameIdentityAs(Sprint other) {
        return other != null && this.sprintID.sameValueAs(other.sprintID);
    }

    /**
     * @param object to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(Sprint)
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Sprint sprint = (Sprint) object;
        return Objects.equals(sprintID, sprint.sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, sprintDuration, sprintNumber, sprintID, startDate);
    }
}



