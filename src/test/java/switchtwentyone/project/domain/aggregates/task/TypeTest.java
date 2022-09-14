package switchtwentyone.project.domain.aggregates.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeTest {

    @Test
    void getDbName() {
        Type type = Type.Deployment;
        String expected = "Deployment";

        String result = type.getDbName();

        assertEquals(expected, result);
    }

    @Test
    void documentation() {
        Type expected = Type.Documentation;

        Type result = Type.fromDbName("Documentation");

        assertEquals(expected, result);
    }

    @Test
    void Design() {
        Type expected = Type.Design;

        Type result = Type.fromDbName("Design");

        assertEquals(expected, result);
    }

    @Test
    void Implementation() {
        Type expected = Type.Implementation;

        Type result = Type.fromDbName("Implementation");

        assertEquals(expected, result);
    }

    @Test
    void Testing() {
        Type expected = Type.Testing;

        Type result = Type.fromDbName("Testing");

        assertEquals(expected, result);
    }

    @Test
    void Deployment() {
        Type expected = Type.Deployment;

        Type result = Type.fromDbName("Deployment");

        assertEquals(expected, result);
    }


}