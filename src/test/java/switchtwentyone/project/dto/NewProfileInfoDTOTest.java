package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class NewProfileInfoDTOTest {

    @Test
    void ensureEqualsItself() {
        NewProfileInfoDTO dto = new NewProfileInfoDTO( "User", "test");

        assertEquals(dto, dto);
    }
    @Test
    void ensureDoesNotEqualDifferentObject() {
        NewProfileInfoDTO dtoOne = new NewProfileInfoDTO( "User", "test");
        NewProfileInfoDTO dtoTwo = new NewProfileInfoDTO( "UserTwo", "testTwo");

        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureIsNotEqualHavingDifferentDescriptions() {
        NewProfileInfoDTO dtoOne = new NewProfileInfoDTO( "User", "test");
        NewProfileInfoDTO dtoTwo = new NewProfileInfoDTO( "User", "testTwo");

        assertNotEquals(dtoOne, dtoTwo);
    }



    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        NewProfileInfoDTO dtoOne = new NewProfileInfoDTO( "User", "test");
        Object somethingElse = new Object();

        assertNotEquals(dtoOne, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        NewProfileInfoDTO dtoOne = new NewProfileInfoDTO( "User", "test");

        assertNotEquals(dtoOne, null);
    }
    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        NewProfileInfoDTO dtoOne = new NewProfileInfoDTO( "User", "test");
        NewProfileInfoDTO dtoTwo = new NewProfileInfoDTO( "User", "test");

        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        NewProfileInfoDTO dtoOne = new NewProfileInfoDTO( "User", "test");
        NewProfileInfoDTO dtoTwo = new NewProfileInfoDTO( "UserTwo", "test");

        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }


    @Test
    void getProfileType() {
        NewProfileInfoDTO dtoOne = new NewProfileInfoDTO( "User", "test");

        String result = dtoOne.getProfileType();

        assertEquals("User", result);

    }

    @Test
    void getDescription() {
        NewProfileInfoDTO dtoOne = new NewProfileInfoDTO( "User", "test");

        String result = dtoOne.getDescription();

        assertEquals("test", result);

    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }
}