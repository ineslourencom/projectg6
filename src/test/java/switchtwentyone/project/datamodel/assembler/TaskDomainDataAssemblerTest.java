package switchtwentyone.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.aggregates.task.*;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.datamodel.TaskJPA;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TaskDomainDataAssemblerTest {

    @Test
    void toData() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);
        String name = "name";
        NoNumberNoSymbolString taskName = NoNumberNoSymbolString.of(name);
        String description = "description";
        Text taskDescription = Text.write(description);
        LocalDate startDate = LocalDate.of(2021, 8, 24);
        LocalDate endDate = LocalDate.of(2022, 8, 24);
        Period taskPeriod = Period.between(startDate, endDate);
        int effortEstimate = 1;
        FibonacciNumber taskEfforEstimate = FibonacciNumber.of(effortEstimate);
        String Type = "Meeting";
        TaskType taskType = TaskType.meeting();
        String Status = "Planned";
        int hoursSpent = 0;
        double percentageOfExecution = 0.0;
        ContainerID containerID = SprintID.ofDouble(1.999);
        String containerIDString = containerID.getIDForTask();
        ResourceID associatedResource = ResourceID.of(123456);
        String resourceDIString = String.valueOf(associatedResource.getResourceID());
        List<String> effortUpdate = new ArrayList<>();
        String effortUpdateOne = "2021-09-24T14:14:14|1.2|effortOne|123456";
        String effortUpdateTwo = "2021-10-24T14:14:14|0.2|effortTwo|654321";
        effortUpdate.add(effortUpdateOne);
        effortUpdate.add(effortUpdateTwo);
        EffortUpdate taskEffortUpdateOne = EffortUpdate.of(LocalDateTime.of(2021, 9, 24, 14, 14, 14), 1.2,
                "effortOne", associatedResource);
        EffortUpdate taskEffortUpdateTwo = EffortUpdate.of(LocalDateTime.of(2021, 10, 24, 14, 14, 14), 0.2,
                "effortTwo", ResourceID.of(654321));
        String taskPrecedenceID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idPrecedence = TaskID.createTaskID(taskPrecedenceID);

        Task task = new Task.TaskBuilder(idOne, taskName, taskDescription, taskType, containerID).parentTask(idPrecedence).
                associatedResource(associatedResource).effortEstimate(taskEfforEstimate).period(taskPeriod).build();
        task.addEffortUpdate(taskEffortUpdateOne);
        task.addEffortUpdate(taskEffortUpdateTwo);

        TaskJPA expected = new TaskJPA(idOne, name, description, startDate, endDate, effortEstimate, Type, Status,
                hoursSpent, percentageOfExecution, containerIDString, effortUpdate, resourceDIString, taskPrecedenceID);
        TaskDomainDataAssembler taskDomainDataAssembler = new TaskDomainDataAssembler();
        TaskJPA result = taskDomainDataAssembler.toData(task);

        assertEquals(expected, result);
    }

    @Test
    void toData_NoEffortUpdates() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);
        String name = "name";
        NoNumberNoSymbolString taskName = NoNumberNoSymbolString.of(name);
        String description = "description";
        Text taskDescription = Text.write(description);
        LocalDate startDate = LocalDate.of(2021, 8, 24);
        LocalDate endDate = LocalDate.of(2022, 8, 24);
        Period taskPeriod = Period.between(startDate, endDate);
        int effortEstimate = 1;
        FibonacciNumber taskEfforEstimate = FibonacciNumber.of(effortEstimate);
        String Type = "Meeting";
        TaskType taskType = TaskType.meeting();
        String Status = "Planned";
        int hoursSpent = 0;
        double percentageOfExecution = 0.0;
        ContainerID containerID = SprintID.ofDouble(1.999);
        String containerIDString = containerID.getIDForTask();
        ResourceID associatedResource = ResourceID.of(123456);
        String resourceDIString = String.valueOf(associatedResource.getResourceID());

        List<String> effortUpdate = new ArrayList<>();
        String taskPrecedenceID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idPrecedence = TaskID.createTaskID(taskPrecedenceID);

        Task task = new Task.TaskBuilder(idOne, taskName, taskDescription, taskType, containerID).parentTask(idPrecedence).
                associatedResource(associatedResource).effortEstimate(taskEfforEstimate).period(taskPeriod).build();

        TaskJPA expected = new TaskJPA(idOne, name, description, startDate, endDate, effortEstimate, Type, Status,
                hoursSpent, percentageOfExecution, containerIDString, effortUpdate, resourceDIString, taskPrecedenceID);
        TaskDomainDataAssembler taskDomainDataAssembler = new TaskDomainDataAssembler();

        TaskJPA result = taskDomainDataAssembler.toData(task);

        assertEquals(expected, result);
    }


    @Test
    void toDomain() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);
        String name = "name";
        NoNumberNoSymbolString taskName = NoNumberNoSymbolString.of(name);
        String description = "description";
        Text taskDescription = Text.write(description);
        LocalDate startDate = LocalDate.of(2021, 8, 24);
        LocalDate endDate = LocalDate.of(2022, 8, 24);
        Period taskPeriod = Period.between(startDate, endDate);
        int effortEstimate = 1;
        FibonacciNumber taskEfforEstimate = FibonacciNumber.of(effortEstimate);
        String Type = "Meeting";
        TaskType taskType = TaskType.meeting();
        String Status = "Planned";
        int hoursSpent = 0;
        double percentageOfExecution = 0.0;
        ContainerID containerID = SprintID.ofDouble(1.999);
        String containerIDString = containerID.getIDForTask();


        ResourceID associatedResource = ResourceID.of(123456);
        String resourceDIString = String.valueOf(associatedResource.getResourceID());

        List<String> effortUpdate = new ArrayList<>();
        String effortUpdateOne = "2021-09-24T14:14:14|1.0|effortOne|123456";
        String effortUpdateTwo = "2021-10-24T14:14:14|1.0|effortTwo|654321";
        effortUpdate.add(effortUpdateOne);
        effortUpdate.add(effortUpdateTwo);
        List<EffortUpdate> listOfEffortUpdates = new ArrayList<>();
        EffortUpdate taskEffortUpdateOne = EffortUpdate.of(LocalDateTime.of(2021, 9, 24, 14, 14, 14), 1.0,
                "effortOne", associatedResource);
        EffortUpdate taskEffortUpdateTwo = EffortUpdate.of(LocalDateTime.of(2021, 10, 24, 14, 14, 14), 1.0,
                "effortTwo", ResourceID.of(654321));
        listOfEffortUpdates.add(taskEffortUpdateOne);
        listOfEffortUpdates.add(taskEffortUpdateTwo);
        String taskPrecedenceID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idPrecedence = TaskID.createTaskID(taskPrecedenceID);

        Task expected = new Task.TaskBuilder(idOne, taskName, taskDescription, taskType, containerID).parentTask(idPrecedence).
                associatedResource(associatedResource).period(taskPeriod).effortEstimate(taskEfforEstimate).build();
        expected.setEffortUpdate(listOfEffortUpdates);

        TaskJPA taskJPA = new TaskJPA(idOne, name, description, startDate, endDate, effortEstimate, Type, Status, hoursSpent,
                percentageOfExecution, containerIDString, effortUpdate, resourceDIString, taskPrecedenceID);
        TaskDomainDataAssembler taskDomainDataAssembler = new TaskDomainDataAssembler();

        Task result = taskDomainDataAssembler.toDomain(taskJPA);

        assertEquals(expected, result);
    }

    @Test
    void toDomain_NoEffortUpdates() {
        String taskID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idOne = TaskID.createTaskID(taskID);
        String name = "name";
        NoNumberNoSymbolString taskName = NoNumberNoSymbolString.of(name);
        String description = "description";
        Text taskDescription = Text.write(description);
        LocalDate startDate = LocalDate.of(2021, 8, 24);
        LocalDate endDate = LocalDate.of(2022, 8, 24);
        Period taskPeriod = Period.between(startDate, endDate);
        int effortEstimate = 1;
        FibonacciNumber taskEfforEstimate = FibonacciNumber.of(effortEstimate);
        String Type = "Meeting";
        TaskType taskType = TaskType.meeting();
        String Status = "Planned";
        int hoursSpent = 0;
        double percentageOfExecution = 0.0;
        ContainerID containerID = SprintID.ofDouble(1.999);
        String containerIDString = containerID.getIDForTask();

        ResourceID associatedResource = ResourceID.of(123456);
        String resourceDIString = String.valueOf(associatedResource.getResourceID());

        List<String> effortUpdate = new ArrayList<>();
        String taskPrecedenceID = "4129964d-10b2-4bf2-a7d5-0a5a1a9d43e4";
        TaskID idPrecedence = TaskID.createTaskID(taskPrecedenceID);

        Task expected = new Task.TaskBuilder(idOne, taskName, taskDescription, taskType, containerID).parentTask(idPrecedence).
                associatedResource(associatedResource).period(taskPeriod).effortEstimate(taskEfforEstimate).build();

        TaskJPA taskJPA = new TaskJPA(idOne, name, description, startDate, endDate, effortEstimate, Type, Status, hoursSpent,
                percentageOfExecution, containerIDString, effortUpdate, resourceDIString, taskPrecedenceID);
        TaskDomainDataAssembler taskDomainDataAssembler = new TaskDomainDataAssembler();

        Task result = taskDomainDataAssembler.toDomain(taskJPA);

        assertEquals(expected, result);
    }
}