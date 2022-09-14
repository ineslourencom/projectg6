package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.TaskRepository;
import switchtwentyone.project.domain.aggregates.task.Task;
import switchtwentyone.project.datamodel.TaskJPA;
import switchtwentyone.project.datamodel.assembler.TaskDomainDataAssembler;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.ITaskJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    TaskDomainDataAssembler taskDomainDataAssembler;

    @Autowired
    ITaskJPARepository iTaskJPARepository;

    @Override
    public Task saveTask(Task task) {
        TaskJPA taskToBeSaved = taskDomainDataAssembler.toData(task);
        TaskJPA taskJPASaved = iTaskJPARepository.save(taskToBeSaved);
        Task taskSaved = taskDomainDataAssembler.toDomain(taskJPASaved);

        return taskSaved;
    }

    @Override
    public List<Task> findAllByContainerID(String containerId) {
        List<TaskJPA> listTaskJPA = iTaskJPARepository.findAllByContainerID(containerId);
        List<Task> listTask = new ArrayList<>();

        for (TaskJPA taskJPA: listTaskJPA) {
            Task task = taskDomainDataAssembler.toDomain(taskJPA);
            listTask.add(task);
        }

        return listTask;
    }

    @Override
    public Optional<Task> findTaskByID(String taskID) {
        Optional<TaskJPA> opTaskJPA = iTaskJPARepository.findByTaskID(taskID);
        return opTaskJPA.map(taskJPA -> taskDomainDataAssembler.toDomain(taskJPA));
    }
}
