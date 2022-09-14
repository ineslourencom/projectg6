package switchtwentyone.project.domain.aggregates.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class PredefinedStatusTest {

    @ParameterizedTest
    @ValueSource(strings = {"planned", "INCEPTION", "  Elaboration  ", "cOnstrucTION  ", "Warranty"})
    void ensureCreationSucceedsWithValidValues(String input) {

        assertDoesNotThrow(() -> {
            PredefinedStatus.of(input);
        });
        assertNotNull(PredefinedStatus.of(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n", "invalid string"})
    void ensureCreationFailsWithNullAndEmptyValues(String input) {
        assertThrows(IllegalArgumentException.class, () -> {
            PredefinedStatus.of(input);
        });
    }

    @Test
    void ensureEqualsItself() {

        PredefinedStatus status = PredefinedStatus.of("inception");

        assertEquals(status, status);

    }

    @Test
    void ensureEqualsObjectWithSameValues() {
        PredefinedStatus statusOne = PredefinedStatus.of("inception");
        PredefinedStatus statusTwo = PredefinedStatus.of("  inception  ");

        assertEquals(statusOne, statusTwo);
    }

    @Test
    void ensureDoesNotEqualObjectWithDifferentValues() {
        PredefinedStatus statusOne = PredefinedStatus.of("inception");
        PredefinedStatus statusTwo = PredefinedStatus.of("  elaboration ");

        assertNotEquals(statusOne, statusTwo);
    }

    @Test
    void ensureDoesNotEqualNull() {
        PredefinedStatus statusOne = PredefinedStatus.of("inception");
        PredefinedStatus statusTwo = null;

        assertNotEquals(statusOne, statusTwo);
    }

    @Test
    void ensureDoesNotEqualObjectOfDifferentClass() {
        PredefinedStatus status = PredefinedStatus.of("inception");
        Object somethingElse = new Object();

        assertNotEquals(status, somethingElse);

    }

    @Test
    void ensureHashCodeOfEqualObjectsIsEqual() {
        PredefinedStatus statusOne = PredefinedStatus.of("inception");
        PredefinedStatus statusTwo = PredefinedStatus.of("inception");
        int hashOne = statusOne.hashCode();
        int hashTwo = statusTwo.hashCode();

        assertEquals(hashOne, hashTwo);

    }

    @Test
    void ensureHashCodeOfDifferentObjectsIsDifferent() {
        PredefinedStatus statusOne = PredefinedStatus.of("inception");
        PredefinedStatus statusTwo = PredefinedStatus.of("closed");
        int hashOne = statusOne.hashCode();
        int hashTwo = statusTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);

    }

    @Test
    void ensureSameValueReturnsTrueForObjectWithTheSameValue() {
        PredefinedStatus statusOne = PredefinedStatus.of("inception");
        PredefinedStatus statusTwo = PredefinedStatus.of("inception");

        boolean result = statusOne.sameValueAs(statusTwo);

        assertTrue(result);
    }

    @Test
    void ensureSameValueReturnsFalseForObjectWithDifferentValue() {
        PredefinedStatus statusOne = PredefinedStatus.of("inception");
        PredefinedStatus statusTwo = PredefinedStatus.of("closed");

        boolean result = statusOne.sameValueAs(statusTwo);

        assertFalse(result);
    }

    @Test
    void ensureSameValueReturnsFalseForObjectThatIsNull() {
        PredefinedStatus statusOne = PredefinedStatus.of("inception");
        PredefinedStatus statusTwo = null;

        boolean result = statusOne.sameValueAs(statusTwo);

        assertFalse(result);
    }

    @Test
    void getValueAsString() {
        PredefinedStatus status = PredefinedStatus.of("inception");
        String expected = "INCEPTION";

        String result = status.getValueAsString();

        assertEquals(expected, result);

    }
}