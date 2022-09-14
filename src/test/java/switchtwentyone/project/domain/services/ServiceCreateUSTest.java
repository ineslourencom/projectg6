package switchtwentyone.project.domain.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServiceCreateUSTest {

    @Test
    void generateUserStoryNumber() {
        //Arrange
        UserStory newUS1 = mock(UserStory.class);
        UserStory newUS2 = mock(UserStory.class);
        PositiveNumber usNumber1 = PositiveNumber.of(1);
        PositiveNumber usNumber2 = PositiveNumber.of(2);

        when(newUS1.isCodeHigher(1)).thenReturn(false);
        when(newUS1.isCodeHigher(2)).thenReturn(true);

        when(newUS2.isCodeHigher(1)).thenReturn(false);
        when(newUS2.isCodeHigher(2)).thenReturn(false);
        when(newUS2.isCodeHigher(3)).thenReturn(true);

        List<UserStory> listUS = new ArrayList<>();
        listUS.add(newUS1);
        listUS.add(newUS2);

        ServiceCreateUS serviceUS = new ServiceCreateUS();

        //Act
        PositiveNumber usNumber3 = serviceUS.generateUSNumber(listUS);

        //Assert
        assertEquals(PositiveNumber.of(3), usNumber3);
    }

    @Test
    void generateUSID() {
        ProjectID projID = new ProjectID(1);
        PositiveNumber usNumber = PositiveNumber.of(1);
        UserStoryID usIDExpected = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);
        ServiceCreateUS serviceUS = new ServiceCreateUS();

        //Act
        UserStoryID usIDResult = serviceUS.generateUSID(usNumber, projID);

        //Assert
        assertEquals(usIDExpected, usIDResult);
    }

    @Test
    void generateUSPriority() {
        //Arrange
        UserStory newUS1 = mock(UserStory.class);
        UserStory newUS2 = mock(UserStory.class);

        int priority1 = 1;
        int priority2 = 2;

        when(newUS1.getPriority()).thenReturn(priority1);
        when(newUS2.getPriority()).thenReturn(priority2);

        List<UserStory> listUS = new ArrayList<>();
        listUS.add(newUS1);
        listUS.add(newUS2);

        ServiceCreateUS serviceUS = new ServiceCreateUS();

        //Act
        int usPriorityExpected = 3;
        int usPriorityResult = serviceUS.determineUSPriority(listUS);

        //Assert
        assertEquals(usPriorityExpected, usPriorityResult);

    }

    @Test
    void generateUSPriorityWhenListIsEmpty() {
        //Arrange
        List<UserStory> listUS = new ArrayList<>();
        ServiceCreateUS serviceUS = new ServiceCreateUS();

        //Act
        int usPriorityExpected = 1;
        int usPriorityResult = serviceUS.determineUSPriority(listUS);

        //Assert
        assertEquals(usPriorityExpected, usPriorityResult);
    }
}