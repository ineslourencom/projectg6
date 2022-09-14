package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.TaskRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.task.Task;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.dto.TaskStatusDTO;
import switchtwentyone.project.dto.mapper.TaskMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ProjectController;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.TaskController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceGetAllTaskStatus {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserStoryRepository userStoryRepository;

    @Autowired
    SprintRepository sprintRepository;


    public List<TaskStatusDTO> getTaskStatusFromProject(int projID){

        ProjectID projectID = new ProjectID(projID);
        List<UserStory> listOfUS = userStoryRepository.findAllUSByProjectID(projectID);
        List<Sprint> listOfSprints = sprintRepository.findAllSprintsByProjectID(projectID);

        List<String> listContainerID = new ArrayList<>();
        List<Task> listAllTasks = new ArrayList<>();
        for (UserStory us: listOfUS) {
            String id = us.getUsID().getIDForTask();
            listContainerID.add(id);
        }

        for (Sprint sprint :listOfSprints) {
            String id = sprint.getSprintID().getIDForTask();
            listContainerID.add(id);
        }

        for (String id:listContainerID) {
            List<Task> listOfTasksForEachId = taskRepository.findAllByContainerID(id);
            listAllTasks.addAll(listOfTasksForEachId);
        }

        List<TaskStatusDTO> taskStatusDTOList = new ArrayList<>();
        for (Task task: listAllTasks) {
            taskStatusDTOList.add(TaskMapper.toDTOForStatus(task));
        }

        taskStatusDTOList.forEach(taskStatusDTO -> {
            String taskNumber = taskStatusDTO.taskNumber;
            taskStatusDTO.add(linkTo(methodOn(ProjectController.class).getProjectDetails(projID)).withSelfRel());
            taskStatusDTO.add(linkTo(methodOn(TaskController.class).getTaskById(taskNumber)).withSelfRel());
        });

        return taskStatusDTOList;

    }

    public Optional<Task> findTaskByID(String taskID){

        return taskRepository.findTaskByID(taskID);
    }




}


