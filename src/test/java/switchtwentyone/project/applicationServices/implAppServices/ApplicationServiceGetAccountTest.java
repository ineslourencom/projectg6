package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.account.Photo;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
class ApplicationServiceGetAccountTest {

    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    ApplicationServiceGetAccount applicationServiceGetAccount;

    @Test
    void getAccountInfoTest() {
        //Arrange
        Email testEmail = Email.of("luis@isep.ipp.pt");
        AccountID testAccountID = AccountID.of(testEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Luis");
        Photo testPhoto = Photo.of(":)");
        NoNumberNoSymbolString testFunction = NoNumberNoSymbolString.of("Engineer");
        ProfileID testProfileID = mock(ProfileID.class);
        when(testProfileID.getProfileType()).thenReturn(ProfileType.of("User"));
        Account testAccount = mock(Account.class);
        when(accountRepository.findByEmail(testEmail)).thenReturn(Optional.of(testAccount));
        when(testAccount.getAccountID()).thenReturn(testAccountID);
        when(testAccount.getEmail()).thenReturn(testEmail);
        when(testAccount.getName()).thenReturn(name);
        when(testAccount.getPhoto()).thenReturn(testPhoto);
        when(testAccount.getFunction()).thenReturn(testFunction);
        when(testAccount.getActiveProfileID()).thenReturn(testProfileID);
        Optional<AccountDTO> expected = Optional.of(AccountMapper.toDTO(testAccount));
        //Act
        Optional<AccountDTO> result = applicationServiceGetAccount.getAccountInfo(testEmail.getEmailData());
        //Assert
        assertEquals(expected, result);

    }


}