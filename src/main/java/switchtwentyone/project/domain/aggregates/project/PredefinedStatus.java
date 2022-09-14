package switchtwentyone.project.domain.aggregates.project;

import switchtwentyone.project.domain.shared.IStatus;
import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

public class PredefinedStatus implements IStatus,
        ValueObject<PredefinedStatus> {
    /**
     *
     */
    private final String status;

    private PredefinedStatus(final String statusValue) {
        this.status = statusValue;
    }

    /**
     * @param status
     * @return
     */
    public static PredefinedStatus of(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        status = status.trim();
        for (EStatus st : EStatus.values()) {
            String statusValue = st.toString();
            if (status.equalsIgnoreCase(statusValue)) {
                return new PredefinedStatus(statusValue);
            }
        }
        throw new IllegalArgumentException("Invalid status");
    }

    @Override
    public boolean sameValueAs(PredefinedStatus other) {
        return other != null
                && this.status.equals(other.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PredefinedStatus that = (PredefinedStatus) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String getValueAsString() {
        return status;
    }

    private enum EStatus {
        PLANNED,
        INCEPTION,
        ELABORATION,
        CONSTRUCTION,
        TRANSITION,
        WARRANTY,
        CLOSED

    }

}
