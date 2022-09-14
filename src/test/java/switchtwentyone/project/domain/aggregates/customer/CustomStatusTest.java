package switchtwentyone.project.domain.aggregates.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.customer.CustomStatus;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CustomStatusTest {

    @Test
    void ensureConfigurationFailsWhenInitialAndFinalKeysAreMissing() {
        HashMap<String,String> config = new HashMap<>();
        config.put("1", "Planned");
        config.put("2", "In Progress");
        config.put("3", "Done");

        assertThrows(IllegalArgumentException.class,  ()-> {
            CustomStatus.configure(config);});

    }
    @Test
    void ensureConfigurationDoesNotFailWhenInitialAndFinalKeysArePresent() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");

        assertDoesNotThrow( ()-> {CustomStatus.configure(config);});

    }

    @Test
    void ensureStatusOptionsAreEncapsulated() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus statusOne = CustomStatus.of("In Progress");

        config.put("2", "Construction");
        CustomStatus statusTwo =CustomStatus.of("In Progress");

        assertEquals(statusOne, statusTwo);
    }

    @Test
    void getInitialStatusTest() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus expected = CustomStatus.of("Planned");

        CustomStatus actual = CustomStatus.initialStatus();

        assertEquals(expected, actual);


    }

    @Test
    void getFinalStatusTest() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus expected = CustomStatus.of("  done  ");

        CustomStatus actual = CustomStatus.finalStatus();

        assertEquals(expected, actual);

    }

    @Test
    void ensureEqualsItself() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus status = CustomStatus.of("  done  ");

        assertEquals(status, status);
    }
    @Test
    void ensureEqualsEqualObject() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus statusOne = CustomStatus.of("In Progress");
        CustomStatus statusTwo = CustomStatus.of("In Progress");

        assertEquals(statusOne, statusTwo);
    }
    @Test
    void ensureDoesNotEqualDifferentObject() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus statusOne = CustomStatus.of("In Progress");
        CustomStatus statusTwo = CustomStatus.finalStatus();

        assertNotEquals(statusOne, statusTwo);
    }
    @Test
    void ensureDoesNotEqualNull() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus statusOne = CustomStatus.of("In Progress");
        CustomStatus statusTwo = null;

        assertNotEquals(statusOne, statusTwo);
    }
    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus status = CustomStatus.of("In Progress");
        Object somethingElse = new Object();

        assertNotEquals(status, somethingElse);
    }
    @Test
    void testHashCodeOfDifferentObjects(){
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus statusOne = CustomStatus.of("In Progress");
        CustomStatus statusTwo = CustomStatus.initialStatus();
        int hashOne = statusOne.hashCode();
        int hashTwo = statusTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);

    }
    @Test
    void testHashCodeOfEqualObjects(){
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus statusOne = CustomStatus.of("In Progress");
        CustomStatus statusTwo = CustomStatus.of("In Progress");
        int hashOne = statusOne.hashCode();
        int hashTwo = statusTwo.hashCode();

        assertEquals(hashOne, hashTwo);

    }

    @Test
    void ensureSameValueReturnsTrueForSimilarObject() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus statusOne = CustomStatus.of("In Progress");
        CustomStatus statusTwo = CustomStatus.of("In Progress");

       boolean result = statusOne.sameValueAs(statusTwo);

        assertTrue(result);

    }
    @Test
    void ensureSameValueReturnsFalseForDifferentObject() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus statusOne = CustomStatus.of("In Progress");
        CustomStatus statusTwo = CustomStatus.initialStatus();

        boolean result = statusOne.sameValueAs(statusTwo);

        assertFalse(result);
    }
    @Test
    void ensureSameValueReturnsFalseForNull() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus statusOne = CustomStatus.of("In Progress");
        CustomStatus statusTwo = null;

        boolean result = statusOne.sameValueAs(statusTwo);

        assertFalse(result);
    }

    @Test
    void ensureGetStatusFailsWhenStatusDoesNotExist(){
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);

        assertThrows(IllegalArgumentException.class, () -> {CustomStatus.of("warranty");});

    }

    @Test
    void getValueAsString() {
        HashMap<String,String> config = new HashMap<>();
        config.put("initial", "Planned");
        config.put("2", "In Progress");
        config.put("final", "Done");
        CustomStatus.configure(config);
        CustomStatus status = CustomStatus.of("In Progress");
        String expected= "In Progress";

        String result = status.getValueAsString();

        assertEquals(expected, result);

    }
}