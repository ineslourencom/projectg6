package switchtwentyone.project.domain.aggregates.profilerequest;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;

@Component
public interface ProfileRequestCreatable {

      public static ProfileRequest createProfileRequest(final ProfileRequestID profileRequestID, final Email email, final ProfileType profileType){
            return new ProfileRequest(profileRequestID,email,profileType);
      }

}
