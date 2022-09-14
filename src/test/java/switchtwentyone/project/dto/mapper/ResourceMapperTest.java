package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.dto.ResourceDTO;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceMapperTest {

    @Test
    void ensureDTOIsCreatedAsExpected(){
        Resource resource = mock(Resource.class);
        when(resource.getProjectID()).thenReturn(new ProjectID(1));
        when(resource.getResourceID()).thenReturn(new ResourceID(2));
        when(resource.getAllocationPeriod()).thenReturn(
                Period.between(LocalDate.of(1993,7,8),
                LocalDate.of(1993,9, 5)));

        when(resource.getAssociatedAccount()).thenReturn(AccountID.of(Email.of("123@isep.pt")));
        when(resource.getCostPerHour()).thenReturn(Monetary.of(12, Currency.getInstance("EUR")));
        when(resource.getPercentageOfAllocation()).thenReturn(LimitedPercentage.percentage(100));
        when(resource.getAssociatedRole()).thenReturn(new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription())));


        ResourceDTO expected = new ResourceDTO();
        expected.associatedAccount = "123@isep.pt";
        expected.resourceID = 2;
        expected.associatedRole = "PRODUCT_OWNER";
        expected.startDate = LocalDate.of(1993,7,8);
        expected.endDate = LocalDate.of(1993,9, 5);
        expected.percentageOfAllocation = 1;
        expected.currency = "EUR";
        expected.projectID = 1;
        expected.costPerHourValue = 12;
        ResourceMapper mapper = new ResourceMapper();
        ResourceDTO actual = mapper.toDTO(resource);

        assertEquals(expected, actual);

    }

}