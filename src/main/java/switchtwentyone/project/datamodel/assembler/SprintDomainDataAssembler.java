package switchtwentyone.project.datamodel.assembler;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.*;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.datamodel.SprintJPA;
import switchtwentyone.project.datamodel.SprintbacklogItemJPA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static switchtwentyone.project.domain.aggregates.sprint.ScrumBoardCategory.dbConverter;

@Service
public class SprintDomainDataAssembler {
    public SprintJPA toData(Sprint sprint) {
        SprintJPA jpa = new SprintJPA(sprint.getSprintID(), sprint.getStartDate(),
                sprint.getProjectID(), sprint.getSprintNumber(), sprint.getSprintDuration().getNumber());

        List<SprintBacklogItem> items = sprint.getSprintBacklogItems();
        ArrayList<SprintbacklogItemJPA> itemsJPA = new ArrayList<>();

        for( SprintBacklogItem item : items ) {
            SprintbacklogItemJPA itemJPA = new SprintbacklogItemJPA(item.getItemID(), item.getUsID().getID(),
                    item.getCategory().getCategory().getDbName(), item.getEffortEstimate().getNumber(), jpa);
            itemsJPA.add(itemJPA);
        }
        jpa.setSprintbacklogItemJPAList(itemsJPA);
        return jpa;
    }

    public Sprint toDomain (SprintJPA sprintJPA) {
        ProjectID projectID = new ProjectID(sprintJPA.getProjectID());
        PositiveNumber sprintNumber =PositiveNumber.of(sprintJPA.getSprintNumber());
        SprintID sprintID = new SprintID(projectID, sprintNumber);
        LocalDate startDate = sprintJPA.getStartDate();
        PositiveNumber sprintDuration = PositiveNumber.of(sprintJPA.getSprintDuration());
        Sprint sprint = new Sprint(sprintID, startDate, projectID, sprintNumber, sprintDuration);

        List<SprintBacklogItem> items = new ArrayList<>();
        for(SprintbacklogItemJPA sprintBacklogItemjpa : sprintJPA.getSprintbacklogItemJPAList()){
            SprintBacklogItem item = new SprintBacklogItem(sprintBacklogItemjpa.getItemID(),
                    UserStoryID.ofDouble(sprintBacklogItemjpa.getUsID()),
                    FibonacciNumber.of(sprintBacklogItemjpa.getEffortEstimate()));
            item.setCategory(dbConverter(sprintBacklogItemjpa.getCategory()));
            items.add(item);
        }

        sprint.setSprintBacklogItems(items);
        return sprint;
    }
}

