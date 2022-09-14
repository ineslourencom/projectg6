package switchtwentyone.project.dto.mapper;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItem;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.dto.SprintDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintMapperTest {
    @Test
    void toDTOTest() {

        SprintDTO expected = new SprintDTO();
        expected.sprintID=2.1;
        expected.projectID=3;
        expected.startDate=LocalDate.of(2022, 10, 23);
        expected.sprintDuration = 1;
        expected.sprintBacklogItemDTOList = new ArrayList<>();
        Sprint test = mock(Sprint.class);
        when(test.getProjectID()).thenReturn(3);
        when(test.getSprintIDDouble()).thenReturn(2.1);
        when(test.getStartDate()).thenReturn(LocalDate.of(2022, 10, 23));
        List<SprintBacklogItem> itemsList = new ArrayList<>();
        when(test.getSprintBacklogItems()).thenReturn(itemsList);
        when(test.getSprintDuration()).thenReturn(PositiveNumber.of(1));


        SprintDTO result = SprintMapper.toDTO(test);

        assertEquals(expected, result);
    }

    }