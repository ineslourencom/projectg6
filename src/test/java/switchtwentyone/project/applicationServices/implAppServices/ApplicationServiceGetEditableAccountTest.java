package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.services.ServiceAccountLinks;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountShortDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceGetEditableAccountTest {

    @Mock
    AccountRepository accountRepository;
    @Mock
    ServiceAccountLinks serviceAccountLinks;
    @InjectMocks
    ApplicationServiceGetEditableAccount applicationServiceGetEditableAccount;

    @Test
    void getAccountShortInfo_test() {
        //Arrange
        Email testEmail = Email.of("andre@isep.ipp.pt");
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString testFunction = NoNumberNoSymbolString.of("developer");
        Photo testPhoto = Photo.of("XD");
        Account testAccount = mock(Account.class);
        when(accountRepository.findByEmail(testEmail)).thenReturn(Optional.of(testAccount));
        when(testAccount.getEmail()).thenReturn(testEmail);
        when(testAccount.getName()).thenReturn(name);
        when(testAccount.getFunction()).thenReturn(testFunction);
        when(testAccount.getPhoto()).thenReturn(testPhoto);
        Optional<AccountShortDTO> expected = Optional.of(AccountMapper.toShortDTO(testAccount));

        //Act
        Optional<AccountShortDTO> result = applicationServiceGetEditableAccount.getAccountShortInfo(testEmail.getEmailData());

        //Assert
        verify(serviceAccountLinks, atMostOnce()).appendGlobalLinkCollection(testAccount, expected.get());
        assertEquals(expected, result);
    }
}