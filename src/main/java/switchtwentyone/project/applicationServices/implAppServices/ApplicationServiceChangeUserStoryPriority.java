package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceChangePriorityOfUserStories;
import switchtwentyone.project.dto.USWithPriorityDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.UserStoryController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceChangeUserStoryPriority {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserStoryRepository userStoryRepository;

    @Autowired
    ServiceChangePriorityOfUserStories serviceChangePriorityOfUserStories;

    @Autowired
    ApplicationServiceViewProductBacklog applicationServiceViewProductBacklog;

    public USWithPriorityDTO changePriorityOfUserStory(int projID, double usID, int newPriority) {
        ProjectID projectID = new ProjectID(projID);
        if (!projectRepository.existsById(projectID)) {
            throw new IllegalArgumentException("Project doesn't exist");
        }

        List<UserStory> productBacklog = applicationServiceViewProductBacklog.aggregateProductBacklogData(projectID);

        UserStoryID userStoryID = UserStoryID.ofDouble(usID);
        Optional<UserStory> userStoryToBeChanged = userStoryRepository.findByID(userStoryID);
        if (userStoryToBeChanged.isEmpty()) {
            throw new IllegalArgumentException("User Story doesn't exist");
        }

        if (!productBacklog.contains(userStoryToBeChanged.get())) {
            throw new IllegalArgumentException("User Story is not in productBacklog");
        }

        int actualNewPriority = serviceChangePriorityOfUserStories.findTrueNewPriority(productBacklog, newPriority);

        List<UserStory> allUserStories = userStoryRepository.findAllByProjectIDOrderByPriority(projectID);

        List<UserStory> newProductBacklog = serviceChangePriorityOfUserStories.changePriorityOfUserStories(allUserStories, userStoryToBeChanged.get(), actualNewPriority);

        for (UserStory userStory : newProductBacklog
        ) {
            userStoryRepository.save(userStory);
        }

        USWithPriorityDTO DTOToReturn = UserStoryMapper.toPriorityDTO(userStoryToBeChanged.get());
        Link link = linkTo(methodOn(UserStoryController.class).getUserStoryById(DTOToReturn.projeID, DTOToReturn.usID)).withSelfRel();
        DTOToReturn.add(link);

        return DTOToReturn;
    }

}
