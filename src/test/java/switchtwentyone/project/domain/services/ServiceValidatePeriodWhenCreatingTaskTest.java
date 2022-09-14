package switchtwentyone.project.domain.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.valueObjects.Period;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServiceValidatePeriodWhenCreatingTaskTest {

    @Test
    void validateSprint() {
        Sprint sprint = mock(Sprint.class);
        Period period = mock(Period.class);
        when(sprint.isRunning()).thenReturn(true);
        when(sprint.isPeriodInsideProject(period)).thenReturn(true);
        ServiceValidatePeriodWhenCreatingTask serviceValidatePeriodWhenCreatingTask = new ServiceValidatePeriodWhenCreatingTask();
        assertTrue(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period));
    }

    @Test
    void validateSprint_SprintNotRunning() {
        Sprint sprint = mock(Sprint.class);
        Period period = mock(Period.class);
        when(sprint.isRunning()).thenReturn(false);
        when(sprint.isPeriodInsideProject(period)).thenReturn(true);
        ServiceValidatePeriodWhenCreatingTask serviceValidatePeriodWhenCreatingTask = new ServiceValidatePeriodWhenCreatingTask();

        assertFalse(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period));
    }

    @Test
    void validateSprint_PeriodNotInsideSprint() {
        Sprint sprint = mock(Sprint.class);
        Period period = mock(Period.class);
        when(sprint.isRunning()).thenReturn(true);
        when(sprint.isPeriodInsideProject(period)).thenReturn(false);
        ServiceValidatePeriodWhenCreatingTask serviceValidatePeriodWhenCreatingTask = new ServiceValidatePeriodWhenCreatingTask();

        assertFalse(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period));
    }
}