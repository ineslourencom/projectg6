package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.dto.ProjectInfoDTO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ProjectInfoToDTOMapperTest {
    @Test
    void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<ProjectInfoToDTOMapper> constructor = ProjectInfoToDTOMapper.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void toDTOTest() {
        Project project = mock(Project.class);
        ProjectInfoDTO dto = ProjectInfoToDTOMapper.toDTO(project);

        assertNotNull(dto);
    }
}