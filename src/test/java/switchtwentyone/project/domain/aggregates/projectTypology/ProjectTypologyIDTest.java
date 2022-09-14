package switchtwentyone.project.domain.aggregates.projectTypology;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTypologyIDTest {

    @Test
    void ProjectTypologyIDWithSameIDAreEquals() {
        NoNumberNoSymbolString name  = NoNumberNoSymbolString.of("example");

        ProjectTypologyID projectTypologyIDOne = ProjectTypologyID.of(name);
        ProjectTypologyID projectTypologyIDTwo = ProjectTypologyID.of(name);

        assertEquals(projectTypologyIDOne, projectTypologyIDTwo);
    }

    @Test
    void ProjectTypologyIDWithDifferentIDAreEquals() {
        NoNumberNoSymbolString name  = NoNumberNoSymbolString.of("example");
        NoNumberNoSymbolString name2  = NoNumberNoSymbolString.of("exampleTwo");

        ProjectTypologyID projectTypologyIDOne = ProjectTypologyID.of(name);
        ProjectTypologyID projectTypologyIDTwo = ProjectTypologyID.of(name2);

        assertNotEquals(projectTypologyIDOne, projectTypologyIDTwo);
    }

    @Test
    void ProjectTypologyIDEqualToItself() {
        NoNumberNoSymbolString name  = NoNumberNoSymbolString.of("example");
        ProjectTypologyID projectTypologyIDOne = ProjectTypologyID.of(name);

        assertEquals(projectTypologyIDOne, projectTypologyIDOne);
    }

    @Test
    void ProjectTypologyIDWithSameIDHaveSameHashCode() {
        NoNumberNoSymbolString name  = NoNumberNoSymbolString.of("example");
        ProjectTypologyID projectTypologyIDOne = ProjectTypologyID.of(name);
        ProjectTypologyID projectTypologyIDTwo = ProjectTypologyID.of(name);

        assertEquals(projectTypologyIDOne.hashCode(), projectTypologyIDTwo.hashCode());
    }

    @Test
    void ProjectTypologyIDWithDifferentIDHaveDifferentHashCode() {
        NoNumberNoSymbolString name  = NoNumberNoSymbolString.of("example");
        NoNumberNoSymbolString name2  = NoNumberNoSymbolString.of("exampleTwo");
        ProjectTypologyID projectTypologyIDOne = ProjectTypologyID.of(name);
        ProjectTypologyID projectTypologyIDTwo = ProjectTypologyID.of(name2);

        assertNotEquals(projectTypologyIDOne.hashCode(), projectTypologyIDTwo.hashCode());
    }

    @Test
    void ProjectTypologyIDNotEqualsWithDifferentObjects() {
        NoNumberNoSymbolString name  = NoNumberNoSymbolString.of("example");
        ProjectTypologyID projectTypologyIDOne = ProjectTypologyID.of(name);

        assertNotEquals(projectTypologyIDOne, name);
    }

    @Test
    void ProjectTypologyEqualsNotNull() {
        NoNumberNoSymbolString name  = NoNumberNoSymbolString.of("example");
        ProjectTypologyID projectTypologyIDOne = ProjectTypologyID.of(name);

        assertNotEquals(projectTypologyIDOne, null);
    }

    @Test
    void ProjectTypologySameIdentity() {
        NoNumberNoSymbolString name  = NoNumberNoSymbolString.of("example");
        ProjectTypologyID projectTypologyIDOne = ProjectTypologyID.of(name);
        ProjectTypologyID projectTypologyIDTwo = ProjectTypologyID.of(name);

        boolean result = projectTypologyIDOne.sameValueAs(projectTypologyIDTwo);

        assertTrue(result);
    }


    @Test
    void getNameAsString() {
        //Arrange
        String SName = "example";
        NoNumberNoSymbolString name  = NoNumberNoSymbolString.of(SName);
        ProjectTypologyID projectTypologyIDOne = ProjectTypologyID.of(name);

        //Act
        String result = projectTypologyIDOne.getNameAsString();

        //Assert
        assertEquals(result, SName);
    }
}