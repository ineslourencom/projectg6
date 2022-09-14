package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintBacklogItem;
import switchtwentyone.project.dto.SprintBacklogItemDTO;
import switchtwentyone.project.dto.SprintDTO;
import switchtwentyone.project.dto.SprintInfoForTaskDTO;
import java.util.ArrayList;
import java.util.List;

public class SprintMapper {
    private SprintMapper() {
    }

    public static SprintDTO toDTO(Sprint sprint) {
        SprintDTO dto = new SprintDTO();
        dto.sprintID = sprint.getSprintIDDouble();
        dto.startDate = sprint.getStartDate();
        dto.projectID = sprint.getProjectID();
        dto.sprintNumber = sprint.getSprintNumber();
        dto.sprintDuration = sprint.getSprintDuration().getNumber();
        List<SprintBacklogItem> itemList = sprint.getSprintBacklogItems();
        List<SprintBacklogItemDTO> itemDTOList = new ArrayList<>();
        for (SprintBacklogItem item : itemList) {
            SprintBacklogItemDTO itemDTO = SprintBacklogItemMapper.toDTO(item);
            itemDTOList.add(itemDTO);
        }
        dto.sprintBacklogItemDTOList = itemDTOList;
        return dto;

    }

    public static SprintInfoForTaskDTO toDTOForTask(Sprint sprint) {
        SprintInfoForTaskDTO sprintInfoForTaskDTO = new SprintInfoForTaskDTO();
        sprintInfoForTaskDTO.sprintID = sprint.getSprintIDDouble();
        sprintInfoForTaskDTO.sprintNumber = sprint.getSprintNumber();
        sprintInfoForTaskDTO.startDate = sprint.getStartDate();
        return sprintInfoForTaskDTO;
    }


}

