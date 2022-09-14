package switchtwentyone.project.infrastructure.persistence.ijparepositories;

import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.task.TaskID;
import switchtwentyone.project.datamodel.TaskJPA;

import java.util.List;
import java.util.Optional;

public interface ITaskJPARepository extends CrudRepository<TaskJPA, TaskID> {

List<TaskJPA> findAllByContainerID(String contID);

Optional<TaskJPA> findByTaskID(String taskID);



}
