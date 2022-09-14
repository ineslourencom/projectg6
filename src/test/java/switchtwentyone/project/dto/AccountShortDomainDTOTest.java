package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;

import static org.junit.jupiter.api.Assertions.*;

class AccountShortDomainDTOTest {


    @Test
    void testEqualsToItself() {
        AccountShortDomainDTO dto = new AccountShortDomainDTO();

        boolean result = dto.equals(dto);

        assertTrue(result);
    }

    @Test
    void testEqualsEqualObject() {
        AccountShortDomainDTO dto = new AccountShortDomainDTO();
        AccountShortDomainDTO dtoTwo = new AccountShortDomainDTO();

        boolean result = dto.equals(dtoTwo);

        assertTrue(result);
    }

    @Test
    void testEqualsDifferentObjects() {
        AccountShortDomainDTO dtoOne = new AccountShortDomainDTO();
        dtoOne.email = "bob@isep.ipp.pt";
        dtoOne.name = "Bob";
        dtoOne.jobTitle = "minion";
        dtoOne.photo = "8)";
        AccountShortDomainDTO dtoTwo = new AccountShortDomainDTO();
        dtoTwo.email = "kevin@isep.ipp.pt";
        dtoTwo.name = "kevin";
        dtoTwo.jobTitle = "minion";
        dtoTwo.photo = "-8)";

        boolean result = dtoOne.equals(dtoTwo);

        assertFalse(result);
    }

    @Test
    void testEqualsObjectsOffDifClasses() {
        AccountShortDomainDTO dto = new AccountShortDomainDTO();
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
        AccountShortDomainDTO dto = new AccountShortDomainDTO();
        dto.email = "kevin@isep.ipp.pt";
        dto.name = "kevin";
        dto.jobTitle = "minion";
        dto.photo = "-8)";

        boolean result = dto.equals(null);

        assertFalse(result);
    }

    @Test
    void testEqualsEqualHash() {
        AccountShortDomainDTO dtoOne = new AccountShortDomainDTO();
        AccountShortDomainDTO dtoTwo = new AccountShortDomainDTO();

        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void testEqualsDifferentHash() {
        AccountShortDomainDTO dtoOne = new AccountShortDomainDTO();
        dtoOne.email = "bob@isep.ipp.pt";
        dtoOne.name = "Bob";
        dtoOne.jobTitle = "minion";
        dtoOne.photo = "8)";
        AccountShortDomainDTO dtoTwo = new AccountShortDomainDTO();
        dtoTwo.email = "kevin@isep.ipp.pt";
        dtoTwo.name = "kevin";
        dtoTwo.jobTitle = "minion";
        dtoTwo.photo = "-8)";

        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }
}