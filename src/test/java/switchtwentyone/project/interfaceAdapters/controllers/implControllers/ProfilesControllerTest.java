package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateProfile;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetProfileList;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceUpdateProfile;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.NewProfileInfoDTO;
import switchtwentyone.project.dto.ProfileDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(MockitoExtension.class)
class ProfilesControllerTest {

    @Mock
    ApplicationServiceCreateProfile appServCreateProfile;

    @Mock
    ApplicationServiceUpdateProfile applicationServiceUpdateProfile;

    @Mock
    ApplicationServiceGetProfileList applicationServiceGetProfileList;

    @InjectMocks
    ProfilesController profilesController;


    @Test
    void createProfileFalse(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Profile profileMock = mock(Profile.class);
        Optional<Profile> optionalProfile = Optional.of(profileMock);

        NewProfileInfoDTO newProfileInfoDTO = mock(NewProfileInfoDTO.class);
        when(newProfileInfoDTO.getProfileType()).thenReturn("User");
        when(newProfileInfoDTO.getDescription()).thenReturn("desc");

        //Act
        ResponseEntity<Object> responseEntity = profilesController.createProfile(newProfileInfoDTO);

        //Assert
        assertEquals(400, responseEntity.getStatusCodeValue());
    }

    @Test
    void createProfileTrue(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NewProfileInfoDTO newProfileInfoDTO = mock(NewProfileInfoDTO.class);
        when(newProfileInfoDTO.getProfileType()).thenReturn("Newprofile");
        when(newProfileInfoDTO.getDescription()).thenReturn("description");


        ProfileDTO profileDTO = mock(ProfileDTO.class);
        ProfileType profileType = ProfileType.of("Newprofile");
        Text description = Text.write("description");


        when(appServCreateProfile.createAndSaveProfile(profileType, description)).thenReturn(Optional.ofNullable(profileDTO));

        //Act
        ResponseEntity<Object> responseEntity = profilesController.createProfile(newProfileInfoDTO);

        //Assert
        assertEquals(201, responseEntity.getStatusCodeValue());

    }


    @Test
    void findProfileByIDTrue(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Profile profile = mock(Profile.class);
        ProfileID profileID = ProfileID.ofProfileType("newProfile");

        Optional<Profile> optionalProfile = Optional.of(profile);
        when(appServCreateProfile.findProfileById(any(ProfileID.class))).thenReturn(optionalProfile);

        //Act
        ResponseEntity<Object> responseEntity = profilesController.findProfileById("newProfile");


        assertEquals(200, responseEntity.getStatusCodeValue());

    }

    @Test
    void findProfileByIDFalse(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(appServCreateProfile.findProfileById(any(ProfileID.class))).thenReturn(Optional.empty());

        //Act
        ResponseEntity<Object> responseEntity = profilesController.findProfileById("newProfile");


        assertEquals(404, responseEntity.getStatusCodeValue());

    }


    @Test
    void getProfilesDTOS() {

        ProfileDTO profileDTOOne = mock(ProfileDTO.class);
        ProfileDTO profileDTOTwo = mock(ProfileDTO.class);

        List<ProfileDTO> profileDTOList = new ArrayList<>();
        profileDTOList.add(profileDTOOne);
        profileDTOList.add(profileDTOTwo);

        when(applicationServiceGetProfileList.getProfilesDTOS()).thenReturn(profileDTOList);

        ResponseEntity<Object> responseEntity = profilesController.getProfilesDTOS();

        assertEquals(200, responseEntity.getStatusCodeValue());

    }

    @Test
    void DoNotUpdateProfile() {

        AccountDTO accountDTO = mock(AccountDTO.class);
        Email email = mock(Email.class);

        ProfileID profileID = mock(ProfileID.class);

        ProfileDTO profileDTO = mock(ProfileDTO.class);
        ResponseEntity<Object> responseEntity = profilesController.updateProfile(profileDTO, email.getEmailData());

        assertEquals(422, responseEntity.getStatusCodeValue());

    }

    @Test
    void UpdateProfile() {
        String profileID = "User";
        String description = "something";
        String id = "luis@isep.ipp.pt";
        ProfileDTO profileDTO = new ProfileDTO(profileID,description);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.accountID = "luis@isep.ipp.pt";
        accountDTO.email = "luis@isep.ipp.pt";
        accountDTO.name = "luis";
        accountDTO.function = "jobTitle";
        accountDTO.photo = "photo";
        accountDTO.profileID = "User";
        Email email = Email.of("luis@isep.ipp.pt");

        ProfileID prfID= ProfileID.ofProfileType(profileID);

        when(applicationServiceUpdateProfile.updateProfile(email,prfID)).thenReturn(Optional.of(accountDTO));

        ResponseEntity<Object> responseEntity = profilesController.updateProfile(profileDTO,id);


        assertEquals(200, responseEntity.getStatusCodeValue());

    }
    @Test
    void getOurProfilesDTOSTest() {

        ProfileDTO profileDTOOne = mock(ProfileDTO.class);
        ProfileDTO profileDTOTwo = mock(ProfileDTO.class);

        List<ProfileDTO> profileDTOList = new ArrayList<>();
        profileDTOList.add(profileDTOOne);
        profileDTOList.add(profileDTOTwo);

        when(applicationServiceGetProfileList.getProfilesDTOS()).thenReturn(profileDTOList);

        ResponseEntity<Object> expected = new ResponseEntity<>(profileDTOList, HttpStatus.OK);

        ResponseEntity<Object> actual = profilesController.getProfilesDTOS();

        assertEquals(expected, actual);

    }


}