package switchtwentyone.project.dto.mapper;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.dto.ResourceDTO;

@Service
public class ResourceMapper {

    public ResourceDTO toDTO(Resource resource){
        ResourceDTO dto = new ResourceDTO();
        dto.associatedAccount = resource.getAssociatedAccount().toString();
        dto.resourceID = resource.getResourceID().getResourceID();
        dto.associatedRole = resource.getAssociatedRole().getRoleAsString();
        dto.startDate = resource.getAllocationPeriod().getStartingDate();
        dto.endDate = resource.getAllocationPeriod().getEndingDate();
        dto.percentageOfAllocation = resource.getPercentageOfAllocation().getPercentValue();
        dto.costPerHourValue = resource.getCostPerHour().getAmount();
        dto.currency = resource.getCostPerHour().getCurrency().getCurrencyCode();
        dto.projectID = resource.getProjectID().getProjectID();
        return dto;
    }

}
