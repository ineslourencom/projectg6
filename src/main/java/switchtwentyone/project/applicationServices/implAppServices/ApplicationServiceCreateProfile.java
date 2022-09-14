package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileCreatable;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProfileDTO;
import switchtwentyone.project.dto.mapper.ProfileDomainDTOMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceCreateProfile {

    @Autowired
    ProfileRepository profileRepository;


    public ApplicationServiceCreateProfile() {
    }

    protected ProfileID generateProfileID(ProfileType profileType) {
        return new ProfileID(profileType);
    }


    protected boolean isProfileDataValid(ProfileID newProfileID) {
        boolean isNewProfileDataValid = true;
        List<Profile> profileList = profileRepository.findAll();
        for (Profile prof : profileList) {

            if (prof.getProfileID().sameValueAs(newProfileID)) {
                isNewProfileDataValid = false;
            }
        }
        return isNewProfileDataValid;
    }


    public Optional<ProfileDTO> createAndSaveProfile(ProfileType profileType, Text description) {
        ProfileID profileID = generateProfileID(profileType);
        Profile newProfile = ProfileCreatable.createProfile(profileID, description);
        if (isProfileDataValid(profileID)) {
            profileRepository.save(newProfile);
            return Optional.of(ProfileDomainDTOMapper.toDTO(newProfile));
        } else {
            return Optional.empty();
        }
    }


    public Optional<Profile> findProfileById(ProfileID prID) {
        return profileRepository.findProfileById(prID);
    }
}
