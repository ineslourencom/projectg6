package switchtwentyone.project.domain.aggregates.account;

import lombok.NoArgsConstructor;
import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Embeddable
public class Email implements ValueObject<Email>, Serializable {

    private static final Pattern EMAILPATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    private static final Pattern PREFIXPATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+$",
            Pattern.CASE_INSENSITIVE);

    private static Matcher emailMatcher;

    private String emailData;

    private Email(String emailString) {
        this.emailData = emailString;
    }



    public static Email of(String fullEmail) {
        if (fullEmail == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        fullEmail = fullEmail.trim();

        if (fullEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        fullEmail = fullEmail.toLowerCase();

        emailMatcher = EMAILPATTERN.matcher(fullEmail);
        if (!emailMatcher.matches()) {
            throw new IllegalArgumentException("Email format is invalid");
        }


        return new Email(fullEmail);
    }

    public static Email ofISEP(String prefix) {

        if (prefix == null) {
            throw new IllegalArgumentException("Prefix cannot be null");
        }

        prefix = prefix.trim();

        if (prefix.isEmpty()) {
            throw new IllegalArgumentException("Prefix cannot be empty");
        }
        prefix = prefix.toLowerCase();
        emailMatcher = PREFIXPATTERN.matcher(prefix);
        if (!emailMatcher.matches()) {
            throw new IllegalArgumentException("prefix format is invalid");
        }

        return new Email(prefix + "@isep.ipp.pt");
    }

    public boolean matches(String email) {
        boolean matches = false;
        if (email != null) {
            email = email.trim();
            matches = emailData.equalsIgnoreCase(email);
        }
        return matches;
    }

    public String getEmailData() {
        return emailData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return this.sameValueAs(email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailData);
    }

    @Override
    public boolean sameValueAs(Email otherEmail) {
        boolean matches = false;
        if (otherEmail != null) {
            matches = this.emailData.equals(otherEmail.emailData);
        }
        return matches;
    }

}
