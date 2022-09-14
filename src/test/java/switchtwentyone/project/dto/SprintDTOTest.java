package switchtwentyone.project.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SprintDTOTest {
    @Test
    void ensureEqualsItself() {
        SprintDTO dto = new SprintDTO();

        assertEquals(dto, dto);
    }
    @Test
    void ensureEqualsEqualObject() {
        SprintDTO dtoOne = new SprintDTO();
        SprintDTO dtoTwo = new SprintDTO();

        assertEquals(dtoOne, dtoTwo);

    }
    @Test
    void ensureDoesNotEqualDifferentObject() {
        SprintDTO dtoOne = new SprintDTO();
        SprintDTO dtoTwo = new SprintDTO();
        dtoOne.sprintID = 1;

        assertNotEquals(dtoOne, dtoTwo);
    }
    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        SprintDTO dto = new SprintDTO();
        Object somethingElse = new Object();

        assertNotEquals(dto, somethingElse);
    }
    @Test
    void ensureDoesNotEqualNull() {
        SprintDTO dto = new SprintDTO();

        assertNotEquals(dto, null);
    }
    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        SprintDTO dtoOne = new SprintDTO();
        SprintDTO dtoTwo = new SprintDTO();
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }
    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        SprintDTO dtoOne = new SprintDTO();
        SprintDTO dtoTwo = new SprintDTO();
        dtoOne.sprintID = 5;
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }

}