package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.ISprintJPARepository;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.SprintRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SprintRepositoryImplTest {
    @Mock
    ISprintJPARepository iSprintJPARepository;
    @InjectMocks
    SprintRepositoryImpl sprintRepositoryImpl;

    @Test
    void existsByID() {
        SprintID sprintID = mock(SprintID.class);
        when(iSprintJPARepository.existsById(sprintID)).thenReturn(true);
        boolean result = sprintRepositoryImpl.existsByID(sprintID);

        assertTrue(result);
    }

    @Test
    void existsByID_False() {
        SprintID sprintID = mock(SprintID.class);
        when(iSprintJPARepository.existsById(sprintID)).thenReturn(false);
        boolean result = sprintRepositoryImpl.existsByID(sprintID);

        assertFalse(result);
    }
}