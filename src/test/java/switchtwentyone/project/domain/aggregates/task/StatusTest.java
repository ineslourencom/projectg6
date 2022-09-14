package switchtwentyone.project.domain.aggregates.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void getDbName() {
        Status status = Status.Finished;
        String expected = "Finished";

        String result = status.getDbName();

        assertEquals(expected, result);

    }

    @Test
    void Running() {
        Status expected = Status.Running;
        Status result = Status.fromDbName("Running");


        assertEquals(expected, result);

    }

    @Test
    void Finished() {
        Status expected = Status.Finished;
        Status result = Status.fromDbName("Finished");


        assertEquals(expected, result);

    }

    @Test
    void Blocked() {
        Status expected = Status.Blocked;
        Status result = Status.fromDbName("Blocked");


        assertEquals(expected, result);

    }
}