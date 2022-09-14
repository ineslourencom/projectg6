package switchtwentyone.project.domain.valueObjects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.Role;

import static org.junit.jupiter.api.Assertions.*;

class PositiveNumberTest {

    @Test
    void negativeNumber() {
        int number = -1;

        assertThrows(IllegalArgumentException.class,
                () -> {
                    PositiveNumber number1 = PositiveNumber.of(number);
                    PositiveNumber.of(number);
                });
    }

    @Test
    void rightNumber() {
        //Arrange
        int number = 1;
        PositiveNumber numberVO = PositiveNumber.of(number);

        //Act
        int result = numberVO.getNumber();

        //Assert
        assertEquals(number, result);
    }

    @Test
    void testEqualsObjectNotNull() {

        //Arrange
        int number = 9;
        PositiveNumber numberVO = PositiveNumber.of(number);

        //Act
        int result = numberVO.getNumber();

        //Assert
        assertNotNull(result);
    }

    @Test
    void testEqualsSameObject() {

        //Arrange
        int number = 9;
        PositiveNumber numberVO = PositiveNumber.of(number);

        //Act
        int result = numberVO.getNumber();

        //Assert
        assertEquals(result, result);
    }

    @Test
    void testEqualsTwoEqualObjects() {
        //Arrange
        int number = 9;
        int numberTwo = 9;

        //Act
        PositiveNumber numberVO = PositiveNumber.of(number);
        PositiveNumber otherVO = PositiveNumber.of(numberTwo);
        //Assert
        assertEquals(numberVO, otherVO);
    }

    @Test
    void testEqualsDiferentObjects() {
        //Arrange
        int number = 9;
        int numberTwo = 5;
        PositiveNumber numberVO = PositiveNumber.of(number);
        PositiveNumber otherVO = PositiveNumber.of(numberTwo);

        //Act
        boolean result = numberVO.equals(otherVO);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsDiferentTypeObjects() {
        //Arrange
        int number = 9;
        PositiveNumber numberVO = PositiveNumber.of(number);
        Erole erole = Erole.DEVELOPER;

        //Act
        boolean result = numberVO.equals(erole);

        //Assert
        assertFalse(result);
    }
    @Test
    void testNotEqualToNull() {
        //Arrange
        int number =9;
        PositiveNumber newPositiveNumber = PositiveNumber.of (number);
        PositiveNumber newPositiveNumberOne = PositiveNumber.of(number);
        PositiveNumber newPositiveNumberTwo = PositiveNumber.of(number);

        //Act
        boolean result= newPositiveNumber.equals(null);

        //Assert
        assertFalse(result);

    }

    @Test
    void validateStringHashCode() {
        //Arrange
        int seedNumber = 5;

        //Act
        PositiveNumber numberOne = PositiveNumber.of(seedNumber);
        PositiveNumber numberTwo = PositiveNumber.of(seedNumber);


        assertEquals(numberOne.hashCode(), numberTwo.hashCode());
        assertNotSame(numberOne, numberTwo);
    }

}
