package switchtwentyone.project.datamodel.assembler;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileCreatable;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProfileDTO;
import switchtwentyone.project.dto.mapper.ProfileDomainDTOMapper;
import switchtwentyone.project.datamodel.ProfileJPA;

@Service
public class ProfileDomainDataAssembler {

    public ProfileJPA toData(Profile profile) {
        ProfileDTO profileDTO = ProfileDomainDTOMapper.toDTO(profile);
        return new ProfileJPA(profileDTO);
    }

    public Profile toDomain(ProfileJPA profileJpa) {
        ProfileID profileID = profileJpa.getProfileIDData();
        Text description = Text.write(profileJpa.getDescriptionData());
        return ProfileCreatable.createProfile(profileID, description);
    }
}
