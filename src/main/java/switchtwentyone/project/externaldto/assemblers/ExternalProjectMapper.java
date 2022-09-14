package switchtwentyone.project.externaldto.assemblers;

import org.springframework.stereotype.Service;
import switchtwentyone.project.dto.ProjectDTO;
import switchtwentyone.project.externaldto.ExternalProjectDTO;

@Service
public class ExternalProjectMapper {

    public ProjectDTO toDTO(ExternalProjectDTO externalDTO) {

        ProjectDTO dto = new ProjectDTO();
        dto.code ="EXT_" + externalDTO.id;
        dto.description = externalDTO.description;
        dto.name = externalDTO.name;

        return dto;
    }
}
