package switchtwentyone.project.domain.services;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;

import java.util.List;

@Service
public class ServiceValidateUserStoryWhenCreatingTask {


    public boolean validateUserStory(List<UserStory> userStoriesInSprint, List<UserStory> userStoriesDone, UserStory userStory) {
        boolean userStoryIsNotDecomposed = false;
        boolean userStoryIsDone = false;
        boolean userStoryIsValid = false;

        for (int i = 0; i < userStoriesInSprint.size(); i++) {
            if (userStoriesInSprint.get(i).equals(userStory)) {
                userStoryIsNotDecomposed = true;
            }
        }

        for (int i = 0; i < userStoriesDone.size(); i++) {
            if (userStoriesDone.get(i).equals(userStory)) {
                userStoryIsDone = true;
            }
        }

        if (userStoryIsNotDecomposed && !userStoryIsDone) {
            userStoryIsValid = true;
        }

        return userStoryIsValid;
    }


}
