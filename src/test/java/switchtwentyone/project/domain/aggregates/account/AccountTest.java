package switchtwentyone.project.domain.aggregates.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestCreatable;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountTest {

    @Autowired
    AccountCreatable accountCreatable;
    @Test
    void accountsWithSameIDAreEqual_equals() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        Password newPassTwo = Password.of("teste456", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccountOne = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        Account newAccountTwo = new Account(newAccountID, newEmail, name, function, photo, newPassTwo, profileID);

        //Act
        boolean result = newAccountOne.equals(newAccountTwo);

        //Assert
        assertTrue(result);
    }

    @Test
    void equals_accountsWithSameIDAreEqual_JPAConstructor() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        Password newPassTwo = Password.of("teste456", 1);
        ProfileID profileIDOne = ProfileID.ofProfileType("Visitor");
        ProfileID profileIDTwo = ProfileID.ofProfileType("User");
        List<ProfileID> profileIDs = new ArrayList<>();
        profileIDs.add(profileIDOne);
        profileIDs.add(profileIDTwo);
        Account newAccountOne = new Account(newAccountID, newEmail, name, function, photo, newPass, profileIDs, true);
        Account newAccountTwo = new Account(newAccountID, newEmail, name, function, photo, newPassTwo, profileIDs, true);

        //Act
        boolean result = newAccountOne.equals(newAccountTwo);

        //Assert
        assertTrue(result);
    }

    @Test
    void accountsWithSameIDAreEqual_sameIdentityAs() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        Password newPassTwo = Password.of("teste456", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccountOne = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        Account newAccountTwo = new Account(newAccountID, newEmail, name, function, photo, newPassTwo, profileID);

        //Act
        boolean result = newAccountOne.sameIdentityAs(newAccountTwo);

        //Assert
        assertTrue(result);
    }

    @Test
    void accountNotEqualToOtherObject_equals() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        Integer other = Integer.valueOf(20);

        //Act
        boolean result = newAccount.equals(other);

        //Assert
        assertFalse(result);
    }

    @Test
    void accountEqualsItself_equals() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);

        //Act
        boolean result = newAccount.equals(newAccount);

        //Assert
        assertTrue(result);
    }

    @Test
    void accountEqualsItself_sameIdentityAs() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);

        //Act
        boolean result = newAccount.sameIdentityAs(newAccount);

        //Assert
        assertTrue(result);
    }

    @Test
    void accountsWithSameIDHaveSameHashCode() {
        //Arrange
        Email newEmailOne = Email.of("marta@isep.ipp.pt");
        Email newEmailTwo = Email.of("salome@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmailOne);
        NoNumberNoSymbolString nameOne = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString nameTwo = NoNumberNoSymbolString.of("João");
        NoNumberNoSymbolString functionOne = NoNumberNoSymbolString.of("Dev");
        NoNumberNoSymbolString functionTwo = NoNumberNoSymbolString.of("Dev");
        Photo photoOne = Photo.of(":)");
        Photo photoTwo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        Password newPassTwo = Password.of("teste456", 1);
        ProfileID profileIDOne = mock(ProfileID.class);
        ProfileID profileIDTwo = mock(ProfileID.class);
        Account newAccountOne = new Account(newAccountID, newEmailOne, nameOne, functionOne, photoOne, newPass, profileIDOne);
        Account newAccountTwo = new Account(newAccountID, newEmailTwo, nameTwo, functionTwo, photoTwo, newPassTwo, profileIDTwo);

        //Act
        int resultOne = newAccountOne.hashCode();
        int resultTwo = newAccountTwo.hashCode();

        //Assert
        assertEquals(resultOne, resultTwo);
    }

    @Test
    void accountsWithDifferentIDAreDifferent_equals() {
        //Arrange
        Email newEmailOne = Email.of("marta@isep.ipp.pt");
        Email newEmailTwo = Email.of("salome@isep.ipp.pt");
        AccountID newAccountIDOne = AccountID.of(newEmailOne);
        AccountID newAccountIDTwo = AccountID.of(newEmailTwo);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountIDOne, newEmailOne, name, function, photo, newPass, profileID);
        Account newAccountTwo = new Account(newAccountIDTwo, newEmailOne, name, function, photo, newPass, profileID);

        //Act
        boolean result = newAccount.equals(newAccountTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    void accountsWithDifferentIDAreDifferent_sameIdentityAs() {
        //Arrange
        Email newEmailOne = Email.of("marta@isep.ipp.pt");
        Email newEmailTwo = Email.of("salome@isep.ipp.pt");
        AccountID newAccountIDOne = AccountID.of(newEmailOne);
        AccountID newAccountIDTwo = AccountID.of(newEmailTwo);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccountOne = new Account(newAccountIDOne, newEmailOne, name, function, photo, newPass, profileID);
        Account newAccountTwo = new Account(newAccountIDTwo, newEmailOne, name, function, photo, newPass, profileID);

        //Act
        boolean result = newAccountOne.sameIdentityAs(newAccountTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    void accountsWithDifferentIDHaveDifferentHashCode() {
        //Arrange
        Email newEmailOne = Email.of("marta@isep.ipp.pt");
        Email newEmailTwo = Email.of("salome@isep.ipp.pt");
        AccountID newAccountIDOne = AccountID.of(newEmailOne);
        AccountID newAccountIDTwo = AccountID.of(newEmailTwo);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccountOne = new Account(newAccountIDOne, newEmailOne, name, function, photo, newPass, profileID);
        Account newAccountTwo = new Account(newAccountIDTwo, newEmailOne, name, function, photo, newPass, profileID);

        //Act
        int resultOne = newAccountOne.hashCode();
        int resultTwo = newAccountTwo.hashCode();

        //Assert
        assertNotEquals(resultOne, resultTwo);
    }

    @Test
    void sameIdentity_Test_Null() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);

        //Act
        boolean result = newAccount.sameIdentityAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void equals_Test_Null() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);

        //Act
        boolean result = newAccount.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void getAccountID() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        AccountID newAccountIDExpected = AccountID.of(newEmail);

        //Act
        AccountID newAccountIDResult = newAccount.getAccountID();

        //Assert
        assertEquals(newAccountIDExpected, newAccountIDResult);
    }

    @Test
    void createAccount_Test_NullAccountID() {
        //Arrange
        AccountID newAccountID = null;
        Email newEmail = Email.of("marta@isep.ipp.pt");
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);

        //Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        });

        //Assert
        Assertions.assertEquals("AccountID is required", thrown.getMessage());
    }

    @Test
    void createAccount_Test_NullEmail() {
        //Arrange
        Email newEmailOne = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmailOne);
        Email newEmail = null;
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);

        //Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        });

        //Assert
        Assertions.assertEquals("Email is required", thrown.getMessage());
    }

    @Test
    void createAccount_Test_NullName() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = null;
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);

        //Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        });

        //Assert
        Assertions.assertEquals("Name is required", thrown.getMessage());
    }

    @Test
    void createAccount_Test_NullFunction() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = null;
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);

        //Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        });

        //Assert
        Assertions.assertEquals("Function is required", thrown.getMessage());
    }

    @Test
    void createAccount_Test_NullPhoto() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = null;
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);

        //Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        });

        //Assert
        Assertions.assertEquals("Photo is required", thrown.getMessage());
    }

    @Test
    void createAccount_Test_NullPassword() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = null;
        ProfileID profileID = mock(ProfileID.class);

        //Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        });

        //Assert
        Assertions.assertEquals("Password is required", thrown.getMessage());
    }

    @Test
    void createAccount_Test_NullProfileID() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = null;

        //Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        });

        //Assert
        Assertions.assertEquals("ProfileID is required", thrown.getMessage());
    }

    @Test
    void getName() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        NoNumberNoSymbolString newNameExpected = NoNumberNoSymbolString.of("Lino");

        //Act
        NoNumberNoSymbolString newNameResult = newAccount.getName();

        //Assert
        assertEquals(newNameExpected, newNameResult);
    }

    @Test
    void getEmail() {
        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        Email newEmailExpected = Email.of("marta@isep.ipp.pt");

        //Act
        Email newEmailResult = newAccount.getEmail();

        //Assert
        assertEquals(newEmailExpected, newEmailResult);

    }

    @Test
    void getFunction() {

        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        NoNumberNoSymbolString newFunctionExpected = NoNumberNoSymbolString.of("Dev");

        //Act
        NoNumberNoSymbolString newFunctionResult = newAccount.getFunction();

        //Assert
        assertEquals(newFunctionExpected, newFunctionResult);
    }

    @Test
    void getPhoto() {

        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);
        Photo newPhotoExpected = Photo.of(":)");

        //Act
        Photo newPhotoResult = newAccount.getPhoto();

        //Assert
        assertEquals(newPhotoExpected, newPhotoResult);
    }

    @Test
    void getProfileIDs() {
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID newProfileIDExpected = new ProfileID(ProfileType.of("User"));

        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, newProfileIDExpected);

        List<ProfileID> profileIDList = new ArrayList<>();
        profileIDList.add(newProfileIDExpected);

        //Act
        ProfileID newProfileIDResult = newAccount.getActiveProfileID();

        //Assert
        assertEquals(newProfileIDExpected, newProfileIDResult);
    }

    @Test
    void emailExistsReturnTrue() {

        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);

        //act
        boolean result = newAccount.emailExists(newEmail);

        //assert
        assertTrue(result);
    }

    @Test
    void emailExistsReturnFalse() {

        //Arrange
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account newAccount = new Account(newAccountID, newEmail, name, function, photo, newPass, profileID);

        Email notEmail = Email.of("luis@isep.ipp.pt");

        //act
        boolean result = newAccount.emailExists(notEmail);

        //assert
        assertFalse(result);
    }

    @Test
    void setFunction_success() {
        //Arrange
        Email testEmail = Email.of("kevin@isep.ipp.pt");
        AccountID testAccountID = AccountID.of(testEmail);
        NoNumberNoSymbolString testName = NoNumberNoSymbolString.of("Kevin");
        NoNumberNoSymbolString testFunction = NoNumberNoSymbolString.of("Minion");
        Photo testPhoto = Photo.of("-8)");
        Password testPass = Password.of("B4n4n4!!", 1);
        ProfileID testProfileID = mock(ProfileID.class);
        Account testAccount = new Account(testAccountID, testEmail, testName, testFunction, testPhoto, testPass, testProfileID);
        NoNumberNoSymbolString newFunction = NoNumberNoSymbolString.of("Dev Minion");

        //act
        testAccount.setFunction(newFunction);
        boolean result = testAccount.getFunction().equals(newFunction);

        //assert
        assertTrue(result);
    }

    @Test
    void setPhoto_success() {
        //Arrange
        Email testEmail = Email.of("kevin@isep.ipp.pt");
        AccountID testAccountID = AccountID.of(testEmail);
        NoNumberNoSymbolString testName = NoNumberNoSymbolString.of("Kevin");
        NoNumberNoSymbolString testFunction = NoNumberNoSymbolString.of("Minion");
        Photo testPhoto = Photo.of("-8)");
        Password testPass = Password.of("B4n4n4!!", 1);
        ProfileID testProfileID = mock(ProfileID.class);
        Account testAccount = new Account(testAccountID, testEmail, testName, testFunction, testPhoto, testPass, testProfileID);
        Photo newPhoto = Photo.of("-8o");

        //act
        testAccount.setPhoto(newPhoto);
        boolean result = testAccount.getPhoto().equals(newPhoto);

        //assert
        assertTrue(result);
    }

    @Test
    void createRequest() {
        Email emailOne = mock(Email.class);
        AccountID newAccountID = AccountID.of(emailOne);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        ProfileID profileID = mock(ProfileID.class);
        ProfileType profileType = mock(ProfileType.class);
        Password newPass = Password.of("teste123", 1);
        ProfileRequestID profReqOne = mock(ProfileRequestID.class);



        ProfileRequest profileRequest = new ProfileRequest(profReqOne, emailOne, profileType);
        ProfileRequest newProfReq = ProfileRequestCreatable.createProfileRequest(profReqOne, emailOne, profileType);

        assertEquals(profileRequest, newProfReq);
    }

    @Test
    void addProfileInvalid() {
        Email emailOne = mock(Email.class);
        AccountID newAccountID = AccountID.of(emailOne);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        Account account = accountCreatable.createAccount(newAccountID,emailOne,name,function,photo,newPass,profileID);

        account.addProfile(profileID);

        assertEquals(account.getProfileIDs().size(),1);
        }

    @Test
    void addProfile() {
        Email emailOne = mock(Email.class);
        AccountID newAccountID = AccountID.of(emailOne);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);
        ProfileID profileIDTwo = mock(ProfileID.class);
        ProfileID profileIDThree = mock(ProfileID.class);

        Account account = accountCreatable.createAccount(newAccountID,emailOne,name,function,photo,newPass,profileID);

        account.getProfileIDs().add(profileID);
        account.getProfileIDs().add(profileIDTwo);
        account.addProfile(profileIDThree);

        assertEquals(account.getProfileIDs().size(),2);
    }

    @Test
    void getActive() {
        Email emailOne = mock(Email.class);
        AccountID newAccountID = AccountID.of(emailOne);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        Password newPass = Password.of("teste123", 1);
        ProfileID profileID = mock(ProfileID.class);

        Account account = accountCreatable.createAccount(newAccountID,emailOne,name,function,photo,newPass,profileID);

        boolean result = account.getActive();

        assertFalse(result);


    }
}
