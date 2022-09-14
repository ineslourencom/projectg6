package switchtwentyone.project.domain.aggregates.account;

import lombok.Getter;
import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

/**
 * This class encapsulates a photo in the form of a string up to
 * a maximum of 2000 chars.
 */
public class Photo implements ValueObject<Photo> {
    /**
     * Maximum photo length (string).
     */
    private static final int MAX_LENGTH = 200;
    /**
     * Encapsulated photo.
     */
    @Getter
    private String photoString;

    /**
     * Constructor of Photo.
     *
     * @param stringToPhoto string to convert into photo
     */
    private Photo(final String stringToPhoto) {
        this.photoString = stringToPhoto;
    }

    /**
     * Creator method for Photo.
     *
     * @param text string to save as photo
     * @return Photo object encapsulating input text.
     */
    public static Photo of(String text) {
        if (text == null || text.isEmpty()) {
            text = "No Photo";
        }

        if (text.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Text surpasses maximum length");
        }

        return new Photo(text);
    }

    /**
     * Override methods for Photo.
     * Fall back to the sameValueAs method for equals evaluation because
     * it is a Value Object (all attributes must be equal).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo other = (Photo) o;
        return this.sameValueAs(other);
    }

    @Override
    public boolean sameValueAs(final Photo other) {
        return other != null
                && this.photoString.equals(other.photoString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoString);
    }

    @Override
    public String toString() {
        return this.photoString;
    }
}
