package switchtwentyone.project.domain.services;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Period;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceValidateResource {

    private static final int ALLOCATION_MAXIMUM = 1;

    /**This method ensures a person's(account) cummulated percentage of allocation
     * in all projects they participate does not exceed the allowed maximum
     *
     * @param resources the resources, referring to an account, against which to perform the validation
     * @param period the period during which
     * @param percentage the allocation percentage
     * @return true if new allocation percentage is valid
     */

    public boolean validatePercentageOfAllocation(List<Resource> resources,
                                                  Period period,
                                                  LimitedPercentage percentage) {
        boolean isValid = true;
        LocalDate startDate = period.getStartingDate();
        LocalDate endDate = period.getEndingDate();
        while (!startDate.isAfter(endDate)) {
            double perc = percentage.getPercentValue();
            for (Resource r : resources) {
                if (r.isActiveInDate(startDate)) {
                    perc += r.getPercentageOfAllocation().getPercentValue();
                }
            }
            if (perc > ALLOCATION_MAXIMUM) {
                isValid = false;
            }
            startDate = startDate.plusDays(1);
        }
        return isValid;
    }

    /** Validates that a resource
     *
     * @param resources
     * @param period
     * @param role
     * @return
     */

    public boolean validateRoleIsUniqueInPeriod(List<Resource> resources, Period period, Role role) {
        boolean isValid = true;
        LocalDate startDate = period.getStartingDate();
        LocalDate endDate = period.getEndingDate();
        while (!startDate.isAfter(endDate)) {
            for (Resource r : resources) {
                if (r.isActiveInDate(startDate) && r.hasRole(role)) {
                        isValid = false;

                }
            }

            startDate = startDate.plusDays(1);
        }

        return isValid;
    }

    /**
     *
     * @param resources
     * @param period
     * @param email
     * @return
     */
    public boolean validateResourceIsNotRepeated(List<Resource> resources, Period period, Email email) {
        boolean isValid = true;
        LocalDate startDate = period.getStartingDate();
        LocalDate endDate = period.getEndingDate();
        while (!startDate.isAfter(endDate)) {

            for (Resource r : resources) {
                if (r.isActiveInDate(startDate) && r.hasEmail(email)) {
                        isValid = false;

                }
            }

            startDate = startDate.plusDays(1);
        }
        return isValid;
    }

}
