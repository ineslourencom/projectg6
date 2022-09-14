package switchtwentyone.project.datamodel;


import lombok.*;
import org.hibernate.Hibernate;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;


@ToString
@NoArgsConstructor
@Entity
@Table(name = "RESOURCES")
public class ResourceJPA {
    @EmbeddedId
    private ResourceID resourceID;
    private String role;
    private String roleDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private double percentageOfAllocation;
    @Embedded
    private ProjectID projectID;
    private double costPerHour;
    private Currency currency;
    @Embedded
    private AccountID associatedAccount;


    public ResourceJPA(ResourceID resourceID, String role, String roleDescription, LocalDate startDate, LocalDate endDate,
                       double percentageOfAllocation, ProjectID projectID, double costPerHour,
                       Currency currency, AccountID associatedAccount) {
        this.resourceID = resourceID;
        this.role = role;
        this.roleDescription = roleDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentageOfAllocation = percentageOfAllocation;
        this.projectID = projectID;
        this.costPerHour = costPerHour;
        this.currency = currency;
        this.associatedAccount = associatedAccount;

    }

    public ResourceID getResourceID() {
        return resourceID;
    }



    public String getRole() {
        return role;
    }



    public String getRoleDescription() {
        return roleDescription;
    }



    public LocalDate getStartDate() {
        return startDate;
    }



    public LocalDate getEndDate() {
        return endDate;
    }



    public double getPercentageOfAllocation() {
        return percentageOfAllocation;
    }



    public ProjectID getProjectID() {
        return projectID;
    }



    public double getCostPerHour() {
        return costPerHour;
    }



    public Currency getCurrency() {
        return currency;
    }



    public AccountID getAssociatedAccount() {
        return associatedAccount;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResourceJPA that = (ResourceJPA) o;
        return resourceID != null && Objects.equals(resourceID, that.resourceID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceID);
    }
}
