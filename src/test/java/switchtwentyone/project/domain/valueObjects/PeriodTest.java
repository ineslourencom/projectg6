package switchtwentyone.project.domain.valueObjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {

    @Test
    void createPeriodBetweenTest() {
        Period testingPeriod = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));

        assertNotNull(testingPeriod);
    }

    @Test
    void createPeriodStartingInDateTest() {
        Period testingPeriod = Period.starting(
                LocalDate.of(1995, 7, 13));

        assertNotNull(testingPeriod);
    }

    @Test
    void ensureCreationOfPeriodBetweenFailsWithNullStartDate() {
        LocalDate date = LocalDate.of(1783, 9, 6);

        assertThrows(IllegalArgumentException.class, () -> Period.between(null, date));

    }

    @Test
    void ensureCreationOfPeriodStartingFailsWithNullStartDate() {
        assertThrows(IllegalArgumentException.class, () -> Period.starting(null));

    }

    @Test
    void ensureCreationFailsWithNullEndDate() {
        LocalDate date = LocalDate.of(1783, 9, 6);

        assertThrows(IllegalArgumentException.class, () -> Period.between(date, null));

    }

    @Test
    void ensureCreationFailsWithSwitchedDates() {
        LocalDate startDate = LocalDate.of(1783, 9, 6);
        LocalDate endDate = LocalDate.of(1893, 8, 9);

        assertThrows(IllegalArgumentException.class, () -> Period.between(endDate, startDate));

    }

    @Test
    void ensureSameValueReturnsTrue() {
        Period periodOne = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));
        Period periodTwo = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));

        boolean result = periodOne.sameValueAs(periodTwo);

        assertTrue(result);

    }

    @Test
    void ensureDifferentValueReturnsFalse() {
        Period periodOne = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));
        Period periodTwo = Period.starting(
                LocalDate.of(1995, 7, 13));
        boolean result = periodOne.sameValueAs(periodTwo);

        assertFalse(result);

    }

    @Test
    void ensureNullReturnsFalse() {
        Period periodOne = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));
        Period periodTwo = null;
        boolean result = periodOne.sameValueAs(periodTwo);

        assertFalse(result);

    }

    @Test
    void ensureIsNotEqualToInstanceOfOtherClass() {
        Period period = Period.starting(
                LocalDate.of(1995, 7, 13));
        LocalDate date = LocalDate.now();

        assertNotEquals(period, date);

    }

    @Test
    void ensureItEqualsItself() {
        Period period = Period.starting(
                LocalDate.of(1995, 7, 13));

        assertEquals(period, period);

    }

    @Test
    void ensureItDoesNotEqualNull() {
        Period period = Period.starting(
                LocalDate.of(1995, 7, 13));

        assertNotEquals(period, null);


    }
    @Test
    void ensureEqualsEqualObject() {
        Period periodOne = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));
        Period periodTwo = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));


        assertEquals(periodOne, periodTwo);
    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        Period periodOne = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));
        Period periodTwo = Period.starting(
                LocalDate.of(1995, 7, 13));

        assertNotEquals(periodOne, periodTwo);

    }

    @Test
    void testHashCodeEqualObjects() {
        Period periodOne = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));
        Period periodTwo = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));

        int one = periodOne.hashCode();
        int other = periodTwo.hashCode();

        assertEquals(one, other);
    }

    @Test
    void testHashCodeDifferentObjects() {
        Period periodOne = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));
        Period periodTwo = Period.starting(
                LocalDate.of(1995, 7, 13));

        int one = periodOne.hashCode();
        int other = periodTwo.hashCode();

        assertNotEquals(one, other);

    }

    @Test
    void getStartingDate() {
        Period period = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));

        LocalDate expected = LocalDate.of(1995, 7, 13);

        LocalDate result =period.getStartingDate();

        assertEquals(expected, result);
    }

    @Test
    void getEndingDate() {
        Period period = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));

        LocalDate expected = LocalDate.of(2008, 5, 9);

        LocalDate result =period.getEndingDate();

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"1995, 7, 13","2000, 7, 3", "2008, 5,9"})
    void containsDate(int year, int month , int day) {
        Period period = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));

        boolean result = period.containsDate(LocalDate.of(year, month, day));

        assertTrue(result);

    }
    @ParameterizedTest
    @CsvSource({"1995, 7, 12","2008, 5,10"})
    void doesNotContainDate(int year, int month , int day) {
        Period period = Period.between(
                LocalDate.of(1995, 7, 13),
                LocalDate.of(2008, 5, 9));
        boolean result = period.containsDate(LocalDate.of(year, month, day));

        assertFalse(result);

    }

    @Test
    void limitedPeriodContainsPeriodTest() {
        Period period = Period.between(LocalDate.of(1993, 7,8), LocalDate.of(1993,8,8));
        Period other = Period.between(LocalDate.of(1993, 7,8), LocalDate.of(1993,8,8));

        boolean result = period.containsPeriod(other);

        assertTrue(result);

    }

    @Test
    void doesNotContainPeriodTest() {
        Period period = Period.between(LocalDate.of(1993, 7,8), LocalDate.of(1993,8,8));
        Period other = Period.between(LocalDate.of(1993, 7,7), LocalDate.of(1993,8,9));

        boolean result = period.containsPeriod(other);

        assertFalse(result);
    }
}