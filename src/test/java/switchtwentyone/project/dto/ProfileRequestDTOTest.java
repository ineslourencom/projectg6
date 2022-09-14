package switchtwentyone.project.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfileRequestDTOTest {
    ProfileRequestID itemID;
    ProfileRequestID itemID1;

    @BeforeAll
    void arrange() {
        itemID = ProfileRequestID.createProfileRequestID("4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4");
        itemID1 = ProfileRequestID.createProfileRequestID("61ead7c9-6930-4191-9841-46f8d45535a3");
    }
    @Test
    void ensureEqualsItself() {
        ProfileRequestDTO dto = new ProfileRequestDTO();

        assertEquals(dto, dto);
    }

    @Test
    void ensureEqualsEqualObject() {
        ProfileRequestDTO dtoOne = new ProfileRequestDTO();
        ProfileRequestDTO dtoTwo = new ProfileRequestDTO();

        assertEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        ProfileRequestID number = itemID;
        ProfileRequestDTO dtoOne = new ProfileRequestDTO();
        ProfileRequestDTO dtoTwo = new ProfileRequestDTO();
        dtoOne.profileRequestID = number;

        assertNotEquals(dtoOne, dtoTwo);
    }
    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        ProfileRequestDTO dto = new ProfileRequestDTO();
        Object somethingElse = new Object();

        assertNotEquals(dto, somethingElse);
    }
    @Test
    void ensureDoesNotEqualNull() {
        ProfileRequestDTO dto = new ProfileRequestDTO();

        assertNotEquals(dto, null);
    }
    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        ProfileRequestDTO dtoOne = new ProfileRequestDTO();
        ProfileRequestDTO dtoTwo = new ProfileRequestDTO();
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }
    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        ProfileRequestID number = itemID1;
        ProfileRequestDTO dtoOne = new ProfileRequestDTO();
        ProfileRequestDTO dtoTwo = new ProfileRequestDTO();
        dtoOne.profileRequestID = number;
        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }
}
