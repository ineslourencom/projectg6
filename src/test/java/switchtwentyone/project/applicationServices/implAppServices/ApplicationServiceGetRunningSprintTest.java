package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.dto.SprintInfoForTaskDTO;
import switchtwentyone.project.dto.mapper.SprintMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.SprintController;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceGetRunningSprintTest {

    @Mock
    switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository sprintRepository;
    @Mock
    switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository projectRepository;

    @InjectMocks
    ApplicationServiceGetRunningSprint applicationServiceGetRunningSprint;

    @Test
    void getRunningSprint() {
        int projID = 1;

        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        when(sprint.getSprintIDDouble()).thenReturn(1.001);
        when(sprint.getSprintNumber()).thenReturn(1);
        when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 9, 25));
        SprintInfoForTaskDTO sprintInfoForTaskDTO = SprintMapper.toDTOForTask(sprint);

        Link link = linkTo(methodOn(SprintController.class).getSprintById(sprintInfoForTaskDTO.sprintID)).withSelfRel();
        sprintInfoForTaskDTO.add(link);

        SprintInfoForTaskDTO result = applicationServiceGetRunningSprint.getRunningSprint(projID);

        assertEquals(sprintInfoForTaskDTO, result);

    }



    @Test
    void getRunningSprint_ProjectDoesntExist() {
        int projID = 1;

        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceGetRunningSprint.getRunningSprint(projID));

    }

    @Test
    void testGetRunningSprint_NoRunning_Sprint() {

        int projID = 1;

        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.empty());


        SprintInfoForTaskDTO sprintInfoForTaskDTO = null;

        SprintInfoForTaskDTO result = applicationServiceGetRunningSprint.getRunningSprint(projID);

        assertEquals(sprintInfoForTaskDTO, result);
    }
}