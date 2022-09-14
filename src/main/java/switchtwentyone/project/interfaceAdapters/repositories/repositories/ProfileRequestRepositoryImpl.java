package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRequestRepository;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRequestRepositoryImpl implements ProfileRequestRepository {
    List<ProfileRequest> listPR;

    public ProfileRequestRepositoryImpl() {
        this.listPR = new ArrayList<>();
    }

    @Override
    public Optional<ProfileRequest> findProfileRequestByID(ProfileRequestID profileRequestID) {
        Optional<ProfileRequest> profileRequest = Optional.empty();
        for (ProfileRequest pr : this.listPR) {
            if (pr.equals(listPR)) {
                profileRequest = Optional.of(pr);
            }
        }
        return profileRequest;
    }

    /**
     * This method verifies first if the profileRequest already exists in the repository, if not, adds it to the list.
     *
     * @param profileRequest
     * @return true/false
     */
    @Override
    public boolean saveProfileRequest(ProfileRequest profileRequest) {
        boolean exists = false;
        boolean isSaved = false;
        for (ProfileRequest pr : listPR) {
            if (pr.equals(profileRequest)) {
                exists = true;
            }
        }
        if (!exists) {
            listPR.add(profileRequest);
            isSaved = true;
        }
        return isSaved;
    }


}


