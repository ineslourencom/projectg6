package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.USDTO;
import switchtwentyone.project.dto.USInfoAndStatusDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryStatusMapperTest {

    @Test
    void toDTO() {

        USInfoAndStatusDTO expected = new USInfoAndStatusDTO();
        expected.statement = "statement";
        expected.storyNumber = 2;
        expected.priority = 2;
        expected.detail = "detail";
        expected.usID = 1.002;
        expected.usStatus = "status";
        UserStory us = mock(UserStory.class);
        when(us.getStatement()).thenReturn(Text.write("statement"));
        when(us.getStoryNumber()).thenReturn(PositiveNumber.of(2));
        when(us.getPriority()).thenReturn(2);
        when(us.getDetail()).thenReturn(Text.write("detail"));
        when(us.getUsID()).thenReturn(UserStoryID.ofProjIDAndUsNumber(new ProjectID(1), PositiveNumber.of(2)));

        USInfoAndStatusDTO result = UserStoryStatusMapper.toDTO(us,"status");
        assertEquals(expected, result);
    }
}