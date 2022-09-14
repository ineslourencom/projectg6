package switchtwentyone.project.domain.aggregates.resource;

import lombok.Getter;
import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ResourceID implements ValueObject<ResourceID>, Serializable {

    @Getter
    private int resourceID;

    public ResourceID(int resourceIDint) {
        this.resourceID = resourceIDint;
    }

    public ResourceID() {
        //This is an empty constructor
    }

    /**Creates a new resource ID of a random, unique value.
     *
     * @return a new resource ID containing a random integer.
     */
    public static ResourceID create(){
       return new ResourceID(Math.abs(UUID.randomUUID().hashCode()));
    }

    /**
     * Regenerates a resource ID from an int from JPA.
     *
     * @return a new resource ID containing an integer.
     */
    public static ResourceID of(int resource){
        return new ResourceID(resource);
    }

    @Override
    public boolean sameValueAs(ResourceID other){return other!= null && this.resourceID == other.resourceID;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceID that = (ResourceID) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceID);
    }
}
