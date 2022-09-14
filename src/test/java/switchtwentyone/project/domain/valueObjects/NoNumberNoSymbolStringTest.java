package switchtwentyone.project.domain.valueObjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.Role;

import static org.junit.jupiter.api.Assertions.*;

class NoNumberNoSymbolStringTest {

    @ParameterizedTest
    @CsvSource({"a><tr", "atr@", "123@.."})
    void testNoSymbol(String valueString) {

        assertThrows(IllegalArgumentException.class, () -> {
            NoNumberNoSymbolString.of(valueString);
        });
    }


    @Test
    void testNull() {
        String valueString = null;
        assertThrows(NullPointerException.class, () -> {
            NoNumberNoSymbolString.of(valueString);
        });
    }

    @Test
    void testEmpty() {
        String valueString = "";
        assertThrows(IllegalArgumentException.class, () -> {
            NoNumberNoSymbolString.of(valueString);
        });
    }

    @Test
    void validateString() {

        String test = "luis";

        NoNumberNoSymbolString expected = NoNumberNoSymbolString.of("luis");


        NoNumberNoSymbolString result = NoNumberNoSymbolString.of(test);

        assertEquals(expected, result);

    }

    @Test
    void testEqualsSameObject() {

        NoNumberNoSymbolString test = NoNumberNoSymbolString.of("Switch");

        assertEquals(test,test);
    }

    @Test
    void testEqualsTwoEqualObjects() {

        NoNumberNoSymbolString test = NoNumberNoSymbolString.of("Switch");
        NoNumberNoSymbolString testOne = NoNumberNoSymbolString.of("Switch");

        assertEquals(test,testOne);
    }

    @Test
    void testEqualsDiferentObjects() {

        NoNumberNoSymbolString test = NoNumberNoSymbolString.of("Switch");
        NoNumberNoSymbolString testOne = NoNumberNoSymbolString.of("Trade");

       boolean result  = test.equals(testOne);

       assertFalse(result);
    }

    @Test
    void testEqualsDiferentTypeObjects() {

        NoNumberNoSymbolString test = NoNumberNoSymbolString.of("Switch");
        Erole testOne = Erole.DEVELOPER;

        boolean result  = test.equals(testOne);

        assertFalse(result);
    }

    @Test
    void testEqualsObjectNotNull() {

        NoNumberNoSymbolString test = NoNumberNoSymbolString.of("Switch");

        assertNotEquals(null,test);
    }
    @Test
    void testNotEqualToNull() {
        //Arrange
        NoNumberNoSymbolString newNoNumberSymbolString = NoNumberNoSymbolString.of("Switch");


        //Act
        boolean result = newNoNumberSymbolString.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateStringHashCode() {

        NoNumberNoSymbolString expected = NoNumberNoSymbolString.of("luis");

        NoNumberNoSymbolString result = NoNumberNoSymbolString.of("luis");

        assertEquals(expected.hashCode(), result.hashCode());

    }

    @Test
    void getValueTest() {
        NoNumberNoSymbolString str = NoNumberNoSymbolString.of("yeti");
        String expected= "yeti";

        String result = str.getValue();

        assertEquals(expected, result);
    }

    @Test
    void getValueStringTest() {
        NoNumberNoSymbolString str = NoNumberNoSymbolString.of("yeti");
        String expected= "yeti";

        String result = str.getValueString();

        assertEquals(expected, result);
    }
}