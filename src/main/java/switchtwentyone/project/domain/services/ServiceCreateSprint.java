package switchtwentyone.project.domain.services;


import org.springframework.stereotype.Service;

import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class ServiceCreateSprint {

    public PositiveNumber generateSprintNumber(List<Sprint> ListSprint) {
        int code = 1;
        for (Sprint sprint : ListSprint) {
            while (!sprint.isCodeHigher(code)) {
                code++;
            }
        }
        PositiveNumber sprintNumber = PositiveNumber.of(code);
        return sprintNumber;
    }

    public SprintID generateSprintID(PositiveNumber sprintNumber, ProjectID projID) {
        return new SprintID(projID, sprintNumber);
    }

    public Sprint determinateLastSprint(List<Sprint> listSprint) {
        return listSprint.get(listSprint.size() - 1);
    }

    public boolean startDateAfterLastSprint(List<Sprint> sprintlist, LocalDate startDate) {
        boolean validDate = false;

        if (sprintlist.isEmpty()) {
            validDate = true;
        } else {
            Sprint lastSprint = determinateLastSprint(sprintlist);
            LocalDate endDateLastSprint = lastSprint.getStartDate().plusDays(lastSprint.getSprintDuration().getNumber() * 7).minusDays(1);
            if (startDate.isAfter(endDateLastSprint)) {
                validDate = true;
            }
        }
        return validDate;
    }

    /**
     * @param sprintlist
     * @param sprint
     * @param newStartDate
     * @return first part - returns the list of subsequent sprints
     * second part - returns the difference in days between the old sprint start date and the new sprint start date
     * third part - sets the new start date of a sprint
     */
    public List<Sprint> setStartDateInsubsequentSprints(List<Sprint> sprintlist, Sprint sprint, LocalDate newStartDate) {
        List<Sprint> newlistSprintSub = new ArrayList<>();

        for (Sprint spt : sprintlist) {
            LocalDate startDate = spt.getStartDate();
            if (startDate.isAfter(newStartDate)){
                newlistSprintSub.add(spt);
            }
        }

        LocalDate oldDate = sprint.getStartDate();
        int days = oldDate.until(newStartDate).getDays();

        for (Sprint sptsub : newlistSprintSub) {
            oldDate = sptsub.getStartDate();
            sptsub.setNewStartDate(oldDate.plusDays(days));
        }
        return newlistSprintSub;
    }
}









