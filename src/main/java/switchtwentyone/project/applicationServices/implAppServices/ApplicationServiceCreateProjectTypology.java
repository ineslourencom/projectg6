package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyFactory;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProjectTypologyDTO;
import switchtwentyone.project.dto.mapper.ProjectTypologyMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectTypologyRepository;

import java.util.Optional;

@Service
public class ApplicationServiceCreateProjectTypology {

    @Autowired
    ProjectTypologyRepository projectTypologyRepository;

    @Autowired
    ProjectTypologyFactory projectTypologyfactory;

    public ProjectTypologyDTO createAndSaveProjectTypology(String name, String description) {
        NoNumberNoSymbolString nameVO = NoNumberNoSymbolString.of(name);
        Describable descriptionVO = Text.write(description);
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(nameVO);
        Optional<ProjectTypology> projectTypologyWithSameID = projectTypologyRepository.findProjectTypologyByID(projectTypologyID);
        ProjectTypology projectTypologySaved;
        if (projectTypologyWithSameID.isEmpty()) {
            ProjectTypology projectTypologyCreated = projectTypologyfactory.createProjectTypology(projectTypologyID, descriptionVO);
            projectTypologySaved = projectTypologyRepository.saveProjectTypology(projectTypologyCreated);
        } else throw new IllegalArgumentException("ProjectTypology already exists");

        return ProjectTypologyMapper.toSingleDTO(projectTypologySaved);
    }
}
