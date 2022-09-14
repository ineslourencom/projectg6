package switchtwentyone.project.datamodel;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountJPATest {

    @Test
    void testSameJPAAreEqual() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);
        AccountJPA accountJPA2= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //Act
        boolean result = accountJPA1.equals(accountJPA2);

        //Assert
        assertTrue(result);
    }
    @Test
    void testSameJPAAreEqual_return() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);
        AccountJPA accountJPA2= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);


        //Assert
        assertEquals(accountJPA1, accountJPA1 );
    }

    @Test
    void testSameJPAHaveSameHashCode() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);
        AccountJPA accountJPA2= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //Assert
        assertEquals(accountJPA1.hashCode(), accountJPA1.hashCode());
    }

    @Test
    void testJPANotEqualToNull() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //Act
        boolean result = accountJPA1.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPANotEqualToOtherObject() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        PositiveNumber number = PositiveNumber.of(1);
        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //Act
        boolean result = accountJPA1.equals(number);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPEqualToItself() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //Act
        boolean result = accountJPA1.equals(accountJPA1);

        //Assert
        assertTrue(result);
    }

    @Test
    void testDifferentJPAshaveDifferentHashcodes() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        AccountID accountID2 =  AccountID.of(Email.of("notemail@email.com"));
        String email = "email@email.com";
        String email2 = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);
        AccountJPA accountJPA2= new AccountJPA(accountID2, email2, name, function, photo, password, profileIDs, active);

        //Assert
        assertNotEquals(accountJPA1.hashCode(), accountJPA2.hashCode());
    }



    @Test
    void getAccountID() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //act
        AccountID result= accountJPA1.getAccountID();
        //Assert
        assertEquals(result, accountID);

    }

    @Test
    void getEmail() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //act
        String result= accountJPA1.getEmail();
        //Assert
        assertEquals(result, email);
    }

    @Test
    void getName() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //act
        String result = accountJPA1.getName();
        //Assert
        assertEquals(result, name);
    }

    @Test
    void getFunction() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //act
        String result = accountJPA1.getFunction();
        //Assert
        assertEquals(result, function);
    }

    @Test
    void getPhoto() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //act
        String result = accountJPA1.getPhoto();
        //Assert
        assertEquals(result, photo);
    }

    @Test
    void getPassword() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //act
        String result = accountJPA1.getPassword();
        //Assert
        assertEquals(result, password);
    }

    @Test
    void getProfileIDs() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //act
        List<String> result = accountJPA1.getProfileIDs();
        //Assert
        assertEquals(result, profileIDs);
    }

    @Test
    void isActive() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA accountJPA1= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);

        //act
        boolean result = accountJPA1.isActive();
        //Assert
        assertTrue(result);    ;

    }

    @Test
    void testToString() {
        //Arrange
        AccountID accountID =  AccountID.of(Email.of("email@email.com"));
        String email = "email@email.com";
        String name = "name";
        String function = "function";
        String photo = "photo";
        String password = "***";
        List<String> profileIDs = new ArrayList<>();
        boolean active = true;

        AccountJPA jpa= new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);


        String expected = "AccountJPA(" +
                "accountID=" + accountID.toString() +
                ", email=" + "email@email.com" +
                ", name=" + "name" +
                ", function=" + "function" +
                ", photo=" + "photo" +
                ", password=" + "***" +
                ", profileIDs=" + "[]" +
                ", active=" + "true" +
                ')';
        //act
        String result = jpa.toString();

        assertEquals(expected, result);


    }
}