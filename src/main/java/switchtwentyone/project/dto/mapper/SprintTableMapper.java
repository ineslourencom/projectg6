package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.dto.SprintDTO;
import switchtwentyone.project.dto.SprintTableDTO;

public class SprintTableMapper {

    private SprintTableMapper (){}

    public static SprintTableDTO tableMapperToDTO (Sprint sprint){
        SprintTableDTO dto= new SprintTableDTO();
        dto.sprintNumber=sprint.getSprintNumber();
        dto.startDate=sprint.getStartDate();
        dto.endDate=sprint.getEndDate();

        return dto;
    }
}
