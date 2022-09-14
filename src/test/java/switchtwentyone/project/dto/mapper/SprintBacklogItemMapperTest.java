package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.aggregates.sprint.ScrumBoardCategory;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItem;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItemID;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.dto.SprintBacklogItemDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintBacklogItemMapperTest {

    @Test
    void toDTO() {
        SprintBacklogItem item = mock(SprintBacklogItem.class);
        SprintBacklogItemID itemID = mock(SprintBacklogItemID.class);
        UserStoryID usID = mock(UserStoryID.class);
        ScrumBoardCategory cat = mock(ScrumBoardCategory.class);
        FibonacciNumber effort = mock(FibonacciNumber.class);

        when(item.getItemID()).thenReturn(itemID);
        when(item.getUsID()).thenReturn(usID);
        when(item.getCategory()).thenReturn(cat);
        when(item.getEffortEstimate()).thenReturn(effort);
        when(itemID.toString()).thenReturn("b74956e7-5cf1-4c7d-b518-135ae5c7fdd0");
        when(usID.getID()).thenReturn(1.001);
        when(cat.toString()).thenReturn("To do");
        when(effort.getNumber()).thenReturn(1);

        SprintBacklogItemDTO dtoExpected = new SprintBacklogItemDTO();
        dtoExpected.effortEstimate = 1;
        dtoExpected.category = "To do";
        dtoExpected.itemID = "b74956e7-5cf1-4c7d-b518-135ae5c7fdd0";
        dtoExpected.usID = 1.001;

        SprintBacklogItemDTO dtoResult = SprintBacklogItemMapper.toDTO(item);

        assertEquals(dtoExpected,  dtoResult);
    }

}