package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.task.*;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceValidatePeriodWhenCreatingTask;
import switchtwentyone.project.domain.services.ServiceValidateUserStoryWhenCreatingTask;
import switchtwentyone.project.domain.services.ServiceFindUS;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.TaskCreationDTO;
import switchtwentyone.project.dto.TaskCreationReturnedDTO;
import switchtwentyone.project.dto.mapper.TaskMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ApplicationServiceCreateTask {
    @Autowired
    switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository projectRepository;
    @Autowired
    switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository sprintRepository;
    @Autowired
    ServiceValidateUserStoryWhenCreatingTask serviceValidateUserStoryWhenCreatingTask;
    @Autowired
    switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository userStoryRepository;
    @Autowired
    ServiceFindUS serviceFindUS;
    @Autowired
    switchtwentyone.project.applicationServices.irepositories.irepositories.ResourceRepository resourceRepository;
    @Autowired
    switchtwentyone.project.applicationServices.irepositories.irepositories.TaskRepository taskRepository;
    @Autowired
    TaskFactory taskFactory;
    @Autowired
    ServiceValidatePeriodWhenCreatingTask serviceValidatePeriodWhenCreatingTask;

    public TaskCreationReturnedDTO createTask(TaskCreationDTO taskCreationDTO, int projID) {

        ProjectID projectID = new ProjectID(projID);
        if (!projectRepository.existsById(projectID)) {
            throw new IllegalArgumentException("Project doesn't exist");
        }

        Optional<Sprint> runningSprint = sprintRepository.findRunningSprintByProjectID(projectID);
        if (runningSprint.isEmpty()) {
            throw new IllegalArgumentException("There is no running Sprint. You can only create userStories for the running sprint or " +
                    "it's user stories");
        }

        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);

        Text description = Text.write(taskCreationDTO.description);

        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        Period period = periodHandler(taskCreationDTO.startDate, taskCreationDTO.endDate, runningSprint.get());

        ContainerID containerID = containerIDHandler(taskCreationDTO.option, runningSprint.get(), taskCreationDTO.containerID);

        FibonacciNumber effortEstimate = null;
        if (taskCreationDTO.effortEstimate != null) {
            effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));
        }

        ResourceID resourceID = resourIDHandler(taskCreationDTO.resourceID, projectID);

        TaskID taskid = TaskID.generate();

        Task taskCreated = taskFactory.createTask(taskid, name, description, period, effortEstimate, taskType, containerID, resourceID);

        Task taskSaved = taskRepository.saveTask(taskCreated);

        return TaskMapper.toDTOReturnedUponCreation(taskSaved);
    }

    private Period periodHandler(LocalDate startDate, LocalDate endDate, Sprint runningSprint) {
        Period period = null;
        if (startDate != null && endDate == null) {
            period = Period.starting(startDate);
        } else if (startDate != null) {
            period = Period.between(startDate, endDate);
        }
        if (!serviceValidatePeriodWhenCreatingTask.validateDates(runningSprint, period)) {
            throw new IllegalArgumentException("Task dates are not between sprint Dates!");
        }
        return period;
    }

    private ContainerID containerIDHandler(String option, Sprint runningSprint, double container) {
        ContainerID containerID = null;
        if (Objects.equals(option, "Sprint")) {
            containerID = runningSprint.getSprintID();
        } else {
            UserStoryID userStoryID = UserStoryID.ofDouble(container);
            Optional<UserStory> userStory = userStoryRepository.findByID(userStoryID);
            if (userStory.isPresent()) {
                List<Sprint> runningSprintInList = new ArrayList<>();
                runningSprintInList.add(runningSprint);
                Optional<Sprint> optionalRunningSprint = Optional.of(runningSprint);
                List<UserStoryID> userStoriesIDInRunningSprint = serviceFindUS.findUSIDinSprint(optionalRunningSprint);
                List<UserStoryID> userStoriesIDDone = serviceFindUS.findDoneUSIDinSprint(runningSprintInList);
                List<UserStory> userStoriesInRunningSprint = createListOfUSFromListOfUSID(userStoriesIDInRunningSprint);
                List<UserStory> userStoriesDone = createListOfUSFromListOfUSID(userStoriesIDDone);
                if (serviceValidateUserStoryWhenCreatingTask.validateUserStory(userStoriesInRunningSprint, userStoriesDone, userStory.get())) {
                    containerID = userStoryID;
                } else {
                    throw new IllegalArgumentException("UserStory is not in running sprint or userStory is already done!");
                }
            } else {
                throw new IllegalArgumentException("UserStory does not exist");
            }
        }
        return containerID;
    }


    private ResourceID resourIDHandler(String resource, ProjectID projectID) {
        ResourceID resourceID = null;
        if (resource != null) {
            resourceID = ResourceID.of(Integer.parseInt(resource));
            Optional<Resource> resourceExists = resourceRepository.findResourceByID(resourceID);
            if (resourceExists.isPresent()) {
                List<Resource> listOfActiveResources = resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now());
                if (!listOfActiveResources.contains(resourceExists.get())) {
                    throw new IllegalArgumentException("Resource is not active in this project");
                }
            } else {
                throw new IllegalArgumentException("Resource does not exist");
            }
        }
        return resourceID;
    }

    private List<UserStory> createListOfUSFromListOfUSID(List<UserStoryID> userStoryIDs) {
        List<UserStory> userStoryList = new ArrayList<>();
        for (UserStoryID usID : userStoryIDs) {
            Optional<UserStory> usOpt = userStoryRepository.findByID(usID);
            usOpt.ifPresent(userStoryList::add);
        }
        return userStoryList;
    }

}
