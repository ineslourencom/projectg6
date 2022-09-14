package switchtwentyone.project.datamodel;

import lombok.*;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItemID;

import javax.persistence.*;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sprintbacklog_items")
public class SprintbacklogItemJPA {
    @EmbeddedId
    private SprintBacklogItemID itemID;
    //We used the serialized class SprintBacklogItemID to identify the JPA. It is unique in the whole system.
    private double usID;
    //because usID is in a another aggregate, a foreign key constraint is not used
    private String category;
    private int effortEstimate;

    @ManyToOne
    @JoinColumn(name = "SPRINT_ID", referencedColumnName = "ID")
    private SprintJPA sprint;
    //the sprint is the root entity of the aggregate that SprintBacklogItem belongs to. So we need a foreign key.
    //A SprintBacklogitem belongs to only one Sprint but a Sprint can have multiple items. So we use the annotation @ManyToOne.

    public SprintbacklogItemJPA(SprintBacklogItemID itemID, double usID, String category, int effortEstimate, SprintJPA sprintJPA ) {
        this.itemID = itemID;
        this.usID = usID;
        this.category = category;
        this.effortEstimate = effortEstimate;
        this.sprint = sprintJPA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintbacklogItemJPA)) return false;
        SprintbacklogItemJPA that = (SprintbacklogItemJPA) o;
        return Double.compare(that.usID, usID) == 0
                && effortEstimate == that.effortEstimate &&
                Objects.equals(itemID, that.itemID) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, usID, category, effortEstimate);
    }

    @Override
    public String toString() {
        return "SprintbacklogItemJPA{" +
                "itemID=" + itemID.toString() +
                ", usID=" + usID +
                ", category='" + category + '\'' +
                ", effortEstimate=" + effortEstimate +
                '}';
    }
}
