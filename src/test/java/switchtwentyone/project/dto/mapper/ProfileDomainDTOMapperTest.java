package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProfileDTO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProfileDomainDTOMapperTest {

    @Test
    void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<ProfileDomainDTOMapper> constructor = ProfileDomainDTOMapper.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void toDTO() {
        Profile profile = mock(Profile.class);
        ProfileID profileID = mock(ProfileID.class);
        ProfileType profileType = ProfileType.of("dev");
        when(profile.getProfileID()).thenReturn(profileID);
        when(profileID.getProfileType()).thenReturn(profileType);
        when(profile.getDescription()).thenReturn(Text.write("developer"));
        ProfileDTO expected = new ProfileDTO("dev", "developer");

        ProfileDTO result = ProfileDomainDTOMapper.toDTO(profile);

        assertEquals(expected, result);
    }



    @Test
    void createProfileListDTO() {
        ProfileDTO dtoOne = new ProfileDTO("testOne", "test");
        ProfileDTO dtoTwo = new ProfileDTO("testeTwo", "tester");
        Profile profile = new Profile(new ProfileID(ProfileType.of("testOne")), Text.write("test"));
        Profile profileTwo = new Profile(new ProfileID(ProfileType.of("testeTwo")), Text.write("tester"));
        List<ProfileDTO> expected = new ArrayList<>();
        expected.add(dtoOne);
        expected.add(dtoTwo);
        List<Profile> listToTest = new ArrayList<>();
        listToTest.add(profile);
        listToTest.add(profileTwo);

        List<ProfileDTO> result = ProfileDomainDTOMapper.createProfileListDTO(listToTest);

        assertEquals(expected, result);
    }

    @Test
    void createProfileListDTOWithMock() {
        ProfileDTO dtoOne = new ProfileDTO("Test", "Tester");
        ProfileDTO dtoTwo = new ProfileDTO("TestTwo", "TesterTwo");
        List<ProfileDTO> expected = new ArrayList<>();
        expected.add(dtoOne);
        expected.add(dtoTwo);
        Profile profile = mock(Profile.class);
        Profile profileTwo = mock(Profile.class);
        List<Profile> profileList = new ArrayList<>();
        profileList.add(profile);
        profileList.add(profileTwo);
        ProfileID profileID = mock(ProfileID.class);
        when(profile.getProfileID()).thenReturn(profileID);
        when(profileID.getProfileType()).thenReturn(ProfileType.of("Test"));
        when(profile.getDescription()).thenReturn(Text.write("Tester"));
        ProfileID profileIDTwo = mock(ProfileID.class);
        when(profileTwo.getProfileID()).thenReturn(profileIDTwo);
        when(profileIDTwo.getProfileType()).thenReturn(ProfileType.of("TestTwo"));
        when(profileTwo.getDescription()).thenReturn(Text.write("TesterTwo"));

        List<ProfileDTO> result = ProfileDomainDTOMapper.createProfileListDTO(profileList);

        assertEquals(expected, result);
    }

}