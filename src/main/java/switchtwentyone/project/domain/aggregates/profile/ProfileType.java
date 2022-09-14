package switchtwentyone.project.domain.aggregates.profile;

import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class ProfileType implements ValueObject<ProfileType>, Describable {

    private String profileTypeData;


    public ProfileType() {}

    private ProfileType(String profileType) {
        this.profileTypeData = profileType;
    }

    public static ProfileType of(String profileType) {
        final Pattern stringPattern = Pattern.compile(
                "[ a-zA-ZáéíóúàèìòùçêôãõÁÉÍÓÚÀÈÌÒÙÇÊÔÃÕ-]+");

        final Matcher stringMatcher = stringPattern.matcher(profileType);


        if (!stringMatcher.matches()) {
            throw new IllegalArgumentException("String format is invalid");
        } else {
            return new ProfileType(profileType);
        }
    }

    /**
     * Getter method for ProfileType.
     *
     * @return String that identifies the type of profile.
     */
    public String getValue() {
        return this.profileTypeData;
    }

    /**
     * Override methods for Photo.
     * Fall back to the sameValueAs method for equals evaluation because
     * it is a Value Object (all attributes must be equal).
     */
    @Override
    public boolean sameValueAs(ProfileType other) {
        return other.profileTypeData != null && this.profileTypeData.equals(other.profileTypeData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileType that = (ProfileType) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileTypeData);
    }
}
