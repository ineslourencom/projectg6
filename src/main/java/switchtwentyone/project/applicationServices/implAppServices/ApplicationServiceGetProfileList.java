package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.iwebrepositories.ProfileWebRepository;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.dto.ProfileDTO;
import switchtwentyone.project.externaldto.assemblers.ExternalProfileMapper;
import switchtwentyone.project.dto.mapper.ProfileDomainDTOMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;
import switchtwentyone.project.externaldto.ExternalProfileDTO;

import java.util.List;

@Service
public class ApplicationServiceGetProfileList {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ProfileWebRepository webRepository;

    @Autowired
    ExternalProfileMapper mapper;

    public List<ProfileDTO> getProfilesDTOS() {
         List<ProfileDTO> profileDTOS = getLocalProfilesDTOS();

         List<ExternalProfileDTO> externalProfiles = webRepository.getProfiles();

         for (ExternalProfileDTO exDTO : externalProfiles){
             ProfileDTO profileDTO = mapper.toDTO(exDTO);
             profileDTOS.add(profileDTO);
         }

         return  profileDTOS;
    }


    public List<ProfileDTO> getLocalProfilesDTOS() {
        List<Profile> profiles = profileRepository.findAll();

        return ProfileDomainDTOMapper.createProfileListDTO(profiles);
    }
}
