package switchtwentyone.project.infrastructure.persistence;


import org.junit.jupiter.api.Test;
import switchtwentyone.project.datamodel.ProfileJPA;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.dto.ProfileDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProfileJPATest {

    @Test
    void getProfileID(){
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA profileJPA = new ProfileJPA(profileDTO);

        //Act
        String result = profileJPA.getProfileIDData().getProfileType().getValue();

        //Assert
        assertEquals("test", result);
    }

    @Test
    void setProfileID(){
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA profileJPA = new ProfileJPA(profileDTO);

        //Act
        profileJPA.setProfileIDData(ProfileID.ofProfileType("testtwo"));
        String result = profileJPA.getProfileIDData().getProfileType().getValue();

        //Assert
        assertEquals("testtwo", result);
    }

    @Test
    void getDescription(){
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA profileJPA = new ProfileJPA(profileDTO);

        //Act
        String result = profileJPA.getDescriptionData();

        //Assert
        assertEquals("test", result);
    }

    @Test
    void setDescription(){
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA profileJPA = new ProfileJPA(profileDTO);

        //Act
        profileJPA.setDescriptionData("testtwo");
        String result = profileJPA.getDescriptionData();

        //Assert
        assertEquals("testtwo", result);
    }



    //--------------------- Testing Equals --------------------

    @Test
    void testSameJPAAreEqual() {
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA jpa = new ProfileJPA(profileDTO);

        //Act
        boolean result = jpa.equals(jpa);

        //Assert
        assertTrue(result);
    }
    @Test
    void testSameJPAAreEqual_return() {
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA jpa = new ProfileJPA(profileDTO);
        ProfileJPA jpa2 = new ProfileJPA(profileDTO);

        //Assert
        assertEquals(jpa, jpa2 );
    }

    @Test
    void testSameJPAHaveSameHashCode() {
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA jpa = new ProfileJPA(profileDTO);
        ProfileJPA jpa2 = new ProfileJPA(profileDTO);

        //Assert
        assertEquals(jpa.hashCode(), jpa2.hashCode());
    }

    @Test
    void testJPANotEqualToNull() {
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA jpa = new ProfileJPA(profileDTO);
        //act
        boolean result = jpa.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPANotEqualToOtherObject() {
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA jpa = new ProfileJPA(profileDTO);
        PositiveNumber number = PositiveNumber.of(1);

        //Act
        boolean result = jpa.equals(number);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPEqualToItself() {
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA jpa = new ProfileJPA(profileDTO);
        //act
        boolean result = jpa.equals(jpa);
        //Assert
        assertTrue(result);
    }



    @Test
    void testToString(){
        //Arrange
        ProfileDTO profileDTO = mock(ProfileDTO.class);
        profileDTO.profileID = "test";
        profileDTO.description = "test";

        ProfileJPA jpa = new ProfileJPA(profileDTO);
        //act
        String expected = "ProfileJPA(" +
                "profileIDData=" + jpa.getProfileIDData().toString() +
                ", descriptionData=" + "test" +
                ')';
        //act
        String result = jpa.toString();

        assertEquals(expected, result);

    }





}