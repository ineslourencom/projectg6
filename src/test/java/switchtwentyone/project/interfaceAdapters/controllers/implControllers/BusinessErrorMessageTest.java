package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessErrorMessageTest {

    @Test
    void getErrorMessage() {
        //Arrange
        BusinessErrorMessage msg = new BusinessErrorMessage("msg", BusinessErrorMessage.NOT_FOUND);

        //Act
        String errorMSG = msg.getErrorMessage();
        boolean result = errorMSG.equalsIgnoreCase("msg");

        //Assert
        assertTrue(result);
    }

    @Test
    void getErrorCode() {
        //Arrange
        BusinessErrorMessage msg = new BusinessErrorMessage("msg", BusinessErrorMessage.NOT_FOUND);

        //Act
        int result = msg.getErrorCode();

        //Assert
        assertEquals(2, result);
    }

    @Test
    void getErrorCodeUnProcessableEntity() {
        //Arrange
        BusinessErrorMessage msg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);

        //Act
        int result = msg.getErrorCode();

        //Assert
        assertEquals(1, result);
    }

    @Test
    void testEqualsEqualObjects() {
        BusinessErrorMessage firstMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        BusinessErrorMessage secondMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);

        assertEquals(firstMsg, secondMsg);
    }
    @Test
    void testEqualsSameObject() {
        BusinessErrorMessage firstMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);

        assertEquals(firstMsg, firstMsg);
    }
    @Test
    void testEqualsDifferentObject() {
        BusinessErrorMessage firstMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        BusinessErrorMessage secondMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.NOT_FOUND);

        assertNotEquals(firstMsg, secondMsg);
    }
    @Test
    void testEqualsDifferentClass() {
        BusinessErrorMessage firstMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);

        assertNotEquals(firstMsg, new Object());
    }
    @Test
    void testEqualsNull() {
        BusinessErrorMessage firstMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);

        assertNotEquals(firstMsg, null);
    }

    @Test
    void testHashCodeEqualObjects() {
        BusinessErrorMessage firstMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        BusinessErrorMessage secondMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);

        int hashOne = firstMsg.hashCode();
        int hashTwo = secondMsg.hashCode();

        assertEquals(hashOne, hashTwo);
    }
    @Test
    void testHashCodeDifferentObjects() {
        BusinessErrorMessage firstMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        BusinessErrorMessage secondMsg = new BusinessErrorMessage("msg", BusinessErrorMessage.NOT_FOUND);

        int hashOne = firstMsg.hashCode();
        int hashTwo = secondMsg.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }
}