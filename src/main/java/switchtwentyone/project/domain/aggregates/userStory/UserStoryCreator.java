package switchtwentyone.project.domain.aggregates.userStory;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;

@Component
public interface UserStoryCreator {

    public static UserStory createUserStory(UserStoryID usID, PositiveNumber storyNumber, Text statement, Text detail, int priority, ProjectID projID) {
        return new UserStory(usID, storyNumber, statement, detail, priority, projID);
    }

}
