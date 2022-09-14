package switchtwentyone.project.dto.mapper;


import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.account.Photo;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountAndStatusDTO;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.AccountShortDTO;
import switchtwentyone.project.dto.AccountShortDomainDTO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountMapperTest {

    @Test
    void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<AccountMapper> constructor = AccountMapper.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void toDTO() {
        AccountDTO expected = new AccountDTO();
        expected.accountID = "luis@isep.ipp.pt";
        expected.profileID = "User";
        expected.name = "Luis";
        expected.function = "Engineer";
        expected.email = "luis@isep.ipp.pt";
        expected.photo =":)";
        Account test = mock(Account.class);
        ProfileID testProfileID = mock(ProfileID.class);
        when(testProfileID.getProfileType()).thenReturn(ProfileType.of("User"));
        when(test.getAccountID()).thenReturn(AccountID.of(Email.of("luis@isep.ipp.pt")));
        when(test.getActiveProfileID()).thenReturn(testProfileID);
        when(test.getName()).thenReturn(NoNumberNoSymbolString.of("Luis"));
        when(test.getFunction()).thenReturn(NoNumberNoSymbolString.of("Engineer"));
        when(test.getEmail()).thenReturn(Email.of("luis@isep.ipp.pt"));
        when(test.getPhoto()).thenReturn(Photo.of(":)"));

        AccountDTO result = AccountMapper.toDTO(test);

        assertEquals(expected, result);
    }

    @Test
    void toShortDTO() {
        AccountShortDTO expected = new AccountShortDTO();
        expected.email = "bob@isep.ipp.pt";
        expected.name = "Bob";
        expected.jobTitle = "Minion";
        expected.photo = "8)";
        Account testAccount = mock(Account.class);
        when(testAccount.getEmail()).thenReturn(Email.of("bob@isep.ipp.pt"));
        when(testAccount.getName()).thenReturn(NoNumberNoSymbolString.of("Bob"));
        when(testAccount.getFunction()).thenReturn(NoNumberNoSymbolString.of("Minion"));
        when(testAccount.getPhoto()).thenReturn(Photo.of("8)"));

        AccountShortDTO result = AccountMapper.toShortDTO(testAccount);

        assertEquals(expected, result);
    }

    @Test
    void toShortDomainDTO() {
        AccountShortDomainDTO expected = new AccountShortDomainDTO();
        expected.email = "bob@isep.ipp.pt";
        expected.name = "Bob";
        expected.jobTitle = "Minion";
        expected.photo = "8)";
        Account testAccount = mock(Account.class);
        when(testAccount.getEmail()).thenReturn(Email.of("bob@isep.ipp.pt"));
        when(testAccount.getName()).thenReturn(NoNumberNoSymbolString.of("Bob"));
        when(testAccount.getFunction()).thenReturn(NoNumberNoSymbolString.of("Minion"));
        when(testAccount.getPhoto()).thenReturn(Photo.of("8)"));

        AccountShortDomainDTO result = AccountMapper.toShortDomainDTO(testAccount);

        assertEquals(expected, result);
    }

    @Test
    void toDomainAccountAndStatusDTO () {
        //Arrange
        AccountAndStatusDTO expected = new AccountAndStatusDTO();
        expected.email = "bob@isep.ipp.pt";
        expected.active = false;

        Account testAccount = mock(Account.class);
        when(testAccount.getEmail()).thenReturn(Email.of("bob@isep.ipp.pt"));
        when(testAccount.getActive()).thenReturn(false);

        //Act
        AccountAndStatusDTO result = AccountMapper.toAccountAndStatusDTO(testAccount);

        //Assert
        assertEquals(expected,result);
    }
}