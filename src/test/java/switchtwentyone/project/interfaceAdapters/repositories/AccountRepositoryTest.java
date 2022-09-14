package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.datamodel.AccountJPA;
import switchtwentyone.project.datamodel.assembler.AccountDomainDataAssembler;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IAccountJPARepository;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepo;
    @MockBean
    AccountDomainDataAssembler AccountAssembler;
    @MockBean
    IAccountJPARepository IAccountJPARepository;

    @Test
    void createAndSaveAccount_Success() {
        Email email = Email.of("lino@isep.ipp.pt");
        AccountID accountID = AccountID.of(email);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Engineer");
        Photo photo = Photo.of("1234abcd");
        Password password = Password.of("1234abcde", 1);
        ProfileID profileID = new ProfileID(ProfileType.of("Visitor"));

        Optional<AccountDTO> result = accountRepo.createAndSaveAccount(accountID, email, name, function, photo, password, profileID);

        Account account = mock(Account.class);
        when(account.getEmail()).thenReturn(email);
        when(account.getName()).thenReturn(name);
        when(account.getFunction()).thenReturn(function);
        when(account.getPhoto()).thenReturn(photo);
        when(account.getAccountID()).thenReturn(accountID);
        when(account.getActiveProfileID()).thenReturn(profileID);

        Optional<AccountDTO> expected = Optional.of(AccountMapper.toDTO(account));

        assertEquals(expected, result);
    }

    @Test
    void existsByAccountIDTest() {
        AccountID accountID = mock(AccountID.class);
        when(IAccountJPARepository.existsById(accountID)).thenReturn(true);

        boolean result = accountRepo.existsByAccountID(accountID);

        assertTrue(result);
    }

    @Test
    void doesNotExistByAccountIDTest() {
        AccountID accountID = mock(AccountID.class);
        when(IAccountJPARepository.existsById(accountID)).thenReturn(false);

        boolean result = accountRepo.existsByAccountID(accountID);

        assertFalse(result);
    }

    @Test
    void findAllAccounts() {
        //Arrange
        AccountJPA accountJPAOne = mock(AccountJPA.class);
        AccountJPA accountJPATwo = mock(AccountJPA.class);
        AccountJPA accountJPAThree = mock(AccountJPA.class);
        List<AccountJPA> accountsJPA = new ArrayList<>();
        accountsJPA.add(accountJPAOne);
        accountsJPA.add(accountJPATwo);
        accountsJPA.add(accountJPAThree);

        Account accountOne = mock(Account.class);
        Account accountTwo = mock(Account.class);
        Account accountThree = mock(Account.class);
        List<Account> expected = new ArrayList<>();
        expected.add(accountOne);
        expected.add(accountTwo);
        expected.add(accountThree);

        when(IAccountJPARepository.findAll()).thenReturn(accountsJPA);
        when(AccountAssembler.toDomain(accountJPAOne)).thenReturn(accountOne);
        when(AccountAssembler.toDomain(accountJPATwo)).thenReturn(accountTwo);
        when(AccountAssembler.toDomain(accountJPAThree)).thenReturn(accountThree);

        //Act
        List<Account> result = accountRepo.findAll();

        //Assert
        assertEquals(expected, result);



    }



}

