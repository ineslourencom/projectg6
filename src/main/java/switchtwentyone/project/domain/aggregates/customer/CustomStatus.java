package switchtwentyone.project.domain.aggregates.customer;

import switchtwentyone.project.domain.shared.IStatus;
import switchtwentyone.project.domain.shared.ValueObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This is an experimental proposal.
 * What if we don't want the status option values to be hardcoded?
 * (e.g. support for different languages,
 * support for different customer options, etc. )
 * The intent is to make a class whose status options are configurable.
 * This is just a proposal, and there is much room for improvements
 */
public class CustomStatus implements IStatus, ValueObject<CustomStatus> {

    private static HashMap<String, String> statusOptions = new HashMap<>();

    private final String statusValue;

    private CustomStatus(final String status) {
        this.statusValue = status;
    }

    public static void configure(HashMap<String, String> options) {
        if (!options.containsKey("initial")
                || !options.containsKey("final")) {
            throw new IllegalArgumentException("Invalid status configurations");
        }

        statusOptions = (HashMap<String, String>) options.clone();

    }

    public static CustomStatus initialStatus() {
        return new CustomStatus(statusOptions.get("initial"));
    }

    public static CustomStatus finalStatus() {
        return new CustomStatus(statusOptions.get("final"));
    }

    public static CustomStatus of(String status) {
        status = status.trim();
        for (Map.Entry<String, String> entry : statusOptions.entrySet()) {
            String value = entry.getValue();
            if (status.equalsIgnoreCase(value)) {
                return new CustomStatus(value);
            }
        }
        throw new IllegalArgumentException("Invalid status");
    }

    @Override
    public boolean sameValueAs(CustomStatus other) {
        return other != null
                && statusValue.equals(other.statusValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomStatus that = (CustomStatus) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusValue);
    }

    @Override
    public String getValueAsString() {

        return this.statusValue;
    }
}



