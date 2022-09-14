package switchtwentyone.project.domain.aggregates.profile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTypeTest {


    @Test
    void ProfileTypeWithSameAttributesAreEqual() {
        String example = "Go Go Gadget";
        ProfileType one = ProfileType.of(example);
        ProfileType two = ProfileType.of(example);

        assertEquals(one, two);
    }

    @Test
    void ProfileTypeWithDifferentNIFIsDifferent() {
        String example = "Go Go Gadget";
        String exampleTwo = "Dont Go  Go  Gadget";
        ProfileType one = ProfileType.of(example);
        ProfileType two = ProfileType.of(exampleTwo);

        assertNotEquals(one, two);
    }

    @Test
    void ProfileTypeEqualsToItself() {
        String example = "GoGo Gadget";
        ProfileType one = ProfileType.of(example);

        assertEquals(one, one);
    }

    @Test
    void ProfileTypeWithSameAttributesHaveSameHashCode() {
        String example = "GoGo Gadget";
        ProfileType one = ProfileType.of(example);
        ProfileType two = ProfileType.of(example);

        assertEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void ProfileTypeWithDifferentAttributesHasDifferentHashCode() {
        String example = "GoGo Gadget";
        String exampleTwo = "Dont Go Go Gadget";
        ProfileType one = ProfileType.of(example);
        ProfileType two = ProfileType.of(exampleTwo);

        assertNotEquals(one.hashCode(), two.hashCode());
    }

    @Test
    void ProfileTypeNotEqualsWithDifferentObjects() {
        String example = "GoGo Gadget";
        ProfileType one = ProfileType.of(example);

        assertNotEquals(one, example);
    }

    @Test
    void ProfileTypeNotEqualsNotNull() {
        String example = "GoGo Gadget";
        ProfileType one = ProfileType.of(example);

        assertNotEquals(one, null);
    }

    @Test
    void ProfileTypeSameIdentity() {
        String example = "GoGo Gadget";
        ProfileType one = ProfileType.of(example);
        ProfileType two = ProfileType.of(example);

        boolean result = one.sameValueAs(two);

        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"a><tr", "atr@", "123@.."})
    void testNoSymbol(String valueString) {

        assertThrows(IllegalArgumentException.class, () -> {
            ProfileType.of(valueString);
        });
    }


    @Test
    void testNull() {
        assertThrows(NullPointerException.class, () -> {
            ProfileType.of(null);
        });
    }


    @Test
    void sameValueAs_true() {
        ProfileType profileType1 = ProfileType.of("test");
        ProfileType profileType2 = ProfileType.of("test");

        boolean result = profileType1.sameValueAs(profileType2);

        assertTrue(result);

    }

    @Test
    void sameValueAs_false() {
        ProfileType profileType1 = ProfileType.of("test");
        ProfileType profileType2 = ProfileType.of("testTwo");

        boolean result = profileType1.sameValueAs(profileType2);

        assertFalse(result);

    }

}