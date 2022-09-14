package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;

import static org.junit.jupiter.api.Assertions.*;

class AccountShortDTOTest {

    @Test
    void testEqualsToItself() {
        AccountShortDTO dto = new AccountShortDTO();

        boolean result = dto.equals(dto);

        assertTrue(result);
    }

    @Test
    void testEqualsEqualObject() {
        AccountShortDTO dto = new AccountShortDTO();
        AccountShortDTO dtoTwo = new AccountShortDTO();

        boolean result = dto.equals(dtoTwo);

        assertTrue(result);
    }

    @Test
    void testEqualsDifferentObjects() {
        AccountShortDTO dtoOne = new AccountShortDTO();
        dtoOne.email = "bob@isep.ipp.pt";
        dtoOne.name = "Bob";
        dtoOne.jobTitle = "minion";
        dtoOne.photo = "8)";
        AccountShortDTO dtoTwo = new AccountShortDTO();
        dtoTwo.email = "kevin@isep.ipp.pt";
        dtoTwo.name = "kevin";
        dtoTwo.jobTitle = "minion";
        dtoTwo.photo = "-8)";

        boolean result = dtoOne.equals(dtoTwo);

        assertFalse(result);
    }

    @Test
    void testEqualsObjectsOffDifClasses() {
        AccountShortDTO dto = new AccountShortDTO();
        dto.email = "stuart@isep.ipp.pt";
        dto.name = "Stuart";
        dto.jobTitle = "minion";
        dto.photo = "o)";
        AccountID accountID = AccountID.of(Email.of("luis@isep.ipp.pt"));

        boolean result = dto.equals(accountID);

        assertFalse(result);
    }

    @Test
    void testEqualsNotNull() {
        AccountShortDTO dto = new AccountShortDTO();
        dto.email = "kevin@isep.ipp.pt";
        dto.name = "kevin";
        dto.jobTitle = "minion";
        dto.photo = "-8)";

        boolean result = dto.equals(null);

        assertFalse(result);
    }

    @Test
    void testEqualsEqualHash() {
        AccountShortDTO dtoOne = new AccountShortDTO();
        AccountShortDTO dtoTwo = new AccountShortDTO();

        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void testEqualsDifferentHash() {
        AccountShortDTO dtoOne = new AccountShortDTO();
        dtoOne.email = "bob@isep.ipp.pt";
        dtoOne.name = "Bob";
        dtoOne.jobTitle = "minion";
        dtoOne.photo = "8)";
        AccountShortDTO dtoTwo = new AccountShortDTO();
        dtoTwo.email = "kevin@isep.ipp.pt";
        dtoTwo.name = "kevin";
        dtoTwo.jobTitle = "minion";
        dtoTwo.photo = "-8)";

        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }
}