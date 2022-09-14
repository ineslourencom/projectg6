package switchtwentyone.project.applicationServices.implAppServices;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.dto.AccountAndStatusDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceListAccountsAndStatusTest {

    @Mock
    private AccountRepository IAccountRepo;
    @InjectMocks
    private ApplicationServiceListAccountsAndStatus appService;

    @Test
    void getAllAccountsAndStatus() {
        //Arrange
        List<Account> accounts = new ArrayList<>();
        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);
        Account accountThree = mock(Account.class);
        accounts.add(accountOne);
        accounts.add(accountTwo);
        accounts.add(accountThree);
        when(IAccountRepo.findAll()).thenReturn(accounts);

        List<AccountAndStatusDTO> expected = new ArrayList<>();
        AccountAndStatusDTO dto1 = new AccountAndStatusDTO();
        AccountAndStatusDTO dto2 = new AccountAndStatusDTO();
        AccountAndStatusDTO dto3 = new AccountAndStatusDTO();
        expected.add(dto1);
        expected.add(dto2);
        expected.add(dto3);

        String email1 = "lino@isep.ipp.pt";
        String email2 = "joao@isep.ipp.pt";
        String email3 = "ines@isep.ipp.pt";
        Email emailOne = mock(Email.class);
        Email emailTwo = mock(Email.class);
        Email emailThree = mock(Email.class);
        boolean active1 = false;
        boolean active2 = false;
        boolean active3 = false;

        dto1.email = email1;
        dto2.email = email2;
        dto3.email = email3;
        dto1.active = active1;
        dto2.active = active2;
        dto3.active = active3;

        when(accountOne.getEmail()).thenReturn(emailOne);
        when(accountTwo.getEmail()).thenReturn(emailTwo);
        when(accountThree.getEmail()).thenReturn(emailThree);

        when(emailOne.getEmailData()).thenReturn(email1);
        when(emailTwo.getEmailData()).thenReturn(email2);
        when(emailThree.getEmailData()).thenReturn(email3);

        when(accountOne.getActive()).thenReturn(active1);
        when(accountTwo.getActive()).thenReturn(active2);
        when(accountThree.getActive()).thenReturn(active3);

        //Act
        List<AccountAndStatusDTO> result = appService.getAllAccountsAndStatus();

        //Assert
        assertEquals(expected, result);



    }
}