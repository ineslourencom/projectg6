package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.dto.NewSprintInfoDTO;
import switchtwentyone.project.dto.NewUserStoryInfoDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NewSprintInfoDTOTest {

    @Test
    void ensureEqualsItself() {
        NewSprintInfoDTO dto = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));

        assertEquals(dto, dto);
    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        NewSprintInfoDTO dtoOne = new NewSprintInfoDTO(LocalDate.of(2022, 01, 2));
        NewSprintInfoDTO dtoTwo = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));

        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        NewSprintInfoDTO dtoOne = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));
        Object somethingElse = new Object();

        assertNotEquals(dtoOne, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        NewSprintInfoDTO dtoOne = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));

        assertNotEquals(dtoOne, null);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        NewSprintInfoDTO dtoOne = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));
        NewSprintInfoDTO dtoTwo = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        NewSprintInfoDTO dtoOne = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));
        NewSprintInfoDTO dtoTwo = new NewSprintInfoDTO(LocalDate.of(2022, 01, 02));
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }

    @Test
    void getStartDate() {
        NewSprintInfoDTO dtoOne = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));
        LocalDate statement = dtoOne.getStartDate();
        assertEquals(LocalDate.of(2022, 01, 01), statement);
    }

    @Test
    void NewSprintInfoDTOWithSameAttributesAreEqual() {
        //Act
        NewSprintInfoDTO dtoOne = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));
        NewSprintInfoDTO dtoTwo = new NewSprintInfoDTO(LocalDate.of(2022, 01, 01));

        //Assert
        assertEquals(dtoOne, dtoTwo);
    }

}