package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class TaskCreationDTO extends RepresentationModel<TaskCreationDTO> {
    public String name;
    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
    public String effortEstimate;
    public String type;
    public String resourceID;
    public String option;
    public double  containerID;
}
