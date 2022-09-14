package switchtwentyone.project.domain.aggregates.task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EffortUpdateTest {

    @Test
    void equals_Test_differentTime() {
        //arrange
        LocalDateTime timeOne = LocalDateTime.now();
        LocalDateTime timeTwo = LocalDateTime.now().minusSeconds(2);
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(timeOne, hoursSpent, comment, submitter);
        EffortUpdate effUpdate2 = EffortUpdate.of(timeTwo, hoursSpent, comment, submitter);

        //act
        boolean result = effUpdate1.equals(effUpdate2);

        //assert
        assertFalse(result);
    }

    @Test
    void equals_Test_differentTime_TwoSeconds() {
        //arrange
        LocalDateTime timeOne = LocalDateTime.now();
        LocalDateTime timeTwo = LocalDateTime.now().minusSeconds(2);
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(timeOne, hoursSpent, comment, submitter);
        EffortUpdate effUpdate2 = EffortUpdate.of(timeTwo, hoursSpent, comment, submitter);

        //act
        boolean result = effUpdate1.equals(effUpdate2);

        //assert
        assertFalse(result);
    }

    @Test
    void equals_Test_differentTime_Lower_than_second() {
        //arrange
        LocalDateTime timeOne = LocalDateTime.now();
        LocalDateTime timeTwo = timeOne.truncatedTo(ChronoUnit.SECONDS);
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(timeOne, hoursSpent, comment, submitter);
        EffortUpdate effUpdate2 = EffortUpdate.of(timeTwo, hoursSpent, comment, submitter);

        //act
        boolean result = effUpdate1.equals(effUpdate2);

        //assert
        assertTrue(result);
    }

    @Test
    void equals_Test_differentHoursSpent() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpentOne = 2.0;
        double hoursSpentTwo = 3.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpentOne, comment, submitter);
        EffortUpdate effUpdate2 = EffortUpdate.of(time, hoursSpentTwo, comment, submitter);

        //act
        boolean result = effUpdate1.equals(effUpdate2);

        //assert
        assertFalse(result);
    }

    @Test
    void equals_Test_differentComment() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String commentOne = "test 1";
        String commentTwo = "test 2";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, commentOne, submitter);
        EffortUpdate effUpdate2 = EffortUpdate.of(time, hoursSpent, commentTwo, submitter);

        //act
        boolean result = effUpdate1.equals(effUpdate2);

        //assert
        assertFalse(result);
    }

    @Test
    void equals_Test_differentSubmitter() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test 1";
        ResourceID submitterOne = mock(ResourceID.class);
        ResourceID submitterTwo = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, comment, submitterOne);
        EffortUpdate effUpdate2 = EffortUpdate.of(time, hoursSpent, comment, submitterTwo);

        //act
        boolean result = effUpdate1.equals(effUpdate2);

        //assert
        assertFalse(result);
    }

    @Test
    void equals_Test_same(){
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, comment, submitter);
        EffortUpdate effUpdate2 = effUpdate1;

        //act
        boolean result = effUpdate1.equals(effUpdate2);

        //assert
        assertTrue(result);
    }

    @Test
    void equals_Test_itself() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, comment, submitter);

        //act & assert
        assertEquals(effUpdate1, effUpdate1);
    }

    @Test
    void equals_Test_itself_HashCode() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, comment, submitter);

        //act & assert
        assertEquals(effUpdate1.hashCode(), effUpdate1.hashCode());
    }

    @Test
    void equals_Test_differentTime_HashCode() {
        //arrange
        LocalDateTime timeOne = LocalDateTime.now();
        LocalDateTime timeTwo = LocalDateTime.now().minusSeconds(2);
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(timeOne, hoursSpent, comment, submitter);
        EffortUpdate effUpdate2 = EffortUpdate.of(timeTwo, hoursSpent, comment, submitter);

        //act & assert
        assertNotEquals(effUpdate1.hashCode(), effUpdate2.hashCode());
    }

    @Test
    void equals_Test_differentHours_HashCode() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpentOne = 2.0;
        double hoursSpentTwo = 3.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpentOne, comment, submitter);
        EffortUpdate effUpdate2 = EffortUpdate.of(time, hoursSpentTwo, comment, submitter);

        //act & assert
        assertNotEquals(effUpdate1.hashCode(), effUpdate2.hashCode());
    }

    @Test
    void equals_Test_differentComment_HashCode() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String commentOne = "test 1";
        String commentTwo = "test 2";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, commentOne, submitter);
        EffortUpdate effUpdate2 = EffortUpdate.of(time, hoursSpent, commentTwo, submitter);

        //act & assert
        assertNotEquals(effUpdate1.hashCode(), effUpdate2.hashCode());
    }

    @Test
    void equals_Test_differentSubmitter_HashCode() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test 1";
        ResourceID submitterOne = mock(ResourceID.class);
        ResourceID submitterTwo = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, comment, submitterOne);
        EffortUpdate effUpdate2 = EffortUpdate.of(time, hoursSpent, comment, submitterTwo);

        //act & assert
        assertNotEquals(effUpdate1.hashCode(), effUpdate2.hashCode());
    }

    @Test
    void effortUpdateNotEqualsToOtherObject() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, comment, submitter);
        Object object = new Object();

        //act & assert
        assertNotEquals(effUpdate1, object);
    }

    @Test
    void SameIdentityAsNull() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, comment, submitter);

        //act
        boolean result = effUpdate1.sameValueAs(null);

        //assert
        assertFalse(result);
    }

    @Test
    void EqualsToNull() {
        //arrange
        LocalDateTime time = LocalDateTime.now();
        double hoursSpent = 2.0;
        String comment = "test";
        ResourceID submitter = mock(ResourceID.class);
        EffortUpdate effUpdate1 = EffortUpdate.of(time, hoursSpent, comment, submitter);

        //act & assert
        assertNotEquals(null, effUpdate1);
    }
}
