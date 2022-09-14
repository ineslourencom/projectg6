package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Objects;

public class SprintTableDTO extends RepresentationModel<SprintTableDTO> {

    public int sprintNumber;
    public LocalDate startDate;
    public LocalDate endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintTableDTO sprintTableDTO = (SprintTableDTO) o;
        return sprintNumber == sprintTableDTO.sprintNumber
                && startDate == sprintTableDTO.startDate
                && Objects.equals(endDate, sprintTableDTO.endDate);

    }


}
