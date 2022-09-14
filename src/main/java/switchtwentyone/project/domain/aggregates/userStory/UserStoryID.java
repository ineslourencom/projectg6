package switchtwentyone.project.domain.aggregates.userStory;

import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.task.ContainerID;
import switchtwentyone.project.domain.shared.ValueObject;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserStoryID implements ValueObject<UserStoryID>, Serializable, ContainerID {

    private double id;

    /**
     * This revealed not to be a good solution because it limits the number of US per project to 999.
     * It is also very coupled to ProjectID class, and the USID will suffer if the ProjectID concept changes.
     */
    public UserStoryID() {
        //this is an empty constructor
    }

    private UserStoryID(Double number) {
        this.id = number;
    }

    public static UserStoryID ofDouble(Double id) {
        return new UserStoryID(id);
    }

    public static UserStoryID ofProjIDAndUsNumber(ProjectID projID, PositiveNumber usNumber) {
        if (usNumber.getNumber() > 999) {
            throw new IllegalArgumentException("US number must be inferior to 1000");
        }
        return new UserStoryID(projID.getProjectID() + usNumber.getNumber() * 0.001);
    }

    public void setID(double id) {
        this.id = id;
    }


    public double getID() {
        return this.id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStoryID other = (UserStoryID) o;

        return sameValueAs(other);
    }

    @Override
    public boolean sameValueAs(UserStoryID other) {
        return other != null && this.id == (other.id);
    }


    @Override
    public String getIDForTask() {
        String id = String.valueOf(this.id);
        return "US-" + id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
