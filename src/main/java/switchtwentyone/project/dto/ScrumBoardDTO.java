package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

public class ScrumBoardDTO extends RepresentationModel<USInfoAndStatusDTO> {

    public int storyNumber;
    public String statement;
    public int priority;
    public String usStatus;
    public double sprintID;
    public double usID;

}
