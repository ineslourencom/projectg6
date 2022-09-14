package switchtwentyone.project.domain.valueObjects;

import switchtwentyone.project.domain.shared.ValueObject;

import java.time.LocalDate;
import java.util.Objects;


/**
 * This class represents a period in time defined by a start date
 * and an end date.
 * Both start date and end date are included in the period
 */

public class Period implements ValueObject<Period> {


    /**
     * Starting day of the period.
     */
    private final LocalDate startingDate;
    /**
     * Ending day of the period.
     */

    private final LocalDate endingDate;


    private Period(final LocalDate startDate, final LocalDate endDate) {
        this.startingDate = startDate;
        this.endingDate = endDate;


    }


    /**
     * Creates a period object with a defined start date and end date.
     *
     * @param startDate defines the start date of the period.
     * @param endDate   defines the end date of the period
     * @return newly created period.
     */
    public static Period between(final LocalDate startDate,
                                 final LocalDate endDate) {
        if (startDate == null
                || endDate == null) {
            throw new IllegalArgumentException("Invalid dates");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Invalid period");
        }
        return new Period(startDate, endDate);
    }

    /**
     * Creates a period without defining its end date.
     *
     * @param startDate defines the start date of the period.
     * @return newly created period.
     */

    public static Period starting(final LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date is invalid");
        }

        return new Period(startDate, null);

    }
    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }


    @Override
    public boolean sameValueAs(final Period other) {
        return other != null
                && startingDate.equals(other.startingDate)
                && Objects.equals(endingDate, other.endingDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return this.sameValueAs(period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingDate, endingDate);
    }

    public boolean containsDate(LocalDate date) {
        boolean contains;
        if(this.endingDate != null){
            contains = !date.isBefore(this.startingDate)
                    && !date.isAfter(this.endingDate);
        }else{
            contains = !date.isBefore(this.startingDate);
        } return contains;
    }

    public boolean containsPeriod(Period period){
        return this.containsDate(period.startingDate)
                && this.containsDate(period.endingDate);
    }
}
