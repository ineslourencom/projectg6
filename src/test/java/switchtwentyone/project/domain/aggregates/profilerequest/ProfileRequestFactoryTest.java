package switchtwentyone.project.domain.aggregates.profilerequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfileRequestFactoryTest {
    ProfileRequestID itemID;
    ProfileRequestID itemID1;

    @BeforeAll
    void arrange() {
        itemID = ProfileRequestID.createProfileRequestID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");
        itemID1 = ProfileRequestID.createProfileRequestID("61ead7c9-6930-4191-9841-46f8d45535a3");
    }
    @Test
    void ProfileRequestTest() {

        //Arrange
        Email emailOne = mock(Email.class);
        ProfileType profileOne = mock(ProfileType.class);
        ProfileRequestID profileRequestID = itemID;
        ProfileRequest newProfRequest = new ProfileRequest(profileRequestID, emailOne, profileOne);

        //Act
        ProfileRequest result = ProfileRequestCreatable.createProfileRequest(profileRequestID, emailOne, profileOne);

        //Assert
        assertEquals(newProfRequest, result);
    }
    @Test
    void ensureInstanceIsTheSame () {
        //Arrange
        ProfileRequestFactory instaceOne =ProfileRequestFactory.getInstance();
        ProfileRequestFactory instanceTwo =ProfileRequestFactory.getInstance();

        //Act
        boolean result = (instaceOne == instanceTwo);

        //Assert
        assertTrue (result);
    }

    @Test
    void ensureInstanceIsNotNull() {
        //Arrange - Act
        ProfileRequestFactory instance = ProfileRequestFactory.getInstance();

        //Assert
        assertNotNull (instance);

    }

}
