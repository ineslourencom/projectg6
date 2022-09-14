package switchtwentyone.project.domain.valueObjects;

import lombok.Getter;
import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Currency;
import java.util.Objects;

/**
 *
 */
public class Monetary implements ValueObject<Monetary> {
    /**
     *
     */
    @Getter
    private final Currency currency;
    /**
     *
     */
    @Getter
    private final double amount;

    private Monetary(final double amountValue, final Currency currencyValue) {
        this.amount = amountValue;
        this.currency = currencyValue;
    }

    /**
     * @param amount
     * @param currency
     * @return
     */

    public static Monetary of(final double amount, final Currency currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount value should be positive.");

        }
        return new Monetary(amount, currency);
    }

    @Override
    public boolean sameValueAs(Monetary other) {
        return other != null
                && Double.compare(other.amount, this.amount) == 0
                && this.currency.equals(other.currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monetary that = (Monetary) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}
