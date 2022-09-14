package switchtwentyone.project.domain.services;

import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServiceChangePriorityOfUserStoriesTest {


    @Test
    void findPriorityOfUserStories() {
        List<UserStory> productBacklog = new ArrayList<>();
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryToBeChanged = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        productBacklog.add(userStoryOne);
        productBacklog.add(userStoryToBeChanged);
        productBacklog.add(userStoryThree);
        int actualNewPriority = 2;
        int newPriority = 3;
        when(userStoryThree.getPriority()).thenReturn(actualNewPriority);

        int expected = 2;
        ServiceChangePriorityOfUserStories serviceChangePriorityOfUserStories = new ServiceChangePriorityOfUserStories();
        int result = serviceChangePriorityOfUserStories.findTrueNewPriority(productBacklog, newPriority);

        assertEquals(expected, result);
    }

    @Test
    void findPriorityOfUserStories_PriorityBiggerThanProductBacklog() {
        List<UserStory> productBacklog = new ArrayList<>();
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        productBacklog.add(userStoryOne);
        productBacklog.add(userStoryTwo);
        productBacklog.add(userStoryThree);
        int actualNewPriority = 2;
        int newPriority = 60;
        when(userStoryThree.getPriority()).thenReturn(actualNewPriority);
        ServiceChangePriorityOfUserStories serviceChangePriorityOfUserStories = new ServiceChangePriorityOfUserStories();

        int expected = 2;

        int result = serviceChangePriorityOfUserStories.findTrueNewPriority(productBacklog, newPriority);

        assertEquals(expected, result);
    }

    @Test
    void changePriority_ToBiggerPriority() {
        List<UserStory> listOfUserStories = new ArrayList<>();
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryToBeChange = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        listOfUserStories.add(userStoryOne);
        listOfUserStories.add(userStoryToBeChange);
        listOfUserStories.add(userStoryThree);
        when(userStoryToBeChange.getPriority()).thenReturn(2);
        when(userStoryThree.getPriority()).thenReturn(3);
        userStoryToBeChange.setPriority(3);
        userStoryThree.setPriority(2);
        ServiceChangePriorityOfUserStories serviceChangePriorityOfUserStories = new ServiceChangePriorityOfUserStories();

        List<UserStory> result = serviceChangePriorityOfUserStories.changePriorityOfUserStories(listOfUserStories,userStoryToBeChange,3);

        assertEquals(listOfUserStories, result);
    }


    @Test
    void changePriority_ToLowerPriority() {
        List<UserStory> listOfUserStories = new ArrayList<>();
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryToBeChange = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        listOfUserStories.add(userStoryOne);
        listOfUserStories.add(userStoryToBeChange);
        listOfUserStories.add(userStoryThree);
        when(userStoryToBeChange.getPriority()).thenReturn(2);
        when(userStoryOne.getPriority()).thenReturn(1);
        userStoryToBeChange.setPriority(1);
        userStoryOne.setPriority(2);
        ServiceChangePriorityOfUserStories serviceChangePriorityOfUserStories = new ServiceChangePriorityOfUserStories();

        List<UserStory> result = serviceChangePriorityOfUserStories.changePriorityOfUserStories(listOfUserStories,userStoryToBeChange,1);

        assertEquals(listOfUserStories, result);
    }


    @Test
    void changePriority_ToZero(){
        List<UserStory> listOfUserStories = new ArrayList<>();
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryToBeChange = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        listOfUserStories.add(userStoryOne);
        listOfUserStories.add(userStoryToBeChange);
        listOfUserStories.add(userStoryThree);
        when(userStoryToBeChange.getPriority()).thenReturn(2);
        ServiceChangePriorityOfUserStories serviceChangePriorityOfUserStories = new ServiceChangePriorityOfUserStories();

        assertThrows(IllegalArgumentException.class, () ->  serviceChangePriorityOfUserStories.changePriorityOfUserStories(listOfUserStories,userStoryToBeChange,0));
    }

    @Test
    void changePriority_ToBiggerThanMax(){
        List<UserStory> listOfUserStories = new ArrayList<>();
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryToBeChange = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        listOfUserStories.add(userStoryOne);
        listOfUserStories.add(userStoryToBeChange);
        listOfUserStories.add(userStoryThree);
        when(userStoryToBeChange.getPriority()).thenReturn(2);
        ServiceChangePriorityOfUserStories serviceChangePriorityOfUserStories = new ServiceChangePriorityOfUserStories();

        assertThrows(IllegalArgumentException.class, () ->  serviceChangePriorityOfUserStories.changePriorityOfUserStories(listOfUserStories,userStoryToBeChange,5));
    }

    @Test
    void changePriority_ToSame(){
        List<UserStory> listOfUserStories = new ArrayList<>();
        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryToBeChange = mock(UserStory.class);
        UserStory userStoryThree = mock(UserStory.class);
        listOfUserStories.add(userStoryOne);
        listOfUserStories.add(userStoryToBeChange);
        listOfUserStories.add(userStoryThree);
        when(userStoryToBeChange.getPriority()).thenReturn(2);
        ServiceChangePriorityOfUserStories serviceChangePriorityOfUserStories = new ServiceChangePriorityOfUserStories();

        assertThrows(IllegalArgumentException.class, () ->  serviceChangePriorityOfUserStories.changePriorityOfUserStories(listOfUserStories,userStoryToBeChange,2));
    }



}