package switchtwentyone.project.applicationServices.implAppServices;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IAccountJPARepository;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;
import switchtwentyone.project.dto.NewProfileRequestInfoDTO;
import switchtwentyone.project.dto.ProfileRequestDTO;
import switchtwentyone.project.dto.mapper.ProfileRequestMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRequestRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
@AutoConfigureMockMvc
class ApplicationServiceCreateProfileRequestTest {


    @InjectMocks
    ApplicationServiceCreateProfileRequest applicationServiceCreateProfileRequest;
    @Mock
    IAccountJPARepository accountJPARepo;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ProfileRequestRepository profileRequestRepo;

    @Test
    void createProfileRequest() {
        //Arrange
        //newinfoDTO - in:
        NewProfileRequestInfoDTO inputDTO = mock(NewProfileRequestInfoDTO.class);
        inputDTO.profileType = "Visitor";
        inputDTO.email = "lino@isep.ipp.pt";
        when(inputDTO.getEmail()).thenReturn("lino@isep.ipp.pt");
        when(inputDTO.getProfileType()).thenReturn("Visitor");

        //account info
        Email email = Email.of("lino@isep.ipp.pt");
        ProfileType profileType = ProfileType.of("Visitor");
        AccountID accntID = AccountID.of(email);
        Account account = mock(Account.class);
        Optional<Account> optAccount = Optional.of(account);
        when(accountRepository.findByAccountID(accntID)).thenReturn(optAccount);
        ProfileRequest profileRequest = mock(ProfileRequest.class);
        ProfileRequestID profileRequestID1 = ProfileRequestID.createProfileRequestID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");
        when(account.createRequest(email, profileType)).thenReturn(profileRequest);
        when(profileRequestRepo.saveProfileRequest(profileRequest)).thenReturn(true);
        when(profileRequest.getProfileRequestID()).thenReturn(profileRequestID1);
        when(profileRequest.getEmail()).thenReturn(email);
        when(profileRequest.getProfileType()).thenReturn(profileType);
        ProfileRequestDTO expected = ProfileRequestMapper.toDTO(profileRequest);

        //Act
        ProfileRequestDTO result = applicationServiceCreateProfileRequest.createProfileRequest(inputDTO);

        //Assert
        assertEquals(expected, result);


    }
    @Test
    void ensureExceptionIsThrownWhenAccountIsNotFound(){
        NewProfileRequestInfoDTO inputDTO = mock(NewProfileRequestInfoDTO.class);
        inputDTO.profileType = "Visitor";
        inputDTO.email = "lino@isep.ipp.pt";
        when(inputDTO.getEmail()).thenReturn("lino@isep.ipp.pt");
        when(inputDTO.getProfileType()).thenReturn("Visitor");

        //account info
        Email email = Email.of("lino@isep.ipp.pt");
        AccountID accntID = AccountID.of(email);

        when(accountRepository.findByAccountID(accntID)).thenReturn(Optional.empty());
        assertThrows(IllegalAccessError.class, ()-> applicationServiceCreateProfileRequest.createProfileRequest(inputDTO) );

    }

    @Test
    void findProfileRequestByID() {
        ProfileRequestID profileRequestID = mock(ProfileRequestID.class);
        ProfileRequest profileRequest = mock(ProfileRequest.class);
        Optional<ProfileRequest> expected = Optional.of(profileRequest);

        when(profileRequestRepo.findProfileRequestByID(profileRequestID)).thenReturn(expected);

        Optional<ProfileRequest> result = applicationServiceCreateProfileRequest.findProfileRequestByID(profileRequestID);

        assertSame(expected, result);
    }
}
