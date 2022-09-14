package switchtwentyone.project.domain.services;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.valueObjects.Period;

import java.time.LocalDate;
import java.util.List;

@Service
public class SprintValidationService {
    /**
     * According tho business rules,
     * a team member cannot initiate functions during a sprint.
     * This method performs that validation
     *
     * @param period  the period we intend to validate
     * @param sprints the list of sprints against which to validate the period
     * @return true if period is valid
     */
    public boolean validateSprintRules(Period period, List<Sprint> sprints) {
        boolean isValid = true;

        LocalDate resourceStartDate = period.getStartingDate();
        LocalDate resourceEndDate = period.getEndingDate();

        for (Sprint s : sprints) {

            LocalDate startDate = s.getStartDate();
            LocalDate endDate = s.getEndDate();
            Period invalidEndDatePeriod = Period.between(startDate, endDate.minusDays(1));
            if (invalidEndDatePeriod.containsDate(resourceEndDate)) {
                isValid = false;
            }
            Period invalidStartDatePeriod = Period.between(startDate.plusDays(1), endDate);
            if (invalidStartDatePeriod.containsDate(resourceStartDate)) {
                isValid = false;
            }

        }

        return isValid;
    }
}
