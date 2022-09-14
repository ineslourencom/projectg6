package switchtwentyone.project.domain.valueObjects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class LimitedPercentageTest {

    @Test
    void createLimitedPercentageMinTest() {

        LimitedPercentage percentageOne = LimitedPercentage.percentage(0);
        LimitedPercentage percentageTwo = LimitedPercentage.min();

        assertEquals(percentageOne, percentageTwo);
    }

    @Test
    void createLimitedPercentageMaxTest() {
        LimitedPercentage percentageOne = LimitedPercentage.percentage(100);
        LimitedPercentage percentageTwo = LimitedPercentage.max();

        assertEquals(percentageOne, percentageTwo);
    }

    @Test
    void ensureCreationWithPercentageFailsWithValueBelowLimit() {
        assertThrows(IllegalArgumentException.class, () -> LimitedPercentage.percentage(101));

    }

    @Test
    void ensureCreationWithPercentageFailsWithValueAboveLimit() {
        assertThrows(IllegalArgumentException.class, () -> LimitedPercentage.percentage(-1));

    }
    @Test
    void ensureCreationWithDecimalFailsWithValueBelowLimit() {

        assertThrows(IllegalArgumentException.class, () -> LimitedPercentage.decimal(1.1));

    }

    @Test
    void ensureCreationWithDecimalFailsWithValueAboveLimit() {
        assertThrows(IllegalArgumentException.class, () -> LimitedPercentage.percentage(-1));

    }

    @Test
    void  ensureCreationWithPercentageSucceeds() {
        LimitedPercentage percentage = LimitedPercentage.percentage(78);

        assertNotNull(percentage);


    }

    @Test
    void ensureCreationWithDecimalSucceeds() {
        LimitedPercentage percentage = LimitedPercentage.decimal(0.8);

        assertNotNull(percentage);
    }

    @Test
    void ensureSameValueReturnsTrueWhenHasTheSameValue() {
        LimitedPercentage percentageOne = LimitedPercentage.percentage(0);
        LimitedPercentage percentageTwo = LimitedPercentage.min();

        boolean result = percentageOne.sameValueAs(percentageTwo);

        assertTrue(result);
    }

    @Test
    void ensureSameValueReturnsFalseWhenHasDifferentValue() {

        LimitedPercentage percentageOne = LimitedPercentage.max();
        LimitedPercentage percentageTwo = LimitedPercentage.min();

        boolean result = percentageOne.sameValueAs(percentageTwo);

        assertFalse(result);
    }
    @Test
    void ensureSameValueReturnsFalseOtherObjectIsNull() {
        LimitedPercentage percentageOne = LimitedPercentage.max();
        LimitedPercentage percentageTwo = null;

        boolean result = percentageOne.sameValueAs(percentageTwo);

        assertFalse(result);
    }

    @Test
    void ensureIsNotEqualToNull() {

        LimitedPercentage percentage = LimitedPercentage.max();

        assertNotEquals(percentage, null);

    }
    @Test
    void ensureIsNotEqualToInstanceOfOtherClass() {
        LimitedPercentage percentage = LimitedPercentage.max();
        Object thing = new Object();

        assertNotEquals(percentage, thing);

    }
    @Test
    void ensureIsEqualToItself() {
        LimitedPercentage percentage = LimitedPercentage.max();

        assertEquals(percentage, percentage);

    }
    @Test
    void ensureIsEqualToObjectWithSameValues() {
        LimitedPercentage percentageOne = LimitedPercentage.percentage(72);
        LimitedPercentage percentageTwo = LimitedPercentage.decimal(0.72);

        assertEquals(percentageOne, percentageTwo);
    }
    @Test
    void ensureIsNotEqualToObjectWithDifferentValues() {
        LimitedPercentage percentageOne = LimitedPercentage.percentage(72);
        LimitedPercentage percentageTwo = LimitedPercentage.decimal(0.70);

        assertNotEquals(percentageOne, percentageTwo);
    }


    @Test
    void testHashCodeDifferentObjects() {
        LimitedPercentage percentageOne = LimitedPercentage.min();
        LimitedPercentage percentageTwo = LimitedPercentage.max();

        int hashOne = percentageOne.hashCode();
        int hashTwo = percentageTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }

    @Test
    void testHashCodeEqualObjects() {
        LimitedPercentage percentageOne = LimitedPercentage.percentage(100);
        LimitedPercentage percentageTwo = LimitedPercentage.max();

        int hashOne = percentageOne.hashCode();
        int hashTwo = percentageTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void getPercentValueTest() {
        LimitedPercentage percentage = LimitedPercentage.max();

        double expected = 1;
        double result = percentage.getPercentValue();

        assertEquals(expected, result);
    }
}