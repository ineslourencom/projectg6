package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.mapper.UserStoryMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewUserStoryInfoDTOTest {

    @Test
    void ensureEqualsItself() {
        NewUserStoryInfoDTO dto = new NewUserStoryInfoDTO("statement", "detail");

        assertEquals(dto, dto);
    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statements", "detail");
        NewUserStoryInfoDTO dtoTwo = new NewUserStoryInfoDTO("statement", "detail");

        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statement", "detail");
        Object somethingElse = new Object();

        assertNotEquals(dtoOne, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statement", "detail");

        assertNotEquals( null, dtoOne);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statement", "detail");
        NewUserStoryInfoDTO dtoTwo = new NewUserStoryInfoDTO("statement", "detail");
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statements", "detail");
        NewUserStoryInfoDTO dtoTwo = new NewUserStoryInfoDTO("statement", "detail");
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }

    @Test
    void getStatement(){
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statement", "detail");
        String statement = dtoOne.getStatement();
        assertEquals("statement", statement);
    }

    @Test
    void getDetail(){
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statement", "detail");
        String detail = dtoOne.getDetail();
        assertEquals("detail", detail);
    }

    @Test
    void NewUSInfoDTOWithSameAttributesAreEqual(){
        //Act
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statement", "detail");
        NewUserStoryInfoDTO dtoTwo = new NewUserStoryInfoDTO("statement", "detail");

        //Assert
        assertEquals(dtoOne, dtoTwo);
    }


    @Test
    void setStatement(){
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statement", "detail");
        dtoOne.setStatement("Test");
        String statement = dtoOne.getStatement();
        assertEquals("Test", statement);
    }

    @Test
    void setDetail(){
        NewUserStoryInfoDTO dtoOne = new NewUserStoryInfoDTO("statement", "detail");
        dtoOne.setDetail("test");
        String detail = dtoOne.getDetail();
        assertEquals("test", detail);
    }

}