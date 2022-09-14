package switchtwentyone.project.applicationServices.implAppServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.dto.SprintInfoForTaskDTO;
import switchtwentyone.project.dto.mapper.SprintMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.SprintController;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceGetRunningSprint {
    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    ProjectRepository projectRepository;

    public SprintInfoForTaskDTO getRunningSprint(int projID) {
        ProjectID projectID = new ProjectID(projID);
        SprintInfoForTaskDTO sprintInfoForTaskDTO = null;
        if (projectRepository.existsById(projectID)) {
            Optional<Sprint> runningSprint = sprintRepository.findRunningSprintByProjectID(projectID);
            if (runningSprint.isPresent()) {
                sprintInfoForTaskDTO = SprintMapper.toDTOForTask(runningSprint.get());
                Link link = linkTo(methodOn(SprintController.class).getSprintById(sprintInfoForTaskDTO.sprintID)).withSelfRel();
                sprintInfoForTaskDTO.add(link);
            }
        } else {
            throw new IllegalArgumentException("Project doesn't exist!");
        }
        return sprintInfoForTaskDTO;
    }


}
