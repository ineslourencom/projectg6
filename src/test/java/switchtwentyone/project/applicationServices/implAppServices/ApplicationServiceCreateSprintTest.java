package switchtwentyone.project.applicationServices.implAppServices;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItem;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.services.ServiceCreateSprint;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.dto.SprintDTO;
import switchtwentyone.project.dto.SprintTableDTO;
import switchtwentyone.project.dto.mapper.SprintMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.dto.mapper.SprintTableMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ApplicationServiceCreateSprintTest {

    @InjectMocks
    ApplicationServiceCreateSprint appServcCreateSprint;
    @Mock
    private ProjectRepository projRepo;
    @Mock
    private SprintRepository sprintRepo;
    @Mock
    private ServiceCreateSprint servcCreateSprint;


    @Test
    void createSprint() {
        //Arrange
        Project proj = mock(Project.class);
        List<Sprint> listSprint = new ArrayList<>();

        ProjectID projID = new ProjectID(1);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        LocalDate start = LocalDate.of(2022, 10, 25);

        //Sprint1:
        Sprint sprint = mock(Sprint.class);
//        when(sprint.getProjectID()).thenReturn(1);
//        when(sprint.getSprintNumber()).thenReturn(1);
//        when(sprint.getStartDate()).thenReturn(start);
//        when(sprint.getSprintDuration()).thenReturn(sprintDurantion);
//        when(sprint.getSprintIDDouble()).thenReturn(1.1);
//        when(sprint.getSprintBacklogItems()).thenReturn(new ArrayList<>());

        listSprint.add(sprint);


        //Sprint2
        Sprint sprint2 = mock(Sprint.class);
        SprintID s2Id = new SprintID(projID, PositiveNumber.of(2));
        LocalDate startDateS2 = LocalDate.of(2022, 11, 25);
        when(sprint2.getProjectID()).thenReturn(1);
        when(sprint2.getSprintNumber()).thenReturn(2);
        when(sprint2.getStartDate()).thenReturn(LocalDate.of(2022, 11, 25));
        when(sprint2.getSprintDuration()).thenReturn(sprintDurantion);
        when(sprint2.getSprintIDDouble()).thenReturn(1.2);
        when(sprint2.getSprintBacklogItems()).thenReturn(new ArrayList<>());


        when(projRepo.findById(projID)).thenReturn(Optional.of(proj));
        when(sprintRepo.findAllSprintsByProjectID(projID)).thenReturn(listSprint);
        when(servcCreateSprint.generateSprintNumber(listSprint)).thenReturn(PositiveNumber.of(2));
        when(servcCreateSprint.generateSprintID(PositiveNumber.of(2), projID)).thenReturn(s2Id);
        when(servcCreateSprint.startDateAfterLastSprint(listSprint, startDateS2)).thenReturn(true);
        when(proj.createSprint(s2Id, startDateS2, PositiveNumber.of(2))).thenReturn(sprint2);
        when(sprintRepo.saveSprint(sprint2)).thenReturn(true);

        SprintDTO expected = SprintMapper.toDTO(sprint2);

        //Act
        SprintDTO sprintDTOResult = appServcCreateSprint.createSprint(startDateS2, projID);

        //Assert
        assertEquals(expected, sprintDTOResult);
    }

    @Test
    void findSprintByID() {

        //Arrange
        Project proj = mock(Project.class);
        List<Sprint> listSprint = new ArrayList<>();

        ProjectID projID = new ProjectID(1);
        PositiveNumber sprintDurantion = PositiveNumber.of(2);
        LocalDate start = LocalDate.of(2022, 10, 25);

        //Sprint1:
        Sprint sprint = mock(Sprint.class);
//        when(sprint.getProjectID()).thenReturn(1);
//        when(sprint.getSprintNumber()).thenReturn(1);
//        when(sprint.getStartDate()).thenReturn(start);
//        when(sprint.getSprintDuration()).thenReturn(sprintDurantion);
//        when(sprint.getSprintIDDouble()).thenReturn(1.001);

        listSprint.add(sprint);


        //Sprint2
        Sprint sprint2 = mock(Sprint.class);
        SprintID s2Id = new SprintID(projID, PositiveNumber.of(2));
        LocalDate startDateS2 = LocalDate.of(2022, 11, 25);
//        when(sprint.getProjectID()).thenReturn(1);
//        when(sprint.getSprintNumber()).thenReturn(2);
//        when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 11, 25));
//        when(sprint.getSprintDuration()).thenReturn(sprintDurantion);
//        when(sprint.getSprintIDDouble()).thenReturn(1.002);

//        when(projRepo.findById(projID)).thenReturn(Optional.of(proj));
//        when(sprintRepo.findAllSprintsByProjectID(projID)).thenReturn(listSprint);
//        when(servcCreateSprint.generateSprintNumber(listSprint)).thenReturn(PositiveNumber.of(2));
//        when(servcCreateSprint.generateSprintID(PositiveNumber.of(2), projID)).thenReturn(s2Id);
//        when(servcCreateSprint.startDateAfterLastSprint(listSprint, startDateS2)).thenReturn(true);
//        when(proj.createSprint(s2Id, startDateS2, PositiveNumber.of(2))).thenReturn(sprint2);
//        when(sprintRepo.saveSprint(sprint2)).thenReturn(true);

//        when(sprint2.hasID(s2Id)).thenReturn(true);
        when(sprintRepo.findByID(s2Id)).thenReturn(Optional.of(sprint2));


        //Act
        Optional<Sprint> foundSprint = appServcCreateSprint.findSprintByID(s2Id);

        //Assert
        assertEquals(Optional.of(sprint2), foundSprint);


    }

    @Test
    void setNewStartDate() {
        //Arrange
        LocalDate startDate = LocalDate.now();
        int projectID = 1;
        int spt1Number = 1;
        int projectDuration = 14;
        Period period = Period.between(startDate, startDate.plusDays(projectDuration));
        ProjectID projID = new ProjectID(projectID);
        Project project = mock(Project.class);
        when(project.getSprintDurationAsInt()).thenReturn(projectDuration);
        when(projRepo.findById(projID)).thenReturn(Optional.of(project));
        when(project.containsPeriod(period)).thenReturn(true);
        SprintID sptID = SprintID.ofDouble(1.001);
        Sprint sprint1 = mock(Sprint.class);
        Sprint sprint2 = mock(Sprint.class);
        when(sprintRepo.findByID(sptID)).thenReturn(Optional.of(sprint1));
        List<Sprint> listSprint = new ArrayList<>();
        listSprint.add(sprint1);
        listSprint.add(sprint2);
        when(sprintRepo.findAllSprintsByProjectID(projID)).thenReturn(listSprint);
        List<Sprint> sprintsToBeSafe = new ArrayList<>();
        sprintsToBeSafe.add(sprint2);
        when(servcCreateSprint.setStartDateInsubsequentSprints(listSprint, sprint1, startDate)).thenReturn(sprintsToBeSafe);

        when(sprint1.getSprintIDDouble()).thenReturn(sptID.getSprintNumber());
        when(sprint1.getStartDate()).thenReturn(startDate);
        when(sprint1.getProjectID()).thenReturn(projectID);
        when(sprint1.getSprintNumber()).thenReturn(spt1Number);
        when(sprint1.getSprintDuration()).thenReturn(PositiveNumber.of(2));

        SprintBacklogItem sprintBacklogItem = mock(SprintBacklogItem.class);
        List<SprintBacklogItem> itemList = new ArrayList<>();
        when(sprint1.getSprintBacklogItems()).thenReturn(itemList);

        SprintDTO answer = new SprintDTO();
        answer.sprintID = sptID.getSprintNumber();
        answer.startDate = startDate;
        answer.projectID = projectID;
        answer.sprintNumber = spt1Number;
        answer.sprintDuration = 2;
        answer.sprintBacklogItemDTOList = new ArrayList<>();
        Optional<SprintDTO> expected = Optional.of(answer);

        //Act
        Optional<SprintDTO> result = appServcCreateSprint.setNewStartDate(startDate, projID, spt1Number);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getAllSprintsByProjectID() {
        //Arrange
        int id = 76;
        ProjectID projectID = new ProjectID(id);

        Sprint sprintOne = mock(Sprint.class);
        Sprint sprintTwo = mock(Sprint.class);
        Sprint sprintThree = mock(Sprint.class);
        List<Sprint> sprintList = new ArrayList<>();
        sprintList.add(sprintOne);
        sprintList.add(sprintTwo);
        sprintList.add(sprintThree);
        when(sprintRepo.findAllSprintsByProjectID(projectID)).thenReturn(sprintList);
        List<SprintTableDTO> sprintDTOS = new ArrayList<>();
        SprintTableDTO sprintOneDTOTable = SprintTableMapper.tableMapperToDTO(sprintOne);
        SprintTableDTO sprintTwoDTOTable = SprintTableMapper.tableMapperToDTO(sprintTwo);
        SprintTableDTO sprintThreeDTOTable = SprintTableMapper.tableMapperToDTO(sprintThree);


        sprintDTOS.add(sprintOneDTOTable);
        sprintDTOS.add(sprintTwoDTOTable);
        sprintDTOS.add(sprintThreeDTOTable);

        List<SprintTableDTO> result = appServcCreateSprint.getAllSprintsByProjectID(id);

        assertEquals(sprintDTOS, result);
    }

    @Test
    void ensureItIsNotPossibleToCreateASprintDuringOrBeforeAnotherSprint() {
        //Arrange
        Project proj = mock(Project.class);
        List<Sprint> listSprint = new ArrayList<>();

        ProjectID projID = new ProjectID(1);


        //Sprint1:
        Sprint sprint = mock(Sprint.class);
        listSprint.add(sprint);
        SprintID s2Id = new SprintID(projID, PositiveNumber.of(2));
        LocalDate startDateS2 = LocalDate.of(2022, 11, 25);


        when(projRepo.findById(projID)).thenReturn(Optional.of(proj));
        when(sprintRepo.findAllSprintsByProjectID(projID)).thenReturn(listSprint);
        when(servcCreateSprint.generateSprintNumber(listSprint)).thenReturn(PositiveNumber.of(2));
        when(servcCreateSprint.generateSprintID(PositiveNumber.of(2), projID)).thenReturn(s2Id);

        assertThrows(IllegalArgumentException.class, () -> appServcCreateSprint.createSprint(startDateS2, projID));

    }
}

