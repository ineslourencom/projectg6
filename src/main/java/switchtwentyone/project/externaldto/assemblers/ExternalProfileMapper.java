package switchtwentyone.project.externaldto.assemblers;

import org.springframework.stereotype.Service;
import switchtwentyone.project.dto.ProfileDTO;
import switchtwentyone.project.externaldto.ExternalProfileDTO;

@Service
public class ExternalProfileMapper {
    public ProfileDTO toDTO(ExternalProfileDTO externalDTO){
        String name = externalDTO.name + " - External";
        String description = "From external source";

        ProfileDTO dto = new ProfileDTO(name, description);

        return dto;

    }
}
