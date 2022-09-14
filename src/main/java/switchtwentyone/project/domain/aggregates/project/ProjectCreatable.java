package switchtwentyone.project.domain.aggregates.project;

import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;

public interface ProjectCreatable {

    Project createProject(final ProjectID projectID, final Nameable name, final Describable description,
                          final Period period, final PositiveNumber sprintDuration,
                          final PositiveNumber budget, final CustomerID customerID, final BusinesSectorID businessSectorID,
                          final ProjectTypologyID projectTypologyID);
}
