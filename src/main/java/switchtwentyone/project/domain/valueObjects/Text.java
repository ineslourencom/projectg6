package switchtwentyone.project.domain.valueObjects;

import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

/**
 * This class encapsulates a text up to a maximum of 2000 chars.
 */
public class Text implements ValueObject<Text>, Describable {
    /**
     * Maximum text length.
     */
    private static final int MAX_LENGTH = 2000;
    /**
     * Encapsulated text.
     */
    private String textString;

    private Text(final String textToWrite) {
        this.textString = textToWrite;
    }

    /**
     * @param text the text to save
     * @return Text object encapsulating input text.
     */

    public static Text write(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text is empty");
        }
        text = text.trim();

        if (text.isEmpty()) {
            throw new IllegalArgumentException("Text is empty");
        }

        if (text.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Text surpasses maximum length");
        }

        return new Text(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text other = (Text) o;
        return this.sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textString);
    }


    @Override
    public boolean sameValueAs(final Text other) {
        return other != null
                && this.textString.equals(other.textString);
    }

    @Override
    public String toString(){
        return this.textString;
    }

    @Override
    public String getValue() {
        return textString;
    }
}
