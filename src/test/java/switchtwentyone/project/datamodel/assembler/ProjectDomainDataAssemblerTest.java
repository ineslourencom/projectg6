package switchtwentyone.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.project.*;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.datamodel.ProjectJPA;
//import switchtwentyone.project.datamodel.ProjectJPA;
//import switchtwentyone.project.datamodel.UserStoryJPA;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectDomainDataAssemblerTest {

    @Test
    void toDomain() {

        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = CustomerID.of(NIF.of(249778360));
        BusinesSectorID business = BusinesSectorID.of(CAE.of("12345"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("Fixed Cost"));
        ProjectID projectID = new ProjectID(1);
        Project expected = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        ProjectDomainDataAssembler projAssembler = new ProjectDomainDataAssembler();

        ProjectJPA projectJPA = new ProjectJPA(projectID, "name", "description",
                LocalDate.of(2022, 1, 1),
                null, 2, "planned", 2000,
                business, customer, projectTypologyID, 0);

        //Act
        Project result = projAssembler.toDomain(projectJPA);


        //Assert
        assertEquals(expected, result);

    }

    @Test
    void toDomainTwo() {

        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = CustomerID.of(NIF.of(249778360));
        BusinesSectorID business = BusinesSectorID.of(CAE.of("12345"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("Fixed Cost"));
        ProjectID projectID = new ProjectID(1);
        Project expected = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        expected.setPlannedSprints(PositiveNumber.of(2));

        ProjectJPA projectJPA = new ProjectJPA(projectID, "name", "description",
                LocalDate.of(2022, 1, 1),
                null, 2, "planned", 2000,
                business, customer, projectTypologyID, 2);

        ProjectDomainDataAssembler projAssembler = new ProjectDomainDataAssembler();


        //Act
        Project result = projAssembler.toDomain(projectJPA);


        //Assert
        assertEquals(expected, result);

    }

    @Test
    void toData() {
        //Arrange
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = CustomerID.of(NIF.of(249778360));
        BusinesSectorID business = BusinesSectorID.of(CAE.of("12345"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("Fixed Cost"));
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        ProjectDomainDataAssembler projAssembler = new ProjectDomainDataAssembler();


        ProjectJPA expected = new ProjectJPA(projectID, "name", "description",
                LocalDate.of(2022, 1, 1),
                null, 2, "PLANNED", 2000,
                business, customer, projectTypologyID, 0);

        //Act
        ProjectJPA result = projAssembler.toData(project);

        //Assert
        assertEquals(expected, result);
    }
}