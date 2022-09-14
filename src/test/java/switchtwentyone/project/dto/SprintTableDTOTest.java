package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SprintTableDTOTest {

    @Test
    void testSprintTableDTO() {
        SprintTableDTO dto1 = new SprintTableDTO();
        dto1.sprintNumber = 1;
        dto1.startDate = LocalDate.of(2020, 01, 01);
        dto1.endDate = LocalDate.of(2020, 01, 15);

        int sprintNumber = 1;
        LocalDate startDate = LocalDate.of(2020, 01, 01);
        LocalDate endDate = LocalDate.of(2020, 01, 15);


        assertEquals(dto1.sprintNumber,sprintNumber );
        assertEquals(dto1.startDate,startDate );
        assertEquals(dto1.endDate,endDate );

    }

    @Test
    void ensureEqualsItself() {
        SprintTableDTO dto = new SprintTableDTO();

        assertEquals(dto, dto);
    }

    @Test
    void ensureEqualsEqualObject() {
        SprintTableDTO dtoOne = new SprintTableDTO();
        SprintTableDTO dtoTwo = new SprintTableDTO();

        assertEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        AccountAndStatusDTO dtoOne = new AccountAndStatusDTO();
        AccountAndStatusDTO dtoTwo = new AccountAndStatusDTO();
        dtoOne.email = "lino@isep.ipp.pt";

        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualsInstanceOfOtherClass () {
        SprintTableDTO dto = new SprintTableDTO();
        Object somethingElse = new Object();

        assertNotEquals(dto, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull () {
        SprintTableDTO dto = new SprintTableDTO();

        assertNotEquals(dto, null);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        SprintTableDTO dtoOne = new SprintTableDTO();
        SprintTableDTO dtoTwo = new SprintTableDTO();
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne,hashTwo);
    }

}