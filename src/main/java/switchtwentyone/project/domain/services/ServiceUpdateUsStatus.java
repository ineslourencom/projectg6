package switchtwentyone.project.domain.services;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.sprint.ScrumBoardCategory;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItem;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;

import java.util.List;
import java.util.Objects;

@Service
public class ServiceUpdateUsStatus {

    public Sprint doesUsIDExistOnRunningSprintAndUpdateNewStatus(UserStoryID usID, Sprint sprint, ScrumBoardCategory category) {

        List<SprintBacklogItem> sprintBacklogItemsList = sprint.getSprintBacklogItems();

        SprintBacklogItem sprintBacklogItem = null;

        boolean validation = false;

        for (int i = 0; i < sprintBacklogItemsList.size(); i++) {
            if (usID.equals(sprintBacklogItemsList.get(i).getUsID())) {
                sprintBacklogItem = sprintBacklogItemsList.get(i);
            }
        }
        if (sprintBacklogItem != null) {

            String newCategoryString = category.toString();

            String oldCategoryString = sprintBacklogItem.getCategory().toString();


            switch (newCategoryString) {
                case "In_progress":
                    if ((Objects.equals(oldCategoryString, "To_do") || (Objects.equals(oldCategoryString, "Code_review")))) {
                        sprintBacklogItem.setCategory(category);
                        validation = true;
                    }
                    break;

                case "Code_review":
                    if ((Objects.equals(oldCategoryString, "In_progress"))) {
                        sprintBacklogItem.setCategory(category);
                        validation = true;
                    }

                    break;
                case "Done":
                    if ((Objects.equals(oldCategoryString, "Code_review"))) {
                        sprintBacklogItem.setCategory(category);
                        validation = true;
                    }

                    break;

                case "Rejected":
                    if ((Objects.equals(oldCategoryString, "In_progress") || (Objects.equals(oldCategoryString, "Code_review") || (Objects.equals(oldCategoryString, "To_do"))))) {
                        sprintBacklogItem.setCategory(category);
                        validation = true;
                    }
                    break;
            }


        } else {
            throw new IllegalArgumentException("This US doesn't exist in this sprint.");


        }
        if (!validation) {
            throw new IllegalArgumentException("You can't change US to this new status.");
        }
        return sprint;
    }

}

