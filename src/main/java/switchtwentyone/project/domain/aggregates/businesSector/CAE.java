package switchtwentyone.project.domain.aggregates.businesSector;

import lombok.NoArgsConstructor;
import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class encapsulates a five digit numeric code
 * that uniquely identifies a business sector.
 */
@Embeddable
@NoArgsConstructor
public class CAE implements ValueObject<CAE>, Serializable {

    /**
     * A five digit numeric code
     * * that uniquely identifies a business sector.
     */
    private String code;

    private CAE(final String cae) {
        this.code = cae;

    }


    /**
     * Creates a new CAE encapsulating input string.
     *
     * @param cae the code that identifies the business sector.
     * @return a new CAE of the input string.
     */
    public static CAE of(String cae) {
        if (cae == null) {
            throw new IllegalArgumentException("CAE is empty");
        }
        cae = cae.trim();

        if (cae.isEmpty()) {
            throw new IllegalArgumentException("CAE is empty");
        }

        Pattern caePattern = Pattern.compile("[0-9]{5}");
        Matcher matcher = caePattern.matcher(cae);
        boolean matches = matcher.matches();

        if (!matches) {
            throw new IllegalArgumentException("CAE format is invalid");
        }


        return new CAE(cae);

    }

    public String getCAEAsString (){
        return this.code;
    }

    @Override
    public boolean sameValueAs(CAE other) {
        return other != null
                && this.code.equals(other.code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CAE cae = (CAE) o;
        return this.sameValueAs(cae);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString(){ return (this.code);
    }
}
