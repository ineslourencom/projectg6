package switchtwentyone.project.domain.aggregates.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @ParameterizedTest
    @CsvSource({
            "asdfghjk,      0",
            "asdfghjk,      1",
            "ASDFGHJK,      0",
            "ASDFGHJK,      1",
            "12345678,      0",
            "12345678,      1",
            "!\"#$%&/(,     0",
            "!\"#$%&/(,     1",
            "'        ',    0",
            "'        ',    1",
            "asdfghjK,      2",
            "Asdfghjk,      2",
            "asdFghjk,      2",
            "aSDFGHJK,      2",
            "ASDFGHJk,      2",
            "ASDfGHJK,      2",
            "1234567a,      2",
            "1234A678,      2",
            "!2345678,      2",
            "!\"#$%&/A,     2",
            "!\"#1%&/(,     2",
            "a\"#$%&/(,     2",
            "'       a',    2",
            "'   1    ',    2",
            "'A       ',    2",
            "asdf1hjK,      3",
            "Asdfghj2,      3",
            "3sdFghjk,      3",
            "aSDF4HJK,      3",
            "5SDFGHJk,      3",
            "ASDfGHJ6,      3",
            "A234567a,      3",
            "1234A67b,      3",
            "!234R678,      3",
            "!\"#7%&/A,     3",
            "l\"#1%&/(,     3",
            "a\"#W%&/(,     3",
            "'   8   a',    3",
            "'   1   S',    3",
            "'A  v    ',    3",
            "a#df1hjK,      4",
            "As&fghj2,      4",
            "3sdF/hjk,      4",
            "aSDF4)JK,      4",
            "5SDFGH(k,      4",
            "A'DfGHJ6,      4",
            "A2.4567a,      4",
            "123\\A67b,     4",
            "!234Rx78,      4",
            "!z#7%&/A,      4",
            "l\"#1%&/Q,     4",
            "a\"#W%&3(,     4",
            "'T  8   a',    4",
            "'   1h  S',    4",
            "'A  v   2',    4"
    })
    void getString_Test_Success_(String input, int cmplx) {
        Password testPasswordVO = Password.of(input, cmplx);

        String result = testPasswordVO.getString();

        assertEquals(input, result);
    }

    @ParameterizedTest
    @CsvSource({
            "1aA!5678,                                  -1",
            "12345678,                                  2",
            "12345678,                                  4",
            "1aA!5678,                                  5",
            "1aA!567,                                      -1",
            "1aA!567,                                      0",
            "1aA!567,                                      4",
            "1aA!567,                                      5",
            "1aA!12345678901234567890123456789,        -1",
            "1aA!1234567890123456789012345678,         -1",
            "1aA!12345678901234567890123456789,        0",
            "1aA!12345678901234567890123456789,        4",
            "1aA!12345678901234567890123456789,        5",
            "1aA!1234567890123456789012345678,         5",
            ",    -1",
            ",    0",
            ",    1",
            ",    2",
            ",    3",
            ",    4",
            ",    5",
            "'',    -1",
            "'',    0",
            "'',    1",
            "'',    2",
            "'',    3",
            "'',    4",
            "'',    5"
    })
    void of_Test_FailNullAndEmpty(String input, int complexity) {
        Password testPassword = Password.of(input, complexity);

        assertNull(testPassword);
    }

    @ParameterizedTest
    @CsvSource({
            "asdfghjk,      0",
            "'        ',    1",
            "'A       ',    2",
            "'A  v    ',    3",
            "'A  v   2',    4"
    })
    void sameValueAs_Test_True(String input, int cmplx) {
        Password testPasswordVO1 = Password.of(input, cmplx);
        Password testPasswordVO2 = Password.of(input, cmplx);

        Boolean result = testPasswordVO1.sameValueAs(testPasswordVO2);

        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({
            "asdfghjk,      0",
            "'        ',    1",
            "'A       ',    2",
            "'A  v    ',    3",
            "'A  v   2',    4"
    })
    void Equals_Test_True(String input, int cmplx) {
        Password testPasswordVO1 = Password.of(input, cmplx);

        Password testPasswordVO2 = Password.of(input, cmplx);

        assertEquals(testPasswordVO1, testPasswordVO2);
    }

    @ParameterizedTest
    @CsvSource({
            "'asdfghjk', 'asdfghjk ',     0",
            "'        ', '         ',     1",
            "'A       ', 'A       .',     2",
            "'A  v    ', 'A  v    1',     3",
            "'A  v   2', 'A  v   2s',     4"
    })
    void sameValueAs_Test_False(String input1, String input2, int cmplx) {
        Password testPasswordVO1 = Password.of(input1, cmplx);
        Password testPasswordVO2 = Password.of(input2, cmplx);

        Boolean result = testPasswordVO1.sameValueAs(testPasswordVO2);

        assertFalse(result);
    }

    @ParameterizedTest
    @CsvSource({
            "'asdfghjk', 'asdfghjk ',     0",
            "'        ', '         ',     1",
            "'A       ', 'A       .',     2",
            "'A  v    ', 'A  v    1',     3",
            "'A  v   2', 'A  v   2s',     4"
    })
    void hashCode_Test_Same(String input1, String input2, int cmplx) {
        int hashOne = Password.of(input1, cmplx).hashCode();
        int hashTwo = Password.of(input2, cmplx).hashCode();

        assertNotEquals(hashOne, hashTwo);
    }
}