package switchtwentyone.project.datamodel.assembler;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.*;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.datamodel.ResourceJPA;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceDomainDataAssemblerTest {

   @Mock
    ResourceFactory resourceFactory;

    @InjectMocks
    ResourceDomainDataAssembler assembler;

    @Test
    void toDomainWithEndDateTest() {

        AccountID accountID = AccountID.of(Email.of("1211795@isep.ipp.pt"));
        ResourceID resourceID = new ResourceID(5);
        ProjectID projectID = new ProjectID(1);
        Role role = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Description"));
        Period period = Period.between(LocalDate.of(1995, 07, 13), LocalDate.of(2008, 05, 3));
        LimitedPercentage percentage = LimitedPercentage.percentage(0.5);
        Monetary costPerHour = Monetary.of(12.5, Currency.getInstance("EUR"));

        ResourceJPA resourceJPA = mock(ResourceJPA.class);
        when(resourceJPA.getResourceID()).thenReturn(resourceID);
        when(resourceJPA.getRole()).thenReturn("PRODUCT_OWNER");
        when(resourceJPA.getRoleDescription()).thenReturn("Description");
        when(resourceJPA.getStartDate()).thenReturn(LocalDate.of(1995, 07, 13));
        when(resourceJPA.getEndDate()).thenReturn(LocalDate.of(2008, 05, 3));
        when(resourceJPA.getAssociatedAccount()).thenReturn(accountID);
        when(resourceJPA.getPercentageOfAllocation()).thenReturn(0.5);
        when(resourceJPA.getProjectID()).thenReturn(projectID);
        when(resourceJPA.getCostPerHour()).thenReturn(12.5);
        when(resourceJPA.getCurrency()).thenReturn(Currency.getInstance("EUR"));


        Resource expected = resourceFactory.createResource(accountID,resourceID,role,period,percentage,projectID,costPerHour);

        Resource result = assembler.toDomain(resourceJPA);


        assertEquals(expected, result);
    }

    @Test
    void toDomainWithoutEndDateTest() {
        AccountID accountID = AccountID.of(Email.of("1211795@isep.ipp.pt"));
        ResourceID resourceID = new ResourceID(5);
        ProjectID projectID = new ProjectID(1);
        Role role = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Description"));
        Period period = Period.starting(LocalDate.of(1995, 07, 13));
        LimitedPercentage percentage = LimitedPercentage.percentage(0.5);
        Monetary costPerHour = Monetary.of(12.5, Currency.getInstance("EUR"));

        ResourceJPA resourceJPA = mock(ResourceJPA.class);
        when(resourceJPA.getResourceID()).thenReturn(resourceID);
        when(resourceJPA.getRole()).thenReturn("PRODUCT_OWNER");
        when(resourceJPA.getRoleDescription()).thenReturn("Description");
        when(resourceJPA.getStartDate()).thenReturn(LocalDate.of(1995, 07, 13));
        when(resourceJPA.getEndDate()).thenReturn(null);
        when(resourceJPA.getAssociatedAccount()).thenReturn(accountID);
        when(resourceJPA.getPercentageOfAllocation()).thenReturn(0.5);
        when(resourceJPA.getProjectID()).thenReturn(projectID);
        when(resourceJPA.getCostPerHour()).thenReturn(12.5);
        when(resourceJPA.getCurrency()).thenReturn(Currency.getInstance("EUR"));

        Resource expected = resourceFactory.createResource(accountID, resourceID, role, period, percentage, projectID, costPerHour);
        Resource result = assembler.toDomain(resourceJPA);


        assertEquals(expected, result);
    }


    @Test
    void toDataTestWhithoutEndDate() {

        AccountID accountID = AccountID.of(Email.of("1211795@isep.ipp.pt"));
        ResourceID resourceID = new ResourceID(5);
        ProjectID projectID = new ProjectID(1);
        Role role = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Description"));
        Period period = Period.starting(LocalDate.of(1995, 07, 13));
        LimitedPercentage percentage = LimitedPercentage.decimal(0.5);
        Monetary costPerHour = Monetary.of(12, Currency.getInstance("EUR"));

        Resource resource = mock(Resource.class);
        when(resource.getResourceID()).thenReturn(resourceID);
        when(resource.getAssociatedRole()).thenReturn(role);
        when(resource.getAllocationPeriod()).thenReturn(period);
        when(resource.getPercentageOfAllocation()).thenReturn(percentage);
        when(resource.getProjectID()).thenReturn(projectID);
        when(resource.getCostPerHour()).thenReturn(costPerHour);
        when(resource.getAssociatedAccount()).thenReturn(accountID);

        ResourceJPA expected = new ResourceJPA(resourceID, "PRODUCT_OWNER", "Description",
                LocalDate.of(1995, 07, 13),
                null, 0.5, projectID,
                12, Currency.getInstance("EUR"),
                accountID);

        ResourceJPA result = assembler.toData(resource);

        assertNotNull(result);
        assertEquals(expected, result);

    }
}
