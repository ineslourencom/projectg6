package switchtwentyone.project.domain.services;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceChangePriorityOfUserStories {

    public int findTrueNewPriority(List<UserStory> productBacklog, int newPriority) {
        if (newPriority > productBacklog.size()) {
            return (productBacklog.get(productBacklog.size() - 1).getPriority());
        }
        return productBacklog.get(newPriority - 1).getPriority();
    }


    /**
     * This method changes priority of an userStory and adjusts priorities of all user stories in product backlog so
     * that there are no equal priorities.
     */
    public List<UserStory> changePriorityOfUserStories(List<UserStory> listOfUserStories, UserStory userStoryToBeChanged, int newPriority) {
        int actualPriority = userStoryToBeChanged.getPriority();

        List<UserStory> userStoriesChanged = new ArrayList<>();

        if (newPriority > 0 && newPriority <= listOfUserStories.size() + 1) {
            if (actualPriority < newPriority) {
                userStoriesChanged = changeUSToHigherPriority(userStoryToBeChanged, newPriority, listOfUserStories);
                userStoryToBeChanged.setPriority(newPriority);
            } else if (actualPriority > newPriority) {
                userStoriesChanged = changeUSToLowerPriority(userStoryToBeChanged, newPriority, listOfUserStories);
                userStoryToBeChanged.setPriority(newPriority);
            } else {
                throw new IllegalArgumentException("UserStory already has priority with the same value");
            }
        } else {
            throw new IllegalArgumentException("New Priority value is not allowed");
        }
        return userStoriesChanged;

    }

    /**
     * This method changes priority of a User Story to a higher priority and adjusts all other user stories so than no
     * user story has the same priority, and all priorities a sequential, beginning at 1
     * Also works when actual priority of a userStory is 0 (ex, when it gets out of sprint backlog uncompleted and needs
     * to come back to product backlog).
     *
     * @param userStory   to be changed
     * @param newPriority to be applied
     */
    private List<UserStory> changeUSToHigherPriority(UserStory userStory, int newPriority, List<UserStory> listOfUserStories) {
        int actualPriority = userStory.getPriority();

        for (int i = actualPriority; i < newPriority; i++) {
            listOfUserStories.get(i).setPriority(listOfUserStories.get(i).getPriority() - 1);
        }
        for (UserStory us :
                listOfUserStories) {
            if (us.equals(userStory)) {
                us.setPriority(newPriority);
            }
        }
        return listOfUserStories;
    }

    /**
     * This method changes priority of a User Story to a lower priority and adjusts all other user stories so than no
     * user story has the same priority, and all priorities a sequential, beginning at 1
     * Also works when new priority of a userStory is 0 (ex, when enters sprint backlog).
     *
     * @param userStory   to be changed
     * @param newPriority to be applied
     */
    private List<UserStory> changeUSToLowerPriority(UserStory userStory, int newPriority, List<UserStory> listOfUserStories) {
        int actualPriority = userStory.getPriority();

        for (int i = newPriority - 1; i <= actualPriority - 2; i++) {
            listOfUserStories.get(i).setPriority(listOfUserStories.get(i).getPriority() + 1);
        }
        for (UserStory us :
                listOfUserStories) {
            if (us.equals(userStory)) {
                us.setPriority(newPriority);
            }
        }
        return listOfUserStories;
    }
}
