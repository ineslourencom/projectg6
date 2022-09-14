package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.dto.ProjectInfoDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceProjectTest {

    @Mock
    ProjectRepository projRepo;
    @InjectMocks
    ApplicationServiceProject appService;

    //TODO mapper is not being isolated
    @Test
    void getProjectDetails() {

        ProjectID id = new ProjectID(1);
        Project project = mock(Project.class);
        when(projRepo.findById(id)).thenReturn(Optional.of(project));

        ProjectInfoDTO dto = new ProjectInfoDTO();
        int code = 1;
        String description = "Desc";
        LocalDate startDate = LocalDate.of(2020, 7, 19);
        LocalDate endDate = LocalDate.of(2022, 3, 9);
        int plannedSprints = 10;
        int sprintDuration = 2;
        String status = "planned";

        dto.code = code;
        dto.description = description;
        dto.startDate = startDate;
        dto.endDate = endDate;
        dto.plannedSprints = plannedSprints;
        dto.sprintDuration = sprintDuration;
        dto.status = status;
        Optional<ProjectInfoDTO> expected = Optional.of(dto);

        when(project.getIDAsInt()).thenReturn(code);
        when(project.getDescriptionAsString()).thenReturn(description);
        when(project.getStartDate()).thenReturn(startDate);
        when(project.getEndDate()).thenReturn(endDate);
        when(project.getPlannedSprintsAsInt()).thenReturn(plannedSprints);
        when(project.getSprintDurationAsInt()).thenReturn(sprintDuration);
        when(project.getStatusAsString()).thenReturn(status);

        Optional<ProjectInfoDTO> actual = appService.getProjectDetails(code);

        assertEquals(expected, actual);

    }

    @Test
    void getProjectDetailsProjectNotFound() {
        ProjectID id = new ProjectID(1);
        when(projRepo.findById(id)).thenReturn(Optional.empty());
        Optional<ProjectInfoDTO> expected = Optional.empty();
        Optional<ProjectInfoDTO> result = appService.getProjectDetails(1);

        assertEquals(expected, result);

    }

    @Test
    void editProject() {
        ProjectID id = new ProjectID(1);
        Project project = mock(Project.class);
        when(projRepo.findById(id)).thenReturn(Optional.of(project));
        ProjectInfoDTO expectedDTO = new ProjectInfoDTO();
        int code = 1;
        String description = "Desc";
        LocalDate startDate = LocalDate.of(2020, 7, 19);
        LocalDate endDate = LocalDate.of(2022, 3, 9);
        int plannedSprints = 10;
        int sprintDuration = 2;
        String status = "planned";

        Optional<ProjectInfoDTO> expected = Optional.of(expectedDTO);
        ProjectInfoDTO dto = new ProjectInfoDTO();
        dto.code = code;
        dto.status = status;
        dto.startDate = startDate;
        dto.endDate = endDate;
        dto.description = description;
        dto.sprintDuration = sprintDuration;
        dto.plannedSprints = plannedSprints;

        expectedDTO.code = code;
        expectedDTO.description = description;
        expectedDTO.startDate = startDate;
        expectedDTO.endDate = endDate;
        expectedDTO.plannedSprints = plannedSprints;
        expectedDTO.sprintDuration = sprintDuration;
        expectedDTO.status = status;

        when(project.getIDAsInt()).thenReturn(code);
        when(project.getDescriptionAsString()).thenReturn(description);
        when(project.getStartDate()).thenReturn(startDate);
        when(project.getEndDate()).thenReturn(endDate);
        when(project.getPlannedSprintsAsInt()).thenReturn(plannedSprints);
        when(project.getSprintDurationAsInt()).thenReturn(sprintDuration);
        when(project.getStatusAsString()).thenReturn(status);
        when(projRepo.save(project)).thenReturn(project);

        Optional<ProjectInfoDTO> actual = appService.editProject(code, dto);
        assertEquals(expected, actual);

    }

    @Test
    void editProjectProjectNotFound() {

        ProjectID id = new ProjectID(1);
        when(projRepo.findById(id)).thenReturn(Optional.empty());
        ProjectInfoDTO dto = mock(ProjectInfoDTO.class);
        Optional<ProjectInfoDTO> expected = Optional.empty();

        Optional<ProjectInfoDTO> result = appService.editProject(1, dto);
        assertEquals(expected, result);


    }
}