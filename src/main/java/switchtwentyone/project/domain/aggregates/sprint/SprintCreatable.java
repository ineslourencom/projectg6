package switchtwentyone.project.domain.aggregates.sprint;


import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.time.LocalDate;

public interface SprintCreatable {

    public static Sprint createSprint(final SprintID sprintID, final LocalDate startDate, final ProjectID projectID, final PositiveNumber sprintNumber, final PositiveNumber sprintDuration){
        return new Sprint (sprintID,startDate,projectID,sprintNumber,sprintDuration);
    }
}
