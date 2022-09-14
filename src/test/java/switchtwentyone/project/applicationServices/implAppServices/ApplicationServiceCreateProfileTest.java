package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProfileDTO;
import switchtwentyone.project.dto.mapper.ProfileDomainDTOMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ApplicationServiceCreateProfileTest {

    @Autowired
    ApplicationServiceCreateProfile appServCreateProfile;

    @MockBean
    private ProfileRepository profRepo;

    @Test
    void generateProfileID() {
        ProfileType profileType = ProfileType.of("test");
        ProfileID expected = new ProfileID(profileType);
        ProfileID result = appServCreateProfile.generateProfileID(profileType);
        assertEquals(expected, result);
    }


    @Test
    void isProfileDataValidFalse() {
        ProfileID profileType = ProfileID.ofProfileType("User");

        Profile userMock = mock(Profile.class);
        ProfileID profileID = new ProfileID(ProfileType.of("User"));
        when(userMock.getProfileID()).thenReturn(profileID);
        when(userMock.getDescription()).thenReturn(Text.write("Account type with the standard permissions level."));

        List<Profile> standardProfiles = new ArrayList<>();
        standardProfiles.add(userMock);
        when(profRepo.findAll()).thenReturn(standardProfiles);

        boolean result = appServCreateProfile.isProfileDataValid(profileType);
        assertFalse(result);

    }


    @Test
    void isProfileDataValidTrue() {
        ProfileID profileType = ProfileID.ofProfileType("Unexistant");

        Profile userMock = mock(Profile.class);
        ProfileID profileID = new ProfileID(ProfileType.of("User"));
        when(userMock.getProfileID()).thenReturn(profileID);
        when(userMock.getDescription()).thenReturn(Text.write("Account type with the standard permissions level."));

        List<Profile> standardProfiles = new ArrayList<>();
        standardProfiles.add(userMock);
        when(profRepo.findAll()).thenReturn(standardProfiles);

        boolean result = appServCreateProfile.isProfileDataValid(profileType);
        assertTrue(result);
    }

    @Test
    void createAndSaveProfileTrue() {
//        //Arrange
//        ProfileType profileType = ProfileType.of("profile");
//        Text description = Text.write("description");
//        ProfileID profileID = new ProfileID(ProfileType.of("profile"));
//        Profile profile = mock(Profile.class);
//        Profile profileOne = mock(Profile.class);
//        List<Profile> profileList = new ArrayList<>();
//        profileList.add(profileOne);
//        when(profRepo.findAll()).thenReturn(profileList);
//        when(profileOne.getProfileID()).thenReturn(profileID);
//        when(profile.getDescription()).thenReturn(description);
//        ProfileDTO profileDTO = new ProfileDTO("profile", "description");
//        Optional<ProfileDTO> expected = Optional.of(profileDTO);

        //Arrange
        Profile profileToDTO = mock(Profile.class);
        ProfileType profileType = ProfileType.of("profileOne");
        Text description = Text.write("descriptionOne");
        when(profileToDTO.getProfileID()).thenReturn(ProfileID.ofProfileType("profileOne"));
        when(profileToDTO.getDescription()).thenReturn(Text.write("descriptionOne"));
        when(profRepo.save(profileToDTO)).thenReturn(profileToDTO);

        Optional<ProfileDTO>  expected = Optional.of(ProfileDomainDTOMapper.toDTO(profileToDTO));

        //Act
        Optional<ProfileDTO> result = appServCreateProfile.createAndSaveProfile(profileType, description);

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void findProfileById() {
        //Arrange
        Profile prf2 = mock(Profile.class);
        ProfileType prType1 = ProfileType.of("newProfile");
        ProfileID prId1 = ProfileID.ofProfileType("newProfile");
        Text prDesc1 = Text.write("description1");

        when(prf2.getProfileID()).thenReturn(prId1);
        when(prf2.getDescription()).thenReturn(prDesc1);

        Optional<ProfileDTO> prf2DTO = Optional.of(ProfileDomainDTOMapper.toDTO(prf2));

        when(profRepo.save(prf2)).thenReturn(prf2);
        when(prf2.hasID(prId1)).thenReturn(true);
        when(profRepo.findProfileById(prId1)).thenReturn(Optional.of(prf2));

        //Act
        Optional<Profile> profileFound = appServCreateProfile.findProfileById(prId1);

        //Assert
        assertEquals(Optional.of(prf2), profileFound);

    }


}