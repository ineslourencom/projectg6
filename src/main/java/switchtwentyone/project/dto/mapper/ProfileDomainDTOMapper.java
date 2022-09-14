package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.dto.ProfileDTO;

import java.util.ArrayList;
import java.util.List;


public class ProfileDomainDTOMapper {

    private ProfileDomainDTOMapper() {
    }

    public static ProfileDTO toDTO(Profile profile) {

        String profileID = profile.getProfileID().getProfileType().getValue();
        String description = profile.getDescription().getValue();

        return new ProfileDTO(profileID, description);
    }

    public static List<ProfileDTO> createProfileListDTO(List<Profile> profileList){

        List<ProfileDTO> profileDTOList = new ArrayList<>();

        for(Profile profile : profileList){
            ProfileDTO dto = toDTO(profile);
            profileDTOList.add(dto);
        }return profileDTOList;

    }


}

