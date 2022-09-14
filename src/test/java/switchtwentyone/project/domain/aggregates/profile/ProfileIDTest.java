package switchtwentyone.project.domain.aggregates.profile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ProfileIDTest {

    @Test
    void profileIDWithSameIDAreEquals() {
        ProfileType profIDType = ProfileType.of("dev");
        ProfileID profileID = new ProfileID(profIDType);
        ProfileID profileIDTwo = new ProfileID(profIDType);

        assertEquals(profileID, profileIDTwo);
    }

    @Test
    void profileIDWithDifferentIDAreDifferent() {
        ProfileType profIDType = ProfileType.of("dev");
        ProfileType profIDTypeTwo = ProfileType.of("user");
        ProfileID profileID = new ProfileID(profIDType);
        ProfileID profileIDTwo = new ProfileID(profIDTypeTwo);

        assertNotEquals(profileID, profileIDTwo);
    }

    @Test
    void profileIDEqualsToItself() {
        ProfileType profIDType = ProfileType.of("dev");
        ProfileID profileID = new ProfileID(profIDType);


        assertEquals(profileID, profileID);
    }


    @Test
    void profileIDWithSameIDHaveSameHashCode() {
        ProfileType profIDType = ProfileType.of("dev");
        ProfileID profileID = new ProfileID(profIDType);
        ProfileID profileIDTwo = new ProfileID(profIDType);

        assertEquals(profileID.hashCode(), profileIDTwo.hashCode());
    }

    @Test
    void profilesWithDifferentIDHaveDifferentHashCode() {
        ProfileType profTypeOne = ProfileType.of("dev");
        ProfileType profTypeTwo= ProfileType.of("user");
        ProfileID profileID = new ProfileID(profTypeOne);
        ProfileID profileIDTwo = new ProfileID(profTypeTwo);

        assertNotEquals(profileID.hashCode(), profileIDTwo.hashCode());
    }

    @Test
    void profileNotEqualsToOtherObject() {
        ProfileType profIDType = ProfileType.of("dev");
        ProfileID profileID = new ProfileID(profIDType);
        int comparator = 2;

        assertNotEquals(profileID, comparator);
    }


    @Test
    void SameIdentityAsNull() {
        ProfileType profIDType = ProfileType.of("dev");
        ProfileID profileID = new ProfileID(profIDType);

        boolean result = profileID.sameValueAs(null);

        assertFalse(result);
    }

    @Test
    void EqualsToNull() {
        ProfileType profIDType = ProfileType.of("dev");
        ProfileID profileID = new ProfileID(profIDType);

       assertNotEquals(null, profileID);
    }

    @Test
    void hasProfileType() {

        ProfileType newProfile = ProfileType.of("dev");
        ProfileID newProfileID = new ProfileID(newProfile);

        boolean result = newProfileID.hasProfileType(newProfile);

        assertTrue(result);
    }
    @Test
    void doesNotHaveProfileType() {

        ProfileType newProfile = ProfileType.of("dev");
        ProfileID newProfileID = new ProfileID(newProfile);
        ProfileType profile = ProfileType.of("director");

        boolean result = newProfileID.hasProfileType(profile);

        assertFalse(result);
    }


    @Test
    void getProfileType() {
        //Arrange
        ProfileType expected = ProfileType.of("dev");
        ProfileID newProfileID = new ProfileID(expected);

        //Act
        ProfileType result = newProfileID.getProfileType();

        //Asert
        assertEquals(expected, result);
    }

    @Test
    void hashcodeTest(){
        ProfileID id1 = new ProfileID();
        ProfileID id2 = new ProfileID();

        assertEquals(id1.hashCode(), id2.hashCode());
    }


    @Test
    void profileIDof(){
        //Arrange
        ProfileType newProfile = ProfileType.of("dev");
        //Act
        ProfileID result = ProfileID.of(newProfile);
        ProfileID expected = new ProfileID(newProfile);
        
        //Assert
        assertEquals(result, expected);
    }
    
    @Test
    void toStringTest(){
        //Arrange
        ProfileType newProfile = ProfileType.of("dev");
        ProfileID profID = ProfileID.of(newProfile);
        String expected = newProfile.toString();

        //Act
        String result= profID.toString();

        assertEquals(expected, result);


    }
}