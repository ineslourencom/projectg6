package switchtwentyone.project.domain.valueObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
public class NoNumberNoSymbolString implements ValueObject<NoNumberNoSymbolString>, Nameable, Serializable {
    @Getter
    private String valueString;

    private NoNumberNoSymbolString(String valueString) {
        this.valueString = valueString;
    }

    public static NoNumberNoSymbolString of(String valueString) {

        final Pattern StringPattern = Pattern.compile(
                "[ a-zA-ZáéíóúàèìòùçêôãõÁÉÍÓÚÀÈÌÒÙÇÊÔÃÕ-]+");

        final Matcher stringMatcher = StringPattern.matcher(valueString);

        if (valueString == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        if (valueString.length() == 0) {
            throw new IllegalArgumentException("String cannot be empty");
        }

        if (!stringMatcher.matches()) {
            throw new IllegalArgumentException("String format is invalid");
        }
        else {
            return new NoNumberNoSymbolString(valueString);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoNumberNoSymbolString that = (NoNumberNoSymbolString) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueString);
    }


    @Override
    public boolean sameValueAs(NoNumberNoSymbolString otherNoNumberNoSymbolString) {
        boolean matches =false;
        if (otherNoNumberNoSymbolString != null ){
            matches = this.valueString.equals(otherNoNumberNoSymbolString.valueString);}
        return matches;
        }

    @Override
    public String getValue() {
        return this.valueString;
    }

}

