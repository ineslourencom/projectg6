package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateProfileRequest;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;
import switchtwentyone.project.dto.NewProfileRequestInfoDTO;
import switchtwentyone.project.dto.ProfileRequestDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@ExtendWith(MockitoExtension.class)
public class ProfileRequestControllerTest {
    ProfileRequestID itemID;
    ProfileRequestID itemID1;

    @Mock
    ApplicationServiceCreateProfileRequest appSrvCreateProfileRequest;
    @InjectMocks
    ProfileRequestController profileRequestController;

    @BeforeAll
    void arrange() {
        itemID = ProfileRequestID.createProfileRequestID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");
        itemID1 = ProfileRequestID.createProfileRequestID("61ead7c9-6930-4191-9841-46f8d45535a3");
    }

    @Test
    void createProfileRequestController_Test_Successful() {
        // Arrange
        String emailTest = "Andreia@isep.ipp.pt";
        String profType = "User";
        NewProfileRequestInfoDTO newProfileRequestInfoDTO = new NewProfileRequestInfoDTO(emailTest, profType);
        ProfileRequestDTO newProfileRequestDTO =new ProfileRequestDTO();
        newProfileRequestDTO.profileRequestID=itemID;
        newProfileRequestDTO.email= Email.of(emailTest);
        newProfileRequestDTO.profileType= ProfileType.of(profType);
        when(appSrvCreateProfileRequest.createProfileRequest(newProfileRequestInfoDTO)).thenReturn(newProfileRequestDTO);
        ResponseEntity<Object> expected = new ResponseEntity(newProfileRequestDTO, HttpStatus.CREATED);
        // Act
        ResponseEntity<Object> result = profileRequestController.createProfileRequest(emailTest, profType);
        // Assert
        assertEquals(expected, result);
    }
   @Test
    void createProfileRequestControllerUnSuccessful() {
        // Arrange



        String emailTest = "luis@isep.ipp.pt";
        String profType = "aa";
        NewProfileRequestInfoDTO newProfileRequestInfoDTO = new NewProfileRequestInfoDTO(emailTest, profType);
        ProfileRequestDTO newProfileRequestDTO =new ProfileRequestDTO();
        newProfileRequestDTO.profileRequestID=itemID;
        newProfileRequestDTO.email= Email.of(emailTest);
        newProfileRequestDTO.profileType= ProfileType.of(profType);
        when(appSrvCreateProfileRequest.createProfileRequest(newProfileRequestInfoDTO)).thenReturn(newProfileRequestDTO);



        // Act
        ResponseEntity<Object> responseEntity = profileRequestController.createProfileRequest(emailTest,null);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 422);
    }

}

