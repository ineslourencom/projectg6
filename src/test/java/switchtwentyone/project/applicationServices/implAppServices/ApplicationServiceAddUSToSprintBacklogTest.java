package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.*;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.dto.AddUSToSprintInfoDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.SprintRepositoryImpl;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ApplicationServiceAddUSToSprintBacklogTest {

    @Mock
    SprintRepositoryImpl sprintRepo;

    @Mock
    ProjectRepository projRepo;

    @InjectMocks
    ApplicationServiceAddUSToSprintBacklog appServcAddUS;

    @Test
    void addUSToSprintBacklogSuccess() {
        ProjectID projectID = new ProjectID(1);
        Project project = mock(Project.class);
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effort = FibonacciNumber.of(1);
        AddUSToSprintInfoDTO info = mock(AddUSToSprintInfoDTO.class);
        when(info.getEffort()).thenReturn(effort.getNumber());
        when(info.getUsID()).thenReturn(usID.getID());
        Sprint sprint = mock(Sprint.class);
        Optional<Sprint> opSprint = Optional.of(sprint);
        SprintBacklogItem item = mock(SprintBacklogItem.class);
        Optional<Project> opProject = Optional.of(project);

        when(projRepo.findById(projectID)).thenReturn(opProject);
        when(sprintRepo.findRunningSprintByProjectID(projectID)).thenReturn(opSprint);
        when(sprint.addUSToSprintBacklog(usID, effort)).thenReturn(item);

        //Act
        boolean result = appServcAddUS.addUSToSprintBacklog(1, info);

        //Assert
        assertTrue(result);

    }

    @Test
    void addUSToSprintBacklogSprintNotRunning() {
        ProjectID projectID = new ProjectID(1);
        Project project = mock(Project.class);
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        FibonacciNumber effort = FibonacciNumber.of(1);
        AddUSToSprintInfoDTO info = mock(AddUSToSprintInfoDTO.class);
        when(info.getEffort()).thenReturn(effort.getNumber());
        when(info.getUsID()).thenReturn(usID.getID());
        Sprint sprint = mock(Sprint.class);
        Optional<Sprint> opSprint = Optional.of(sprint);
        SprintBacklogItem item = mock(SprintBacklogItem.class);
        Optional<Project> opProject = Optional.of(project);

        when(projRepo.findById(projectID)).thenReturn(opProject);
        when(sprintRepo.findRunningSprintByProjectID(projectID)).thenThrow(new IllegalArgumentException("There isn't a sprint currently running."));

       assertThrows(IllegalArgumentException.class, () -> appServcAddUS.addUSToSprintBacklog(1, info));

    }

    @Test
    void addUSToSprintBacklogSprintProjectNotFound() {
        AddUSToSprintInfoDTO info = mock(AddUSToSprintInfoDTO.class);

        assertThrows(EntityNotFoundException.class, () -> appServcAddUS.addUSToSprintBacklog(1, info));

    }



}