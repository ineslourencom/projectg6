package switchtwentyone.project.domain.aggregates.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import switchtwentyone.project.domain.shared.Entity;
import switchtwentyone.project.domain.valueObjects.Text;

import java.util.Objects;


public class Profile implements Entity<Profile> {
    @Getter
    private ProfileID profileID;
    @Getter
    private Text description;


    /**
     * Creator method for Profile.
     *
     * @param profileID   profile identifier
     * @param description general description of profile
     * @return Photo object encapsulating input text.
     */
    public Profile(@JsonProperty("profileType") ProfileID profileID,
                   @JsonProperty("description") Text description) {
        this.profileID = profileID;
        this.description = description;
    }

    public static Profile of(ProfileID profileID, Text description) {
        return new Profile(profileID, description);
    }


    /**
     * Override methods for Photo.
     * Fall back to the sameValueAs method for equals evaluation because
     * it is a Value Object (all attributes must be equal).
     */
    @Override
    public boolean sameIdentityAs(Profile other) {
        return other != null && this.profileID.sameValueAs(other.profileID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return this.sameIdentityAs(profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileID);
    }

//    public String getProfileIDdata(){
//        return this.profileID.getProfileTypeData().getValue();
//    }
//
//    public String getProfileDescription(){
//        return  this.description.getValue();
//    }


    public boolean hasID(ProfileID id){
        return this.profileID.sameValueAs(id);

    }

}
