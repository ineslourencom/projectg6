package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.task.*;
import switchtwentyone.project.datamodel.TaskJPA;
import switchtwentyone.project.datamodel.assembler.TaskDomainDataAssembler;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.ITaskJPARepository;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.TaskRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskRepositoryImplTest {
    @Mock
    TaskDomainDataAssembler taskDomainDataAssembler;

    @Mock
    ITaskJPARepository iTaskJPARepository;

    @InjectMocks
    TaskRepositoryImpl taskRepositoryImpl;

    @Test
    void saveTask() {
        Task taskToBeSaved = mock(Task.class);
        TaskJPA taskJPAToBeSaved = mock(TaskJPA.class);
        TaskJPA taskJPASaved = mock(TaskJPA.class);
        Task taskSaved = mock(Task.class);
        when(taskDomainDataAssembler.toData(taskToBeSaved)).thenReturn(taskJPAToBeSaved);
        when(iTaskJPARepository.save(taskJPAToBeSaved)).thenReturn(taskJPASaved);
        when(taskDomainDataAssembler.toDomain(taskJPASaved)).thenReturn(taskSaved);

        Task result = taskRepositoryImpl.saveTask(taskToBeSaved);

        assertEquals(taskSaved, result);
    }

    /*
    @Test
    void findAllByContainerIDStartingWith(){

        int projID = 1;

        TaskJPA taskJpa001 = mock(TaskJPA.class);
        ContainerID containerIDJpa = mock(ContainerID.class);
        when(containerIDJpa.getID()).thenReturn(1.001);
        List<String> listJpa = new ArrayList<>();
        ResourceID resourceID = new ResourceID();
        TaskID taskIdJpa = TaskID.createTaskID("6530fce2-ed9a-11ec-8ea0-0242ac120002");
        TaskID taskIdPrecedence = TaskID.createTaskID("8030fce2-ed9a-11ec-8ea0-0242ac120002");
        String taskPrec= taskIdPrecedence.getTaskIdentityNumber();
        when(taskJpa001.getTaskID()).thenReturn(taskIdJpa);
        when(taskJpa001.getTaskName()).thenReturn("task one");
        when(taskJpa001.getTaskDescription()).thenReturn("description");
        when(taskJpa001.getStartDate()).thenReturn(LocalDate.of(2022, 01, 01));
        when(taskJpa001.getEndDate()).thenReturn(LocalDate.of(2022, 01, 05));
        when(taskJpa001.getEffortEstimate()).thenReturn(5);
        when(taskJpa001.getTaskType()).thenReturn("Meeting");
        when(taskJpa001.getTaskStatus()).thenReturn("Planned");
        when(taskJpa001.getHoursSpent()).thenReturn(3);
        when(taskJpa001.getPercentageOfExecution()).thenReturn(80.0);
        when(taskJpa001.getContainerID()).thenReturn(containerIDJpa);
        when(taskJpa001.getEffortUpdate()).thenReturn(listJpa);
        when(taskJpa001.getAssociatedResource()).thenReturn(resourceID);
        when(taskJpa001.getTaskPrecedenceId()).thenReturn(taskPrec);


        Task task001 = mock(Task.class);
        ContainerID containerID = mock(ContainerID.class);
        when(containerID.getID()).thenReturn(1.001);
        when(task001.getContainerID()).thenReturn(containerID);
        when(task001.getTaskNameAsString()).thenReturn("task One");
        when(containerID.getID()).thenReturn(1.101);
        List<EffortUpdate> list = new ArrayList<>();

        TaskID taskId = TaskID.createTaskID("6530fce2-ed9a-11ec-8ea0-0242ac120002");
        when(task001.getTaskIdentityNumber()).thenReturn(taskId);
        when(task001.getTaskNameAsString()).thenReturn("task one");
        when(task001.getTaskDescriptionAsString()).thenReturn("description");
        when(task001.getStartDate()).thenReturn(LocalDate.of(2022, 01, 01));
        when(task001.getEndDate()).thenReturn(LocalDate.of(2022, 01, 05));
        when(task001.getEffortEstimateAsInt()).thenReturn(5);
        when(task001.getTaskTypeAsString()).thenReturn("Meeting");
        when(task001.getTaskStatusAsString()).thenReturn("Planned");
        when(task001.getHoursSpentAsInt()).thenReturn(3);
        when(task001.getPercentageOfExecution()).thenReturn(80.0);
        when(task001.getContainerID()).thenReturn(containerIDJpa);
        when(task001.getEffortUpdates()).thenReturn(list);
        when(task001.getAssociatedResource()).thenReturn(resourceID);
        when(task001.getTaskPrecedenceId()).thenReturn(taskPrec);

        when(taskDomainDataAssembler.toData(task001)).thenReturn(taskJpa001);
        when(iTaskJPARepository.save(taskJpa001)).thenReturn(taskJpa001);
        when(taskDomainDataAssembler.toDomain(taskJpa001)).thenReturn(task001);

        List<Task> expected = new ArrayList<>();
        expected.add(task001);

        List<Task> result = taskRepositoryImpl.findAllByContainerIDStartingWith(projID);

        assertEquals(expected, result);


    }

     */






}