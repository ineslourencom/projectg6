package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class ProjectInfoReturnedWhenCreatedDTO extends RepresentationModel<ProjectInfoReturnedWhenCreatedDTO> {

    public int projectID;
    public String name;
    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
    public int sprintDuration;
    public int budget;
    public int CustomerID;
    public String BusinessSectorID;
    public String ProjectTypologyID;
}
