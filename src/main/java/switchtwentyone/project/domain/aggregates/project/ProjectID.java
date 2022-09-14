package switchtwentyone.project.domain.aggregates.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class ProjectID implements ValueObject<ProjectID>, Serializable, Cloneable {

    @Getter
    private int projectID;

    //TODO - check how to generate profileID;

    public ProjectID(int projectID) {
        this.projectID = projectID;
    }

    @Override
    public boolean sameValueAs(ProjectID other) {
        return other != null && this.projectID == other.projectID;
    }

    @Override
    public String toString() {
        return Integer.toString(projectID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectID projectIDTwo = (ProjectID) o;
        return this.sameValueAs(projectIDTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID);
    }

    @Override
    public ProjectID clone() {
        try {
            ProjectID clone = (ProjectID) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
