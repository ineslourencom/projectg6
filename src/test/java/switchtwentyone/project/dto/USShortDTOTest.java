package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class USShortDTOTest {
    @Test
    void ensureEqualsItself() {
        USShortDTO dto = new USShortDTO();

        assertEquals(dto, dto);
    }

    @Test
    void ensureEqualsEqualObject() {
        USShortDTO dtoOne = new USShortDTO();
        USShortDTO dtoTwo = new USShortDTO();
        dtoOne.detail = "detail";
        dtoTwo.detail = "detail";
        dtoOne.statement = "statement";
        dtoTwo.statement = "statement";
        dtoOne.usID = 1.001;
        dtoOne.projID = 1;
        dtoOne.storyNumber = 1;
        dtoOne.priority = 1;
        dtoTwo.usID = 1.001;
        dtoTwo.projID = 1;
        dtoTwo.storyNumber = 1;
        dtoTwo.priority = 1;

        assertEquals(dtoOne, dtoTwo);

    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        USShortDTO dtoOne = new USShortDTO();
        USShortDTO dtoTwo = new USShortDTO();
        dtoOne.detail = "details";
        dtoTwo.detail = "detail";
        dtoOne.statement = "statement";
        dtoTwo.statement = "statement";
        dtoOne.usID = 1.001;
        dtoOne.projID = 1;
        dtoOne.storyNumber = 1;
        dtoOne.priority = 1;
        dtoTwo.usID = 1.001;
        dtoTwo.projID = 1;
        dtoTwo.storyNumber = 1;
        dtoTwo.priority = 1;

        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        USShortDTO dto = new USShortDTO();
        Object somethingElse = new Object();

        assertNotEquals(dto, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        USShortDTO dto = new USShortDTO();

        assertNotEquals( null, dto);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        USShortDTO dtoOne = new USShortDTO();
        USShortDTO dtoTwo = new USShortDTO();
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        USShortDTO dtoOne = new USShortDTO();
        USShortDTO dtoTwo = new USShortDTO();
        dtoOne.detail = "5";
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }


}