package switchtwentyone.project.dto;

import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.util.Objects;

public class ProjectWithValueObjectsDTO {

    public Nameable name;
    public Describable description;
    public Period period;
    public PositiveNumber sprintDuration;
    public PositiveNumber budget;
    public BusinesSectorID businessSector;
    public CustomerID customer;
    public ProjectTypologyID projectTypologyID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectWithValueObjectsDTO that = (ProjectWithValueObjectsDTO) o;
        return Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(period, that.period)
                && Objects.equals(sprintDuration, that.sprintDuration)
                && Objects.equals(budget, that.budget)
                && Objects.equals(businessSector, that.businessSector)
                && Objects.equals(customer, that.customer)
                && Objects.equals(projectTypologyID, that.projectTypologyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, period, sprintDuration, budget, businessSector, customer, projectTypologyID);
    }
}
