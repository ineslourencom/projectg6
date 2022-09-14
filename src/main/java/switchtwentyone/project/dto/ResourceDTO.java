package switchtwentyone.project.dto;


import java.time.LocalDate;
import java.util.Objects;

public class ResourceDTO {
    public String associatedAccount;
    public int resourceID;
    public String associatedRole;
    public LocalDate startDate;
    public LocalDate endDate;
    public double percentageOfAllocation;
    public int projectID;
    public double costPerHourValue;
    public String currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceDTO that = (ResourceDTO) o;
        return resourceID == that.resourceID && Double.compare(that.percentageOfAllocation, percentageOfAllocation) == 0 && projectID == that.projectID && Double.compare(that.costPerHourValue, costPerHourValue) == 0 && Objects.equals(associatedAccount, that.associatedAccount) && Objects.equals(associatedRole, that.associatedRole) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(associatedAccount, resourceID, associatedRole, startDate, endDate, percentageOfAllocation, projectID, costPerHourValue, currency);
    }
}
