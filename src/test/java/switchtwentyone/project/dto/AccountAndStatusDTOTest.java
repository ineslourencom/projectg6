package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class AccountAndStatusDTOTest {

    @Test
    void ensureEqualsItself() {
    AccountAndStatusDTO dto = new AccountAndStatusDTO();

    assertEquals(dto, dto);
    }

    @Test
    void ensureEqualsEqualObject() {
        AccountAndStatusDTO dtoOne = new AccountAndStatusDTO();
        AccountAndStatusDTO dtoTwo = new AccountAndStatusDTO();

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
        AccountAndStatusDTO dto = new AccountAndStatusDTO();
        Object somethingElse = new Object();

        assertNotEquals(dto, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull () {
        AccountAndStatusDTO dto = new AccountAndStatusDTO();

        assertNotEquals(dto, null);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        AccountAndStatusDTO dtoOne = new AccountAndStatusDTO();
        AccountAndStatusDTO dtoTwo = new AccountAndStatusDTO();
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne,hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDiferentHashCodes (){
        AccountAndStatusDTO dtoOne = new AccountAndStatusDTO();
        AccountAndStatusDTO dtoTwo = new AccountAndStatusDTO();
        dtoOne.email = "lino@isep.ipp.pt";
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne,hashTwo);
    }
}