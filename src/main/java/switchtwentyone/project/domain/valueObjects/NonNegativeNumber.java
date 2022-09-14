package switchtwentyone.project.domain.valueObjects;

import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

public class NonNegativeNumber implements ValueObject<NonNegativeNumber> {

    private final int number;

    private NonNegativeNumber(int number) {
        this.number = number;
    }

    public static NonNegativeNumber of(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Invalid number");
        }
        return new NonNegativeNumber(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonNegativeNumber that = (NonNegativeNumber) o;
        return sameValueAs(that);

    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }


    @Override
    public boolean sameValueAs(NonNegativeNumber other) {
        return this.number == other.number;
    }
}
