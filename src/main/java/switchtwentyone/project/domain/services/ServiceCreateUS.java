package switchtwentyone.project.domain.services;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.util.List;

@Service
public class ServiceCreateUS {

    public PositiveNumber generateUSNumber(List<UserStory> listUS){
        int code = 1;
        for (UserStory us : listUS) {
            while (!us.isCodeHigher(code)) {
                code++;
            }
        }
        return PositiveNumber.of(code);
    }


    public UserStoryID generateUSID(PositiveNumber usID, ProjectID projID){
        return UserStoryID.ofProjIDAndUsNumber(projID, usID);
    }

    public int determineUSPriority(List<UserStory> listUS){
        int priorityToBeGenerated = 1;

        if (!listUS.isEmpty()) {
            for (UserStory us : listUS) {
                if (priorityToBeGenerated <= us.getPriority()) {
                    priorityToBeGenerated = us.getPriority() + 1;
                }
            }
        }
        return priorityToBeGenerated;
    }

}
