package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;

import java.util.List;
import java.util.Optional;

public interface ProfileRequestRepository {
    Optional<ProfileRequest> findProfileRequestByID(ProfileRequestID profileRequestID);

    boolean saveProfileRequest(ProfileRequest profileRequest);


}
