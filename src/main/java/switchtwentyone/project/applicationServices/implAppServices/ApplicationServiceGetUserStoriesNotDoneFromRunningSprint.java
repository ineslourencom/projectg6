package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceValidateUserStoryWhenCreatingTask;
import switchtwentyone.project.domain.services.ServiceFindUS;
import switchtwentyone.project.dto.USMinimalInfoDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.UserStoryController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceGetUserStoriesNotDoneFromRunningSprint {

    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ServiceValidateUserStoryWhenCreatingTask serviceValidateUserStoryWhenCreatingTask;
    @Autowired
    UserStoryRepository userStoryRepository;
    @Autowired
    ServiceFindUS serviceFindUS;

    public List<USMinimalInfoDTO> getUSNotDoneFromRunningSprint(int projID) {
        ProjectID projectID = new ProjectID(projID);
        if (!projectRepository.existsById(projectID)) {
            throw new IllegalArgumentException("Project doesnt exist");
        }
        Optional<Sprint> runningSprint = sprintRepository.findRunningSprintByProjectID(projectID);
        if (runningSprint.isEmpty()) {
            throw new IllegalArgumentException("Project has no running sprint");
        }
        List<Sprint> runningSprintInList = new ArrayList<>();
        runningSprintInList.add(runningSprint.get());
        List<UserStoryID> userStoriesIDInRunningSprint = serviceFindUS.findUSIDinSprint(runningSprint);
        List<UserStoryID> userStoriesIDDone = serviceFindUS.findDoneUSIDinSprint(runningSprintInList);
        List<UserStory> userStoriesInRunningSprint = createListOfUSFromListOfUSID(userStoriesIDInRunningSprint);
        List<UserStory> userStoriesDone = createListOfUSFromListOfUSID(userStoriesIDDone);

        for (UserStory userStory : userStoriesInRunningSprint
        ) {
            if (userStoriesDone.contains(userStory)) {
                userStoriesInRunningSprint.remove(userStory);
            }
        }
        List<USMinimalInfoDTO> listUSMinimalDTO = new ArrayList<>();
        for (UserStory userStory :
                userStoriesInRunningSprint) {
            USMinimalInfoDTO dto = UserStoryMapper.toMinimalDTO(userStory);
            Link link = linkTo(methodOn(UserStoryController.class).getUserStoryById(projID, dto.usID)).withSelfRel();
            dto.add(link);
            listUSMinimalDTO.add(dto);
        }
        return listUSMinimalDTO;
    }


    private List<UserStory> createListOfUSFromListOfUSID(List<UserStoryID> userStoryIDs) {
        List<UserStory> userStoryList = new ArrayList<>();
        for (UserStoryID usID : userStoryIDs) {
            Optional<UserStory> usOpt = userStoryRepository.findByID(usID);
            usOpt.ifPresent(userStoryList::add);
        }
        return userStoryList;
    }
}
