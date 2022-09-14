package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.valueObjects.Text;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountDTOTest {

    @Test
    void testEqualsToItself() {
        AccountDTO dto = new AccountDTO();
        boolean result = dto.equals(dto);

        assertTrue(result);
    }

    @Test
    void testEqualsEqualObject() {
        AccountDTO dto = new AccountDTO();
        AccountDTO dtoTwo = new AccountDTO();
        boolean result = dto.equals(dtoTwo);

        assertTrue(result);
    }

    @Test
    void AccountDTOWithDifferentPhotoAreDifferent() {
        AccountDTO dto = new AccountDTO();
        dto.photo = ":))";
        dto.accountID = "accountID";
        dto.name = "luis";
        dto.email = "luis@isep.ipp.pt";
        dto.function = "Accounter";
        dto.profileID = "User";

        AccountDTO dtoTwo = new AccountDTO();

        dtoTwo.photo = ":)";
        dtoTwo.accountID = "accountID";
        dtoTwo.name = "luis";
        dtoTwo.email = "luis@isep.ipp.pt";
        dtoTwo.function = "Accounter";
        dtoTwo.profileID = "User";


        boolean result = dto.equals(dtoTwo);

        assertFalse(result);
    }

    @Test
    void AccountDTOWithDifferentAccountIDAreDifferent() {
        AccountDTO dto = new AccountDTO();
        dto.photo = ":)";
        dto.accountID = "accountID";
        dto.name = "luis";
        dto.email = "luis@isep.ipp.pt";
        dto.function = "Accounter";
        dto.profileID = "User";

        AccountDTO dtoTwo = new AccountDTO();

        dtoTwo.photo = ":)";
        dtoTwo.accountID = "accountID1";
        dtoTwo.name = "luis";
        dtoTwo.email = "luis@isep.ipp.pt";
        dtoTwo.function = "Accounter";
        dtoTwo.profileID = "User";


        boolean result = dto.equals(dtoTwo);

        assertFalse(result);
    }

    @Test
    void AccountDTOWithDifferentNameAreDifferent() {
        AccountDTO dto = new AccountDTO();
        dto.photo = ":)";
        dto.accountID = "accountID1";
        dto.name = "luis";
        dto.email = "luis@isep.ipp.pt";
        dto.function = "Accounter";
        dto.profileID = "User";

        AccountDTO dtoTwo = new AccountDTO();

        dtoTwo.photo = ":)";
        dtoTwo.accountID = "accountID1";
        dtoTwo.name = "luis1";
        dtoTwo.email = "luis@isep.ipp.pt";
        dtoTwo.function = "Accounter";
        dtoTwo.profileID = "User";


        boolean result = dto.equals(dtoTwo);

        assertFalse(result);
    }

    @Test
    void AccountDTOWithDifferentEmailAreDifferent() {
        AccountDTO dto = new AccountDTO();
        dto.photo = ":)";
        dto.accountID = "accountID1";
        dto.name = "luis1";
        dto.email = "luis@isep.ipp.com";
        dto.function = "Accounter";
        dto.profileID = "User";

        AccountDTO dtoTwo = new AccountDTO();

        dtoTwo.photo = ":)";
        dtoTwo.accountID = "accountID1";
        dtoTwo.name = "luis1";
        dtoTwo.email = "luis@isep.ipp.pt";
        dtoTwo.function = "Accounter";
        dtoTwo.profileID = "User";


        boolean result = dto.equals(dtoTwo);

        assertFalse(result);
    }


    @Test
    void AccountDTOWithDifferentFunctionAreDifferent() {
        AccountDTO dto = new AccountDTO();
        dto.photo = ":)";
        dto.accountID = "accountID1";
        dto.name = "luis1";
        dto.email = "luis@isep.ipp.pt";
        dto.function = "Accounter";
        dto.profileID = "User";

        AccountDTO dtoTwo = new AccountDTO();

        dtoTwo.photo = ":)";
        dtoTwo.accountID = "accountID1";
        dtoTwo.name = "luis1";
        dtoTwo.email = "luis@isep.ipp.pt";
        dtoTwo.function = "Accounter1";
        dtoTwo.profileID = "User";


        boolean result = dto.equals(dtoTwo);

        assertFalse(result);
    }

    @Test
    void AccountDTOWithDifferentProfileIDAreDifferent() {
        AccountDTO dto = new AccountDTO();
        dto.photo = ":)";
        dto.accountID = "accountID1";
        dto.name = "luis1";
        dto.email = "luis@isep.ipp.pt";
        dto.function = "Accounter1";
        dto.profileID = "User";

        AccountDTO dtoTwo = new AccountDTO();

        dtoTwo.photo = ":)";
        dtoTwo.accountID = "accountID1";
        dtoTwo.name = "luis1";
        dtoTwo.email = "luis@isep.ipp.pt";
        dtoTwo.function = "Accounter1";
        dtoTwo.profileID = "User1";


        boolean result = dto.equals(dtoTwo);

        assertFalse(result);
    }

    @Test
    void testEqualsObjectsOffDifClasses() {
        AccountDTO dto = new AccountDTO();
        dto.photo = ":))";
        dto.accountID = "accountID";
        dto.name = "luis";
        dto.email = "luis@isep.ipp.pt";
        dto.function = "Accounter";
        dto.profileID = "User";

        AccountID accountID = AccountID.of(Email.of("luis@isep.ipp.pt"));


        boolean result = dto.equals(accountID);

        assertFalse(result);
    }

    @Test
    void testEqualsNotNull() {
        AccountDTO dto = new AccountDTO();
        dto.photo = ":))";
        dto.accountID = "accountID";
        dto.name = "luis";
        dto.email = "luis@isep.ipp.pt";
        dto.function = "Accounter";
        dto.profileID = "User";


        boolean result = dto.equals(null);

        assertFalse(result);
    }

    @Test
    void testEqualsEqualHash() {
        AccountDTO dto = new AccountDTO();
        AccountDTO dtoTwo = new AccountDTO();

        assertEquals(dto.hashCode(),dtoTwo.hashCode());
    }

    @Test
    void testEqualsDifferentHash() {
        AccountDTO dto = new AccountDTO();
        dto.photo = ":))";
        dto.accountID = "accountID";
        dto.name = "luis";
        dto.email = "luis@isep.ipp.pt";
        dto.function = "Accounter";
        dto.profileID = "User";

        AccountDTO dtoTwo = new AccountDTO();

        dtoTwo.photo = ":)";
        dtoTwo.accountID = "accountIDOne";
        dtoTwo.name = "Pedro";
        dtoTwo.email = "Pedro@isep.ipp.pt";
        dtoTwo.function = "Engineer";
        dtoTwo.profileID = "Director";

        assertNotEquals(dto.hashCode(),dtoTwo.hashCode());
    }

}