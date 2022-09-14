package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Optional<Project> findById(ProjectID id);

    int getHighestProjectID();

    Project save(Project proj);

    List<Project> findAll();

    boolean existsById(ProjectID id);
}
