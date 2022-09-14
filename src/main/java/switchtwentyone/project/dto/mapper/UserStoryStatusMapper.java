package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.dto.USInfoAndStatusDTO;

public class UserStoryStatusMapper {

    private UserStoryStatusMapper(){
        //Not supposed to be instantiated
    }

    public static USInfoAndStatusDTO toDTO(UserStory us, String status){

        USInfoAndStatusDTO dto = new USInfoAndStatusDTO();
        dto.storyNumber = us.getStoryNumber().getNumber();
        dto.priority = us.getPriority();
        dto.usID = us.getUsID().getID();
        dto.detail = us.getDetail().getValue();
        dto.statement = us.getStatement().getValue();
        dto.usStatus = status;
        return dto;
    }

}
