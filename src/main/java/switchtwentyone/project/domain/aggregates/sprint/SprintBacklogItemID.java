package switchtwentyone.project.domain.aggregates.sprint;

import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class SprintBacklogItemID implements ValueObject<SprintBacklogItemID>, Serializable {

    private UUID id;

    protected SprintBacklogItemID(){
        this.id = UUID.randomUUID();
    }


    /**
     * Call constructor method to create an Instance with a random UUID identity.
     *
     * @return new SprintBacklogItemID object.
     */
    public static SprintBacklogItemID createSprintBacklogItemID() {
        return new SprintBacklogItemID();
    }

    /**
     * Call constructor method to create an Instance with a defined  UUID identity.
     *
     * @param identity String identity
     * @return new instance.
     */
    public static SprintBacklogItemID createSprintBacklogItemID(final String identity) {
        SprintBacklogItemID itemID = new SprintBacklogItemID();
        itemID.id = UUID.fromString(identity);
        return itemID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintBacklogItemID other = (SprintBacklogItemID) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean sameValueAs(SprintBacklogItemID other) {
        return other != null && this.id==(other.id);
    }
}
