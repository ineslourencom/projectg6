package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

public class USWithPriorityDTO extends RepresentationModel<USWithPriorityDTO> {
    public double usID;
    public int projeID;
    public String statment;
    public int priority;
}
