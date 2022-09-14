package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.applicationServices.irepositories.iwebrepositories.ProfileWebRepository;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.dto.ProfileDTO;
import switchtwentyone.project.externaldto.assemblers.ExternalProfileMapper;
import switchtwentyone.project.dto.mapper.ProfileDomainDTOMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;
import switchtwentyone.project.externaldto.ExternalProfileDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceGetProfileListTest {

    @Mock
    ProfileRepository profileRepository;
    @Mock
    ProfileWebRepository webRepository;
    @Mock
    ExternalProfileMapper mapper;
    @InjectMocks
    ApplicationServiceGetProfileList applicationServiceGetProfileList;

    @Test
    void getProfilesDTOS() {

        List<Profile> ourProfiles = mock(List.class);
        when(profileRepository.findAll()).thenReturn(ourProfiles);

        ProfileDTO profileDTOOne = mock(ProfileDTO.class);
        ProfileDTO profileDTOTwo = mock(ProfileDTO.class);
        ProfileDTO profileDTOThree = mock(ProfileDTO.class);
        ProfileDTO profileDTOFour = mock(ProfileDTO.class);

        List<ProfileDTO> expected = new ArrayList<>();
        expected.add(profileDTOOne);
        expected.add(profileDTOTwo);

        ExternalProfileDTO exProfileOne = new ExternalProfileDTO();
        ExternalProfileDTO exProfileTwo = new ExternalProfileDTO();
        List<ExternalProfileDTO> externalProfileDTOS = Arrays.asList(exProfileOne, exProfileTwo);
        when(webRepository.getProfiles()).thenReturn(externalProfileDTOS);
        when(mapper.toDTO(exProfileOne)).thenReturn(profileDTOThree);
        when(mapper.toDTO(exProfileTwo)).thenReturn(profileDTOFour);

        MockedStatic<ProfileDomainDTOMapper> mock = mockStatic(ProfileDomainDTOMapper.class);
        when(ProfileDomainDTOMapper.createProfileListDTO(ourProfiles)).thenReturn(expected);

        List<ProfileDTO> actual = applicationServiceGetProfileList.getProfilesDTOS();

        mock.close();


        assertEquals(expected, actual);


    }

    @Test
    void getOurProfilesDTOS() {
        List<Profile> ourProfiles = mock(List.class);
        when(profileRepository.findAll()).thenReturn(ourProfiles);
        List<ProfileDTO> expected = mock(List.class);
        MockedStatic<ProfileDomainDTOMapper> mock = mockStatic(ProfileDomainDTOMapper.class);
        when(ProfileDomainDTOMapper.createProfileListDTO(ourProfiles)).thenReturn(expected);

        List<ProfileDTO> actual = applicationServiceGetProfileList.getLocalProfilesDTOS();

        mock.close();

        assertSame(expected, actual);
    }
}