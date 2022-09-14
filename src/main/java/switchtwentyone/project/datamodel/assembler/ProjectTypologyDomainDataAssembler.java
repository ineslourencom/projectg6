package switchtwentyone.project.datamodel.assembler;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyFactory;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.datamodel.ProjectTypologyJPA;


@Service
public class ProjectTypologyDomainDataAssembler {
    @Autowired
    ProjectTypologyFactory projectTypologyFactory;

    public ProjectTypologyJPA toData(ProjectTypology projectTypology){
        String description = projectTypology.getDescriptionAsString();
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(NoNumberNoSymbolString.of(projectTypology.getProjectTypologyIDAsString()));
        return new ProjectTypologyJPA(projectTypologyID, description);
    }

    public ProjectTypology toDomain(ProjectTypologyJPA projectTypologyJPA){
        Describable description = Text.write(projectTypologyJPA.getDescription());
        ProjectTypologyID projectTypologyID = projectTypologyJPA.getProjectTypologyID();
        return projectTypologyFactory.createProjectTypology(projectTypologyID,description);
    }
}

