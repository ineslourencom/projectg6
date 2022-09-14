package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;

import java.util.List;
import java.util.Optional;

public interface ProjectTypologyRepository {
    ProjectTypology saveProjectTypology(ProjectTypology projectTypology);

    Optional<ProjectTypology> findProjectTypologyByID(ProjectTypologyID projectTypologyID);

    List<ProjectTypology> getAllProjectTypologies();
}
