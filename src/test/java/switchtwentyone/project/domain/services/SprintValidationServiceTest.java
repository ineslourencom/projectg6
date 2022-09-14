package switchtwentyone.project.domain.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.valueObjects.Period;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class SprintValidationServiceTest {

    @Test
    void validateSprintRulesIsValidTest() {
        List<Sprint> sprints = new ArrayList<>();
        Sprint sprintOne = mock(Sprint.class);
        Sprint sprintTwo = mock(Sprint.class);
        Sprint sprintThree = mock(Sprint.class);
        sprints.add(sprintOne);
        sprints.add(sprintTwo);
        sprints.add(sprintThree);


        when(sprintOne.getStartDate()).thenReturn(LocalDate.of(1993, 7, 13));
        when(sprintOne.getEndDate()).thenReturn(LocalDate.of(1993, 7, 27));
        when(sprintTwo.getStartDate()).thenReturn(LocalDate.of(1993, 7, 30));
        when(sprintTwo.getEndDate()).thenReturn(LocalDate.of(1993, 8, 13));
        when(sprintThree.getStartDate()).thenReturn(LocalDate.of(1993, 8, 14));
        when(sprintThree.getEndDate()).thenReturn(LocalDate.of(1993, 8, 28));


        Period period = Period.between(LocalDate.of(1993, 7, 13),
                LocalDate.of(1993, 8, 13));
        SprintValidationService service = new SprintValidationService();
        boolean result = service.validateSprintRules(period, sprints);
        assertTrue(result);

    }

    @Test
    void validateSprintRulesStartDateIsInvalidTest() {
        List<Sprint> sprints = new ArrayList<>();
        Sprint sprintOne = mock(Sprint.class);
        Sprint sprintTwo = mock(Sprint.class);
        Sprint sprintThree = mock(Sprint.class);
        sprints.add(sprintOne);
        sprints.add(sprintTwo);
        sprints.add(sprintThree);

        when(sprintOne.getStartDate()).thenReturn(LocalDate.of(1993, 7, 12));
        when(sprintOne.getEndDate()).thenReturn(LocalDate.of(1993, 7, 26));
        when(sprintTwo.getStartDate()).thenReturn(LocalDate.of(1993, 7, 30));
        when(sprintTwo.getEndDate()).thenReturn(LocalDate.of(1993, 8, 13));
        when(sprintThree.getStartDate()).thenReturn(LocalDate.of(1993, 8, 14));
        when(sprintThree.getEndDate()).thenReturn(LocalDate.of(1993, 8, 28));


        Period period = Period.between(LocalDate.of(1993, 7, 13),
                LocalDate.of(1993, 8, 13));
        SprintValidationService service = new SprintValidationService();

        boolean result = service.validateSprintRules(period, sprints);

        assertFalse(result);

    }
    @Test
    void validateSprintRulesEndDateIsInvalidTest() {
        List<Sprint> sprints = new ArrayList<>();
        Sprint sprintOne = mock(Sprint.class);
        Sprint sprintTwo = mock(Sprint.class);
        Sprint sprintThree = mock(Sprint.class);
        sprints.add(sprintOne);
        sprints.add(sprintTwo);
        sprints.add(sprintThree);

        when(sprintOne.getStartDate()).thenReturn(LocalDate.of(1993, 7, 13));
        when(sprintOne.getEndDate()).thenReturn(LocalDate.of(1993, 7, 27));
        when(sprintTwo.getStartDate()).thenReturn(LocalDate.of(1993, 7, 31));
        when(sprintTwo.getEndDate()).thenReturn(LocalDate.of(1993, 8, 14));
        when(sprintThree.getStartDate()).thenReturn(LocalDate.of(1993, 8, 16));
        when(sprintThree.getEndDate()).thenReturn(LocalDate.of(1993, 8, 30));


        Period period = Period.between(LocalDate.of(1993, 7, 13),
                LocalDate.of(1993, 8, 13));
        SprintValidationService service = new SprintValidationService();

        boolean result = service.validateSprintRules(period, sprints);
        assertFalse(result);
    }

}