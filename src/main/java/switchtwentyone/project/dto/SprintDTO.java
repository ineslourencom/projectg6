package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class SprintDTO extends RepresentationModel<SprintDTO> {
    public double sprintID;
    public LocalDate startDate;
    public int projectID;
    public int sprintNumber;
    public int sprintDuration;
    public List<SprintBacklogItemDTO> sprintBacklogItemDTOList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintDTO sprintDTO = (SprintDTO) o;
        return sprintID == sprintDTO.sprintID && projectID == sprintDTO.projectID && Objects.equals(startDate, sprintDTO.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID, startDate, projectID);
    }

}
