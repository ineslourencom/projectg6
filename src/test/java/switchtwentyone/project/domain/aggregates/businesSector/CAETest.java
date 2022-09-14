package switchtwentyone.project.domain.aggregates.businesSector;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;

import static org.junit.jupiter.api.Assertions.*;

class CAETest {


    @Test
    void ensureSameValueReturnsTrueWhenValueIsTheSame() {

        CAE caeOne = CAE.of("   00000     ");
        CAE caeTwo = CAE.of("00000");

        boolean result = caeOne.sameValueAs(caeTwo);

        assertTrue(result);

    }

    @Test
    void ensureSameValueReturnsFalseWhenValueIsNotTheSame() {
        CAE caeOne = CAE.of("00000");
        CAE caeTwo = CAE.of("00100");

        boolean result = caeOne.sameValueAs(caeTwo);

        assertFalse(result);
    }

    @Test
    void ensureSameValueReturnsFalseWhenOtherObjectIsNull() {
        CAE caeOne = CAE.of("00000");
        CAE caeTwo = null;

        boolean result = caeOne.sameValueAs(caeTwo);

        assertFalse(result);
    }

    @Test
    void testHashCodeOfDifferentObjects() {
        CAE caeOne = CAE.of("00000");
        CAE caeTwo = CAE.of("00100");

        int hashOne = caeOne.hashCode();
        int hashTwo = caeTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }

    @Test
    void testHashCodeOfEqualObjects() {
        CAE caeOne = CAE.of("00000");
        CAE caeTwo = CAE.of("00000");

        int hashOne = caeOne.hashCode();
        int hashTwo = caeTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureItDoesNotEqualInstanceOfOtherClass() {
        CAE cae = CAE.of("00000");
        Object somethingElse = new Object();

        assertNotEquals(cae, somethingElse);
    }

    @Test
    void ensureItDoesNotEqualNull() {
        CAE cae = CAE.of("00000");

        assertNotEquals(null, cae);
    }

    @Test
    void ensureItDoesNotEqualDifferentObject() {
        CAE caeOne = CAE.of("00000");
        CAE caeTwo = CAE.of("00100");

        assertNotEquals(caeOne, caeTwo);

    }

    @Test
    void ensureItEqualsEqualObject() {
        CAE caeOne = CAE.of("00000");
        CAE caeTwo = CAE.of("00000");

        assertEquals(caeOne, caeTwo);

    }

    @Test
    void ensureItEqualsItself() {
        CAE caeTwo = CAE.of("00000");

        assertEquals(caeTwo, caeTwo);
    }

    @Test
    void ensureTrailingSpacesAreRemoved() {
        CAE caeOne = CAE.of("   00000     ");
        CAE caeTwo = CAE.of("00000");

        assertEquals(caeOne, caeTwo);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void ensureCreationFailsWithNullAndEmptyValues(String input) {

        assertThrows(IllegalArgumentException.class, () -> CAE.of(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "a12345b", "122345", "12e45"})
    void ensureCreationFailsWhenStringDoesNotMatchPattern(String input) {
        assertThrows(IllegalArgumentException.class, () -> CAE.of(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "01123", " 65432  "})
    void ensureCreationSucceedsWhenStringMatchesPattern(String input) {
        CAE cae = CAE.of(input);

        assertNotNull(cae);
    }

    @Test
    void getCAEAsString() {
        String SCAE = "00000";
        CAE cae = CAE.of(SCAE);

        String result = cae.getCAEAsString();

        assertEquals(SCAE, result);
    }
}