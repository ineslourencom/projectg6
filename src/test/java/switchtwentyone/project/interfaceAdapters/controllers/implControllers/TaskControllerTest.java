package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateTask;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetAllTaskStatus;
import switchtwentyone.project.domain.aggregates.task.*;
import switchtwentyone.project.dto.TaskCreationDTO;
import switchtwentyone.project.dto.TaskCreationReturnedDTO;
import switchtwentyone.project.dto.TaskStatusDTO;
import switchtwentyone.project.dto.mapper.TaskMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    ApplicationServiceCreateTask applicationServiceCreateTask;

    @Mock
    private ApplicationServiceGetAllTaskStatus appServGetAllTaskStatus;

    @InjectMocks
    TaskController taskController;

    @Test
    void createTask_Success() {
        int id = 1;
        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        TaskCreationReturnedDTO taskCreationReturnedDTO = mock(TaskCreationReturnedDTO.class);
        taskCreationReturnedDTO.taskID =1;
        Link link = linkTo(methodOn(TaskController.class).getTaskInfo(taskCreationReturnedDTO.taskID)).withSelfRel();
        taskCreationReturnedDTO.add(link);
        when(applicationServiceCreateTask.createTask(taskCreationDTO,id)).thenReturn(taskCreationReturnedDTO);
        ResponseEntity expected = new ResponseEntity<>(taskCreationReturnedDTO, HttpStatus.CREATED);

        ResponseEntity result = taskController.createTask(id, taskCreationDTO);

        assertEquals(expected, result);
    }

    @Test
    void createTask_Unsuccess() {
        int id = 1;
        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        when(applicationServiceCreateTask.createTask(taskCreationDTO,id)).thenThrow(new IllegalArgumentException("Bye, bye"));
        BusinessErrorMessage msg = new BusinessErrorMessage("Bye, bye", BusinessErrorMessage.UNPROCESSABLE_ENTITY);


        ResponseEntity expected = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity result = taskController.createTask(id, taskCreationDTO);

        assertEquals(expected, result);
    }


    @Test
    void getTaskInfo(){
        TaskCreationReturnedDTO dto1 = null;
        int id = 1;
        ResponseEntity expected = new ResponseEntity<>(dto1, HttpStatus.OK);

        ResponseEntity result =  taskController.getTaskInfo(1);

        assertEquals(expected, result);
    }


    @Test
    void viewTaskSatusFromProject_withTask() {
        TaskStatusDTO dto1 = mock(TaskStatusDTO.class);
        TaskStatusDTO dto2 = mock(TaskStatusDTO.class);

        List<TaskStatusDTO> taskStatusDTOList = new ArrayList<>();
        taskStatusDTOList.add(dto1);
        taskStatusDTOList.add(dto2);

        ResponseEntity expected = new ResponseEntity<>(taskStatusDTOList, HttpStatus.OK);
        when(appServGetAllTaskStatus.getTaskStatusFromProject(1)).thenReturn(taskStatusDTOList);

        ResponseEntity result =  taskController.viewTaskSatusFromProject(1);


        assertEquals(expected, result);

    }

    @Test
    void viewTaskSatusFromProject_withoutTask() {
        List<TaskStatusDTO> taskStatusDTOList = new ArrayList<>();


        ResponseEntity expected = new ResponseEntity<>("No Tasks Found", HttpStatus.OK);
        when(appServGetAllTaskStatus.getTaskStatusFromProject(1)).thenReturn(taskStatusDTOList);

        ResponseEntity result =  taskController.viewTaskSatusFromProject(1);


        assertEquals(expected, result);

    }

    @Test
    void viewTaskStatusFromProject_BadRequest(){
        String message = "No Project was found";
        when(appServGetAllTaskStatus.getTaskStatusFromProject(2)).thenThrow(new IllegalArgumentException(message));
        BusinessErrorMessage msg = new BusinessErrorMessage(message, BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        ResponseEntity<Object> result = taskController.viewTaskSatusFromProject(2);

        assertEquals(expected.getStatusCode(), result.getStatusCode());

    }



    @Test
    void getTaskByID_withTask(){
        //Arrange
        Task task = mock(Task.class);
        Optional<Task> optTask = Optional.of(task);
        TaskID id = TaskID.createTaskID("50f8f590-201a-495a-973e-0a6601222591");
        when(appServGetAllTaskStatus.findTaskByID("50f8f590-201a-495a-973e-0a6601222591")).thenReturn(optTask);
        when(task.getTaskIdentityNumber()).thenReturn(id);
        when(task.getTaskNameAsString()).thenReturn("taskOne");
        when(task.findBelongsTo()).thenReturn("Sprint");

        ContainerID containerID = mock(ContainerID.class);
        when(task.getContainerID()).thenReturn(containerID);

        when(task.getTaskStatusAsString()).thenReturn("To do");
        when(task.getPercentageOfExecution()).thenReturn(80.00);


        TaskStatusDTO taskDTO = TaskMapper.toDTOForStatus(task);
        ResponseEntity expected = new ResponseEntity<>( taskDTO, HttpStatus.OK );

        //Act
        ResponseEntity result =  taskController.getTaskById("50f8f590-201a-495a-973e-0a6601222591");
        //Assert
        assertEquals(expected, result);


    }
    @Test
    void getTaskByID_withoutTask(){
        //Arrange
        BusinessErrorMessage msg = new BusinessErrorMessage( "Task Not Found", BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        Task task = mock(Task.class);
        Optional<Task> optTask = Optional.empty();
        when(appServGetAllTaskStatus.findTaskByID("50f8f590-201a-495a-973e-0a6601222591")).thenReturn(optTask);

        ResponseEntity expected = new ResponseEntity<>(msg, HttpStatus.OK );

        //Act
        ResponseEntity result =  taskController.getTaskById("50f8f590-201a-495a-973e-0a6601222591");
        //Assert
        assertEquals(expected, result);

    }


}