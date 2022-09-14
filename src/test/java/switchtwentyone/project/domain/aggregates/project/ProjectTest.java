package switchtwentyone.project.domain.aggregates.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.aggregates.resource.*;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.*;
import switchtwentyone.project.dto.ProjectInfoDomainDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("ProjectTest")
@ExtendWith(SpringExtension.class)
class ProjectTest {

    @Autowired
    ResourceCreatable resourceCreatable;

    @Test
    void createUserStorySuccessful_Integration() {
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project proj = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);

        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;

        UserStory usOne = new UserStory(usID1, usNumber1, statement1, detail, usPriority1, projectID);

        UserStory newUSTwo = proj.createUserStory(statement1, detail, usID1, usPriority1, usNumber1);

        assertEquals(newUSTwo, usOne);
    }

    @Test
    void createUserStorySuccessful() {
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project proj = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);

        PositiveNumber usNumber1 = PositiveNumber.of(1);
        UserStoryID usID1 = UserStoryID.ofProjIDAndUsNumber(projectID, usNumber1);
        Text statement1 = Text.write("This is statement 1");
        Text detail = Text.write("This is a detail");
        int usPriority1 = 1;

        UserStory usOne = mock(UserStory.class);

        UserStory newUSTwo = proj.createUserStory(statement1, detail, usID1, usPriority1, usNumber1);

        assertNotEquals(newUSTwo, usOne);
    }


    @Test
    void projectWithSameIDAreEquals() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = Text.write("Example One");
        Text descriptionTwo = Text.write("Example Two");
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project projectOne = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        Project projectTwo = new Project(projectID, projectName, descriptionTwo, period, sprintDuration, budget, customer, business, projectTypologyID);

        assertEquals(projectOne, projectTwo);
    }

    @Test
    void profileWithDifferentIDAreDifferent() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = Text.write("Example One");
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        ProjectID projectIDTwo = new ProjectID(2);
        Project projectOne = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        Project projectTwo = new Project(projectIDTwo, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);

        assertNotEquals(projectOne, projectTwo);
    }

    @Test
    void projectEqualsToItself() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = Text.write("Example One");
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project projectOne = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        assertEquals(projectOne, projectOne);
    }


    @Test
    void projectsWithSameIDHaveSameHashCode() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = Text.write("Example One");
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project projectOne = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        Project projectTwo = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);

        assertEquals(projectOne.hashCode(), projectTwo.hashCode());
    }

    @Test
    void projectsWithDifferentIDHaveDifferentHashCode() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = Text.write("Example One");
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        ProjectID projectIDTwo = new ProjectID(2);
        Project projectOne = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        Project projectTwo = new Project(projectIDTwo, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);

        assertNotEquals(projectOne.hashCode(), projectTwo.hashCode());
    }

    @Test
    void projectNotEqualsToOtherObject() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = Text.write("Example One");
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project projectOne = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        int comparator = 1;

        assertNotEquals(projectOne, comparator);
    }


    @Test
    void SameIdentityAsNull() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = Text.write("Example One");
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project projectOne = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        boolean expected = projectOne.sameIdentityAs(null);

        assertFalse(expected);
    }

    @Test
    void SameIdentityAsOtherObjectSameID() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = Text.write("Example One");
        Text descriptionTwo = Text.write("Example Two");
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project projectOne = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        Project projectTwo = new Project(projectID, projectName, descriptionTwo, period, sprintDuration, budget, customer, business, projectTypologyID);
        boolean expected = projectOne.sameIdentityAs(projectTwo);

        assertTrue(expected);
    }


    @Test
    void EqualsToNull() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = Text.write("Example One");
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project projectOne = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);

        assertNotEquals(null, projectOne);
    }

    @Test
    void getNameAsString() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = mock(Text.class);
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project project = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        when(projectName.getValue()).thenReturn("Project");
        String expected = "Project";

        String result = project.getNameAsString();

        assertEquals(expected, result);
    }

    @Test
    void getIDAsInt() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = mock(Text.class);
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project project = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        when(projectID.getProjectID()).thenReturn(1);
        int expected = 1;

        int result = project.getIDAsInt();

        assertEquals(expected, result);
    }

    @Test
    void getDescriptionAsString() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text description = mock(Text.class);
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        when(description.getValue()).thenReturn("Project");
        String expected = "Project";

        String result = project.getDescriptionAsString();

        assertEquals(expected, result);
    }

    @Test
    void getStartDateTest() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text description = mock(Text.class);
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        when(period.getStartingDate()).thenReturn(LocalDate.of(1143, 10, 5));
        LocalDate expected = LocalDate.of(1143, 10, 5);

        LocalDate result = project.getStartDate();

        assertEquals(expected, result);

    }

    @Test
    void getEndDateTest() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text description = mock(Text.class);
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        when(period.getEndingDate()).thenReturn(LocalDate.of(1143, 10, 5));
        LocalDate expected = LocalDate.of(1143, 10, 5);

        LocalDate result = project.getEndDate();

        assertEquals(expected, result);
    }

    @Test
    void getPlannedSprintsAsIntTest() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = mock(Text.class);
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project project = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        int expected = 0;

        int result = project.getPlannedSprintsAsInt();

        assertEquals(expected, result);
    }

    @Test
    void getSprintDurationAsIntTest() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text descriptionOne = mock(Text.class);
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project project = new Project(projectID, projectName, descriptionOne, period, sprintDuration, budget, customer, business, projectTypologyID);
        when(sprintDuration.getNumber()).thenReturn(1);
        int expected = 1;

        int result = project.getSprintDurationAsInt();

        assertEquals(expected, result);
    }

    @Test
    void getStatusAsString() {
        NoNumberNoSymbolString projectName = mock(NoNumberNoSymbolString.class);
        Text description = mock(Text.class);
        Period period = mock(Period.class);
        PositiveNumber sprintDuration = mock(PositiveNumber.class);
        PositiveNumber budget = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = mock(ProjectID.class);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);

        String expected = "PLANNED";

        String result = project.getStatusAsString();

        assertEquals(expected, result);
    }


    @Test
    void getID() {
        //Arrange
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);

        //Act
        ProjectID projID = project.getID();

        //Assert
        assertEquals(projID, projectID);
    }

    @Test
    void getBudget() {
        //Arrange
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);

        //Act
        int budgetFound = project.getBudget();

        //Assert
        assertEquals(2000, budgetFound);
    }

    @Test
    void hasID() {
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        ProjectID newID = new ProjectID(1);

        boolean result = project.hasID(newID);

        assertTrue(result);
    }

    @Test
    void doesNotHaveIDTest() {
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        ProjectID newID = new ProjectID(2);

        boolean result = project.hasID(newID);

        assertFalse(result);
    }

    @Test
    void doesNotHaveIDTestOtherIDIsNull() {
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);


        boolean result = project.hasID(null);

        assertFalse(result);
    }

    @Test
    void editTest() {

        //TODO: I think this is very ugly.

        ProjectInfoDomainDTO dto = new ProjectInfoDomainDTO();
        dto.description = Text.write("newDesc");
        dto.period = Period.starting(LocalDate.of(2022, 2, 2));
        dto.plannedSprints = PositiveNumber.of(10);
        dto.sprintDuration = PositiveNumber.of(3);
        dto.status = PredefinedStatus.of("warranty");

        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration,
                budget, customer, business, projectTypologyID);

        project.edit(dto);

        boolean result = project.getDescriptionAsString().equals(dto.description.getValue())
                && project.getStartDate().equals(dto.period.getStartingDate())
                && project.getPlannedSprintsAsInt() == dto.plannedSprints.getNumber()
                && project.getSprintDurationAsInt() == dto.sprintDuration.getNumber()
                && project.getStatusAsString().equals(dto.status.getValueAsString());

        assertTrue(result);
    }

    @Test
    void containsPeriodTest() {
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration,
                budget, customer, business, projectTypologyID);

        boolean result = project.containsPeriod(Period.between(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10)));

        assertTrue(result);


    }

    @Test
    void doesNotContainPeriodTest() {
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 3));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration,
                budget, customer, business, projectTypologyID);

        boolean result = project.containsPeriod(Period.between(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10)));

        assertFalse(result);


    }


}