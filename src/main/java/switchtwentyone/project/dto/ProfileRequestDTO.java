package switchtwentyone.project.dto;

import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;

import java.util.Objects;

public class ProfileRequestDTO {
    public ProfileRequestID profileRequestID;
    public Email email;
    public ProfileType profileType;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileRequestDTO that = (ProfileRequestDTO) o;
        return Objects.equals(profileRequestID, that.profileRequestID) && Objects.equals(profileType, that.profileType) && Objects.equals(email, that.email);    }
    @Override
    public int hashCode() {
        return Objects.hash(profileRequestID, email, profileType);
    }



}
