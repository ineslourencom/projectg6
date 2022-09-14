package switchtwentyone.project.domain.aggregates.projectTypology;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Text;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTypologyTest {

    @Test
    void ProjectTypologyWithSameIDAreEquals() {
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("exampleOne");
        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        NoNumberNoSymbolString nameTwo = NoNumberNoSymbolString.of("exampleTwo");
        Text description = Text.write("exampleOne");
        Text descriptionTwo = Text.write("exampleTwo");


        ProjectTypology projectTypologyOne = new ProjectTypology(pTID, description);
        ProjectTypology projectTypologyTwo = new ProjectTypology(pTID, descriptionTwo);

        assertEquals(projectTypologyOne, projectTypologyTwo);
    }

    @Test
    void ProjectTypologyWithDifferentIDAreEquals() {

        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("exampleOne");
        NoNumberNoSymbolString nameTwo = NoNumberNoSymbolString.of("exampleTwo");

        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        ProjectTypologyID pTIDTwo = ProjectTypologyID.of(nameTwo);
        Text description = Text.write("exampleOne");
        Text descriptionTwo = Text.write("exampleTwo");


        ProjectTypology projectTypologyOne = new ProjectTypology(pTID, description);
        ProjectTypology projectTypologyTwo = new ProjectTypology(pTIDTwo, descriptionTwo);

        assertNotEquals(projectTypologyOne, projectTypologyTwo);
    }

    @Test
    void ProjectTypologyEqualToItself() {

        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("exampleOne");
        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        Text description = Text.write("exampleOne");

        ProjectTypology projectTypologyOne = new ProjectTypology(pTID, description);

        assertEquals(projectTypologyOne, projectTypologyOne);
    }

    @Test
    void ProjectTypologyWithSameIDHaveSameHashCode() {


        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("exampleOne");
        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        NoNumberNoSymbolString nameTwo = NoNumberNoSymbolString.of("exampleTwo");
        Text description = Text.write("exampleOne");
        Text descriptionTwo = Text.write("exampleTwo");


        ProjectTypology projectTypologyOne = new ProjectTypology(pTID, description);
        ProjectTypology projectTypologyTwo = new ProjectTypology(pTID, descriptionTwo);

        assertEquals(projectTypologyOne.hashCode(), projectTypologyTwo.hashCode());
    }

    @Test
    void ProjectTypologyWithDifferentIDHaveDifferentHashCode() {

        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("exampleOne");
        NoNumberNoSymbolString nameTwo = NoNumberNoSymbolString.of("exampleTwo");

        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        ProjectTypologyID pTIDTwo = ProjectTypologyID.of(nameTwo);
        Text description = Text.write("exampleOne");
        Text descriptionTwo = Text.write("exampleTwo");


        ProjectTypology projectTypologyOne = new ProjectTypology(pTID, description);
        ProjectTypology projectTypologyTwo = new ProjectTypology(pTIDTwo, descriptionTwo);

        assertNotEquals(projectTypologyOne.hashCode(), projectTypologyTwo.hashCode());
    }

    @Test
    void ProjectTypologyNotEqualsWithDifferentObjects() {
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("exampleOne");
        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        Text description = Text.write("exampleOne");
        int comparator = 2;

        ProjectTypology projectTypologyOne = new ProjectTypology(pTID, description);

        assertNotEquals(projectTypologyOne, comparator);
    }

    @Test
    void ProjectTypologyEqualsNotNull() {
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("exampleOne");
        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        Text description = Text.write("exampleOne");


        ProjectTypology projectTypologyOne = new ProjectTypology (pTID, description);

        assertNotEquals(projectTypologyOne, null);
    }

    @Test
    void ProjectTypologySameIdentity() {

        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("exampleOne");
        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        NoNumberNoSymbolString nameTwo = NoNumberNoSymbolString.of("exampleTwo");
        Text description = Text.write("exampleOne");
        Text descriptionTwo = Text.write("exampleTwo");


        ProjectTypology projectTypologyOne = new ProjectTypology(pTID, description);
        ProjectTypology projectTypologyTwo = new ProjectTypology(pTID, descriptionTwo);

        boolean result = projectTypologyOne.sameIdentityAs(projectTypologyTwo);

        assertTrue(result);
    }


    @Test
    void getProjectTypologyIDAsString() {

        String SName = "exampleOne";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(SName);
        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        Text description = Text.write("exampleOne");
        ProjectTypology projectTypologyOne = new ProjectTypology(pTID, description);
        String result = projectTypologyOne.getProjectTypologyIDAsString();

        assertEquals(result, SName);
    }
    

    @Test
    void getDescriptionAsString() {
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Name");
        ProjectTypologyID pTID = ProjectTypologyID.of(name);
        String descriptionString = "exampleOne";
        Text description = Text.write(descriptionString);
        ProjectTypology projectTypologyOne = new ProjectTypology(pTID, description);
        String result = projectTypologyOne.getDescriptionAsString();

        assertEquals(result, descriptionString);
    }
}