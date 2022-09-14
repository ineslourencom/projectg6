package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.dto.ProfileDTO;
import switchtwentyone.project.externaldto.ExternalProfileDTO;
import switchtwentyone.project.externaldto.assemblers.ExternalProfileMapper;

import static org.junit.jupiter.api.Assertions.*;

class ExternalProfileMapperTest {

    @Test
    void toDTO() {
        ExternalProfileDTO dto = new ExternalProfileDTO();
        dto.name = "Visitor";
        ProfileDTO expected= new ProfileDTO("Visitor - External", "From external source");
        ExternalProfileMapper mapper = new ExternalProfileMapper();

        ProfileDTO result = mapper.toDTO(dto);

        assertEquals(expected, result);
    }
}