package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.mapper.UserStoryMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class USDTOTest {

    @Test
    void ensureEqualsItself() {
        USDTO dto = new USDTO();

        assertEquals(dto, dto);
    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        USDTO dtoOne = new USDTO();
        USDTO dtoTwo = new USDTO();
        dtoOne.projID = 1;

        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        USDTO dto = new USDTO();
        Object somethingElse = new Object();

        assertNotEquals(dto, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        USDTO dto = new USDTO();

        assertNotEquals(dto, null);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        USDTO dtoOne = new USDTO();
        USDTO dtoTwo = new USDTO();
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        USDTO dtoOne = new USDTO();
        USDTO dtoTwo = new USDTO();
        dtoOne.projID = 5;
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }

    @Test
    void USDTOWithSameAttributesAreEqual() {
       //Arrange
       ProjectID projID1 = new ProjectID(1);
       PositiveNumber usNumber1 = PositiveNumber.of(1);
       UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projID1, usNumber1);
       Text statement1 = Text.write("This is statement 1");
       Text detail = Text.write("This is a detail");
       int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        UserStory us1 = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getIsDecomposed()).thenReturn(false);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getProjectID()).thenReturn(projID1);

        when(us1.getUsID()).thenReturn(usID1);
        when(us1.getStoryNumber()).thenReturn(usNumber1);
        when(us1.getStatement()).thenReturn(statement1);
        when(us1.getDetail()).thenReturn(detail);
        when(us1.getIsDecomposed()).thenReturn(false);
        when(us1.getParent()).thenReturn(usID1);
        when(us1.getPriority()).thenReturn(usPriority1);
        when(us1.getProjectID()).thenReturn(projID1);

        //Act
        USDTO newUSDTO = UserStoryMapper.toDTO(us);
        USDTO newUSDTO1 = UserStoryMapper.toDTO(us1);

        //Assert
        assertEquals(newUSDTO1, newUSDTO);

    }

    @Test
    void USDTOWithDifferentAttributesAreDifferent() {
        //Arrange
        ProjectID projID1 = new ProjectID(1);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        PositiveNumber usNumber2 = PositiveNumber.of(2);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projID1, usNumber1);
        UserStoryID usID2 = UserStoryID.ofProjIDAndUsNumber(projID1, usNumber2);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;
        UserStory us = mock(UserStory.class);
        UserStory us1 = mock(UserStory.class);
        when(us.getUsID()).thenReturn(usID1);
        when(us.getStoryNumber()).thenReturn(usNumber1);
        when(us.getStatement()).thenReturn(statement1);
        when(us.getDetail()).thenReturn(detail);
        when(us.getIsDecomposed()).thenReturn(false);
        when(us.getParent()).thenReturn(usID1);
        when(us.getPriority()).thenReturn(usPriority1);
        when(us.getProjectID()).thenReturn(projID1);

        when(us1.getUsID()).thenReturn(usID2);
        when(us1.getStoryNumber()).thenReturn(usNumber2);
        when(us1.getStatement()).thenReturn(statement1);
        when(us1.getDetail()).thenReturn(detail);
        when(us1.getIsDecomposed()).thenReturn(false);
        when(us1.getParent()).thenReturn(usID1);
        when(us1.getPriority()).thenReturn(usPriority1);
        when(us1.getProjectID()).thenReturn(projID1);

        //Act
        USDTO newUSDTO = UserStoryMapper.toDTO(us);
        USDTO newUSDTO1 = UserStoryMapper.toDTO(us1);

        //Assert
        assertNotEquals(newUSDTO1, newUSDTO);

    }

}