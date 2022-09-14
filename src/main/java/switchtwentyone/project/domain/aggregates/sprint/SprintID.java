package switchtwentyone.project.domain.aggregates.sprint;

import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.task.ContainerID;
import switchtwentyone.project.domain.shared.ValueObject;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public final class SprintID implements ValueObject<SprintID>, ContainerID, Serializable {

    private double id;

    //TODO - check how to generate sprintID;
    public SprintID(ProjectID projectID, PositiveNumber sprintNumber) {
        this.id = projectID.getProjectID() + (sprintNumber.getNumber() * 0.001);
    }

    private SprintID(Double number) {
        this.id = number;
    }

    public SprintID() {
        //This is an empty constructor
    }

    public static SprintID ofDouble(Double id) {
        return new SprintID(id);
    }

    @Override
    public String toString() {
        return Double.toString(id);
    }


    public double getSprintNumber() {
        return this.id;
    }



    @Override
    public boolean sameValueAs(SprintID other) {
        return other != null && this.id == other.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintID other = (SprintID) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public double getID() {
        return this.id;
    }

    @Override
    public String getIDForTask() {

        String id = String.valueOf(this.id);
        return "Sp-" + id;
    }
}

