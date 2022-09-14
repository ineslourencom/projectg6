package switchtwentyone.project.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchtwentyone.project.datamodel.ProjectJPA;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectJPATest {

    ProjectJPA newProjJPA;

    @BeforeEach
    void arrange(){
        newProjJPA = new ProjectJPA(new ProjectID(1), "name", "desc", LocalDate.of(2022,1,1)
                , LocalDate.of(2022,2,1), 2, "planned",
                1000, BusinesSectorID.of(CAE.of("00000")), CustomerID.of(NIF.of(274188732)),
                ProjectTypologyID.of(NoNumberNoSymbolString.of("test")), 10);
    }

    @Test
    void getId() {
        //Arrange
        ProjectID id = new ProjectID(2);
        newProjJPA.setId(id);

        //Act
        ProjectID result = newProjJPA.getId();

        //Assert
        assertEquals(id, result);

    }

    @Test
    void setId() {
        //Arrange
        ProjectID id = new ProjectID(2);

        //Act
        newProjJPA.setId(id);
        ProjectID result = newProjJPA.getId();


        //Assert
        assertEquals(id, result);
    }

    @Test
    void getProjID() {
        //Arrange
        ProjectID expected = new ProjectID(1);

        //Act
        ProjectID result = newProjJPA.getId();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getName() {

        //Act
        String result = newProjJPA.getName();

        //Assert
        assertEquals("name", result);
    }

    @Test
    void getDescription() {

        //Act
        String result = newProjJPA.getDescription();

        //Assert
        assertEquals("desc", result);

    }


    @Test
    void getSprintDuration() {

        //Act
        int result = newProjJPA.getSprintDuration();

        //Assert
        assertEquals( 2, result);
    }

    @Test
    void getStatus() {

        //Act
        String result = newProjJPA.getStatus();

        //Assert
        assertEquals("planned", result);
    }

    @Test
    void getBudget() {
        //Act
        int result = newProjJPA.getBudget();

        //Assert
        assertEquals(1000, result);
    }

    @Test
    void getBusinessSector() {
        //Arrange
        BusinesSectorID expected = BusinesSectorID.of(CAE.of("00000"));

        //Act
        BusinesSectorID result = newProjJPA.getBusinessSector();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getCustomer() {
        //Arrange
        CustomerID expected = CustomerID.of(NIF.of(274188732));

        //Act
        CustomerID result = newProjJPA.getCustomer();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getProjectTypologyID() {
        //Arrange
        ProjectTypologyID expected = ProjectTypologyID.of(NoNumberNoSymbolString.of("test"));

        //Act
        ProjectTypologyID result = newProjJPA.getProjectTypologyID();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getPlannedSprints() {
        //Act
        int result = newProjJPA.getPlannedSprints();

        //Assert
        assertEquals( 10, result);
    }

    @Test
    void getStartDate(){
        //Arrange
        LocalDate startDate = LocalDate.of(2022,1,1);
        //Act
        LocalDate result = newProjJPA.getStartDate();

        //Assert
        assertEquals(startDate, result);
    }

    @Test
    void getEndDate(){
        //Arrange
        LocalDate endDate = LocalDate.of(2022,2,1);

        //Act
        LocalDate result = newProjJPA.getEndDate();

        //Assert
        assertEquals( endDate, result);
    }


    @Test
    void testEqualsItself() {
        //Act
        boolean result = newProjJPA.equals(newProjJPA);

        //Assert
        assertTrue(result);
    }

    @Test
    void testNotEqualNull() {
        //Act
        boolean result = newProjJPA.equals(null);

        //Assert
        assertFalse(result);
    }
}