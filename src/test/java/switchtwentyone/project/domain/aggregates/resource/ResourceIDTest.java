package switchtwentyone.project.domain.aggregates.resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ResourceIDTest {

    @Test
    void sameValueAsNull() {
        //Arrange
        int testnumber = 1;
        ResourceID resourceID = new ResourceID(testnumber);
        //Act
        boolean result = resourceID.sameValueAs(null);
        //Assert
        assertFalse(result);
    }

    @Test
    void sameValueAsSameNumber() {
        //Arrange
        int testnumber = 1;
        ResourceID resourceID = new ResourceID(testnumber);
        //Act
        boolean result = resourceID.sameValueAs(resourceID);
        //Assert
        assertTrue(result);
    }

    @Test
    void sameValueAsDiferentNumber() {
        //Arrange
        int testOne = 1;
        ResourceID resourceID = new ResourceID(testOne);
        int tesTwo = 2;
        ResourceID secondID = new ResourceID(tesTwo);
        //Act
        boolean result = resourceID.sameValueAs(secondID);
        //Assert
        assertFalse(result);
    }


    @Test
    void testEqualsSameObject() {
        //Arrange
        int testNumber = 1;
        ResourceID resourceID = new ResourceID(testNumber);
        //Act
        boolean result = resourceID.equals(resourceID);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsObjectSameValue() {
        //Arrange
        int testNumber = 1;
        ResourceID resourceID = new ResourceID(testNumber);
        ResourceID secondID = new ResourceID(testNumber);
        //Act
        boolean result = resourceID.equals(secondID);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsObjectDifferentValues() {
        //Arrange
        int testNumber = 1;
        ResourceID resourceID = new ResourceID(testNumber);
        int secondTest = 2;
        ResourceID secondID = new ResourceID(secondTest);
        //Act
        boolean result = resourceID.equals(secondID);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentObjects() {
        //Arrange
        int testNumber = 1;
        ResourceID resourceID = new ResourceID(testNumber);

        Object object = new Object();
        //Act
        boolean result = resourceID.equals(object);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsObjectNotNull() {
        //Arrange
        int testNumber = 1;
        ResourceID resourceID = new ResourceID(testNumber);
        //Act
        boolean result = resourceID.equals(null);
        //Assert
        assertNotNull(result);
    }

    @Test
    void getResourceID() {
        int testNumber = 1;
        ResourceID resourceID = new ResourceID(testNumber);

        int expected = 1;

        int result =resourceID.getResourceID();

        assertEquals(expected, result);
    }

    @Test
    void createResourceIDTest() {
        ResourceID result = ResourceID.create();

        assertNotNull(result);
    }
    @Test
    void ensureResourceIDsAreDifferent() {
        ResourceID firstResult = ResourceID.create();
        ResourceID secondResult = ResourceID.create();

        assertNotEquals(firstResult, secondResult);
    }

    @Test
    void sameValueAs() {

        System.out.println(UUID.randomUUID().hashCode());
    }
}