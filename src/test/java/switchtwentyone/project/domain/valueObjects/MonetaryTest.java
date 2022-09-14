package switchtwentyone.project.domain.valueObjects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class MonetaryTest {

    @Test
    void ensureCreationSucceeds() {
        Monetary value = Monetary.of(0, Currency.getInstance("EUR"));

        assertNotNull(value);
    }

    @Test
    void ensureCreationFailsWithNegativeAmount() {

        Currency currency = Currency.getInstance("EUR");

        assertThrows(IllegalArgumentException.class, () -> Monetary.of(-1, currency));

    }

    @Test
    void testHashCodeEqualObjects() {
        Monetary amountOne = Monetary.of(5, Currency.getInstance("EUR"));
        Monetary amountTwo = Monetary.of(5, Currency.getInstance("EUR"));

        int hashOne = amountOne.hashCode();
        int hashTwo = amountTwo.hashCode();

        assertEquals(hashOne, hashTwo);

    }

    @Test
    void testHashCodeDifferentObjects() {
        Monetary amountOne = Monetary.of(5, Currency.getInstance("EUR"));
        Monetary amountTwo = Monetary.of(5, Currency.getInstance("USD"));

        int hashOne = amountOne.hashCode();
        int hashTwo = amountTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);

    }

    @Test
    void ensureEqualsItself() {

        Monetary amount = Monetary.of(5, Currency.getInstance("EUR"));

        assertEquals(amount, amount);

    }

    @Test
    void ensureEqualsEqualObject() {
        Monetary amountOne = Monetary.of(5, Currency.getInstance("USD"));
        Monetary amountTwo = Monetary.of(5, Currency.getInstance("USD"));


        assertEquals(amountOne, amountTwo);

    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        Monetary amountOne = Monetary.of(5, Currency.getInstance("EUR"));
        Monetary amountTwo = Monetary.of(5, Currency.getInstance("USD"));

        assertNotEquals(amountOne, amountTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        Monetary amount = Monetary.of(5, Currency.getInstance("EUR"));
        Object somethingElse = new Object();

        assertNotEquals(amount, somethingElse);

    }

    @Test
    void ensureItDoesNotEqualNull() {
        Monetary amount = Monetary.of(5, Currency.getInstance("EUR"));

        assertNotEquals(amount, null);

    }

    @Test
    void ensureSameValueReturnsTrueWhenValuesAreTheSame() {
        Monetary amountOne = Monetary.of(5, Currency.getInstance("USD"));
        Monetary amountTwo = Monetary.of(5, Currency.getInstance("USD"));

        boolean result = amountOne.sameValueAs(amountTwo);

        assertTrue(result);

    }

    @Test
    void ensureSameValueReturnsFalseWhenValuesAreDifferent() {
        Monetary amountOne = Monetary.of(5, Currency.getInstance("USD"));
        Monetary amountTwo = Monetary.of(10, Currency.getInstance("USD"));

        boolean result = amountOne.sameValueAs(amountTwo);

        assertFalse(result);
    }

    @Test
    void ensureSameValueReturnsFalseWhenOtherIsNull() {
        Monetary amount = Monetary.of(5, Currency.getInstance("USD"));

        boolean result = amount.sameValueAs(null);

        assertFalse(result);
    }

    @Test
    void getCurrencyTest() {
        Monetary amount = Monetary.of(5, Currency.getInstance("USD"));
        Currency expected = Currency.getInstance("USD");

        Currency result = amount.getCurrency();

        assertEquals(expected, result);
    }

    @Test
    void getAmountTest() {
        Monetary cost = Monetary.of(5, Currency.getInstance("USD"));
        double expected = 5;

        double result = cost.getAmount();

        assertEquals(expected, result);

    }
}