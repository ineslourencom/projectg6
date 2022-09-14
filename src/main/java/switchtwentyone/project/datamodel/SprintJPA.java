package switchtwentyone.project.datamodel;

import lombok.*;
import org.hibernate.Hibernate;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "sprints")

public class SprintJPA {
    @EmbeddedId
    private SprintID sprintID;
    //We used the serialized class SprintID to identify the JPA. It is unique in the whole system.

    private int sprintNumber;
    private int projectID;
    //Because projectID is in another aggregate, and therefore foreign key constraint is not used

    private LocalDate startDate;
    private int sprintDuration;
    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<SprintbacklogItemJPA> sprintbacklogItemJPAList;
    //The sprintBacklogItem class is another entity of the same aggregate that Sprint belongs to. So we need a foreign key.
    //One Sprint can have multiple Sprintbacklogitems therefore we use the annotation @OnetoMany


    public SprintJPA(SprintID sprintID, LocalDate startDate, int projectID, int sprintNumber, int sprintDuration) {
        this.sprintID = sprintID;
        this.startDate = startDate;
        this.projectID = projectID;
        this.sprintNumber = sprintNumber;
        this.sprintDuration = sprintDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SprintJPA sprintJPA = (SprintJPA) o;
        return sprintID != null && Objects.equals(sprintID, sprintJPA.sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID);
    }
}
