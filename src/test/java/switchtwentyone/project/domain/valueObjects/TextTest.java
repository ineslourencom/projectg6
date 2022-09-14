package switchtwentyone.project.domain.valueObjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void ensureCreationFailsWithNullAndEmptyValues(String input) {

        assertThrows(IllegalArgumentException.class, () -> Text.write(input));
    }

    @Test
    void ensureCreationFailsWithValueAboveMaximum() {

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 2001; i++) {
            builder.append("a");

        }
        String input = builder.toString();


        assertThrows(IllegalArgumentException.class, () -> Text.write(input));
    }
    @Test
    void ensureCreationSucceedsWithValueBelowMaximum() {

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 2000; i++) {
            builder.append("a");

        }
        String input = builder.toString();

        assertDoesNotThrow(() -> Text.write(input));
    }


    @Test
    void ensureTrailingSpacesAreRemoved() {

        Text expected = Text.write("hello world");

        Text result = Text.write("   hello world        ");

        assertEquals(expected, result);

    }

    @Test
    void ensureCreationSucceeds() {

        Text result = Text.write("helloWorld");

        assertNotNull(result);

    }

    @Test
    void ensureItEqualsItself() {
        Text text = Text.write("Hello world!");

        assertEquals(text, text);
    }

    @Test
    void ensureItEqualsEqualObject() {
        String input = "Hello world!";
        Text textOne = Text.write(input);
        Text textTwo = Text.write(input);

        assertEquals(textOne, textTwo);

    }
    @Test
    void ensureItDoesNotEqualDifferentObject() {
        Text textOne = Text.write("input");
        Text textTwo = Text.write("input two");

        assertNotEquals(textOne, textTwo);
    }
    @Test
    void ensureItDoesNotEqualNull() {
        Text textOne = Text.write("input");
        Text textTwo = null;

        assertNotEquals(textOne, textTwo);
    }
    @Test
    void ensureItDoesNotEqualInstanceOfOtherClass() {

        Text text = Text.write("input");
        Object otherThing = new Object();

        assertNotEquals(text, otherThing);
    }


    @Test
    void testHashCodeOfEqualObjects() {

        Text textOne = Text.write("hello world");
        Text textTwo = Text.write("   hello world        ");
        int hashOne = textOne.hashCode();
        int hashTwo = textTwo.hashCode();

        boolean result = (hashOne == hashTwo);

        assertTrue(result);

    }

    @Test
    void testHashCodeOfDifferentObjects() {
        Text textOne = Text.write("hello world");
        Text textTwo = Text.write("   helloworld        ");
        int hashOne = textOne.hashCode();
        int hashTwo = textTwo.hashCode();

        boolean result = (hashOne == hashTwo);

        assertFalse(result);
    }


    @Test
    void ensureSameValueReturnsTrueWhenValueIsTheSame() {
        String input = "Hello world!";
        Text textOne = Text.write(input);
        Text textTwo = Text.write(input);

        boolean result = textOne.sameValueAs(textTwo);

        assertTrue(result);

    }

    @Test
    void ensureSameValueReturnsFalseWhenValueIsNotTheSame() {

        Text textOne = Text.write("hello world");
        Text textTwo = Text.write("helloworld");

        boolean result = textOne.sameValueAs(textTwo);

        assertFalse(result);

    }

    @Test
    void ensureSameValueReturnsFalseWhenOtherObjectIsNull() {
        Text textOne = Text.write("hello world");
        Text textTwo = null;

        boolean result = textOne.sameValueAs(textTwo);

        assertFalse(result);
    }

    @Test
    void getTextStringTest() {
        String expected = "hello world";
        Text textOne = Text.write("hello world");

        String result = textOne.getValue();

        assertEquals(expected, result);
    }
    @Test
    void getValueTest(){
        String expected = "hello world";
        Text textOne = Text.write("hello world");

        String result = textOne.getValue();

        assertEquals(expected, result);

    }
}
