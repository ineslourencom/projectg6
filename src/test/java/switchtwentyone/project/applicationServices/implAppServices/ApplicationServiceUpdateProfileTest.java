package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceUpdateProfileTest {

    @Mock
    ApplicationServiceUpdateAccount applicationServiceUpdateAccount;
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    ApplicationServiceUpdateProfile applicationServiceUpdateProfile;

    @Test
    void updateProfileTrue() {

        Email emailOne = Email.of("luis@gmail.com");
        AccountID newAccountID = AccountID.of(emailOne);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        ProfileID profileID = ProfileID.ofProfileType("Director");
        Account account = mock(Account.class);
        when(account.getAccountID()).thenReturn(newAccountID);
        when(account.getEmail()).thenReturn(emailOne);
        when(account.getName()).thenReturn(name);
        when(account.getFunction()).thenReturn(function);
        when(account.getPhoto()).thenReturn(photo);
        when(account.getActiveProfileID()).thenReturn(profileID);
        Optional<Account> accountOptional = Optional.of(account);
        when(accountRepository.findByEmail(emailOne)).thenReturn(accountOptional);
        when(applicationServiceUpdateAccount.updateAccount(accountOptional, profileID)).thenReturn(accountOptional);
        Optional<AccountDTO> expectedOne = Optional.of(AccountMapper.toDTO(account));

        Optional<AccountDTO> result = applicationServiceUpdateProfile.updateProfile(emailOne, profileID);

        assertEquals(result, expectedOne);

    }
}