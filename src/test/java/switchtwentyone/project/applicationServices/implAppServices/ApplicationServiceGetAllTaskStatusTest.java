package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.TaskRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.aggregates.task.ContainerID;
import switchtwentyone.project.domain.aggregates.task.Task;
import switchtwentyone.project.domain.aggregates.task.TaskID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.dto.TaskStatusDTO;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ProjectController;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.TaskController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@SpringBootTest
class ApplicationServiceGetAllTaskStatusTest {

    @Autowired
    ApplicationServiceGetAllTaskStatus appService;

    @MockBean
    TaskRepository taskRepository;

    @MockBean
    UserStoryRepository userStoryRepository;

    @MockBean
    SprintRepository sprintRepository;

    @Test
    void viewTaskStatusFromProject() {

        int projID = 1;
        ProjectID projectID =  new ProjectID(projID);

        UserStory us1 = mock(UserStory.class);
        UserStoryID usID1 = UserStoryID.ofDouble(1.001);
        when(us1.getUsID()).thenReturn(usID1);
        List<UserStory> listOfUS = new ArrayList<>();
        listOfUS.add(us1);

        Sprint sprint1 = mock(Sprint.class);
        SprintID sprintID1 = SprintID.ofDouble(1.001);
        when(sprint1.getSprintID()).thenReturn(sprintID1);
        List<Sprint> listOfSprints = new ArrayList<>();
        listOfSprints.add(sprint1);

        when(userStoryRepository.findAllUSByProjectID(projectID)).thenReturn(listOfUS);
        when(sprintRepository.findAllSprintsByProjectID(projectID)).thenReturn(listOfSprints);

        List<String> listContainerID = new ArrayList<>();
        listContainerID.add("Sp-1.001");
        listContainerID.add("US-1.001");

        Task task1 = mock(Task.class);
        TaskID taskId1 = TaskID.createTaskID("890af6af-2167-4e14-bb9f-d6c0a10b60f3");
        when(task1.getTaskIdentityNumber()).thenReturn(taskId1);
        when(task1.getTaskNameAsString()).thenReturn("Task One");
        when(task1.findBelongsTo()).thenReturn("Sprint");
        ContainerID contID1 =  mock(ContainerID.class);
        when(contID1.getIDForTask()).thenReturn("Sp-1.001");
        when(task1.getContainerID()).thenReturn(contID1);
        when(task1.getTaskStatusAsString()).thenReturn("Planned");
        when(task1.getPercentageOfExecution()).thenReturn(80.0);

        Task task2 = mock(Task.class);
        TaskID taskId2 = TaskID.createTaskID("700af6af-2167-4e14-bb9f-d6c0a10b60f3");
        when(task2.getTaskIdentityNumber()).thenReturn(taskId2);
        when(task2.getTaskNameAsString()).thenReturn("Task Two");
        when(task2.findBelongsTo()).thenReturn("User Story");
        ContainerID contID2 =  mock(ContainerID.class);
        when(contID2.getIDForTask()).thenReturn("Us-1.001");
        when(task2.getContainerID()).thenReturn(contID2);
        when(task2.getTaskStatusAsString()).thenReturn("Planned");
        when(task2.getPercentageOfExecution()).thenReturn(70.0);

        List<String> listID = new ArrayList<>();
        listID.add(contID1.getIDForTask());
        listID.add(contID2.getIDForTask());

        List <Task> listOfTasksForEachId = new ArrayList<>();
        listOfTasksForEachId.add(task1);
        listOfTasksForEachId.add(task2);
        when(taskRepository.findAllByContainerID("Us-1.001")).thenReturn(listOfTasksForEachId);
        when(taskRepository.findAllByContainerID("Sp-1.001")).thenReturn(listOfTasksForEachId);

        TaskStatusDTO dto1 = new TaskStatusDTO();
        dto1.taskNumber = "890af6af-2167-4e14-bb9f-d6c0a10b60f3";
        dto1.taskName = "Task One";
        dto1.belongsTo = "Sprint";
        dto1.containerId = "Sp-1.001";
        dto1.status = "Planned";
        dto1.percOfExec = 80.0;
        dto1.add(linkTo(methodOn(ProjectController.class).getProjectDetails(projID)).withSelfRel());
        dto1.add(linkTo(methodOn(TaskController.class).getTaskById(dto1.taskNumber)).withSelfRel());


        TaskStatusDTO dto2 = new TaskStatusDTO();
        dto2.taskNumber = "700af6af-2167-4e14-bb9f-d6c0a10b60f3";
        dto2.taskName = "Task One";
        dto2.belongsTo = "User Story";
        dto2.containerId ="US 1.001";
        dto2.status = "Planned";
        dto2.percOfExec = 70.0;
        dto2.add(linkTo(methodOn(ProjectController.class).getProjectDetails(projID)).withSelfRel());
        dto2.add(linkTo(methodOn(TaskController.class).getTaskById(dto2.taskNumber)).withSelfRel());


        List<TaskStatusDTO> expected = new ArrayList<>();
        expected.add(dto1);
        expected.add(dto2);


        List<TaskStatusDTO> result = appService.getTaskStatusFromProject(1);

        assertEquals(expected, result);

    }

    @Test
    void viewTaskStatusFromProject_empty() {

        int projID = 1;
        ProjectID projectID =  new ProjectID(projID);

        UserStory us1 = mock(UserStory.class);
        UserStoryID usID1 = UserStoryID.ofDouble(1.001);
        when(us1.getUsID()).thenReturn(usID1);
        List<UserStory> listOfUS = new ArrayList<>();
        listOfUS.add(us1);

        Sprint sprint1 = mock(Sprint.class);
        SprintID sprintID1 = SprintID.ofDouble(1.001);
        when(sprint1.getSprintID()).thenReturn(sprintID1);
        List<Sprint> listOfSprints = new ArrayList<>();
        listOfSprints.add(sprint1);

        when(userStoryRepository.findAllUSByProjectID(projectID)).thenReturn(listOfUS);
        when(sprintRepository.findAllSprintsByProjectID(projectID)).thenReturn(listOfSprints);

        List<TaskStatusDTO> expected = new ArrayList<>();

        List<TaskStatusDTO> result = appService.getTaskStatusFromProject(1);

        assertEquals(expected, result);


    }






}


