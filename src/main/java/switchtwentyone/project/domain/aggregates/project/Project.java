package switchtwentyone.project.domain.aggregates.project;

import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintCreatable;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryCreator;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Entity;
import switchtwentyone.project.domain.shared.IStatus;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.*;
import switchtwentyone.project.dto.ProjectInfoDomainDTO;

import java.time.LocalDate;
import java.util.Objects;

public class Project implements Entity<Project> {

    private ProjectID projectID;
    private Nameable name;
    private Describable description;
    private Period period;
    private PositiveNumber sprintDuration;
    private IStatus status;
    private PositiveNumber budget;
    private BusinesSectorID businessSectorID;
    private CustomerID customerID;
    private ProjectTypologyID projectTypologyID;
    private PositiveNumber plannedSprints;

    public Project(){}

    public Project(final ProjectID projectID, final Nameable name, final Describable description,
                   final Period period, final PositiveNumber sprintDuration,
                   final PositiveNumber budget, final CustomerID customerID, final BusinesSectorID businessSectorID,
                   final ProjectTypologyID projectTypologyID) {
        this.projectID = projectID;
        this.name = name;
        this.description = description;
        this.period = period;
        this.sprintDuration = sprintDuration;
        this.budget = budget;
        this.status = PredefinedStatus.of("Planned");
        this.customerID = customerID;
        this.businessSectorID = businessSectorID;
        this.projectTypologyID = projectTypologyID;
    }



    public BusinesSectorID getBusinessSectorID() {
        return businessSectorID;
    }

    public CustomerID getCustomerID() {
        return customerID;
    }

    public ProjectTypologyID getProjectTypologyID() {
        return projectTypologyID;
    }

    public LocalDate getStartDate() {
        return period.getStartingDate();
    }

    public LocalDate getEndDate() {
        return period.getEndingDate();
    }

    public String getNameAsString() {
        return this.name.getValue();
    }

    public int getIDAsInt() {
        return this.projectID.getProjectID();
    }

    public ProjectID getID() { return new ProjectID(this.projectID.getProjectID());}

    public String getDescriptionAsString() {
        return this.description.getValue();
    }

    public int getPlannedSprintsAsInt() {
        if (this.plannedSprints == null) {
            return 0;
        }
        return this.plannedSprints.getNumber();
    }

    public int getSprintDurationAsInt() {
        return this.sprintDuration.getNumber();
    }

    public String getStatusAsString() {
        return this.status.getValueAsString();
    }

    public int getBudget() { return this.budget.getNumber(); }

    public int getCustomer() { return this.customerID.getCustomerIDAsInt(); }

    public String getBusinessSector() { return this.businessSectorID.getBusinessSectorIDAsSting(); }

    public String getProjectTypologyIDAsString() { return this.projectTypologyID.getNameAsString(); }

    public void setStatus(IStatus status) {
        this.status = status;
    }

    public void setPlannedSprints(PositiveNumber plannedSprints) {
        this.plannedSprints = plannedSprints;
    }


    public UserStory createUserStory(Text statement, Text detail, UserStoryID usID,
                                     int priority, PositiveNumber usNumber){
       return UserStoryCreator.createUserStory(usID, usNumber, statement, detail, priority, this.projectID);
    }

    public Sprint createSprint (SprintID sprintID, LocalDate startDate, PositiveNumber sprintNumber){
        return SprintCreatable.createSprint(sprintID, startDate, this.projectID, sprintNumber, this.sprintDuration);
    }



    @Override
    public boolean sameIdentityAs(Project other) {
        return other != null && this.projectID.sameValueAs(other.projectID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return project.projectID != null && this.sameIdentityAs(project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID);
    }

    public boolean hasID(ProjectID id) {
        return this.projectID.sameValueAs(id);

    }

    public void edit(ProjectInfoDomainDTO newDetails) {
        this.description = newDetails.description;
        this.period = newDetails.period;
        this.sprintDuration = newDetails.sprintDuration;
        this.status = newDetails.status;
       this.plannedSprints = newDetails.plannedSprints;

    }

    public boolean containsPeriod(Period period) {
        return this.period.containsPeriod(period);
    }
}
