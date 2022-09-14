package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;


public class SprintBacklogItemDTO extends RepresentationModel<SprintBacklogItemDTO> {

    public String itemID;
    public double usID;
    public String category;
    public int effortEstimate;

}
