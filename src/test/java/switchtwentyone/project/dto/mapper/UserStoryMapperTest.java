package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ScrumBoardDTO;
import switchtwentyone.project.dto.USDTO;
import switchtwentyone.project.dto.USShortDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryMapperTest {

    @Test
    void toDTOTest() {
        USDTO expected = new USDTO();
        expected.projID = 1;
        expected.statement = "statement";
        expected.parent = 1.001;
        expected.isDecomposed = false;
        expected.storyNumber = 2;
        expected.priority = 2;
        expected.detail = "detail";
        expected.usID = 1.002;
        UserStory us = mock(UserStory.class);
        when(us.getProjectID()).thenReturn(new ProjectID(1));
        when(us.getStatement()).thenReturn(Text.write("statement"));
        when(us.getParent()).thenReturn(UserStoryID.ofProjIDAndUsNumber(new ProjectID(1), PositiveNumber.of(1)));
        when(us.getIsDecomposed()).thenReturn(false);
        when(us.getStoryNumber()).thenReturn(PositiveNumber.of(2));
        when(us.getPriority()).thenReturn(2);
        when(us.getDetail()).thenReturn(Text.write("detail"));
        when(us.getUsID()).thenReturn(UserStoryID.ofProjIDAndUsNumber(new ProjectID(1), PositiveNumber.of(2)));

        USDTO result = UserStoryMapper.toDTO(us);
        assertEquals(expected, result);
    }

    @Test
    void toShortDTO_Test() {
        USShortDTO expected = new USShortDTO();
        expected.projID = 1;
        expected.statement = "statement";
        expected.storyNumber = 2;
        expected.detail = "detail";
        expected.usID = 1.002;
        UserStory us = mock(UserStory.class);
        when(us.getProjectID()).thenReturn(new ProjectID(1));
        when(us.getStatement()).thenReturn(Text.write("statement"));
        when(us.getStoryNumber()).thenReturn(PositiveNumber.of(2));
        when(us.getDetail()).thenReturn(Text.write("detail"));
        when(us.getUsID()).thenReturn(UserStoryID.ofProjIDAndUsNumber(new ProjectID(1), PositiveNumber.of(2)));

        USShortDTO result = UserStoryMapper.toShortDTO(us);
        assertEquals(expected, result);
    }

    @Test
    void toScrumBoardDTO() {
        ScrumBoardDTO expected = new ScrumBoardDTO();
        expected.statement = "statement";
        expected.storyNumber = 2;
        expected.priority = 2;
        expected.usStatus = "status";
        expected.sprintID = 1.001;
        UserStory us = mock(UserStory.class);
        when(us.getStatement()).thenReturn(Text.write("statement"));
        when(us.getStoryNumber()).thenReturn(PositiveNumber.of(2));
        when(us.getPriority()).thenReturn(2);
        when(us.getDetail()).thenReturn(Text.write("detail"));
        when(us.getUsID()).thenReturn(UserStoryID.ofProjIDAndUsNumber(new ProjectID(1), PositiveNumber.of(2)));

        ScrumBoardDTO result = UserStoryMapper.toScrumBoardDTO(us,"status", 1.001);
        assertEquals(expected, result);
    }
}