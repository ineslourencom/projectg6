package switchtwentyone.project.domain.services;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ServiceCreateSprintTest {

    @Autowired
    SprintRepository sprintRepo;

    @Test
    void generateSprintNumber() {
        //Arrange
        Sprint newSpt1 = mock(Sprint.class);
        Sprint newSpt2 = mock(Sprint.class);
        PositiveNumber sptNumber1 = PositiveNumber.of(1);
        PositiveNumber sptNumber2 = PositiveNumber.of(2);

        when(newSpt1.isCodeHigher(1)).thenReturn(false);
        when(newSpt1.isCodeHigher(2)).thenReturn(true);

        when(newSpt2.isCodeHigher(1)).thenReturn(false);
        when(newSpt2.isCodeHigher(2)).thenReturn(false);
        when(newSpt2.isCodeHigher(3)).thenReturn(true);

        List<Sprint> ListSprint = new ArrayList<>();
        ListSprint.add(newSpt1);
        ListSprint.add(newSpt2);

        ServiceCreateSprint serviceSprint = new ServiceCreateSprint();

        //Act
        PositiveNumber sptNumber3 = serviceSprint.generateSprintNumber(ListSprint);

        //Assert
        assertEquals(PositiveNumber.of(3), sptNumber3);
    }

    @Test
    void generateSprintID() {
        ProjectID projID = new ProjectID(1);
        PositiveNumber sptNumber = PositiveNumber.of(1);
        SprintID sprintIDExpected = new SprintID(projID, sptNumber);
        ServiceCreateSprint serviceSprint = new ServiceCreateSprint();

        //Act
        SprintID sprintIDResult = serviceSprint.generateSprintID(sptNumber, projID);

        //Assert
        assertEquals(sprintIDExpected, sprintIDResult);
    }

    @Test
    void determinateLastSprint() {

        PositiveNumber sptNumber1 = PositiveNumber.of(1);
        PositiveNumber sptNumber2 = PositiveNumber.of(2);
        PositiveNumber sprintDur1 = PositiveNumber.of(2);
        PositiveNumber sprintDur2 = PositiveNumber.of(2);
        ProjectID projectID = new ProjectID(1);
        SprintID sprintID1 = new SprintID(projectID, sptNumber1);
        SprintID sprintID2 = new SprintID(projectID, sptNumber2);
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2022, 10, 12);
        Project project = mock(Project.class);

        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintNumber()).thenReturn(1);
        when(sprint.getSprintDuration()).thenReturn(sprintDur1);
        when(sprint.getProjectID()).thenReturn(1);
        when(sprint.getStartDate()).thenReturn(localDate);

        Sprint sprint1 = mock(Sprint.class);
        when(sprint1.getSprintNumber()).thenReturn(2);
        when(sprint1.getSprintDuration()).thenReturn(sprintDur2);
        when(sprint1.getProjectID()).thenReturn(1);
        when(sprint1.getStartDate()).thenReturn(localDate1);

        List<Sprint> sprintList = new ArrayList<>();
        sprintList.add(sprint);
        sprintList.add(sprint1);
        ServiceCreateSprint serviceSprint = new ServiceCreateSprint();

        //Act
        Sprint sprintExpected = sprint1;
        Sprint sprintResult = serviceSprint.determinateLastSprint(sprintList);

        //Assert
        assertEquals(sprintExpected, sprintResult);
    }

    @Test
    void startDateAfterLastSprint() {
        //Arrange
        LocalDate localDate = LocalDate.now();
        LocalDate endDateLastSprint = LocalDate.of(2022, 04, 12);
        PositiveNumber sprintDur1 = mock(PositiveNumber.class);

        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintNumber()).thenReturn(1);
        when(sprint.getSprintDuration()).thenReturn(sprintDur1);
        when(sprintDur1.getNumber()).thenReturn(2);
        when(sprint.getProjectID()).thenReturn(1);
        when(sprint.getStartDate()).thenReturn(endDateLastSprint);

        List<Sprint> listSprint = new ArrayList<>();
        listSprint.add(sprint);
        ServiceCreateSprint serviceSprint = new ServiceCreateSprint();
        //Act
        boolean result = serviceSprint.startDateAfterLastSprint(listSprint, localDate);

        //Assert
        assertTrue(result);
    }

    @Test
    void startDateAfterLastSprintFalse() {
        //Arrange
        LocalDate startDate = LocalDate.of(2022, 04, 13);
        LocalDate endDateLastSprint = LocalDate.of(2022, 04, 14);
        PositiveNumber sprintDur1 = mock(PositiveNumber.class);

        Sprint sprint = mock(Sprint.class);
        when(sprint.getSprintNumber()).thenReturn(1);
        when(sprint.getSprintDuration()).thenReturn(sprintDur1);
        when(sprintDur1.getNumber()).thenReturn(2);
        when(sprint.getProjectID()).thenReturn(1);
        when(sprint.getStartDate()).thenReturn(endDateLastSprint);

        List<Sprint> listSprint = new ArrayList<>();
        listSprint.add(sprint);
        ServiceCreateSprint serviceSprint = new ServiceCreateSprint();

        //Act
        boolean result = serviceSprint.startDateAfterLastSprint(listSprint, startDate);

        //Assert
        assertFalse(result);
    }

    @Test
    void setStartDateInsubsequentSprints() {
        //Arrange
        LocalDate oldDateSprint1 = LocalDate.now();
        Sprint sprint1 = mock(Sprint.class);
        when(sprint1.getStartDate()).thenReturn(oldDateSprint1);
        LocalDate oldDateSprint2 = LocalDate.now().plusMonths(1);
        Sprint sprint2 = mock(Sprint.class);
        when(sprint2.getStartDate()).thenReturn(oldDateSprint2);
        LocalDate oldDateSprint3 = LocalDate.now().plusMonths(2);
        Sprint sprint3 = mock(Sprint.class);
        when(sprint3.getStartDate()).thenReturn(oldDateSprint3);
        List<Sprint> listSprint = new ArrayList<>();
        listSprint.add(sprint1);
        listSprint.add(sprint2);
        listSprint.add(sprint3);
        LocalDate newStartDateSprint = LocalDate.now().plusDays(2);

        List<Sprint> expected = new ArrayList<>();
        expected.add(sprint2);
        expected.add(sprint3);

        ServiceCreateSprint serviceSprint = new ServiceCreateSprint();

        int days1 = oldDateSprint1.until(newStartDateSprint).getDays();
        int days2 = oldDateSprint2.until(newStartDateSprint).getDays();
        int days3 = oldDateSprint3.until(newStartDateSprint).getDays();

        //ACT
        List<Sprint> result = serviceSprint.setStartDateInsubsequentSprints(listSprint, sprint2, newStartDateSprint);

        //ASSERT
        assertEquals(expected, result);


    }
}