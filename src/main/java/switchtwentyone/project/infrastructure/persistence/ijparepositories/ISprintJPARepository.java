package switchtwentyone.project.infrastructure.persistence.ijparepositories;


import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.datamodel.SprintJPA;

import java.util.List;
import java.util.Optional;


public interface ISprintJPARepository extends CrudRepository<SprintJPA, SprintID> {

    Optional<SprintJPA> findBySprintID(SprintID sprintID);

    List<SprintJPA> findAllByProjectID(int projectID);

}