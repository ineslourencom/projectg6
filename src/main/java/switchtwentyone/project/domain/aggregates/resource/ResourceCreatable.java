package switchtwentyone.project.domain.aggregates.resource;

import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.Period;

public interface ResourceCreatable {
    Resource createResource(final AccountID associatedAccount, final ResourceID resourceID, final Role associatedRole,
                            final Period allocationPeriod, final LimitedPercentage percentageOfAllocation,
                            final ProjectID projectID, final Monetary costPerHour);
}
