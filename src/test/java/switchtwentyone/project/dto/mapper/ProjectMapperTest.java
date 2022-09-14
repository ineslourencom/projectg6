package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectFactory;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProjectDTO;
import switchtwentyone.project.dto.ProjectInfoReturnedWhenCreatedDTO;
import switchtwentyone.project.dto.ProjectWithValueObjectsDTO;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectMapperTest {


    @Test
    void testConstructorIsPrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<ProjectMapper> constructor = ProjectMapper.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void toDTOTest() {
        ProjectDTO expected = new ProjectDTO();
        expected.code = "1";
        expected.name = "Project";
        expected.description = "This is the project description";
        Project project = mock(Project.class);
        when(project.getIDAsInt()).thenReturn(1);
        when(project.getNameAsString()).thenReturn("Project");
        when(project.getDescriptionAsString()).thenReturn("This is the project description");

        ProjectDTO result = ProjectMapper.toDTO(project);

        assertEquals(expected, result);
    }

    @Test
    void toDTOWithValueObjects() {

        NoNumberNoSymbolString name = mock(NoNumberNoSymbolString.class);
        Text description = mock(Text.class);
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID businessSector = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);

        ProjectWithValueObjectsDTO expected = new ProjectWithValueObjectsDTO();
        expected.name = name;
        expected.description = description;
        expected.period = period;
        expected.sprintDuration = sprintDuration;
        expected.budget = budget;
        expected.businessSector = businessSector;
        expected.customer = customer;
        expected.projectTypologyID = projectTypologyID;

        ProjectWithValueObjectsDTO result = ProjectMapper.toDTOWithValueObjects(name, description, period, sprintDuration, budget, businessSector, customer, projectTypologyID);

        assertEquals(expected, result);
    }

    @Test
    void toDTOReturnedUpoCreation() {

        Project project = mock(Project.class);
        when(project.getIDAsInt()).thenReturn(1);
        when(project.getNameAsString()).thenReturn("name");
        when(project.getDescriptionAsString()).thenReturn("description");
        when(project.getStartDate()).thenReturn(LocalDate.of(2022, 8, 24));
        when(project.getEndDate()).thenReturn(LocalDate.of(2022, 12, 24));
        when(project.getSprintDurationAsInt()).thenReturn(3);
        when(project.getBudget()).thenReturn(5000);
        when(project.getCustomer()).thenReturn(257715347);
        when(project.getBusinessSector()).thenReturn("00000");
        when(project.getProjectTypologyIDAsString()).thenReturn("PTname");

        ProjectInfoReturnedWhenCreatedDTO expected = new ProjectInfoReturnedWhenCreatedDTO();
        expected.projectID = 1;
        expected.name = "name";
        expected.description = "description";
        expected.startDate = LocalDate.of(2022, 8, 24);
        expected.endDate = LocalDate.of(2022, 12, 24);
        expected.sprintDuration = 3;
        expected.budget = 5000;
        expected.CustomerID = 257715347;
        expected.BusinessSectorID = "00000";
        expected.ProjectTypologyID= "PTName";

        ProjectInfoReturnedWhenCreatedDTO result = ProjectMapper.toDTOReturnedUpoCreation(project);

        assertEquals(expected, result);
    }
}