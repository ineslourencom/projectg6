package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

public class TaskStatusDTO extends RepresentationModel<TaskStatusDTO> {

    public String taskNumber;
    public String taskName;
    public String belongsTo;
    public String containerId;
    public String status;
    public double percOfExec;


}
