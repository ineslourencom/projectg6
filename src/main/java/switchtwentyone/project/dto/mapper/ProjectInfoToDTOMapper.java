package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.dto.ProjectInfoDTO;

public class ProjectInfoToDTOMapper {

    private ProjectInfoToDTOMapper(){}

    public static ProjectInfoDTO toDTO(Project project){
        ProjectInfoDTO dto = new ProjectInfoDTO();
        dto.code = project.getIDAsInt();
        dto.description = project.getDescriptionAsString();
        dto.startDate = project.getStartDate();
        dto.endDate = project.getEndDate();
        dto.plannedSprints = project.getPlannedSprintsAsInt();
        dto.sprintDuration = project.getSprintDurationAsInt();
        dto.status = project.getStatusAsString();
        return dto;

    }
}
