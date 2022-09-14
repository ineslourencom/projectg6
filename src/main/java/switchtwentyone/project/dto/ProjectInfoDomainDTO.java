package switchtwentyone.project.dto;

import switchtwentyone.project.domain.shared.IStatus;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

public class ProjectInfoDomainDTO<D extends Describable, S extends IStatus> {
    public D description;
    public S status;
    public PositiveNumber plannedSprints;
    public  PositiveNumber sprintDuration;
    public Period period;

}
