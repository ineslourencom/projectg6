package switchtwentyone.project.domain.aggregates.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.customer.NIF;

import static org.junit.jupiter.api.Assertions.*;

class NIFTest {

    @Test
    void NIFWithSameAttributesAreEqual() {
        int NIFNumber = 257715347;

        NIF NIFOne = NIF.of(NIFNumber);
        NIF NIFTwo = NIF.of(NIFNumber);

        assertEquals(NIFOne, NIFTwo);
    }

    @Test
    void NIFWithDifferentAttributesAreDifferent() {
        int NIFNumber = 257715347;
        int NIFNumberTwo = 506943119;

        NIF NIFOne = NIF.of(NIFNumber);
        NIF NIFTwo = NIF.of(NIFNumberTwo);

        assertNotEquals(NIFOne, NIFTwo);
    }

    @Test
    void NIFEqualsToItself() {
        int NIFNumber = 257715347;

        NIF NIFOne = NIF.of(NIFNumber);

        assertEquals(NIFOne, NIFOne);
    }

    @Test
    void NIFWithSameAttributesHAsSameHashCode() {
        int NIFNumber = 257715347;

        NIF NIFOne = NIF.of(NIFNumber);
        NIF NIFTwo = NIF.of(NIFNumber);

        assertEquals(NIFOne.hashCode(), NIFTwo.hashCode());
    }

    @Test
    void NIFWithDifferentAttributesHasDifferentHashCode() {
        int NIFNumber = 257715347;
        int NIFNumberTwo = 506943119;

        NIF NIFOne = NIF.of(NIFNumber);
        NIF NIFTwo = NIF.of(NIFNumberTwo);

        assertNotEquals(NIFOne.hashCode(), NIFTwo.hashCode());
    }

    @Test
    void NIFNotEqualsWithDifferentObjects() {
        int NIFNumber = 257715347;
        NIF NIFOne = NIF.of(NIFNumber);

        assertNotEquals(NIFOne, NIFNumber);
    }

    @Test
    void NIFNotEqualsNotNull() {
        int NIFNumber = 257715347;
        NIF NIFOne = NIF.of(NIFNumber);

        assertNotEquals(NIFOne, null);
    }

    @Test
    void NIFSameIdentity() {
        int NIFNumber = 257715347;
        NIF NIFOne = NIF.of(NIFNumber);
        NIF NIFTwo = NIF.of(NIFNumber);

        boolean result = NIFOne.sameValueAs(NIFTwo);

        assertTrue(result);
    }

    @Test
    void InvalidNIF_LessThanNineDigits() {
        int NIFNumber = 27153479;

        assertThrows(IllegalArgumentException.class, () -> NIF.of(NIFNumber));
    }

    @Test
    void InvalidNIF_() {
        int NIFNumber = 257715348;

        assertThrows(IllegalArgumentException.class, () -> NIF.of(NIFNumber));
    }

    @Test
    void getNIFAsINT() {
        int NIFNumber = 257715347;
        NIF nif = NIF.of(NIFNumber);

        int result = nif.getNIFAsINT();

        assertEquals(NIFNumber, result);

    }
}