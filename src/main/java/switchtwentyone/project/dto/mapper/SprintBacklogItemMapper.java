package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.sprint.*;
import switchtwentyone.project.dto.SprintBacklogItemDTO;

public class SprintBacklogItemMapper {

    private SprintBacklogItemMapper () {
    }

    public static SprintBacklogItemDTO toDTO(SprintBacklogItem sprintBacklogItem) {
        SprintBacklogItemDTO dto = new SprintBacklogItemDTO();
        dto.itemID = sprintBacklogItem.getItemID().toString();
        dto.usID = sprintBacklogItem.getUsID().getID();
        dto.category = sprintBacklogItem.getCategory().toString();
        dto.effortEstimate = sprintBacklogItem.getEffortEstimate().getNumber();
        return dto;

    }
}
