package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.aggregates.task.*;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceValidatePeriodWhenCreatingTask;
import switchtwentyone.project.domain.services.ServiceValidateUserStoryWhenCreatingTask;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.TaskCreationDTO;
import switchtwentyone.project.dto.TaskCreationReturnedDTO;
import switchtwentyone.project.dto.mapper.TaskMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApplicationServiceCreateTaskTest {
    @Autowired
    ApplicationServiceCreateTask applicationServiceCreateTask;
    @MockBean
    switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository projectRepository;
    @MockBean
    switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository sprintRepository;
    @MockBean
    ServiceValidateUserStoryWhenCreatingTask serviceValidateUserStoryWhenCreatingTask;
    @MockBean
    switchtwentyone.project.applicationServices.irepositories.irepositories.TaskRepository taskRepository;
    @MockBean
    TaskFactory taskFactory;
    @MockBean
    switchtwentyone.project.applicationServices.irepositories.irepositories.ResourceRepository resourceRepository;
    @MockBean
    switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository userStoryRepository;
    @MockBean
    ApplicationServiceViewProductBacklog applicationServiceViewProductBacklog;
    @MockBean
    ServiceValidatePeriodWhenCreatingTask serviceValidatePeriodWhenCreatingTask;

    @Test
    void createTask_Sprint() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);

        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        taskCreationDTO.endDate = LocalDate.of(2021, 1, 8);
        Period period = Period.between(taskCreationDTO.startDate, taskCreationDTO.endDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.option = "Sprint";
        SprintID sprintID = mock(SprintID.class);
        when(sprint.getSprintID()).thenReturn(sprintID);
        ContainerID containerID = sprintID;

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.of(resource));
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);
        activeResources.add(resource);

        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, containerID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        TaskCreationReturnedDTO result = applicationServiceCreateTask.createTask(taskCreationDTO, projID);

        assertEquals(expected, result);

        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_SprintDoesntExist() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.empty());

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);

        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        taskCreationDTO.endDate = LocalDate.of(2021, 1, 8);
        Period period = Period.between(taskCreationDTO.startDate, taskCreationDTO.endDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.option = "Sprint";
        SprintID sprintID = mock(SprintID.class);
        when(sprint.getSprintID()).thenReturn(sprintID);
        ContainerID containerID = sprintID;

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.of(resource));
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);

        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, containerID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateTask.createTask(taskCreationDTO, projID));


        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_SprintNotCompatibleWithPeriod() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);

        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        taskCreationDTO.endDate = LocalDate.of(2021, 1, 8);
        Period period = Period.between(taskCreationDTO.startDate, taskCreationDTO.endDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(false);

        taskCreationDTO.option = "Sprint";
        SprintID sprintID = mock(SprintID.class);
        when(sprint.getSprintID()).thenReturn(sprintID);
        ContainerID containerID = sprintID;

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.of(resource));
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);

        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, containerID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateTask.createTask(taskCreationDTO, projID));


        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_UserStory() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);


        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        taskCreationDTO.endDate = LocalDate.of(2021, 1, 8);
        Period period = Period.between(taskCreationDTO.startDate, taskCreationDTO.endDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.containerID = 001.001;
        taskCreationDTO.option = "User Story";
        UserStoryID userStoryID = UserStoryID.ofDouble(taskCreationDTO.containerID);
        UserStory userStory = mock(UserStory.class);
        when(userStoryRepository.findByID(userStoryID)).thenReturn(Optional.of(userStory));

        List<UserStoryID> userStoryIDInRunnigSprint = new ArrayList<>();
        List<UserStory> userStoryInRunningSprint = new ArrayList<>();
        List<UserStory> userStoryDone = new ArrayList<>();
        when(applicationServiceViewProductBacklog.findUSIDinRunningSprintByProjectID(projectID)).thenReturn(userStoryIDInRunnigSprint);
        when(applicationServiceViewProductBacklog.createListOfUSFromListOfUSID(userStoryIDInRunnigSprint)).thenReturn(userStoryInRunningSprint);
        when(applicationServiceViewProductBacklog.findDoneUSByProjectID(projectID)).thenReturn(userStoryDone);
        when(serviceValidateUserStoryWhenCreatingTask.validateUserStory(userStoryInRunningSprint, userStoryDone, userStory)).thenReturn(true);

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.of(resource));
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);
        activeResources.add(resource);
        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, userStoryID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        TaskCreationReturnedDTO result = applicationServiceCreateTask.createTask(taskCreationDTO, projID);

        assertEquals(expected, result);

        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_UserStoryDoesNotExist() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);


        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        taskCreationDTO.endDate = LocalDate.of(2021, 1, 8);
        Period period = Period.between(taskCreationDTO.startDate, taskCreationDTO.endDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.containerID = 001.001;
        taskCreationDTO.option = "User Story";
        UserStoryID userStoryID = UserStoryID.ofDouble(taskCreationDTO.containerID);
        UserStory userStory = mock(UserStory.class);
        when(userStoryRepository.findByID(userStoryID)).thenReturn(Optional.empty());

        List<UserStoryID> userStoryIDInRunnigSprint = new ArrayList<>();
        List<UserStory> userStoryInRunningSprint = new ArrayList<>();
        List<UserStory> userStoryDone = new ArrayList<>();
        when(applicationServiceViewProductBacklog.findUSIDinRunningSprintByProjectID(projectID)).thenReturn(userStoryIDInRunnigSprint);
        when(applicationServiceViewProductBacklog.createListOfUSFromListOfUSID(userStoryIDInRunnigSprint)).thenReturn(userStoryInRunningSprint);
        when(applicationServiceViewProductBacklog.findDoneUSByProjectID(projectID)).thenReturn(userStoryDone);
        when(serviceValidateUserStoryWhenCreatingTask.validateUserStory(userStoryInRunningSprint, userStoryDone, userStory)).thenReturn(true);

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.of(resource));
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);
        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, userStoryID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateTask.createTask(taskCreationDTO, projID));


        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_UserStoryIncompatible() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);


        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        taskCreationDTO.endDate = LocalDate.of(2021, 1, 8);
        Period period = Period.between(taskCreationDTO.startDate, taskCreationDTO.endDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.containerID = 001.001;
        taskCreationDTO.option = "User Story";
        UserStoryID userStoryID = UserStoryID.ofDouble(taskCreationDTO.containerID);
        UserStory userStory = mock(UserStory.class);
        when(userStoryRepository.findByID(userStoryID)).thenReturn(Optional.of(userStory));

        List<UserStoryID> userStoryIDInRunnigSprint = new ArrayList<>();
        List<UserStory> userStoryInRunningSprint = new ArrayList<>();
        List<UserStory> userStoryDone = new ArrayList<>();
        when(applicationServiceViewProductBacklog.findUSIDinRunningSprintByProjectID(projectID)).thenReturn(userStoryIDInRunnigSprint);
        when(applicationServiceViewProductBacklog.createListOfUSFromListOfUSID(userStoryIDInRunnigSprint)).thenReturn(userStoryInRunningSprint);
        when(applicationServiceViewProductBacklog.findDoneUSByProjectID(projectID)).thenReturn(userStoryDone);
        when(serviceValidateUserStoryWhenCreatingTask.validateUserStory(userStoryInRunningSprint, userStoryDone, userStory)).thenReturn(false);

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.of(resource));
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);

        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, userStoryID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateTask.createTask(taskCreationDTO, projID));


        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_ResourceDoesNotExist() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);

        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        taskCreationDTO.endDate = LocalDate.of(2021, 1, 8);
        Period period = Period.between(taskCreationDTO.startDate, taskCreationDTO.endDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.option = "Sprint";
        SprintID sprintID = mock(SprintID.class);
        when(sprint.getSprintID()).thenReturn(sprintID);
        ContainerID containerID = sprintID;

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.empty());
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);

        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, containerID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateTask.createTask(taskCreationDTO, projID));


        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_ResourceIsNotActive() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);

        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        taskCreationDTO.endDate = LocalDate.of(2021, 1, 8);
        Period period = Period.between(taskCreationDTO.startDate, taskCreationDTO.endDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.option = "Sprint";
        SprintID sprintID = mock(SprintID.class);
        when(sprint.getSprintID()).thenReturn(sprintID);
        ContainerID containerID = sprintID;

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.of(resource));
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);

        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, containerID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateTask.createTask(taskCreationDTO, projID));


        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_ProjectDoesntExist() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(false);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);

        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        taskCreationDTO.endDate = LocalDate.of(2021, 1, 8);
        Period period = Period.between(taskCreationDTO.startDate, taskCreationDTO.endDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.option = "Sprint";
        SprintID sprintID = mock(SprintID.class);
        when(sprint.getSprintID()).thenReturn(sprintID);
        ContainerID containerID = sprintID;

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.of(resource));
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);

        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, containerID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateTask.createTask(taskCreationDTO, projID));


        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_PeriodWithNoEndDate() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);

        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);

        taskCreationDTO.startDate = LocalDate.of(2021, 1, 1);
        Period period = Period.starting(taskCreationDTO.startDate);
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.option = "Sprint";
        SprintID sprintID = mock(SprintID.class);
        when(sprint.getSprintID()).thenReturn(sprintID);
        ContainerID containerID = sprintID;

        taskCreationDTO.effortEstimate = "1";
        FibonacciNumber effortEstimate = FibonacciNumber.of(Integer.parseInt(taskCreationDTO.effortEstimate));

        taskCreationDTO.resourceID = "2";
        Resource resource = mock(Resource.class);
        ResourceID resourceID = ResourceID.of(Integer.parseInt(taskCreationDTO.resourceID));
        when(resourceRepository.findResourceByID(resourceID)).thenReturn(Optional.of(resource));
        List<Resource> activeResources = new ArrayList<>();
        when(resourceRepository.findAllActiveResourcesByProjectID(projectID, LocalDate.now())).thenReturn(activeResources);
        activeResources.add(resource);

        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, containerID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        TaskCreationReturnedDTO result = applicationServiceCreateTask.createTask(taskCreationDTO, projID);

        assertEquals(expected, result);

        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask_WithMinimalInfo() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectID)).thenReturn(Optional.of(sprint));

        TaskCreationDTO taskCreationDTO = mock(TaskCreationDTO.class);
        taskCreationDTO.name = "name";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(taskCreationDTO.name);

        taskCreationDTO.description = "description";
        Text description = Text.write(taskCreationDTO.description);

        taskCreationDTO.type = "Meeting";
        TaskType taskType = TaskType.dbConverter(taskCreationDTO.type);


        Period period = null;
        when(serviceValidatePeriodWhenCreatingTask.validateDates(sprint, period)).thenReturn(true);

        taskCreationDTO.option = "Sprint";
        SprintID sprintID = mock(SprintID.class);
        when(sprint.getSprintID()).thenReturn(sprintID);
        ContainerID containerID = sprintID;

        FibonacciNumber effortEstimate = null;

        ResourceID resourceID = null;

        TaskID taskID = TaskID.generate();
        MockedStatic<TaskID> taskIDClass = mockStatic(TaskID.class);
        taskIDClass.when(() -> TaskID.generate()).thenReturn(taskID);

        Task taskCreated = mock(Task.class);
        when(taskFactory.createTask(taskID, name, description, period, effortEstimate, taskType, containerID, resourceID)).thenReturn(taskCreated);

        Task taskSaved = mock(Task.class);
        when(taskRepository.saveTask(taskCreated)).thenReturn(taskSaved);

        TaskCreationReturnedDTO expected = mock(TaskCreationReturnedDTO.class);
        MockedStatic<TaskMapper> taskMapperClass = mockStatic(TaskMapper.class);
        taskMapperClass.when(() -> TaskMapper.toDTOReturnedUponCreation(taskSaved)).thenReturn(expected);

        TaskCreationReturnedDTO result = applicationServiceCreateTask.createTask(taskCreationDTO, projID);

        assertEquals(expected, result);

        taskMapperClass.close();
        taskIDClass.close();
    }

    @Test
    void createTask() {
    }
}