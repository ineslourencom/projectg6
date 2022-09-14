package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.dto.*;

public class UserStoryMapper {

    private UserStoryMapper() {
        //Not supposed to be instantiated
    }

    public static USDTO toDTO(UserStory us) {

        USDTO dto = new USDTO();
        dto.storyNumber = us.getStoryNumber().getNumber();
        dto.priority = us.getPriority();
        dto.usID = us.getUsID().getID();
        dto.detail = us.getDetail().getValue();
        dto.isDecomposed = us.getIsDecomposed();
        if (us.getParent() != null) {
            dto.parent = us.getParent().getID();
        }
        dto.projID = us.getProjectID().getProjectID();
        dto.statement = us.getStatement().getValue();
        return dto;

    }

    public static USShortDTO toShortDTO(UserStory us) {

        USShortDTO dto = new USShortDTO();
        dto.storyNumber = us.getStoryNumber().getNumber();
        dto.priority = us.getPriority();
        dto.usID = us.getUsID().getID();
        dto.detail = us.getDetail().getValue();
        dto.projID = us.getProjectID().getProjectID();
        dto.statement = us.getStatement().getValue();
        return dto;
    }

    public static ScrumBoardDTO toScrumBoardDTO(UserStory us, String status, double sprintID) {
        ScrumBoardDTO dto = new ScrumBoardDTO();
        dto.storyNumber = us.getStoryNumber().getNumber();
        dto.priority = us.getPriority();
        dto.statement = us.getStatement().getValue();
        dto.usStatus = status;
        dto.sprintID = sprintID;
        dto.usID = us.getUsID().getID();
        return dto;
    }

    public static USMinimalInfoDTO toMinimalDTO(UserStory us) {
        USMinimalInfoDTO usMinimalInfoDTO = new USMinimalInfoDTO();
        usMinimalInfoDTO.usID = us.getUsID().getID();
        usMinimalInfoDTO.storyNumber = us.getStoryNumber().getNumber();
        usMinimalInfoDTO.statement = us.getStatement().getValue();
        return usMinimalInfoDTO;
    }

    public static USWithPriorityDTO toPriorityDTO (UserStory us){
        USWithPriorityDTO usWithPriorityDTO = new USWithPriorityDTO();
        usWithPriorityDTO.usID = us.getUsID().getID();
        usWithPriorityDTO.projeID = us.getProjectID().getProjectID();
        usWithPriorityDTO.statment = us.getStatement().getValue();
        usWithPriorityDTO.priority = us.getPriority();
        return usWithPriorityDTO;
    }
}
