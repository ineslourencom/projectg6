package switchtwentyone.project.domain.aggregates.task;

import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.shared.ValueObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class EffortUpdate implements ValueObject<EffortUpdate>, Serializable {

    /**
     * This attribute registers a timestamp for the submitted data.
     */
    private final LocalDateTime dateTime;
    /**
     * This attribute registers the hours spent (decimal number) on task.
     */
    private final double hoursSpent;
    /**
     * This attribute registers an optional comment to the work made on task.
     */
    private final String comment;
    /**
     * This attribute registers the resource that executed the work.
     */
    private final ResourceID submitter;

    /**
     * This is the private EffortUpdate class constructor.
     *
     * @param timestamp      for use as an EffortUpdate identifier aid
     * @param hoursSpentData of work done in task (negative, zero or positive)
     * @param commentData    a comment regarding the effort / work done
     * @param submitterData  used as a user identifier
     */
    private EffortUpdate(final LocalDateTime timestamp,
                         final double hoursSpentData,
                         final String commentData,
                         final ResourceID submitterData) {
        this.dateTime = timestamp.truncatedTo(ChronoUnit.SECONDS);
        this.hoursSpent = hoursSpentData;
        this.comment = commentData;
        this.submitter = submitterData;
    }

    /**
     * This is the publicly accessible EffortUpdate class constructor.
     * It is assumed each contribution to a task is updated daily by the
     * contributor. Each call should pass a timestamp with LocalDateTime.now()
     * for improved traceability.
     *
     * @param timestamp      for use as an EffortUpdate identifier aid
     * @param hoursSpentData of work done in task (negative, zero or positive)
     * @param commentData    a comment regarding the effort / work done
     * @param submitterData  used as a user identifier
     * @return an effort update object if data is valid, throws an error if otherwise
     */
    public static EffortUpdate of(final LocalDateTime timestamp,
                                  final double hoursSpentData,
                                  final String commentData,
                                  final ResourceID submitterData) {

        if (timestamp == null) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }
        if (commentData.length() == 0) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }
        if (submitterData == null) {
            throw new IllegalArgumentException("Submitter data is invalid");
        }
        else {
            return new EffortUpdate(timestamp, hoursSpentData, commentData, submitterData);
        }

    }

    /**
     * Getter for DateTime.
     *
     * @return EffortUpdate submission time as string
     */
    public String getDateTimeAsString() {
        return dateTime.toString();
    }

    /**
     * Getter for HoursSpent.
     *
     * @return measure of submitted effort in hours as string
     */
    public String getHoursSpentAsString() {
        return Double.toString(hoursSpent);
    }

    /**
     * Getter for HoursSpent.
     *
     * @return comment for EffortUpdate
     */
    public String getComment() {
        return comment;
    }

    /**
     * Getter for Submitter.
     *
     * @return resource identifier as string
     */
    public String getSubmitterAsString() {
        return String.valueOf(submitter.getResourceID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EffortUpdate other = (EffortUpdate) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, hoursSpent, comment, submitter);
    }


    @Override
    public boolean sameValueAs(EffortUpdate other) {
        return other != null && this.submitter.equals(other.submitter) &&
                this.dateTime.equals(other.dateTime) &&
                this.comment.equals(other.comment)&&
                this.hoursSpent==(other.hoursSpent);
    }
}


