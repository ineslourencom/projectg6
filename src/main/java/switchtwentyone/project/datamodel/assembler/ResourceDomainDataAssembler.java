package switchtwentyone.project.datamodel.assembler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.*;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.datamodel.ResourceJPA;

import java.time.LocalDate;
import java.util.Currency;

@Service
public class ResourceDomainDataAssembler {


    @Autowired
    ResourceCreatable resourceFactory;

    public Resource toDomain(ResourceJPA resourceJPA) {

        ResourceID resourceID = resourceJPA.getResourceID();

        String role = resourceJPA.getRole();
        String roleDescription = resourceJPA.getRoleDescription();

        LocalDate startDate = resourceJPA.getStartDate();
        LocalDate endDate = resourceJPA.getEndDate();
        Period period;
        if (endDate == null) {
            period = Period.starting(startDate);
        } else {
            period = Period.between(startDate, endDate);
        }

        NoNumberNoSymbolString roleDesc = NoNumberNoSymbolString.of(roleDescription);
        Role resourceRole = new Role(Erole.valueOf(role), roleDesc);

        AccountID accountID = resourceJPA.getAssociatedAccount();

        LimitedPercentage percAllocation = LimitedPercentage.decimal(resourceJPA.getPercentageOfAllocation());

        ProjectID projectID = resourceJPA.getProjectID();

        double cost = resourceJPA.getCostPerHour();
        Currency currency = resourceJPA.getCurrency();
        Monetary costPerHour = Monetary.of(cost, currency);


        return resourceFactory.createResource(accountID, resourceID, resourceRole, period, percAllocation, projectID, costPerHour);
    }

    public ResourceJPA toData(Resource resource) {

        ProjectID projectID = resource.getProjectID();
        String role = resource.getAssociatedRole().getRoleAsString();
        String roleDescription = resource.getAssociatedRole().getDescriptionAsString();
        LocalDate startDate = resource.getAllocationPeriod().getStartingDate();
        LocalDate endDate = resource.getAllocationPeriod().getEndingDate();
        double percentageOfAllocation = resource.getPercentageOfAllocation().getPercentValue();
        double costPerHour = resource.getCostPerHour().getAmount();
        Currency currency = resource.getCostPerHour().getCurrency();
        AccountID associatedAccount = resource.getAssociatedAccount();
        ResourceID resourceID = resource.getResourceID();


        return new ResourceJPA(resourceID, role, roleDescription, startDate, endDate,
                percentageOfAllocation, projectID, costPerHour,
                currency, associatedAccount);


    }
}
