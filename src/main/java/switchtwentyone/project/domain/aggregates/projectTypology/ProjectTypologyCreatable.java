package switchtwentyone.project.domain.aggregates.projectTypology;

import switchtwentyone.project.domain.shared.Describable;

public interface ProjectTypologyCreatable {

    ProjectTypology createProjectTypology(ProjectTypologyID projectTypologyID, Describable description);
}
