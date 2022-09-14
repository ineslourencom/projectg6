package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class SprintInfoForTaskDTO extends RepresentationModel<SprintInfoForTaskDTO> {
    public double sprintID;
    public int sprintNumber;
    public LocalDate startDate;

}
