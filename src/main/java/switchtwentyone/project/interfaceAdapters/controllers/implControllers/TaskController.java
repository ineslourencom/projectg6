package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateTask;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetAllTaskStatus;
import switchtwentyone.project.domain.aggregates.task.Task;
import switchtwentyone.project.dto.TaskCreationDTO;
import switchtwentyone.project.dto.TaskCreationReturnedDTO;
import switchtwentyone.project.dto.TaskStatusDTO;
import switchtwentyone.project.dto.mapper.TaskMapper;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@CrossOrigin
@Controller
@RestController
public class TaskController {

    @Autowired
    ApplicationServiceCreateTask applicationServiceCreateTask;

    @Autowired
    private ApplicationServiceGetAllTaskStatus appServGetAllTaskStatus;

    @PostMapping(value = "/project/{id}/tasks")
    public ResponseEntity<Object> createTask(@PathVariable int id, @RequestBody TaskCreationDTO dto) {
        try {
            TaskCreationReturnedDTO taskCreated = applicationServiceCreateTask.createTask(dto, id);

            Link link = linkTo(methodOn(TaskController.class).getTaskInfo(taskCreated.taskID)).withSelfRel();
            taskCreated.add(link);

            return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> getTaskInfo(@PathVariable int id) {
        TaskCreationReturnedDTO taskCreationReturnedDTO = null;
        return new ResponseEntity<>(taskCreationReturnedDTO, HttpStatus.OK);
    }


    @GetMapping("/projects/{id}/tasks")
    public ResponseEntity<Object> viewTaskSatusFromProject(@PathVariable int id){
        ResponseEntity<Object> response;
        try{
            List<TaskStatusDTO> result = appServGetAllTaskStatus.getTaskStatusFromProject(id);

            if(result.isEmpty()){
                response = new ResponseEntity<>("No Tasks Found", HttpStatus.OK);
            } else{
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        }catch (Exception e){
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            response = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable String id){
        Optional<Task> optionalTask = appServGetAllTaskStatus.findTaskByID(id);
        BusinessErrorMessage msg = new BusinessErrorMessage( "Task Not Found", BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            if(optionalTask.isPresent()){
                Task task = optionalTask.get();
                TaskStatusDTO taskDto = TaskMapper.toDTOForStatus(task);
                return new ResponseEntity<>(taskDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(msg, HttpStatus.OK);
            }
    }
}
