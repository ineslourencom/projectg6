package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;

import java.util.List;
import java.util.Optional;

public interface SprintRepository {
    boolean saveSprint(Sprint sprint);

    Optional<Sprint> findByID(SprintID sprintID);

    List<Sprint> findAllSprintsByProjectID(ProjectID projID);

    Optional<Sprint> findRunningSprintByProjectID(ProjectID projectID);

    boolean existsByID(SprintID sprintID);
}
