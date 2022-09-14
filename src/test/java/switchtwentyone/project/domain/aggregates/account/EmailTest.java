package switchtwentyone.project.domain.aggregates.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void testGetEmailOf() {
        Email testEmail = Email.of("maria@gmail.com");

        assertNotNull(testEmail);
    }

    @Test
    void testGetEmailOfISEP() {

        Email testEmail = Email.ofISEP("andreia");

        assertNotNull(testEmail);
    }

    @ParameterizedTest
    @CsvSource({"a><tr", "atr@", "123@..", "@isep.ipp"})
    void testEmailOfUnconformities(String email) {

        assertThrows(IllegalArgumentException.class, () -> {
            Email.of(email);
        });

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testEmailOfNullAndEmpty(String email) {

        assertThrows(IllegalArgumentException.class, () -> {
            Email.of(email);
        });

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testEmailOfISEPNullAndEmpty(String prefix) {

        assertThrows(IllegalArgumentException.class, () -> {
            Email.ofISEP(prefix);
        });

    }

    @ParameterizedTest
    @CsvSource({"iana@", "joao andre", "!8iefp"})
    void testEmailOfISEPUnconformities(String prefix) {
        assertThrows(IllegalArgumentException.class, () -> {
            Email.ofISEP(prefix);
        });

    }

    @ParameterizedTest
    @CsvSource({"info@quercus.pt", "   info@quercus.pt   ", "INfo@QUerCus.pt"})
    void testEmailMatchString(String match) {
        Email testEmail = Email.of("info@quercus.pt");

        boolean result = testEmail.matches(match);

        assertTrue(result);

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testEmailDoesNotMatchString(String match) {
        Email testEmail = Email.of("info@quercus.pt");

        boolean result = testEmail.matches(match);

        assertFalse(result);

    }

    @ParameterizedTest
    @CsvSource({"info@quercus.pt","    info@quercus.pt   ", "INFO@QUERCUS.PT"})
    void testEmailMatchEmail(String email) {

        Email oneEmail = Email.of(email);
        Email otherEmail = Email.of("info@quercus.pt");

        boolean result = oneEmail.sameValueAs(otherEmail);

        assertTrue(result);

    }

    @ParameterizedTest
    @CsvSource({"inf@quercus.pt", "  info@quercus.com",  "123@123.az"})
    void testEmailDoesNotMatchEmail(String email) {

        Email oneEmail = Email.of(email);
        Email otherEmail = Email.of("info@quercus.pt");

        boolean result = oneEmail.sameValueAs(otherEmail);

        assertFalse(result);

    }

    @Test
    void testEmailDoesNotMatchNullEmail() {
        Email nullEmail = null;
        Email otherEmail = Email.ofISEP("12345");

        boolean result = otherEmail.sameValueAs(nullEmail);

        assertFalse(result);
    }

    @Test
    void testEmailsAreEqual(){

        Email emailOne = Email.of("teresa@google.com");
        Email emailTwo = Email.of("   teresa@google.com   ");

        assertEquals(emailOne, emailTwo);
        assertNotSame(emailOne, emailTwo);
    }

    @Test
    void ensureSameEmailsAreEqual(){

        Email emailOne = Email.of("teresa@google.com");
        Email emailTwo = emailOne;

        assertEquals(emailOne, emailTwo);
    }

    @Test
    void ensureEmailIsNotEqualToNull(){

        Email emailOne = Email.of("teresa@google.com");
        Email emailTwo = null;


        assertNotEquals(emailOne, emailTwo);
    }
    @Test
    void ensureEmailIsNotEqualToInstanceOfOtherClass(){

        Email email = Email.of("teresa@google.com");
        Integer other = Integer.valueOf(20);

        assertNotEquals(email, other);
    }

    @Test
    void ensureDifferentEmailsAreNotEqual(){
        Email emailOne = Email.of("teresa@google.com");
        Email emailTwo = Email.of("vicente@google.com");

        assertNotEquals(emailOne, emailTwo);
    }

    @Test
    void testHashCodeForEqualObjects(){
        int hashOne = Email.of("teresa@google.com").hashCode();
        int hashTwo = Email.of("  teresa@google.com  ").hashCode();

        assertEquals(hashOne, hashTwo);
    }
    @Test
    void testHashCodeForDifferentObjects(){
        int hashOne = Email.of("teresa@google.com").hashCode();
        int hashTwo = Email.of("vicente@google.com").hashCode();

        assertNotEquals(hashOne, hashTwo);
    }


    @Test
    void getEmailData() {
        Email testEmail = Email.of("maria@gmail.com");

        String expected = "maria@gmail.com";

        String result = testEmail.getEmailData();

        assertEquals(expected, result);

    }
}