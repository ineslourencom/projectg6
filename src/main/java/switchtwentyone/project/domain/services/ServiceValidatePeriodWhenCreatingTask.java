package switchtwentyone.project.domain.services;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.valueObjects.Period;

@Service
public class ServiceValidatePeriodWhenCreatingTask {

    public boolean validateDates(Sprint sprint, Period period) {
        boolean sprintValidated = false;
        if (sprint.isRunning() && sprint.isPeriodInsideProject(period)) {
            sprintValidated = true;
        }
        return sprintValidated;
    }
}
