package switchtwentyone.project.domain.aggregates.userStory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciNumberTest {

    @Test
    void ensureCreationSucceeds() {
        FibonacciNumber value = FibonacciNumber.of(1);
        assertNotNull(value);
    }

    @Test
    void ensureCreationFailsWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> FibonacciNumber.of(100));

    }

    @Test
    void testHashCodeEqualObjects() {
        FibonacciNumber amountOne = FibonacciNumber.of(1);
        FibonacciNumber amountTwo = FibonacciNumber.of(1);

        int hashOne = amountOne.hashCode();
        int hashTwo = amountTwo.hashCode();

        assertEquals(hashOne, hashTwo);

    }

    @Test
    void testHashCodeDifferentObjects() {
        FibonacciNumber amountOne = FibonacciNumber.of(1);
        FibonacciNumber amountTwo = FibonacciNumber.of(2);

        int hashOne = amountOne.hashCode();
        int hashTwo = amountTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);

    }

    @Test
    void ensureEqualsItself() {

        FibonacciNumber amount = FibonacciNumber.of(1);

        assertEquals(amount, amount);

    }

    @Test
    void ensureEqualsEqualObject() {
        FibonacciNumber amountOne = FibonacciNumber.of(1);
        FibonacciNumber amountTwo = FibonacciNumber.of(1);

        assertEquals(amountOne, amountTwo);

    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        FibonacciNumber amountOne = FibonacciNumber.of(1);
        FibonacciNumber amountTwo = FibonacciNumber.of(2);

        assertNotEquals(amountOne, amountTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        FibonacciNumber amountOne = FibonacciNumber.of(1);
        Object somethingElse = new Object();

        assertNotEquals(amountOne, somethingElse);

    }

    @Test
    void ensureItDoesNotEqualNull() {
        FibonacciNumber amountOne = FibonacciNumber.of(1);

        assertNotEquals(null, amountOne);

    }

    @Test
    void ensureSameValueReturnsTrueWhenValuesAreTheSame() {
        FibonacciNumber amountOne = FibonacciNumber.of(1);
        FibonacciNumber amountTwo = FibonacciNumber.of(1);

        boolean result = amountOne.sameValueAs(amountTwo);

        assertTrue(result);

    }

    @Test
    void ensureSameValueReturnsFalseWhenValuesAreDifferent() {
        FibonacciNumber amountOne = FibonacciNumber.of(1);
        FibonacciNumber amountTwo = FibonacciNumber.of(2);

        boolean result = amountOne.sameValueAs(amountTwo);

        assertFalse(result);
    }

    @Test
    void ensureSameValueReturnsFalseWhenOtherIsNull() {
        FibonacciNumber amountOne = FibonacciNumber.of(1);

        boolean result = amountOne.sameValueAs(null);

        assertFalse(result);
    }
}