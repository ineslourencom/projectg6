package switchtwentyone.project.domain.aggregates.profilerequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.account.Email;

import switchtwentyone.project.domain.aggregates.profile.ProfileType;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfileRequestTest {


    ProfileRequestID itemID;
    ProfileRequestID itemID1;

    @BeforeAll
    void arrange(){
        itemID = ProfileRequestID.createProfileRequestID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");
        itemID1 = ProfileRequestID.createProfileRequestID("61ead7c9-6930-4191-9841-46f8d45535a3");
    }

    @Test
    void profileRequestWithSameIDAreEqual(){
        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID = itemID;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);
        ProfileRequest profileRequestTwo = new ProfileRequest (profileRequestID, emailOne, requestOne);

        //Act
        boolean result = profileRequestOne.equals(profileRequestTwo);

        //Assert
        assertTrue(result);

    }

   @Test
    void profileRequestNotEqualToOtherObject(){
        //Arrange
        Email emailOne = mock(Email.class);
       ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID = itemID;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);
        Integer other = Integer.valueOf(20);

        //Act
        boolean result = profileRequestOne.equals(other);

        //Assert
        assertFalse(result);

    }

    @Test
    void profileRequestEqualsItself(){
        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID =itemID;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);

        //Act
        boolean result = profileRequestOne.equals(profileRequestOne);

        //Assert
        assertTrue(result);

    }

    @Test
    void profileRequestWithSameIDHaveSameHashCode(){
        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID = itemID;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);
        ProfileRequest profileRequestTwo = new ProfileRequest (profileRequestID, emailOne, requestOne);

        //Assert
        assertEquals(profileRequestOne.hashCode(), profileRequestTwo.hashCode());
    }

    @Test
    void profileRequestWithDifferentIDAreDifferent(){
        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID = itemID;
        ProfileRequestID profileRequestID2 = itemID1;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);
        ProfileRequest profileRequestTwo = new ProfileRequest(profileRequestID2, emailOne, requestOne);

        //Act
        boolean result = profileRequestOne.equals(profileRequestTwo);

        //Assert
        assertFalse(result);

    }

    @Test
    void profileRequestsWithDifferentIDHaveDifferentHashCode(){
        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID = itemID;
        ProfileRequestID profileRequestID2 = itemID1;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);
        ProfileRequest profileRequestTwo = new ProfileRequest(profileRequestID2, emailOne, requestOne);

        //Assert
        assertNotEquals(profileRequestOne.hashCode(), profileRequestTwo.hashCode());
    }


    @Test
    void SameIdentityAs() {
        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID = itemID;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);
        ProfileRequest profileRequestTwo = new ProfileRequest(profileRequestID, emailOne, requestOne);

        //Act
        boolean result = profileRequestOne.sameValueAs(profileRequestTwo);

        //Assert
        assertTrue(result);
    }

    @Test
    void sameIdentityAsNull() {
        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID = itemID;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);


        //Act
        boolean result = profileRequestOne.sameValueAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void equalsNull() {
        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID = itemID;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);

        //Act
        boolean result = profileRequestOne.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void differentValueAs() {
        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType requestOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID =itemID;
        ProfileRequestID profileRequestID2 = itemID1;
        ProfileRequest profileRequestOne = new ProfileRequest (profileRequestID, emailOne, requestOne);
        ProfileRequest profileRequestTwo = new ProfileRequest (profileRequestID2, emailOne, requestOne);

        //Act
        boolean result = profileRequestOne.sameValueAs(profileRequestTwo);

        //Assert
        assertFalse(result);
    }

}


