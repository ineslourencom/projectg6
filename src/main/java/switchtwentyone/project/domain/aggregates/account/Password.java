package switchtwentyone.project.domain.aggregates.account;

import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password implements ValueObject<Password> {

    /**
     * Attribute of the value object Password.
     */
    private final String passwordString;

    /**
     * Password constructor (private).
     *
     * @param stringData string to convert to password
     */
    private Password(final String stringData) {
        this.passwordString = stringData;
    }

    /**
     * Creator method for the value object Password.
     * This creation will only happen if minimum required conditions are met.
     * These conditions are defined by the passwordComplexityStandard variable.
     * It ranges between 0 and 4 (an integer from lowest to maximum complexity
     * resulting from the usage of different types of characters: numbers,
     * lower-case letters, upper-case letters and punctuation marks). Value 4
     * is recommended but 1 is the current project wide implementation. Minimum
     * string size is 8 and maximum string size is 32 (these standardized sizes
     * are hard coded in isStringComplex() validation function).
     *
     * @param passwordData               string password
     * @param passwordComplexityStandard integer ranging from 0 to 4
     * @return Password object if operation successful, null if otherwise
     */
    public static Password of(final String passwordData,
                              final int passwordComplexityStandard) {
        Password answer;
        if (passwordData != null
                && isStringComplex(passwordData, passwordComplexityStandard)) {
            answer = new Password(passwordData);
        } else {
            answer = null;
        }
        return answer;
    }

    /**
     * This code validates the entry string according to its complexity.
     *
     * @param passwordString a UTF_8 string to be hashed
     * @param complexity     integer ranging from 0 to 4
     * @return a boolean that is true if string is not null
     */
    private static boolean isStringComplex(final String passwordString,
                                           final int complexity) {

        boolean answer;
        int counter = 0;
        boolean validateParameters = false;
        int maxStringSize = 32;
        int minStringSize = 8;
        int stringSize = passwordString.length();

        if ((complexity >= 0
                && complexity <= 4)
                && (stringSize >= minStringSize
                && stringSize <= maxStringSize)) {
            validateParameters = true;
        }

        if (validateParameters) {

            final Pattern lowerCaseLetterPattern = Pattern.compile("[a-z]",
                    Pattern.UNICODE_CHARACTER_CLASS);
            final Matcher lowerCaseLetterMatcher =
                    lowerCaseLetterPattern.matcher(passwordString);

            final Pattern upperCaseLetterPattern = Pattern.compile("[A-Z]",
                    Pattern.UNICODE_CHARACTER_CLASS);
            final Matcher upperCaseLetterMatcher =
                    upperCaseLetterPattern.matcher(passwordString);

            final Pattern numberPattern = Pattern.compile("[0-9]",
                    Pattern.UNICODE_CHARACTER_CLASS);
            final Matcher numberMatcher =
                    numberPattern.matcher(passwordString);

            final Pattern specialCharPattern = Pattern.compile("[^A-Za-z0-9]",
                    Pattern.UNICODE_CHARACTER_CLASS);
            final Matcher specialCharMatcher =
                    specialCharPattern.matcher(passwordString);

            if (lowerCaseLetterMatcher.find()) {
                counter++;
            }
            if (upperCaseLetterMatcher.find()) {
                counter++;
            }
            if (numberMatcher.find()) {
                counter++;
            }
            if (specialCharMatcher.find()) {
                counter++;
            }

            if (counter >= complexity) {
                answer = true;
            } else {
                answer = false;
            }

        } else {
            answer = false;
        }
        return answer;
    }

    /**
     * Getter method for Password Value Object.
     *
     * @return password string
     */
    public String getString() {
        return passwordString;
    }

    @Override
    public boolean sameValueAs(final Password other) {
        boolean matches = false;
        if (other != null ){
            matches = this.passwordString.equals(other.passwordString);
        }
        return matches;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return this.sameValueAs(password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passwordString);
    }

}
