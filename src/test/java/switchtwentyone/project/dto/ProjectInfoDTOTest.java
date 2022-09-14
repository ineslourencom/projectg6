package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectInfoDTOTest {

    @Test
    void ensureEqualsItself() {
        ProjectInfoDTO dto = new ProjectInfoDTO();

        assertEquals(dto, dto);
    }

    @Test
    void ensureEqualsEqualObject() {
        ProjectInfoDTO dtoOne = new ProjectInfoDTO();
        ProjectInfoDTO dtoTwo = new ProjectInfoDTO();

        assertEquals(dtoOne, dtoTwo);

    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        ProjectInfoDTO dtoOne = new ProjectInfoDTO();
        ProjectInfoDTO dtoTwo = new ProjectInfoDTO();
        dtoOne.code = 1;

        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        ProjectInfoDTO dto = new ProjectInfoDTO();
        Object somethingElse = new Object();

        assertNotEquals(dto, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        ProjectInfoDTO dto = new ProjectInfoDTO();

        assertNotEquals(dto, null);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        ProjectInfoDTO dtoOne = new ProjectInfoDTO();
        ProjectInfoDTO dtoTwo = new ProjectInfoDTO();
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        ProjectInfoDTO dtoOne = new ProjectInfoDTO();
        ProjectInfoDTO dtoTwo = new ProjectInfoDTO();
        dtoOne.code = 5;
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }

}