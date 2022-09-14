package switchtwentyone.project.domain.aggregates.sprint;


import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;

public interface SprintBacklogItemCreator {

    public static SprintBacklogItem createSprintBacklogItem(SprintBacklogItemID itemID, UserStoryID usID, FibonacciNumber effortEstimate) {
        return new SprintBacklogItem(itemID, usID, effortEstimate);
    }

}
