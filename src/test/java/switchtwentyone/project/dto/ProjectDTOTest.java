package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProjectDTOTest {
    @Test
    void ensureEqualsItself() {
        ProjectDTO dto = new ProjectDTO();

        assertEquals(dto, dto);
    }

    @Test
    void ensureEqualsEqualObject() {
        ProjectDTO dtoOne = new ProjectDTO();
        ProjectDTO dtoTwo = new ProjectDTO();

        assertEquals(dtoOne, dtoTwo);

    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        ProjectDTO dtoOne = new ProjectDTO();
        ProjectDTO dtoTwo = new ProjectDTO();
        dtoOne.code = "1";

        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        ProjectDTO dto = new ProjectDTO();
        Object somethingElse = new Object();

        assertNotEquals(dto, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        ProjectDTO dto = new ProjectDTO();

        assertNotEquals(dto, null);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        ProjectDTO dtoOne = new ProjectDTO();
        ProjectDTO dtoTwo = new ProjectDTO();
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        ProjectDTO dtoOne = new ProjectDTO();
        ProjectDTO dtoTwo = new ProjectDTO();
        dtoOne.code = "5";
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }


    @Test
    void testToString() {
        ProjectDTO dtoOne = new ProjectDTO();
        dtoOne.code = "1";
        dtoOne.description = "description";
        dtoOne.name = "name";

        String expected = "ProjectDTO{code='1', name='name', description='description'}";

        String result = dtoOne.toString();

        assertEquals(expected, result);

    }
}