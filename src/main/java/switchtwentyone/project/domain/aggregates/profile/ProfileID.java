package switchtwentyone.project.domain.aggregates.profile;

import switchtwentyone.project.domain.shared.ValueObject;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProfileID implements ValueObject<ProfileID>, Serializable {

    @Embedded
    private ProfileType profileType;


    public ProfileID() {}

    public ProfileID(ProfileType profileType) {
        this.profileType = profileType;
    }

    public static ProfileID ofProfileType(String id){
        return new ProfileID(ProfileType.of(id));
    }

    public static ProfileID of(ProfileType profileType){
        return new ProfileID(profileType);
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public boolean hasProfileType(ProfileType profileType) {
        return this.profileType.sameValueAs(profileType);
    }

    @Override
    public boolean sameValueAs(ProfileID other) {
        return other != null && this.profileType.equals(other.profileType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileID profileID1 = (ProfileID) o;
        return this.sameValueAs(profileID1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileType);
    }


    @Override
    public String toString() {
        return this.profileType.toString();
    }

}
