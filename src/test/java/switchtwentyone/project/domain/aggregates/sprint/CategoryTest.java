package switchtwentyone.project.domain.aggregates.sprint;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void fromDbNameToDo() {
        String dbName = "To do";
        Category result = Category.fromDbName(dbName);
        Category expected = Category.To_do;

        assertEquals(expected, result);
    }

    @Test
    void fromDbNameInProgress() {
        String dbName = "In progress";
        Category result = Category.fromDbName(dbName);
        Category expected = Category.In_progress;

        assertEquals(expected, result);
    }

    @Test
    void fromDbNameCodeReview() {
        String dbName = "Code review";
        Category result = Category.fromDbName(dbName);
        Category expected = Category.Code_review;

        assertEquals(expected, result);
    }

    @Test
    void fromDbNameDone() {
        String dbName = "Done";
        Category result = Category.fromDbName(dbName);
        Category expected = Category.Done;

        assertEquals(expected, result);
    }

    @Test
    void fromDbNameRejected() {
        String dbName = "Rejected";
        Category result = Category.fromDbName(dbName);
        Category expected = Category.Rejected;

        assertEquals(expected, result);
    }

    @Test
    void fromDbNameInvalid() {
        String dbName = "ejrahkwrb";

        assertThrows(IllegalArgumentException.class, () -> {
            Category.fromDbName(dbName);
        });
    }
}