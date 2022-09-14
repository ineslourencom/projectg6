package switchtwentyone.project.domain.aggregates.profilerequest;

import switchtwentyone.project.domain.shared.ValueObject;

import java.util.*;

public class ProfileRequestID implements ValueObject<ProfileRequestID> {


    private UUID profileRequestID;

    private ProfileRequestID() {
        this.profileRequestID = UUID.randomUUID();
    }

    public static ProfileRequestID createProfileRequestID() {
        return new ProfileRequestID();
    }

    /**
     * Call constructor method to create an instance with a defined UUID identity
     *
     * @return itemID
     */
    public static ProfileRequestID createProfileRequestID(final String identity) {
        ProfileRequestID itemID = new ProfileRequestID();
        itemID.profileRequestID = UUID.fromString(identity);
        return itemID;
    }

    public String getValue() {
        return this.profileRequestID.toString();
    }

    @Override
    public boolean sameValueAs(ProfileRequestID other) {
        return other != null && this.profileRequestID == other.profileRequestID;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileRequestID profileRequestID1 = (ProfileRequestID) o;
        return this.sameValueAs(profileRequestID1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileRequestID);
    }


}
