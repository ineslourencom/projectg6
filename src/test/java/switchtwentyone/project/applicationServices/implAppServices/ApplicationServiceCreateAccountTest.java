package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceCreateAccountTest {

    @Mock
    AccountRepository accountRepository;
    @Mock
    ProfileRepository profileRepository;
    @InjectMocks
    ApplicationServiceCreateAccount appServiceCreateAccount;

    @Test
    void createNewAccount() {
        //Arrange
        Email email = Email.of("lino@isep.ipp.pt");
        AccountID accountID = AccountID.of(email);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Engineer");
        Photo photo = Photo.of("1234abcd");
        Password password = Password.of("1234abcde", 1);
        ProfileID profileID = new ProfileID(ProfileType.of("Visitor"));
        Profile profile = mock(Profile.class);
        Optional<Profile> optProfile = Optional.of(profile);
        when(profileRepository.findProfileById(ProfileID.ofProfileType("Visitor"))).thenReturn(optProfile);
        when(optProfile.get().getProfileID()).thenReturn(profileID);
        Account account = mock(Account.class);
        when(account.getEmail()).thenReturn(email);
        when(account.getName()).thenReturn(name);
        when(account.getFunction()).thenReturn(function);
        when(account.getPhoto()).thenReturn(photo);
        when(account.getAccountID()).thenReturn(accountID);
        when(account.getActiveProfileID()).thenReturn(profileID);
        Optional<AccountDTO> expected = Optional.of(AccountMapper.toDTO(account));
        when(accountRepository.createAndSaveAccount(accountID, email, name, function, photo, password, profileID)).thenReturn(expected);

        Optional<AccountDTO> result = appServiceCreateAccount.createNewAccount(email, name, function, photo, password);

        assertEquals(expected, result);
    }

    @Test
    void ensureExceptionIsThrownWhenVisitorProfileCannotBeFound(){
        Email email = Email.of("lino@isep.ipp.pt");
        AccountID accountID = AccountID.of(email);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Engineer");
        Photo photo = Photo.of("1234abcd");
        Password password = Password.of("1234abcde", 1);
        ProfileID profileID = new ProfileID(ProfileType.of("Visitor"));
        Profile profile = mock(Profile.class);

        when(profileRepository.findProfileById(ProfileID.ofProfileType("Visitor"))).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, ()->appServiceCreateAccount.createNewAccount(email, name, function, photo, password));

    }
}
