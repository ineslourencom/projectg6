package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectWithValueObjectsDTOTest {

    @Test
    void testEqualsToItSelf() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        boolean result=dto.equals(dto);
        assertTrue(result);
    }

    @Test
    void testEqualsEqualObject() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();

        boolean result=dto.equals(dtoTwo);
        assertTrue(result);
    }

    @Test
    void projectWithValueObjectDTODiferentName() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));



        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();

        dtoTwo.name = NoNumberNoSymbolString.of("project");
        dtoTwo.description = Text.write("description");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dtoTwo.sprintDuration= PositiveNumber.of(1);
        dtoTwo.budget=PositiveNumber.of(1);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dtoTwo.customer = CustomerID.of(NIF.of(253272050));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        boolean result=dto.equals(dtoTwo);
        assertFalse(result);
    }

    @Test
    void projectWithValueObjectDTODiferentDescription() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));



        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();

        dtoTwo.name = NoNumberNoSymbolString.of("projecto");
        dtoTwo.description = Text.write("descript");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dtoTwo.sprintDuration= PositiveNumber.of(1);
        dtoTwo.budget=PositiveNumber.of(1);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dtoTwo.customer = CustomerID.of(NIF.of(253272050));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        boolean result=dto.equals(dtoTwo);
        assertFalse(result);
    }

    @Test
    void projectWithValueObjectDTODiferentPeriod() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();
        dtoTwo.name = NoNumberNoSymbolString.of("project");
        dtoTwo.description = Text.write("description");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,11,12));
        dtoTwo.sprintDuration= PositiveNumber.of(1);
        dtoTwo.budget=PositiveNumber.of(1);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dtoTwo.customer = CustomerID.of(NIF.of(253272050));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        boolean result=dto.equals(dtoTwo);
        assertFalse(result);
    }

    @Test
    void projectWithValueObjectDTODiferentSprindDuration() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();
        dtoTwo.name = NoNumberNoSymbolString.of("project");
        dtoTwo.description = Text.write("description");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dtoTwo.sprintDuration= PositiveNumber.of(2);
        dtoTwo.budget=PositiveNumber.of(1);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dtoTwo.customer = CustomerID.of(NIF.of(253272050));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        boolean result=dto.equals(dtoTwo);
        assertFalse(result);
    }
    @Test
    void projectWithValueObjectDTODiferentBudget() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();
        dtoTwo.name = NoNumberNoSymbolString.of("project");
        dtoTwo.description = Text.write("description");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dtoTwo.sprintDuration= PositiveNumber.of(1);
        dtoTwo.budget=PositiveNumber.of(2);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dtoTwo.customer = CustomerID.of(NIF.of(253272050));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        boolean result=dto.equals(dtoTwo);
        assertFalse(result);
    }

    @Test
    void projectWithValueObjectDTODiferentBusinessSector() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();
        dtoTwo.name = NoNumberNoSymbolString.of("project");
        dtoTwo.description = Text.write("description");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dtoTwo.sprintDuration= PositiveNumber.of(1);
        dtoTwo.budget=PositiveNumber.of(1);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23422"));
        dtoTwo.customer = CustomerID.of(NIF.of(253272050));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        boolean result=dto.equals(dtoTwo);
        assertFalse(result);
    }

    @Test
    void projectWithValueObjectDTODiferentCustomer() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();
        dtoTwo.name = NoNumberNoSymbolString.of("project");
        dtoTwo.description = Text.write("description");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dtoTwo.sprintDuration= PositiveNumber.of(1);
        dtoTwo.budget=PositiveNumber.of(1);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dtoTwo.customer = CustomerID.of(NIF.of(123456789));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        boolean result=dto.equals(dtoTwo);
        assertFalse(result);
    }
    @Test
    void projectWithValueObjectDTODiferentProjectTypologyID() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();
        dtoTwo.name = NoNumberNoSymbolString.of("project");
        dtoTwo.description = Text.write("description");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dtoTwo.sprintDuration= PositiveNumber.of(1);
        dtoTwo.budget=PositiveNumber.of(1);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dtoTwo.customer = CustomerID.of(NIF.of(253272050));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("v"));

        boolean result=dto.equals(dtoTwo);
        assertFalse(result);
    }

    @Test
    void projectWithValueObjectDTOSameProject() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();
        dtoTwo.name = NoNumberNoSymbolString.of("projecto");
        dtoTwo.description = Text.write("description");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dtoTwo.sprintDuration= PositiveNumber.of(1);
        dtoTwo.budget=PositiveNumber.of(1);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dtoTwo.customer = CustomerID.of(NIF.of(253272050));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        boolean result=dto.equals(dtoTwo);
        assertTrue(result);
    }
    @Test
    void testEqualsObjectOfDiferentClasses() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        AccountID accountID = AccountID.of(Email.of("luis@isep.ipp.pt"));

        boolean result=dto.equals(accountID);
        assertFalse(result);
    }

    @Test
    void testEqualsNotNull() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));


        boolean result=dto.equals(null);
        assertFalse(result);
    }

    @Test
    void testEqualsEqualHas(){
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();

        assertEquals(dto.hashCode(),dtoTwo.hashCode());
    }

    @Test
    void testEqualsDifferentHash() {
        ProjectWithValueObjectsDTO dto= new ProjectWithValueObjectsDTO();
        dto.name = NoNumberNoSymbolString.of("projecto");
        dto.description =  Text.write("description");
        dto.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dto.sprintDuration= PositiveNumber.of(1);
        dto.budget=PositiveNumber.of(1);
        dto.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dto.customer = CustomerID.of(NIF.of(253272050));
        dto.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        ProjectWithValueObjectsDTO dtoTwo= new ProjectWithValueObjectsDTO();
        dtoTwo.name = NoNumberNoSymbolString.of("project");
        dtoTwo.description = Text.write("description");
        dtoTwo.period = Period.between(LocalDate.now(),LocalDate.of(2023,12,12));
        dtoTwo.sprintDuration= PositiveNumber.of(1);
        dtoTwo.budget=PositiveNumber.of(1);
        dtoTwo.businessSector = BusinesSectorID.of(CAE.of("23423"));
        dtoTwo.customer = CustomerID.of(NIF.of(253272050));
        dtoTwo.projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("a"));

        assertNotEquals(dto.hashCode(),dtoTwo.hashCode());
    }


}