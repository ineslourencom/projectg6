package switchtwentyone.project.domain.aggregates.sprint;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SprintFactoryTest {

    @Test
    void SprintTest() {
        ProjectID projID1 = new ProjectID(1);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        PositiveNumber sprintIDTest =PositiveNumber.of(2);
        LocalDate start = LocalDate.of (2022,10,25);
        SprintID sprintID = new SprintID(projID1,sprintIDTest);
        Sprint newSprint = new Sprint (sprintID,start,projID1,sprintIDTest,sprintDurantion);


        Sprint result = SprintCreatable.createSprint(sprintID,start, projID1,sprintIDTest,sprintDurantion);

        assertEquals(newSprint, result);
    }

    @Test
    void ensureInstanceIsTheSame () {
        SprintFactory instanceOne = SprintFactory.getInstance();
        SprintFactory instanceTwo = SprintFactory.getInstance();

        boolean result = (instanceOne == instanceTwo);

        assertTrue(result);

    }

    @Test
    void ensureInstanceIsNotNull() {
        SprintFactory instance = SprintFactory.getInstance();

        assertNotNull(instance);
    }
}