package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Text;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTypologyDTOTest {

    @Test
    void ProjectTypologyDTOWithSameAtributesAreEqual() {
        ProjectTypologyDTO projectTypologyDTOOne = new ProjectTypologyDTO();
        projectTypologyDTOOne.name = "yupi";
        projectTypologyDTOOne.description = "yeah";
        ProjectTypologyDTO projectTypologyDTOTwo = new ProjectTypologyDTO();
        projectTypologyDTOTwo.name = "yupi";
        projectTypologyDTOTwo.description = "yeah";

        assertEquals(projectTypologyDTOOne, projectTypologyDTOTwo);
    }

    @Test
    void ProjectTypologyDTOWithDifferentNameAreDifferent() {

        ProjectTypologyDTO projectTypologyDTOOne = new ProjectTypologyDTO();
        projectTypologyDTOOne.name = "yupi";
        projectTypologyDTOOne.description = "yeah";
        ProjectTypologyDTO projectTypologyDTOTwo = new ProjectTypologyDTO();
        projectTypologyDTOTwo.name = "NotYupi";
        projectTypologyDTOTwo.description = "yeah";
        assertNotEquals(projectTypologyDTOOne, projectTypologyDTOTwo);
    }

    @Test
    void ProjectTypologyDTOWithDifferentDescriptionAreDifferent() {

        ProjectTypologyDTO projectTypologyDTOOne = new ProjectTypologyDTO();
        projectTypologyDTOOne.name = "yupi";
        projectTypologyDTOOne.description = "yeah";
        ProjectTypologyDTO projectTypologyDTOTwo = new ProjectTypologyDTO();
        projectTypologyDTOTwo.name = "yupi";
        projectTypologyDTOTwo.description = "NotYeah";
        assertNotEquals(projectTypologyDTOOne, projectTypologyDTOTwo);
    }

    @Test
    void ProjectTypologyDTOWithSameAtributesHaveSameHashCode() {

        ProjectTypologyDTO projectTypologyDTOOne = new ProjectTypologyDTO();
        projectTypologyDTOOne.name = "yupi";
        projectTypologyDTOOne.description = "yeah";
        ProjectTypologyDTO projectTypologyDTOTwo = new ProjectTypologyDTO();
        projectTypologyDTOTwo.name = "yupi";
        projectTypologyDTOTwo.description = "yeah";
        assertEquals(projectTypologyDTOOne.hashCode(), projectTypologyDTOTwo.hashCode());
    }

    @Test
    void ProjectTypologyWithDifferentAtributesHaveDifferentHashCode() {

        ProjectTypologyDTO projectTypologyDTOOne = new ProjectTypologyDTO();
        projectTypologyDTOOne.name = "Notyupi";
        projectTypologyDTOOne.description = "yeah";
        ProjectTypologyDTO projectTypologyDTOTwo = new ProjectTypologyDTO();
        projectTypologyDTOTwo.name = "yupi";
        projectTypologyDTOTwo.description = "yeah";
        assertNotEquals(projectTypologyDTOOne.hashCode(), projectTypologyDTOTwo.hashCode());
    }

    @Test
    void ProjectTypologyDTONotEqualsWithDifferentObjects() {
        ProjectTypologyDTO projectTypologyDTOOne = new ProjectTypologyDTO();
        projectTypologyDTOOne.name = "yupi";
        projectTypologyDTOOne.description = "yeah";
        int comparator = 5;

        assertNotEquals(projectTypologyDTOOne, comparator);
    }

    @Test
    void ProjectTypologyEqualsNotNull() {
        ProjectTypologyDTO projectTypologyDTOOne = new ProjectTypologyDTO();
        projectTypologyDTOOne.name = "yupi";
        projectTypologyDTOOne.description = "yeah";

        assertNotEquals(projectTypologyDTOOne, null);
    }


}