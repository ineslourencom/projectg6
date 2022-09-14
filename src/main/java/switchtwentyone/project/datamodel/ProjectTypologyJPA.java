package switchtwentyone.project.datamodel;


import lombok.*;
import org.hibernate.Hibernate;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="PROJECT_TYPOLOGY")

public class ProjectTypologyJPA {
    @EmbeddedId
    ProjectTypologyID projectTypologyID;
    private String description;

    public ProjectTypologyJPA(ProjectTypologyID projectTypologyID, String description) {
        this.projectTypologyID = projectTypologyID;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectTypologyJPA that = (ProjectTypologyJPA) o;
        return projectTypologyID != null && Objects.equals(projectTypologyID, that.projectTypologyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectTypologyID);
    }
}

