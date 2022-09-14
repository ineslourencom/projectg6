package switchtwentyone.project.applicationServices.implAppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceCreateUS;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.USDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceCreateUserStory {
    @Autowired
    UserStoryRepository usRepo;

    @Autowired
    ProjectRepository projRepo;

    @Autowired
    ServiceCreateUS createUSService;


    public ApplicationServiceCreateUserStory() {
        //this is an empty constructor
    }

    public USDTO createUserStory(Text statement, Text detail, ProjectID projID){
        Optional<Project> optProj = projRepo.findById(projID);
        USDTO newUSDTO;

        if (optProj.isPresent()){
            List<UserStory> listUS = usRepo.findAllUSByProjectID(projID);
            PositiveNumber usNumber = createUSService.generateUSNumber(listUS);
            UserStoryID usID = createUSService.generateUSID(usNumber, projID);
            int priority = createUSService.determineUSPriority(listUS);

            UserStory newUS = optProj.get().createUserStory(statement, detail, usID, priority, usNumber);
            usRepo.saveUS(newUS);
            newUSDTO = UserStoryMapper.toDTO(newUS);
        }
        else{
            throw new EntityNotFoundException("Project not found");
        }

       return newUSDTO;
    }

    public Optional<UserStory> findUSByID (UserStoryID usID){
        return usRepo.findByID(usID);
    }

}


