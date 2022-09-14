package switchtwentyone.project.domain.aggregates.profile;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.valueObjects.Text;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProfileTest {

    @Test
    void profileWithSameIDAreEquals() {
        ProfileID profileID = new ProfileID(ProfileType.of("User"));
        Text description = mock(Text.class);
        Profile profileOne = new Profile(profileID, description);
        Profile profileTwo = new Profile(profileID , description);

        assertEquals(profileTwo, profileOne);
    }

    @Test
    void profileWithDifferentIDAreDifferent() {
        Text description = mock(Text.class);
        ProfileID profileID = new ProfileID(ProfileType.of("User"));
        ProfileID profileIDTwo = new ProfileID(ProfileType.of("Tester"));

        Profile profileOne = new Profile(profileID, description);
        Profile profileTwo = new Profile(profileIDTwo, description);

        assertNotEquals(profileTwo, profileOne);
    }

    @Test
    void profileEqualsToItself() {
        Text description = mock(Text.class);
        ProfileID profileID = new ProfileID(ProfileType.of("User"));

        Profile profileOne = new Profile(profileID, description);

        assertEquals(profileOne, profileOne);
    }


    @Test
    void profilesWithSameIDHaveSameHashCode() {

        Text description = mock(Text.class);

        ProfileID profileID = new ProfileID(ProfileType.of("User"));

        Profile profileOne = new Profile(profileID, description);
        Profile profileTwo = new Profile(profileID, description);

        assertEquals(profileOne.hashCode(), profileTwo.hashCode());
    }

    @Test
    void profilesWithDifferentIDHaveDifferentHashCode() {
        Text description = mock(Text.class);
        ProfileID profileID = new ProfileID(ProfileType.of("User"));
        ProfileID profileIDTwo = new ProfileID(ProfileType.of("Tester"));

        Profile profileOne = new Profile(profileID, description);
        Profile profileTwo = new Profile(profileIDTwo, description);

        assertNotEquals(profileOne.hashCode(), profileTwo.hashCode());
    }

    @Test
    void profileNotEqualsToOtherObject() {
        Text description = mock(Text.class);
        ProfileID profileID = new ProfileID(ProfileType.of("User"));
        Profile profileOne = new Profile(profileID, description);
        Object object = new Object();

        assertNotEquals(profileOne, object);
    }


    @Test
    void SameIdentityAsNull() {
        Text description = mock(Text.class);
        ProfileID profileID = new ProfileID(ProfileType.of("User"));
        Profile profileOne = new Profile(profileID, description);

        boolean result = profileOne.sameIdentityAs(null);

        assertFalse(result);
    }

    @Test
    void EqualsToNull() {
        Text description = mock(Text.class);
        ProfileID profileID = new ProfileID(ProfileType.of("User"));
        Profile profileOne = new Profile(profileID, description);

        assertNotEquals(null, profileOne);
    }





    @Test
    void getDescription() {
        Text description = Text.write("description");
        ProfileID profileID = mock(ProfileID.class);
        Profile profile = new Profile(profileID, description);

        Text result = profile.getDescription();

        assertEquals(description, result);
    }

    @Test
    void getProfileType() {
        Text description = mock(Text.class);
        ProfileID profileID = new ProfileID(ProfileType.of("User"));
        Profile profileOne = new Profile(profileID, description);

        ProfileID result = profileOne.getProfileID();

        assertEquals(profileID, result);


    }

    @Test
    void hasID_true() {
        Profile profile = new Profile(ProfileID.ofProfileType("test"), Text.write("test"));
        boolean result = profile.hasID(ProfileID.ofProfileType("test"));
        assertTrue(result);
    }

    @Test
    void hasID_false() {
        Profile profile = new Profile(ProfileID.ofProfileType("test"), Text.write("test"));
        boolean result = profile.hasID(ProfileID.ofProfileType("testTwo"));
        assertFalse(result);
    }
}