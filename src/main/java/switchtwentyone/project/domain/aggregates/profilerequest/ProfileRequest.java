package switchtwentyone.project.domain.aggregates.profilerequest;

import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

public class ProfileRequest implements ValueObject<ProfileRequest> {


    /**
     * private attributes - restricted access
     */
    private ProfileRequestID profileRequestID;
    private Email email;
    private ProfileType profileType;


    /**
     * @param email   the email of account
     * @param profileType the profile that account want to change
     * @return profileRequest
     */


    public ProfileRequest(ProfileRequestID profileRequestID,  Email email,  ProfileType profileType) {
        if (email != null && profileType != null) {
            this.profileRequestID = profileRequestID;
            this.email = email;
            this.profileType = profileType;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileRequest)) return false;
        ProfileRequest other = (ProfileRequest) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileRequestID);
    }

    @Override
    public boolean sameValueAs(ProfileRequest other) {
        return other != null && this.profileRequestID.equals(other.profileRequestID);
    }

    public ProfileRequestID getProfileRequestID() {
        return profileRequestID;
    }

    public Email getEmail() {
        return email;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

}

