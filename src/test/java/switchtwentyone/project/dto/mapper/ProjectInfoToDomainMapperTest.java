package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.dto.ProjectInfoDTO;
import switchtwentyone.project.dto.ProjectInfoDomainDTO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectInfoToDomainMapperTest {
    @Test
    void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<ProjectInfoToDomainMapper> constructor = ProjectInfoToDomainMapper.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void toDomainTest() {
        ProjectInfoDTO dto = new ProjectInfoDTO();
        dto.description = "Project Description.";
        dto.startDate = LocalDate.of(1910, 10, 5);
        dto.endDate = LocalDate.of(1974, 4, 25);
        dto.plannedSprints = 10;
        dto.sprintDuration = 2;
        dto.status = "planned";

        ProjectInfoDomainDTO result = ProjectInfoToDomainMapper.toDomain(dto);

        assertNotNull(result);
    }

    @Test
    void toDomainTestNullEndDate() {
        ProjectInfoDTO dto = new ProjectInfoDTO();
        dto.description = "Project Description.";
        dto.startDate = LocalDate.of(1910, 10, 5);
        dto.endDate = null;
        dto.plannedSprints = 10;
        dto.sprintDuration = 2;
        dto.status = "planned";

        ProjectInfoDomainDTO result = ProjectInfoToDomainMapper.toDomain(dto);

        assertNotNull(result);
    }
}