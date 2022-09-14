package switchtwentyone.project.domain.aggregates.resource;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.Period;
@Component
public class ResourceFactory implements ResourceCreatable {


   public ResourceFactory() {

    }



    @Override
    public Resource createResource(AccountID associatedAccount, ResourceID resourceID, Role associatedRole,
                                   Period allocationPeriod, LimitedPercentage percentageOfAllocation, ProjectID projectID, Monetary costPerHour) {
        return new Resource(associatedAccount, resourceID, associatedRole, allocationPeriod, percentageOfAllocation, projectID, costPerHour);
    }
}
