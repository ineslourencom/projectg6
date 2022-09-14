package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.ScrumBoardCategory;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceUpdateUsStatus;
import switchtwentyone.project.dto.ChangeUsCategoryDTO;

import java.util.Optional;

@Service
public class ApplicationServiceUpdateUsStatus {

    @Autowired
    ServiceUpdateUsStatus serviceUpdateUsStatus;

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    UserStoryRepository userStoryRepository;


    public boolean updateUsStatus(int projID, double usID, ChangeUsCategoryDTO cat) {

        ProjectID projectID = new ProjectID(projID);

        Optional<Sprint> sprintOptional = sprintRepository.findRunningSprintByProjectID(projectID);

        boolean update = false;

        if (sprintOptional.isPresent()) {

            UserStoryID userStoryID = UserStoryID.ofDouble(usID);
            ScrumBoardCategory scrumBoardCategory = ScrumBoardCategory.dbConverter(cat.category);

            Sprint sprintUpdated = serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID, sprintOptional.get(), scrumBoardCategory);

            sprintRepository.saveSprint(sprintUpdated);

            update = true;
        }
        return update;


    }

}
