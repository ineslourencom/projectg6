package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.account.Photo;
import switchtwentyone.project.domain.services.ServiceAccountLinks;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountShortDTO;
import switchtwentyone.project.dto.AccountShortDomainDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceUpdateAccountTest {

    @Mock
    AccountRepository accountRepositoryOne;
    @Mock
    ServiceAccountLinks serviceAccountLinks;
    @InjectMocks
    ApplicationServiceUpdateAccount applicationServiceUpdateAccount;

    @Test
    void updateAccount_test_success() throws Exception {
        AccountShortDomainDTO domainDTO = new AccountShortDomainDTO();
        domainDTO.email = "kevin@isep.ipp.pt";
        domainDTO.name = "Kevin";
        domainDTO.jobTitle = "Minion Master";
        domainDTO.photo = "-8o";
        Account testAccount = mock(Account.class);
        Optional<Account> optTestAccount = Optional.of(testAccount);
        Email searchEmail = Email.of(domainDTO.email);
        when(accountRepositoryOne.findByEmail(searchEmail)).thenReturn(optTestAccount);
        when(accountRepositoryOne.update(optTestAccount)).thenReturn(optTestAccount);
        when(testAccount.getEmail()).thenReturn(Email.of(domainDTO.email));
        when(testAccount.getName()).thenReturn(NoNumberNoSymbolString.of(domainDTO.name));
        when(testAccount.getFunction()).thenReturn(NoNumberNoSymbolString.of(domainDTO.jobTitle));
        when(testAccount.getPhoto()).thenReturn(Photo.of(domainDTO.photo));
        Optional<AccountShortDTO> expected = Optional.of(AccountMapper.toShortDTO(testAccount));

        Optional<AccountShortDTO> result = applicationServiceUpdateAccount.updateAccount(domainDTO);

        verify(serviceAccountLinks, atMostOnce()).appendGlobalLinkCollection(testAccount, expected.get());
        assertEquals(expected, result);
    }

    @Test
    void updateAccount_test_fail_AccountNotFound() throws Exception {
        AccountShortDomainDTO domainDTO = new AccountShortDomainDTO();
        domainDTO.email = "salome@isep.ipp.pt";
        domainDTO.name = "Kevin";
        domainDTO.jobTitle = "Minion Master";
        domainDTO.photo = "-8o";
        Email searchEmail = Email.of(domainDTO.email);
        Optional<Account> optAccountEmpty = Optional.empty();
        when(accountRepositoryOne.findByEmail(searchEmail)).thenReturn(optAccountEmpty);
        Optional<AccountShortDTO> expected = Optional.empty();

        Optional<AccountShortDTO> result = applicationServiceUpdateAccount.updateAccount(domainDTO);

        assertEquals(expected, result);
    }

}
