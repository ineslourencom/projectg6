package switchtwentyone.project.domain.aggregates.resource;

import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.shared.Entity;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;

import java.time.LocalDate;
import java.util.Objects;

public class Resource implements Entity<Resource> {

    private final AccountID associatedAccount;
    private final ResourceID resourceID;
    private final Role associatedRole;
    private final LimitedPercentage percentageOfAllocation;
    private final ProjectID projectID;
    private final Monetary costPerHour;
    private Period allocationPeriod;


    public Resource(AccountID associatedAccount, ResourceID resourceID, Role associatedRole, Period allocationPeriod,
                    LimitedPercentage percentageOfAllocation, ProjectID projectID, Monetary costPerHour) {

        this.associatedAccount = associatedAccount;
        this.resourceID = resourceID;
        this.associatedRole = associatedRole;
        this.allocationPeriod = allocationPeriod;
        this.percentageOfAllocation = percentageOfAllocation;
        this.projectID = projectID;
        this.costPerHour = costPerHour;
    }

    @Override
    public boolean sameIdentityAs(Resource other) {
        return other != null && this.resourceID.sameValueAs(other.resourceID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return this.sameIdentityAs(resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceID);
    }

    public ProjectID getProjectID() {
        return projectID.clone();
    }


    public Role getAssociatedRole() {
        return new Role(Erole.valueOf(this.associatedRole.getRoleAsString()),
                NoNumberNoSymbolString.of(this.associatedRole.getDescriptionAsString()));
    }

    public AccountID getAssociatedAccount() {
        Email email = this.associatedAccount.getID();
        return AccountID.of(email);
    }

    public ResourceID getResourceID() {
        return new ResourceID(this.resourceID.getResourceID());
    }

    public Period getAllocationPeriod() {
        LocalDate startDate = this.allocationPeriod.getStartingDate();
        LocalDate endDate = this.allocationPeriod.getEndingDate();
        Period period;
        if (endDate == null) {
            period = Period.starting(startDate);
        } else {
            period = Period.between(startDate, endDate);
        }
        return period;
    }

    public LimitedPercentage getPercentageOfAllocation() {
        return LimitedPercentage.decimal(this.percentageOfAllocation.getPercentValue());
    }

    public Monetary getCostPerHour() {
        return Monetary.of(this.costPerHour.getAmount(), this.costPerHour.getCurrency());
    }

    public boolean hasEmail(Email email) {
        return this.associatedAccount.hasEmail(email);
    }

    public boolean isActive() {
        return this.allocationPeriod.containsDate(LocalDate.now());
    }

    public boolean hasRole(Role role) {
        return this.associatedRole.sameValueAs(role);
    }

    public boolean hasProjectID(ProjectID projectID) {
        return this.projectID.sameValueAs(projectID);
    }

    /**
     * Changes a resource's end date
     *
     * @param endDate new resource end date
     * @return true if new end date is valid and operation was done, false otherwise.
     */
    public boolean changeEndDate(LocalDate endDate) {
        boolean done;
        try {
            this.allocationPeriod = Period.between(this.allocationPeriod.getStartingDate(), endDate);
            done = true;

        } catch (Exception e) {
            done = false;
        }
        return done;
    }

    /**
     * Verifies if a resource is active at a certain date
     *
     * @param date the date at which to determine if the resource is active
     * @return true if the resource's allocation period contains the given date
     */
    public boolean isActiveInDate(LocalDate date) {
        return this.allocationPeriod.containsDate(date);
    }
}
