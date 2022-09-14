package switchtwentyone.project.infrastructure.persistence.ijparepositories;

import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.datamodel.UserStoryJPA;

import java.util.List;

public interface UserStoryJPARepository extends CrudRepository<UserStoryJPA, UserStoryID> {

    List<UserStoryJPA> findByProjectID(int projectID);

    List<UserStoryJPA> findAllByProjectIDAndDecomposedFalseOrderByPriority(int projectID);

    List<UserStoryJPA> findAllByProjectIDOrderByPriority(int projectID);

}
