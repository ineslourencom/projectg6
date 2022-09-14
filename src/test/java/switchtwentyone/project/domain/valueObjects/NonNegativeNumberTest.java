package switchtwentyone.project.domain.valueObjects;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;

import static org.junit.jupiter.api.Assertions.*;

class NonNegativeNumberTest {

    @Test
    void negativeNumber() {
        int number = -1;

        assertThrows(IllegalArgumentException.class,
                () -> {
                    NonNegativeNumber.of(number);
                });
    }

    @Test
    void PositiveNumber() {
        //Arrange
        int number = 1;
        NonNegativeNumber numberTest = NonNegativeNumber.of(number);

        //Act
        int result = numberTest.getNumber();

        //Assert
        assertEquals(number, result);
    }

    @Test
    void Zero() {
        //Arrange
        int number = 0;
        NonNegativeNumber numberTest = NonNegativeNumber.of(number);

        //Act
        int result = numberTest.getNumber();

        //Assert
        assertEquals(number, result);
    }

    @Test
    void testEqualsObjectNotNull() {

        //Arrange
        int number = 4;
        NonNegativeNumber numberTest = NonNegativeNumber.of(number);

        //Act
        int result = numberTest.getNumber();

        //Assert
        assertNotNull(result);
    }

    @Test
    void testEqualsSameObject() {

        int number = 0;
        NonNegativeNumber numberTest = NonNegativeNumber.of(number);


        assertEquals(numberTest, numberTest);
    }

    @Test
    void testEqualsTwoEqualObjects() {
        //Arrange
        int number = 9;
        int numberTwo = 9;

        //Act
        NonNegativeNumber numberOneTest = NonNegativeNumber.of(number);
        NonNegativeNumber numberTwoTest = NonNegativeNumber.of(numberTwo);
        //Assert
        assertEquals(numberOneTest, numberTwoTest);
    }

    @Test
    void testDiferentObjects() {
        //Arrange
        int number = 9;
        int numberTwo = 6;

        //Act
        NonNegativeNumber numberOneTest = NonNegativeNumber.of(number);
        NonNegativeNumber numberTwoTest = NonNegativeNumber.of(numberTwo);
        //Assert
        assertNotEquals(numberOneTest, numberTwoTest);
    }

    @Test
    void testEqualsDiferentTypeObjects() {
        //Arrange
        int number = 9;

        //Act
        NonNegativeNumber numberOneTest = NonNegativeNumber.of(number);
        ProjectID projectID = new ProjectID(2);
        //Assert
        assertNotEquals(numberOneTest, projectID);
    }

    @Test
    void validateEqualHashCode() {
        //Arrange
        int number = 6;
        int numberTwo = 6;

        //Act
        NonNegativeNumber numberOneTest = NonNegativeNumber.of(number);
        NonNegativeNumber numberTwoTest = NonNegativeNumber.of(numberTwo);
        //Assert
        assertEquals(numberOneTest.hashCode(), numberTwoTest.hashCode());
    }

    @Test
    void validateDifferentHashCode() {
        //Arrange
        int number = 8;
        int numberTwo = 6;

        //Act
        NonNegativeNumber numberOneTest = NonNegativeNumber.of(number);
        NonNegativeNumber numberTwoTest = NonNegativeNumber.of(numberTwo);
        //Assert
        assertNotEquals(numberOneTest.hashCode(), numberTwoTest.hashCode());
    }

}