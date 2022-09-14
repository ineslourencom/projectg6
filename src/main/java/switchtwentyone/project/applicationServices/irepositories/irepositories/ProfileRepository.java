package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    List<Profile> findAll();

    Profile save(Profile profile);

    Optional<Profile> findProfileById(ProfileID profileID);

    void deleteAll();
}
