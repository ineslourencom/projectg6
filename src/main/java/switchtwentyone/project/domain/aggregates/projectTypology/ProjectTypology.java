package switchtwentyone.project.domain.aggregates.projectTypology;


import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Entity;

import java.util.Objects;

public class ProjectTypology implements Entity<ProjectTypology> {

    /**
     * ProjectTypologyID is composed by project Typology name
     */
    private ProjectTypologyID projectTypologyID;
    private Describable description;

    public String getProjectTypologyIDAsString() {
        return projectTypologyID.getNameAsString();
    }


    public String getDescriptionAsString() {
        return description.getValue();
    }

    protected ProjectTypology(ProjectTypologyID projectTypologyID, Describable description) {
        this.projectTypologyID = projectTypologyID;
        this.description = description;
    }



    @Override
    public boolean sameIdentityAs(ProjectTypology other) {
        return other.projectTypologyID != null && this.projectTypologyID.equals(other.projectTypologyID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTypology that = (ProjectTypology) o;
        return this.sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectTypologyID);
    }
}
