package switchtwentyone.project.domain.valueObjects;

import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

public class PositiveNumber implements ValueObject<PositiveNumber> {

    private final int number;

    private PositiveNumber(int number) {
        this.number = number;
    }

    public static PositiveNumber of(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Invalid number");
        }
        return new PositiveNumber(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositiveNumber that = (PositiveNumber) o;
        return sameValueAs(that);

    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public boolean sameValueAs(PositiveNumber otherPositiveNumber) {
        boolean matches =false;
        if (otherPositiveNumber != null ){
            matches=this.number==(otherPositiveNumber.number);}
        return matches;
    }


}


