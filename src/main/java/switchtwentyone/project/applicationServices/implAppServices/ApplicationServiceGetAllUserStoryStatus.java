package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.services.ServiceFindUS;
import switchtwentyone.project.dto.USInfoAndStatusDTO;
import switchtwentyone.project.dto.mapper.UserStoryStatusMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.ProjectController;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.UserStoryController;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceGetAllUserStoryStatus {


        @Autowired
        UserStoryRepository userStoryRepository;

        @Autowired
        SprintRepository sprintRepository;

        @Autowired
        ServiceFindUS serviceFindUS;



        public List<USInfoAndStatusDTO> getAllUserStoryStatus(int projectID){

            ProjectID projID = new ProjectID(projectID);
            List<USInfoAndStatusDTO> returnList = new ArrayList<>();
            List<UserStory> listUS = userStoryRepository.findAllUSByProjectID(projID);
            List<Sprint> listSprints = sprintRepository.findAllSprintsByProjectID(projID);
            Optional<Sprint> optRunningSprint = sprintRepository.findRunningSprintByProjectID(projID);


            if(optRunningSprint.isPresent()){

                TreeMap<UserStory, String> usInfoMap = serviceFindUS.getAllUserStoriesInfo(listUS, optRunningSprint.get(), listSprints);
                if(!usInfoMap.isEmpty()) {
                    Set<Map.Entry<UserStory, String>> entrySet = usInfoMap.entrySet();
                    Map.Entry<UserStory, String>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);

                    for (int i = 0; i < entryArray.length; i++) {
                        UserStory userStory = entryArray[i].getKey();
                        String status = entryArray[i].getValue();
                        returnList.add(UserStoryStatusMapper.toDTO(userStory, status));
                    }
                }

                returnList.forEach(usDTO -> {
                    double usId = usDTO.usID;
                    usDTO.add(linkTo(methodOn(UserStoryController.class).getUserStoryById(projectID, usId)).withSelfRel());
                });

            }else {
                TreeMap<UserStory, String> usInfoMap = serviceFindUS.getAllUserStoriesInfoWithNoRunningSprint(listUS, listSprints);
                if(!usInfoMap.isEmpty()) {
                    Set<Map.Entry<UserStory, String>> entrySet = usInfoMap.entrySet();
                    Map.Entry<UserStory, String>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);

                    for (int i = 0; i < entryArray.length; i++) {
                        UserStory userStory = entryArray[i].getKey();
                        String status = entryArray[i].getValue();
                        returnList.add(UserStoryStatusMapper.toDTO(userStory, status));

                    }
                }

                returnList.forEach(usDTO -> {
                    double usId = usDTO.usID;
                    usDTO.add(linkTo(methodOn(ProjectController.class).getProjectDetails(projectID)).withSelfRel());
                    usDTO.add(linkTo(methodOn(UserStoryController.class).getUserStoryById(projectID, usId)).withSelfRel());
                });


            }






            return returnList;
        }







}
