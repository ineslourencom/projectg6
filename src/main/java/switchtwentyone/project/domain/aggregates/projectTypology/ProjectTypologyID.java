package switchtwentyone.project.domain.aggregates.projectTypology;

import lombok.NoArgsConstructor;
import switchtwentyone.project.domain.shared.ValueObject;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
@NoArgsConstructor
public class ProjectTypologyID implements ValueObject<ProjectTypologyID>, Serializable {

    private NoNumberNoSymbolString name;

    private ProjectTypologyID(NoNumberNoSymbolString name) {
        this.name = name;
    }

    public static ProjectTypologyID of(NoNumberNoSymbolString name) {
        return new ProjectTypologyID(name);
    }

    public String getNameAsString() {
        return this.name.getValue();
    }

    @Override
    public boolean sameValueAs(ProjectTypologyID other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTypologyID that = (ProjectTypologyID) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
