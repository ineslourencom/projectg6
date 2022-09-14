package switchtwentyone.project.domain.valueObjects;

import lombok.Getter;
import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

/**
 * This class encapsulates a value that represents
 * a percentage between 0 and 100 inslusive.
 */
public class LimitedPercentage implements ValueObject<LimitedPercentage> {
    /**
     * The minimum value for the decimal representation.
     */
    private static final double MIN = 0;
    /**
     * The maximum value for the decimal representation.
     */
    private static final double DECIMAL_MAX = 1;
    /**
     * The maximum value for the percentage representation.
     */
    private static final double PERCENT_MAX = 100.0;
    /**
     * A decimal value between 0 and 1 that represents a fractional part.
     */
    @Getter
    private final double percentValue;


    private LimitedPercentage(final double percentage) {
        this.percentValue = percentage;

    }

    /**
     * Creates an object representing 0%.
     *
     * @return an object representing 0%.
     */

    public static LimitedPercentage min() {
        return new LimitedPercentage(MIN);

    }

    /**
     * Creates an object representing 100%.
     *
     * @return an object representing 100%.
     */
    public static LimitedPercentage max() {
        return new LimitedPercentage(DECIMAL_MAX);
    }

    /**
     * Creates a LimitedPercentage object from a value between 0 and 1.
     *
     * @param decimalValue a value expressed as a decimal part.
     * @return a LimitedPercentage object of the indicated value.
     */
    public static LimitedPercentage decimal(final double decimalValue) {
        validateValue(decimalValue);

        return new LimitedPercentage(decimalValue);

    }

    /**
     * Creates a LimitedPercentage object from a value between 0 and 100.
     *
     * @param percentage a value expressed as percentage.
     * @return a LimitedPercentage object of the indicated value.
     */

    public static LimitedPercentage percentage(double percentage) {
        percentage /= PERCENT_MAX;

        validateValue(percentage);

        return new LimitedPercentage(percentage);

    }


    private static void validateValue(final double value) {
        if (value < MIN) {
            throw new IllegalArgumentException("Value should be positive");
        }
        if (value > DECIMAL_MAX) {
            throw new IllegalArgumentException("Value exceeds maximum");
        }

    }


    @Override
    public boolean sameValueAs(LimitedPercentage other) {
        return other != null
                && this.percentValue == other.percentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null
                || getClass() != o.getClass()) return false;
        LimitedPercentage that = (LimitedPercentage) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentValue);
    }
}
