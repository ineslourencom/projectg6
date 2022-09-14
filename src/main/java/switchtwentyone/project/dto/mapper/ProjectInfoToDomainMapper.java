package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.project.PredefinedStatus;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProjectInfoDTO;
import switchtwentyone.project.dto.ProjectInfoDomainDTO;

public class ProjectInfoToDomainMapper {
    private ProjectInfoToDomainMapper() {
        //Not supposed to be instantiated
    }

    public static ProjectInfoDomainDTO<Text, PredefinedStatus>
    toDomain(ProjectInfoDTO dto) {
        Text description = Text.write(dto.description);
        Period period;
        if (dto.endDate == null) {
            period = Period.starting(dto.startDate);
        } else {
            period = Period.between(dto.startDate, dto.endDate);
        }
        PositiveNumber plannedSprints = PositiveNumber.of(dto.plannedSprints);
        PositiveNumber sprintDuration = PositiveNumber.of(dto.sprintDuration);
        PredefinedStatus status = PredefinedStatus.of(dto.status);


        ProjectInfoDomainDTO<Text, PredefinedStatus> domainDTO =
                new ProjectInfoDomainDTO<>();

        domainDTO.description = description;
        domainDTO.period = period;
        domainDTO.plannedSprints = plannedSprints;
        domainDTO.sprintDuration = sprintDuration;
        domainDTO.status = status;

        return domainDTO;
    }
}
