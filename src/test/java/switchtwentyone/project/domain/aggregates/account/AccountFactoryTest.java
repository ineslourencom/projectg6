package switchtwentyone.project.domain.aggregates.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccountFactoryTest {

    @Test
    void createAccount_Test() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account expected = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        AccountFactory accountFactory = new AccountFactory();

        //Act
        Account actual = accountFactory.createAccount(newAccountID, newEmail, name, function, photo, newPass, profileID);

        //Assert
        assertEquals(expected, actual);
    }
}