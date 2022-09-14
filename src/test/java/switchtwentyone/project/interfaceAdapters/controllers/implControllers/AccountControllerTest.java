package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.implAppServices.*;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.account.Photo;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.*;
import switchtwentyone.project.dto.mapper.AccountMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@Transactional
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @Mock
    ApplicationServiceGetAccount applicationServiceGetAccount;
    @Mock
    ApplicationServiceGetEditableAccount applicationServiceGetEditableAccount;
    @Mock
    ApplicationServiceUpdateAccount applicationServiceUpdateAccount;
    @Mock
    ApplicationServiceListAccountsAndStatus appService;
    @InjectMocks
    AccountController accountController;

   /* @Test
    void createAccountSuccess(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NewAccountInfoDTO infoDTO = mock(NewAccountInfoDTO.class);

        when(infoDTO.getEmail()).thenReturn("lino@isep.ipp.pt");
        when(infoDTO.getName()).thenReturn("Lino");
        when(infoDTO.getFunction()).thenReturn("Engineer");
        when(infoDTO.getPassword()).thenReturn("1234abcde");
        when(infoDTO.getPhoto()).thenReturn("1234abcde");


        ProfileID profileID = new ProfileID(ProfileType.of("Visitor"));
        Profile profile = mock(Profile.class);
        when(profileRepo.findProfileByProfileType("Visitor")).thenReturn(profile);
        when(profile.getProfileType()).thenReturn(profileID);

        Email email = Email.of("lino@isep.ipp.pt");
        AccountID ID = AccountID.of(email);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Engineer");
        Photo photo = Photo.of("1234abcd");
        Password password = Password.of("1234abcde", 1);

        Account account = mock(Account.class);
        when(account.getEmail()).thenReturn(email);
        when(account.getName()).thenReturn(name);
        when(account.getFunction()).thenReturn(function);
        when(account.getPhoto()).thenReturn(photo);
        when(account.getAccountID()).thenReturn(ID);
        when(account.getActiveProfileID()).thenReturn(profileID);

        Optional<AccountDTO> expected = Optional.of(AccountMapper.toDTO(account));

        when(appServiceCreateAccount.createNewAccount(email,password,function, name, photo)).thenReturn(expected);

        //Act
        ResponseEntity<Object> responseEntity = createAccountController.createAccount(infoDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }*/


    @Test
    void createAccount_Fail() {
        NewAccountInfoDTO infoDTO = mock(NewAccountInfoDTO.class);

        //Act
        ResponseEntity<Object> responseEntity = accountController.createAccount(infoDTO);

        //Assert
        assertEquals(400, responseEntity.getStatusCodeValue());

    }


    @Test
    void findAccountInfo_long_ReturnInformation() {
        // Arrange
        Email testEmail = Email.of("luis@isep.ipp.pt");
        AccountID testAccountID = AccountID.of(testEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Luis");
        Photo testPhoto = Photo.of(":)");
        NoNumberNoSymbolString testFunction = NoNumberNoSymbolString.of("Engineer");
        ProfileID testProfileID = mock(ProfileID.class);
        when(testProfileID.getProfileType()).thenReturn(ProfileType.of("User"));

        Account testAccount = mock(Account.class);
        when(testAccount.getAccountID()).thenReturn(testAccountID);
        when(testAccount.getEmail()).thenReturn(testEmail);
        when(testAccount.getName()).thenReturn(name);
        when(testAccount.getPhoto()).thenReturn(testPhoto);
        when(testAccount.getFunction()).thenReturn(testFunction);
        when(testAccount.getActiveProfileID()).thenReturn(testProfileID);
        AccountDTO testDTO = AccountMapper.toDTO(testAccount);

        when(applicationServiceGetAccount.getAccountInfo("luis@isep.ipp.pt")).thenReturn(Optional.of(testDTO));
        ResponseEntity expected = ResponseEntity.status(HttpStatus.OK).body(testDTO);
        //Act
        ResponseEntity result = accountController.findAccountInfo("luis@isep.ipp.pt", "long");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void findAccountInfo_long_NoReturn() {
        // Arrange

        Email testEmail = Email.of("luis@isep.ipp.pt");
        AccountID testAccountID = AccountID.of(testEmail);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Luis");
        Photo testPhoto = Photo.of(":)");
        NoNumberNoSymbolString testFunction = NoNumberNoSymbolString.of("Engineer");
        ProfileID testProfileID = mock(ProfileID.class);

        Account testAccount = mock(Account.class);

        ResponseEntity expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        //Act
        ResponseEntity result = accountController.findAccountInfo("lino@isep.ipp.pt", "long");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void findAccountInfo_long_ExceptionTest() {
        // Arrange

        IllegalArgumentException exception = new IllegalArgumentException("Invalid Email");

        when(applicationServiceGetAccount.getAccountInfo("luisisep.ipp.pt")).thenThrow(exception);
        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        //Act
        ResponseEntity result = accountController.findAccountInfo("luisisep.ipp.pt", "long");
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getAccountDetails_Test_short_Success() {
        Email testEmail = Email.of("luis@isep.ipp.pt");
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Luis");
        Photo testPhoto = Photo.of(":)");
        NoNumberNoSymbolString testFunction = NoNumberNoSymbolString.of("Developer");
        Account testAccount = mock(Account.class);
        when(testAccount.getEmail()).thenReturn(testEmail);
        when(testAccount.getName()).thenReturn(name);
        when(testAccount.getPhoto()).thenReturn(testPhoto);
        when(testAccount.getFunction()).thenReturn(testFunction);
        AccountShortDTO testShortDTO = AccountMapper.toShortDTO(testAccount);
        when(applicationServiceGetEditableAccount.getAccountShortInfo("luis@isep.ipp.pt")).thenReturn(Optional.of(testShortDTO));
        ResponseEntity expected = ResponseEntity.status(HttpStatus.OK).body(testShortDTO);

        ResponseEntity result = accountController.findAccountInfo("luis@isep.ipp.pt", "short");

        assertEquals(expected,result);
    }

    @Test
    void getAccountDetails_Test_short_Fail_NoReturn() {
        Email testEmail = Email.of("luis@isep.ipp.pt");
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Luis");
        Photo testPhoto = Photo.of(":)");
        NoNumberNoSymbolString testFunction = NoNumberNoSymbolString.of("Developer");
        Account testAccount = mock(Account.class);
        when(applicationServiceGetEditableAccount.getAccountShortInfo("lino@isep.ipp.pt")).thenReturn(Optional.empty());
        ResponseEntity expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        ResponseEntity result = accountController.findAccountInfo("lino@isep.ipp.pt", "short");

        assertEquals(expected,result);
    }

    @Test
    void getAccountDetails_Test_short_Fail_ExceptionTest() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid Email");
        when(applicationServiceGetEditableAccount.getAccountShortInfo("luisisep.ipp.pt")).thenThrow(exception);
        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

        ResponseEntity result = accountController.findAccountInfo("luisisep.ipp.pt", "short");

        assertEquals(expected,result);
    }

    @Test
    void getAccountDetails_Test_short_Fail_BadQuery() {
        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("code #001");

        ResponseEntity result = accountController.findAccountInfo("lino@isep.ipp.pt", "wrong");

        assertEquals(expected,result);
    }


    @Test
    void updateAccount_Test_Success() {
        AccountShortDomainDTO newDetailsDTO = new AccountShortDomainDTO();
        newDetailsDTO.email = "luis@isep.ipp.pt";
        newDetailsDTO.name = "Luis";
        newDetailsDTO.jobTitle = "Developer";
        newDetailsDTO.photo = ":)";
        AccountShortDTO testShortDTO = new AccountShortDTO();
        testShortDTO.email = "luis@isep.ipp.pt";
        testShortDTO.name = "Luis";
        testShortDTO.jobTitle = "Developer";
        testShortDTO.photo = ":)";
        when(applicationServiceUpdateAccount.updateAccount(newDetailsDTO)).thenReturn(Optional.of(testShortDTO));
        ResponseEntity expected = ResponseEntity.status(HttpStatus.OK).body(testShortDTO);

        ResponseEntity result = accountController.updateAccount("luis@isep.ipp.pt", newDetailsDTO, "short");

        assertEquals(expected,result);
    }

    @Test
    void updateAccount_Test_Fail_NoReturn() {
        AccountShortDomainDTO newDetailsDTO = new AccountShortDomainDTO();
        newDetailsDTO.email = "luis@isep.ipp.pt";
        newDetailsDTO.name = "Luis";
        newDetailsDTO.jobTitle = "Developer";
        newDetailsDTO.photo = ":)";
        Optional<AccountShortDTO> optTestShortDTO = Optional.empty();
        when(applicationServiceUpdateAccount.updateAccount(newDetailsDTO)).thenReturn(optTestShortDTO);
        ResponseEntity expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        ResponseEntity result = accountController.updateAccount("luis@isep.ipp.pt", newDetailsDTO, "short");

        assertEquals(expected,result);
    }

    @Test
    void updateAccount_Test_Fail_ExceptionTest() {
        AccountShortDomainDTO newDetailsDTO = new AccountShortDomainDTO();
        newDetailsDTO.email = "luisisep.ipp.pt";
        newDetailsDTO.name = "Luis";
        newDetailsDTO.jobTitle = "Developer";
        newDetailsDTO.photo = ":)";
        IllegalArgumentException exception = new IllegalArgumentException("Invalid Email");
        when(applicationServiceUpdateAccount.updateAccount(newDetailsDTO)).thenThrow(new IllegalArgumentException("Invalid Email"));
        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

        ResponseEntity result = accountController.updateAccount("luisisep.ipp.pt", newDetailsDTO, "short");

        assertEquals(expected,result);
    }

    @Test
    void updateAccount_Test_Fail_Exception_EmailMissMatch() {
        AccountShortDomainDTO newDetailsDTO = new AccountShortDomainDTO();
        newDetailsDTO.email = "luis@isep.ipp.pt";
        newDetailsDTO.name = "Luis";
        newDetailsDTO.jobTitle = "Developer";
        newDetailsDTO.photo = ":)";
        IllegalArgumentException exception = new IllegalArgumentException("Security error");
        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

        ResponseEntity result = accountController.updateAccount("kevin@isep.ipp.pt", newDetailsDTO, "short");

        assertEquals(expected,result);
    }



    @Test
    void getListOfAccounts(){
        //Arrange
        List<AccountAndStatusDTO> accountsList = new ArrayList<>();
        AccountAndStatusDTO dtoOne = mock(AccountAndStatusDTO.class);
        AccountAndStatusDTO dtoTwo = mock(AccountAndStatusDTO.class);
        AccountAndStatusDTO dtoThree = mock(AccountAndStatusDTO.class);
        accountsList.add(dtoOne);
        accountsList.add(dtoTwo);
        accountsList.add(dtoThree);
        when(appService.getAllAccountsAndStatus()).thenReturn(accountsList);
        ResponseEntity<Object> expected = new ResponseEntity<>(accountsList, HttpStatus.OK);
        ResponseEntity<Object> result = accountController.getListOfAllAccountsAndStatus();
        assertEquals(expected,result);


    }


}