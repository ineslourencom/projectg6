package switchtwentyone.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileCreatable;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.datamodel.ProfileJPA;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProfileDomainDataAssemblerTest {

    @Test
    void toData() {
        Profile profile = mock(Profile.class);
        ProfileID profileID = mock(ProfileID.class);
        when(profile.getProfileID()).thenReturn(profileID);
        when(profileID.getProfileType()).thenReturn(ProfileType.of("test"));
        when(profile.getDescription()).thenReturn(Text.write("test"));
        ProfileDomainDataAssembler profileAssembler = new ProfileDomainDataAssembler();
        ProfileJPA expected = profileAssembler.toData(profile);

        ProfileJPA result = profileAssembler.toData(profile);

        assertEquals(expected, result);
    }

    @Test
    void toDomain() {
        ProfileID profileID = ProfileID.ofProfileType("test");
        Text textDescription = Text.write("test");
        ProfileJPA profileJpa = mock(ProfileJPA.class);
        when(profileJpa.getProfileIDData()).thenReturn(profileID);
        when(profileJpa.getDescriptionData()).thenReturn("test");
        Profile expected = ProfileCreatable.createProfile(profileID, textDescription);
        ProfileDomainDataAssembler profileAssembler = new ProfileDomainDataAssembler();
        Profile result = profileAssembler.toDomain(profileJpa);

        assertEquals(expected, result);


    }
}