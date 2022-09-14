package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

public class USInfoAndStatusDTO extends RepresentationModel<USInfoAndStatusDTO> {

    public double usID;
    public int storyNumber;
    public String statement;
    public String detail;
    public int priority;
    public String usStatus;


}
