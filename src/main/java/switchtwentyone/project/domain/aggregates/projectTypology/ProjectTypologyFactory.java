package switchtwentyone.project.domain.aggregates.projectTypology;


import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.shared.Describable;

@Component
public class ProjectTypologyFactory implements ProjectTypologyCreatable{

    @Override
    public ProjectTypology createProjectTypology(ProjectTypologyID projectTypologyID, Describable description) {
        return new ProjectTypology(projectTypologyID, description);
    }
}
