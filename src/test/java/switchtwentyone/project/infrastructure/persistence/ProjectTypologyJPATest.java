package switchtwentyone.project.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.datamodel.ProjectTypologyJPA;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTypologyJPATest {

    @Test
    void getId() {
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);
        ProjectTypologyJPA projectTypologyJPA = new ProjectTypologyJPA(projectTypologyID, "description");
        ProjectTypologyID expected = projectTypologyID;
        ProjectTypologyID result = projectTypologyJPA.getProjectTypologyID();

        assertEquals(expected, result);
    }


    @Test
    void getDescription() {
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);
        ProjectTypologyJPA projectTypologyJPA = new ProjectTypologyJPA(projectTypologyID, "description");
        String result = projectTypologyJPA.getDescription();

        assertEquals("description", result);
    }

    @Test
    void setId() {
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);
        NoNumberNoSymbolString newName = NoNumberNoSymbolString.of("newName");
        ProjectTypologyID newProjectTypologyID = ProjectTypologyID.of(newName);
        ProjectTypologyJPA projectTypologyJPA = new ProjectTypologyJPA(projectTypologyID, "description");
        projectTypologyJPA.setProjectTypologyID(newProjectTypologyID);
        ProjectTypologyID result = projectTypologyJPA.getProjectTypologyID();

        assertEquals(newProjectTypologyID, result);
    }

    @Test
    void setDescription() {
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);
        ProjectTypologyJPA projectTypologyJPA = new ProjectTypologyJPA(projectTypologyID, "description");
        String expected = "Bom Dia";
        projectTypologyJPA.setDescription(expected);
        String result = projectTypologyJPA.getDescription();

        assertEquals(expected, result);
    }


    @Test
    void testSameJPAAreEqual() {
        //arrange
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);

        ProjectTypologyJPA jpa = new ProjectTypologyJPA(projectTypologyID, "description");


        //Act
        boolean result = jpa.equals(jpa);

        //Assert
        assertTrue(result);
    }
    @Test
    void testSameJPAAreEqual_return() {
        //arrange
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);

        ProjectTypologyJPA jpa = new ProjectTypologyJPA(projectTypologyID, "description");
        ProjectTypologyJPA jpa2 = new ProjectTypologyJPA(projectTypologyID, "description");


        //Assert
        assertEquals(jpa, jpa2 );
    }

    @Test
    void testSameJPAHaveSameHashCode() {
        //arrange
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);

        ProjectTypologyJPA jpa = new ProjectTypologyJPA(projectTypologyID, "description");
        ProjectTypologyJPA jpa2 = new ProjectTypologyJPA(projectTypologyID, "description");

        //Assert
        assertEquals(jpa, jpa2 );
        //Assert
        assertEquals(jpa.hashCode(), jpa2.hashCode());
    }

    @Test
    void testJPANotEqualToNull() {
        //arrange
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);

        ProjectTypologyJPA jpa = new ProjectTypologyJPA(projectTypologyID, "description");

        //act
        boolean result = jpa.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPANotEqualToOtherObject() {
        //arrange
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);

        ProjectTypologyJPA jpa = new ProjectTypologyJPA(projectTypologyID, "description");
        PositiveNumber number = PositiveNumber.of(1);

        //Act
        boolean result = jpa.equals(number);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPEqualToItself() {
        //arrange
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);

        ProjectTypologyJPA jpa = new ProjectTypologyJPA(projectTypologyID, "description");
        //act
        boolean result = jpa.equals(jpa);
        //Assert
        assertTrue(result);
    }



    @Test
    void testToString(){
        //
        //arrange
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("name");
        ProjectTypologyID id = ProjectTypologyID.of(name);

        ProjectTypologyJPA jpa = new ProjectTypologyJPA(id, "description");

        //act

        String expected ="ProjectTypologyJPA(" +
                "projectTypologyID=" + id.toString() +
                ", description=" + "description" +
                ')';
        //act
        String result = jpa.toString();

        assertEquals(expected, result);

    }
}
