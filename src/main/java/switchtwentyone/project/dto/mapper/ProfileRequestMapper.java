package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequest;
import switchtwentyone.project.dto.NewProfileRequestInfoDTO;
import switchtwentyone.project.dto.ProfileRequestDTO;

public class ProfileRequestMapper {
    private ProfileRequestMapper() {
    }

    public static ProfileRequestDTO toDTO(ProfileRequest profileRequest) {
        ProfileRequestDTO dto = new ProfileRequestDTO();
        dto.profileRequestID = profileRequest.getProfileRequestID();
        dto.email = profileRequest.getEmail();
        dto.profileType = profileRequest.getProfileType();
        return dto;

    }

    public static NewProfileRequestInfoDTO toNativeDTO(ProfileRequest profileRequest) {
        NewProfileRequestInfoDTO infoDTO = new NewProfileRequestInfoDTO();
        infoDTO.email = profileRequest.getEmail().getEmailData();
        infoDTO.profileType = profileRequest.getProfileType().getValue();
        return infoDTO;

    }
}
