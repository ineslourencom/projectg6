package switchtwentyone.project.domain.aggregates.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class AccountIDTest {


    @Test
    void testSameIDsAreEqual() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        AccountID newAccountIDTwo = AccountID.of(newEmail);

        //Act
        boolean result = newAccountID.equals(newAccountIDTwo);

        //Assert
        assertTrue(result);
    }

    @Test
    void accountNotEqualToOtherObjectl(){
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        Integer other = Integer.valueOf(20);

        //Act
        boolean result = newAccountID.equals(other);

        //Assert
        assertFalse(result);

    }

    @Test
    void equalToItself() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);

        //Act
        boolean result = newAccountID.equals(newAccountID);

        //Assert
        assertTrue(result);
    }

    @Test
    void testNotEqualToNull() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        AccountID newAccountIDTwo = AccountID.of(newEmail);

        //Act
        boolean result = newAccountID.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void createAccountIDWithNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> AccountID.of(null));
    }

    @Test
    void testDifferentIDsAreDifferent() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        Email newEmailTwo = Email.of("mart@isep.ipp.pt");

        AccountID newAccountID = AccountID.of(newEmail);
        AccountID newAccountIDTwo = AccountID.of(newEmailTwo);

        //Act
        boolean result = newAccountID.equals(newAccountIDTwo);

        //Assert
        assertFalse(result);
    }


    @Test
    void testSameIDsHaveSameHashCode() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        AccountID newAccountIDTwo = AccountID.of(newEmail);

        //Assert
        assertEquals(newAccountID.hashCode(), newAccountIDTwo.hashCode());
    }

    @Test
    void testDifferentIDsHaveDifferentHashCode() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        Email newEmailTwo = Email.of("mart@isep.ipp.pt");

        AccountID newAccountID = AccountID.of(newEmail);
        AccountID newAccountIDTwo = AccountID.of(newEmailTwo);

        //Assert
        assertNotEquals(newAccountID.hashCode(), newAccountIDTwo.hashCode());
    }


    @Test
    void getID() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);

        //Act
        Email result = newAccountID.getID();
        Email expected = newEmail;

        //Asert
        assertEquals(expected, result);
    }

    @Test
    void sameValueAs() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        AccountID newAccountIDTwo = AccountID.of(newEmail);

        //Act
        boolean result = newAccountID.sameValueAs(newAccountIDTwo);

        //Assert
        assertTrue(result);

    }

    @Test
    void notSameValueAsNull() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);

        //Act
        boolean result = newAccountID.sameValueAs(null);

        //Assert
        assertFalse(result);

    }

    @Test
    void differentValueAs() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        Email newEmailTwo = Email.of("mart@isep.ipp.pt");

        AccountID newAccountID = AccountID.of(newEmail);
        AccountID newAccountIDTwo = AccountID.of(newEmailTwo);

        //Act
        boolean result = newAccountID.sameValueAs(newAccountIDTwo);

        //Assert
        assertFalse(result);

    }

    @Test
    void hasEmailTest() {
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);

        boolean result = newAccountID.hasEmail(newEmail);

        assertTrue(result);


    }
    @Test
    void doeesNotHaveEmailTest() {

        Email email = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(email);
        Email newEmail = Email.of("joao@isep.ipp.pt");

        boolean result = newAccountID.hasEmail(newEmail);

        assertFalse(result);

    }
    @Test
    void doesNotHaveEmailTestNullEmail() {
        Email email = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(email);

        boolean result = newAccountID.hasEmail(null);

        assertFalse(result);

    }
}