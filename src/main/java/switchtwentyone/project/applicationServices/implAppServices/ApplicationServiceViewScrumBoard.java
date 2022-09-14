package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.services.ServiceFindUS;
import switchtwentyone.project.dto.ScrumBoardDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ProjectController;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.UserStoryController;

import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ScrumBoardController;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceViewScrumBoard {

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    ServiceFindUS serviceFindUS;

    @Autowired
    UserStoryRepository userStoryRepository;

    public ApplicationServiceViewScrumBoard() {
        // TODO this is an empty constructor
    }

    public List<ScrumBoardDTO> viewScrumBoard(int projectID) {
        List<ScrumBoardDTO> returnList = new ArrayList<>();
        ProjectID projID = new ProjectID(projectID);
        Optional<Sprint> optRunningSprint = sprintRepository.findRunningSprintByProjectID(projID);
        List<UserStory> listUS = userStoryRepository.findAllUSByProjectID(projID);


        if (optRunningSprint.isPresent()) {
            TreeMap<UserStory, String> usInfoMap = serviceFindUS.getUSFromRunningSprint(listUS, optRunningSprint.get());
            if (!usInfoMap.isEmpty()) {
                Set<Map.Entry<UserStory, String>> entrySet = usInfoMap.entrySet();
                Map.Entry<UserStory, String>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);

                for (int i = 0; i < entryArray.length; i++) {
                    UserStory userStory = entryArray[i].getKey();
                    String status = entryArray[i].getValue();
                    ScrumBoardDTO usDTO = UserStoryMapper.toScrumBoardDTO(userStory, status, optRunningSprint.get().getSprintIDDouble());
                    usDTO.add(linkTo(methodOn(ProjectController.class).getProjectDetails(projectID)).withSelfRel());
                    usDTO.add(linkTo(methodOn(UserStoryController.class).getUserStoryById(projectID, userStory.getUsID().getID())).withSelfRel());
                    usDTO.add(linkTo(methodOn(ScrumBoardController.class).getUSStatus(projectID, userStory.getUsID().getID())).withRel("editCategory"));
                    returnList.add(usDTO);
                }
            }
        }

        return returnList;
    }

}