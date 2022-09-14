package switchtwentyone.project.domain.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServiceValidateResourceTest {

    @Test
    void validatePercentageOfAllocationIsValidTest() {
        List<Resource> resourceList = new ArrayList<>();
        Resource resourceOne = mock(Resource.class);
        Resource resourceTwo = mock(Resource.class);
        Resource resourceThree = mock(Resource.class);
        resourceList.add(resourceOne);
        resourceList.add(resourceTwo);
        resourceList.add(resourceThree);
        when(resourceOne.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceTwo.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceThree.isActiveInDate(LocalDate.of(1993, 7, 13))).thenReturn(true);

        when(resourceOne.getPercentageOfAllocation()).thenReturn(LimitedPercentage.percentage(30));
        when(resourceTwo.getPercentageOfAllocation()).thenReturn(LimitedPercentage.percentage(20));
        when(resourceThree.getPercentageOfAllocation()).thenReturn(LimitedPercentage.percentage(50));
        Period period = Period.between(LocalDate.of(1993, 7, 13),
                LocalDate.of(1993, 8, 13));
        LimitedPercentage percentage = LimitedPercentage.percentage(50);
        ServiceValidateResource serviceValidateResource = new ServiceValidateResource();

        boolean result = serviceValidateResource.validatePercentageOfAllocation(resourceList, period, percentage);
        assertTrue(result);


    }

    @Test
    void validatePercentageOfAllocationIsInvalidTest() {
        List<Resource> resourceList = new ArrayList<>();
        Resource resourceOne = mock(Resource.class);
        Resource resourceTwo = mock(Resource.class);
        Resource resourceThree = mock(Resource.class);
        resourceList.add(resourceOne);
        resourceList.add(resourceTwo);
        resourceList.add(resourceThree);
        when(resourceOne.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceTwo.isActiveInDate(LocalDate.of(1993, 7, 13))).thenReturn(true);
        when(resourceTwo.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceThree.isActiveInDate(LocalDate.of(1993, 7, 13))).thenReturn(true);

        when(resourceOne.getPercentageOfAllocation()).thenReturn(LimitedPercentage.percentage(30));
        when(resourceTwo.getPercentageOfAllocation()).thenReturn(LimitedPercentage.percentage(30));
        when(resourceThree.getPercentageOfAllocation()).thenReturn(LimitedPercentage.percentage(50));
        Period period = Period.between(LocalDate.of(1993, 7, 13),
                LocalDate.of(1993, 8, 13));
        LimitedPercentage percentage = LimitedPercentage.percentage(50);
        ServiceValidateResource serviceValidateResource = new ServiceValidateResource();


        boolean result = serviceValidateResource.validatePercentageOfAllocation(resourceList, period, percentage);
        assertFalse(result);
    }

    @Test
    void validateRoleIsUniqueInPeriodIsValidTest() {
        List<Resource> resourceList = new ArrayList<>();
        Resource resourceOne = mock(Resource.class);
        Resource resourceTwo = mock(Resource.class);
        Resource resourceThree = mock(Resource.class);
        resourceList.add(resourceOne);
        resourceList.add(resourceTwo);
        resourceList.add(resourceThree);
        when(resourceOne.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceTwo.isActiveInDate(LocalDate.of(1993, 7, 13))).thenReturn(true);
        when(resourceTwo.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceThree.isActiveInDate(LocalDate.of(1993, 7, 13))).thenReturn(true);
        Role role = mock(Role.class);
        ServiceValidateResource serviceValidateResource = new ServiceValidateResource();

        Period period = Period.between(LocalDate.of(1993, 7, 13),
                LocalDate.of(1993, 8, 13));

        boolean result = serviceValidateResource.validateRoleIsUniqueInPeriod(resourceList, period, role);
        assertTrue(result);

    }

    @Test
    void validateRoleIsUniqueInPeriodIsInvalidTest() {
        List<Resource> resourceList = new ArrayList<>();
        Resource resourceOne = mock(Resource.class);
        Resource resourceTwo = mock(Resource.class);
        Resource resourceThree = mock(Resource.class);
        resourceList.add(resourceOne);
        resourceList.add(resourceTwo);
        resourceList.add(resourceThree);
        when(resourceOne.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceTwo.isActiveInDate(LocalDate.of(1993, 7, 13))).thenReturn(true);
        when(resourceTwo.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceThree.isActiveInDate(LocalDate.of(1993, 7, 13))).thenReturn(true);
        Role role = mock(Role.class);

        when(resourceOne.hasRole(role)).thenReturn(true);

        Period period = Period.between(LocalDate.of(1993, 7, 13),
                LocalDate.of(1993, 8, 13));
        ServiceValidateResource serviceValidateResource = new ServiceValidateResource();

        boolean result = serviceValidateResource.validateRoleIsUniqueInPeriod(resourceList, period, role);
        assertFalse(result);

    }

    @Test
    void validateResourceIsNotRepeatedIsValidTest() {
        List<Resource> resourceList = new ArrayList<>();
        Resource resourceOne = mock(Resource.class);
        Resource resourceTwo = mock(Resource.class);
        Resource resourceThree = mock(Resource.class);
        resourceList.add(resourceOne);
        resourceList.add(resourceTwo);
        resourceList.add(resourceThree);
        when(resourceOne.getAllocationPeriod()).thenReturn(Period.between(LocalDate.of(1993, 7, 20),
                LocalDate.of(1993, 8, 13)));
        when(resourceTwo.getAllocationPeriod()).thenReturn(Period.between(LocalDate.of(1993, 7, 30),
                LocalDate.of(1993, 8, 13)));
        when(resourceThree.getAllocationPeriod()).thenReturn(Period.between(LocalDate.of(1993, 6, 20),
                LocalDate.of(1993, 7, 19)));
        Email email = mock(Email.class);

        Period period = Period.between(LocalDate.of(1993, 7, 13),
                LocalDate.of(1993, 8, 13));

        ServiceValidateResource serviceValidateResource = new ServiceValidateResource();

        boolean result = serviceValidateResource.validateResourceIsNotRepeated(resourceList, period, email);
        assertTrue(result);

    }

    @Test
    void validateResourceIsNotRepeatedIsInvalidTest() {
        List<Resource> resourceList = new ArrayList<>();
        Resource resourceOne = mock(Resource.class);
        Resource resourceTwo = mock(Resource.class);
        Resource resourceThree = mock(Resource.class);
        resourceList.add(resourceOne);
        resourceList.add(resourceTwo);
        resourceList.add(resourceThree);
        when(resourceOne.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceTwo.isActiveInDate(LocalDate.of(1993, 7, 13))).thenReturn(true);
        when(resourceTwo.isActiveInDate(LocalDate.of(1993, 8, 13))).thenReturn(true);
        when(resourceThree.isActiveInDate(LocalDate.of(1993, 7, 13))).thenReturn(true);
        Email email = mock(Email.class);
        when(resourceTwo.hasEmail(email)).thenReturn(true);

        Period period = Period.between(LocalDate.of(1993, 7, 13),
                LocalDate.of(1993, 8, 13));
        ServiceValidateResource serviceValidateResource = new ServiceValidateResource();

        boolean result = serviceValidateResource.validateResourceIsNotRepeated(resourceList, period, email);

        assertFalse(result);
    }

}