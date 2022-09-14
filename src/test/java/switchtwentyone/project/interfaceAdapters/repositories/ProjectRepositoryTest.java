package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IProjectJPARepository;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.datamodel.ProjectJPA;
import switchtwentyone.project.datamodel.assembler.ProjectDomainDataAssembler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository repository;
    @MockBean
    private IProjectJPARepository projJPARepo;
    @MockBean
    private ProjectDomainDataAssembler projAssembler;


    @Test
    void getHighestProjectIDTest() {
        ProjectJPA projectOne = mock(ProjectJPA.class);
        ProjectJPA projectTwo = mock(ProjectJPA.class);
        when(projectOne.getId()).thenReturn(new ProjectID(1));
        when(projectTwo.getId()).thenReturn(new ProjectID(2));
        List<ProjectJPA> allProjectsJPA = new ArrayList<>();
        allProjectsJPA.add(projectOne);
        allProjectsJPA.add(projectTwo);
        when(projJPARepo.findAll()).thenReturn(allProjectsJPA);

        int expected = 2;

        int result = repository.getHighestProjectID();

        assertEquals(expected, result);

    }


    //TODO the following portion of code represents tests that were built for the implmentation
    //TODO of a JPA repository. To be done later.


    @Test
    void findByIdUnsuccessful() {
        //Arrange
        ProjectID projID = new ProjectID(1);

        //Act
        Optional projFound = repository.findById(projID);

        //Assert
        assertEquals(projFound, Optional.empty());
    }


    @Test
    void findByIdSuccessful() {
        //Arrange
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("name");
        Text description = Text.write("description");
        Period period = Period.starting(LocalDate.of(2022, 1, 1));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(2000);
        CustomerID customer = CustomerID.of(NIF.of(123456789));
        BusinesSectorID business = BusinesSectorID.of(CAE.of("12345"));
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of("cab"));
        ProjectID projectID = new ProjectID(1);
        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customer, business, projectTypologyID);
        ProjectJPA projectJPA = mock(ProjectJPA.class);
        when(projJPARepo.findById(projectID)).thenReturn(Optional.of(projectJPA));
        when(projAssembler.toDomain(projectJPA)).thenReturn(project);

        //Act
        Optional<Project> projFound = repository.findById(projectID);

        //Assert
        assertEquals(Optional.of(project), projFound);
    }


    @Test
    void save() {
        //Arrange
        Project project = mock(Project.class);
        when(project.getIDAsInt()).thenReturn(1);
        when(project.getNameAsString()).thenReturn("name");
        when(project.getDescriptionAsString()).thenReturn("description");
        when(project.getStartDate()).thenReturn(LocalDate.of(2022, 1, 1));
        when(project.getEndDate()).thenReturn(LocalDate.of(2022, 2, 1));
        when(project.getSprintDurationAsInt()).thenReturn(2);
        ProjectID projID = new ProjectID(1);
        when(project.getID()).thenReturn(projID);
        when(project.getBudget()).thenReturn(2000);
        when(project.getBusinessSector()).thenReturn("12345_sector");

        ProjectJPA projJPA = mock(ProjectJPA.class);

        when(projAssembler.toData(project)).thenReturn(projJPA);
        when(projJPARepo.save(projJPA)).thenReturn(projJPA);
        when(repository.save(project)).thenReturn(project);

        //Act
        Project newProject = repository.save(project);

        //Assert
        assertEquals(project, newProject);
    }

    @Test
    void findAllTest(){
        ProjectJPA projectJPAOne = mock(ProjectJPA.class);
        ProjectJPA projectJPATwo = mock(ProjectJPA.class);
        ProjectJPA projectJPAThree = mock(ProjectJPA.class);
        List<ProjectJPA> projectsJPA = new ArrayList<>();
        projectsJPA.add(projectJPAOne);
        projectsJPA.add(projectJPATwo);
        projectsJPA.add(projectJPAThree);
        Project projectOne = mock(Project.class);
        Project projectTwo = mock(Project.class);
        Project projectThree = mock(Project.class);
        List<Project> expected = new ArrayList<>();
        expected.add(projectOne);
        expected.add(projectTwo);
        expected.add(projectThree);

        when(projJPARepo.findAll()).thenReturn(projectsJPA);
        when(projAssembler.toDomain(projectJPAOne)).thenReturn(projectOne);
        when(projAssembler.toDomain(projectJPATwo)).thenReturn(projectTwo);
        when(projAssembler.toDomain(projectJPAThree)).thenReturn(projectThree);

        List<Project>result = repository.findAll();

        assertEquals(expected, result);

    }

    @Test
    void existsById_Test_Success() {
        //Arrange
        ProjectID projectID = mock(ProjectID.class);
        when(projJPARepo.existsById(projectID)).thenReturn(true);

        //Act
        boolean actual = repository.existsById(projectID);

        //Assert
        assertTrue(actual);
    }

    @Test
    void existsById_Test_Fail() {
        //Arrange
        ProjectID projectID = mock(ProjectID.class);
        when(projJPARepo.existsById(projectID)).thenReturn(false);

        //Act
        boolean actual = repository.existsById(projectID);

        //Assert
        assertFalse(actual);
    }

}
