package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.FibonacciNumber;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.dto.AddUSToSprintInfoDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ApplicationServiceAddUSToSprintBacklog {

    @Autowired
    SprintRepository sprintRepo;

    @Autowired
    ProjectRepository projRepo;

    public boolean addUSToSprintBacklog(int pid, AddUSToSprintInfoDTO info){
        ProjectID projectID = new ProjectID(pid);
        Optional<Project> project = projRepo.findById(projectID);
        if(project.isPresent()){
            UserStoryID usID = UserStoryID.ofDouble(info.getUsID());
            FibonacciNumber effortEstimate = FibonacciNumber.of(info.getEffort());
            Optional<Sprint> sprintFound = sprintRepo.findRunningSprintByProjectID(projectID);
            if(sprintFound.isPresent()){
                sprintFound.get().addUSToSprintBacklog(usID, effortEstimate);
                sprintRepo.saveSprint(sprintFound.get());
                return true;
            }
            else{
                throw new IllegalArgumentException("There isn't a sprint currently running.");
            }
        }
        else{
            throw new EntityNotFoundException("Project not found.");
        }
    }
}
