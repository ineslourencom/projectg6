package switchtwentyone.project.domain.aggregates.profile;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.valueObjects.Text;

import static org.junit.jupiter.api.Assertions.*;
class ProfileCreatableTest {


    @Test
    void createProfileTest() {
        ProfileID profID = new ProfileID(ProfileType.of("test"));
        Text description = Text.write("description");

        Profile expected = new Profile(profID, description);

        Profile result = ProfileCreatable.createProfile(profID, description);

        assertEquals(expected, result);

    }

}