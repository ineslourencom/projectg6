package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class TaskCreationReturnedDTO extends RepresentationModel<TaskCreationReturnedDTO> {
    public int taskID;
    public String name;
    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
    public int effortEstimate;
    public String type;
    public String resourceID;
    public double  containerID;
}
