package switchtwentyone.project.domain.aggregates.task;

import javax.persistence.Embeddable;

@Embeddable
public interface ContainerID{

    double getID();

    String getIDForTask();
}
