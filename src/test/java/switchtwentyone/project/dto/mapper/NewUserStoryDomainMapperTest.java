package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ChildUserStoryDTO;
import switchtwentyone.project.dto.NewUserStoryDomainDTO;

import static org.junit.jupiter.api.Assertions.*;

class NewUserStoryDomainMapperTest {

    @Test
    void toDomain() {
        ChildUserStoryDTO dto = new ChildUserStoryDTO();
        dto.statement = "ABC";
        dto.detail ="DEFG";
        NewUserStoryDomainDTO expected = new NewUserStoryDomainDTO();
        expected.statement = Text.write("ABC");
        expected.detail = Text.write("DEFG");

        NewUserStoryDomainDTO result = NewUserStoryDomainMapper.toDomain(dto);

        assertEquals(expected, result);

    }
}