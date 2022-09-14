package switchtwentyone.project.domain.services;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.valueObjects.Period;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServiceValidateUserStoryWhenCreatingTaskTest {

    @Test
    void validateUserStory() {
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        UserStory userStoryFour = mock(UserStory.class);

        List<UserStory> userStoriesNotDecomposed = new ArrayList<>();
        userStoriesNotDecomposed.add(userStoryOne);
        userStoriesNotDecomposed.add(userStoryTwo);
        List<UserStory> userStoriesDone = new ArrayList<>();
        userStoriesDone.add(userStoryThree);
        userStoriesDone.add(userStoryFour);
        ServiceValidateUserStoryWhenCreatingTask serviceValidateUserStoryWhenCreatingTask = new ServiceValidateUserStoryWhenCreatingTask();
        boolean result = serviceValidateUserStoryWhenCreatingTask.validateUserStory(userStoriesNotDecomposed, userStoriesDone, userStoryOne);

        assertTrue(result);
    }

    @Test
    void validateUserStory_USDecomposed() {
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        UserStory userStoryFour = mock(UserStory.class);
        UserStory userStoryFive = mock(UserStory.class);


        List<UserStory> userStoriesNotDecomposed = new ArrayList<>();
        userStoriesNotDecomposed.add(userStoryOne);
        userStoriesNotDecomposed.add(userStoryTwo);
        List<UserStory> userStoriesDone = new ArrayList<>();
        userStoriesDone.add(userStoryThree);
        userStoriesDone.add(userStoryFour);
        ServiceValidateUserStoryWhenCreatingTask serviceValidateUserStoryWhenCreatingTask = new ServiceValidateUserStoryWhenCreatingTask();

        boolean result = serviceValidateUserStoryWhenCreatingTask.validateUserStory(userStoriesNotDecomposed, userStoriesDone, userStoryFive);

        assertFalse(result);
    }

    @Test
    void validateUserStory_USDone() {
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        UserStory userStoryFour = mock(UserStory.class);


        List<UserStory> userStoriesNotDecomposed = new ArrayList<>();
        userStoriesNotDecomposed.add(userStoryOne);
        userStoriesNotDecomposed.add(userStoryTwo);
        List<UserStory> userStoriesDone = new ArrayList<>();
        userStoriesDone.add(userStoryThree);
        userStoriesDone.add(userStoryFour);
        ServiceValidateUserStoryWhenCreatingTask serviceValidateUserStoryWhenCreatingTask = new ServiceValidateUserStoryWhenCreatingTask();

        boolean result = serviceValidateUserStoryWhenCreatingTask.validateUserStory(userStoriesNotDecomposed, userStoriesDone, userStoryThree);

        assertFalse(result);
    }


}