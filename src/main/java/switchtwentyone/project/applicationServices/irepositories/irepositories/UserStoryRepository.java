package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;

import java.util.List;
import java.util.Optional;

public interface UserStoryRepository {
    boolean saveUS(UserStory us);

    List<UserStory> findAllUSByProjectID(ProjectID projID);

    Optional<UserStory> findByID(UserStoryID userStoryID);

    UserStory save(UserStory userStory);

    boolean saveAll(List<UserStory> userStories);

    List<UserStory> findAllByProjectIDNotDecomposed(ProjectID projectID);

    List<UserStory> findAllByProjectIDOrderByPriority(ProjectID projectID);

}
