package switchtwentyone.project.domain.aggregates.resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResourceTest {

    @Test
    void sameIdentityAsSameResource() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);

        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        //Act
        boolean result = resource.sameIdentityAs(resource);
        //Assert
        assertTrue(result);
    }

    @Test
    void sameIdentityAsDiferentResource() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountIDsecond = AccountID.of(emailsecond);
        int rIdsecond = 2;
        ResourceID resourceIDsecond = new ResourceID(rIdsecond);
        Role rolesecond = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate startsecond = LocalDate.of(2022, 01, 01);
        LocalDate endsecond = LocalDate.of(2022, 02, 25);
        Period datesecond = Period.between(startsecond, endsecond);
        double percentagesecond = 20.0;
        LimitedPercentage alocationsecond = LimitedPercentage.percentage(percentagesecond);
        int pIdsecond = 2;
        ProjectID projectIDsecond = new ProjectID(pIdsecond);
        Monetary costTwo = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource resourceTwo = new Resource(accountIDsecond, resourceIDsecond, rolesecond, datesecond, alocationsecond, projectIDsecond, costTwo);

        //Act
        boolean result = resource.sameIdentityAs(resourceTwo);
        //Assert
        assertFalse(result);
    }

    @Test
    void sameIdentityAsResourceNull() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        //Act
        boolean result = resource.sameIdentityAs(null);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsSameObject() {

        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        //Act
        boolean result = resource.equals(resource);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsObjectSameValue() {

        //Arrange
        Email email = Email.of("luis@gmail.com");
        Email emailTwo = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        AccountID accountIDTwo = AccountID.of(emailTwo);
        int rId = 1;
        int rIdTwo = 1;
        ResourceID resourceID = new ResourceID(rId);
        ResourceID resourceIDTwo = new ResourceID(rIdTwo);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role roleTwo = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        LocalDate startTwo = LocalDate.of(2022, 01, 01);
        LocalDate endTwo = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        Period dateTwo = Period.between(startTwo, endTwo);
        double percentage = 80.0;
        double percentageTwo = 80.0;

        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        LimitedPercentage alocationTwo = LimitedPercentage.percentage(percentageTwo);

        int pId = 1;
        int pIdTwo = 1;
        ProjectID projectID = new ProjectID(pId);
        ProjectID projectIDTwo = new ProjectID(pIdTwo);

        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Monetary costTwo = Monetary.of(15.5, Currency.getInstance("EUR"));


        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        Resource resourceTwo = new Resource(accountIDTwo, resourceIDTwo, roleTwo, dateTwo, alocationTwo, projectIDTwo, costTwo);


        //Act
        boolean result = resource.equals(resourceTwo);
        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsObjectDiferentValue() {

        //Arrange
        Email email = Email.of("luis@gmail.com");
        Email emailTwo = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(email);
        AccountID accountIDTwo = AccountID.of(emailTwo);
        int rId = 1;
        int rIdTwo = 2;
        ResourceID resourceID = new ResourceID(rId);
        ResourceID resourceIDTwo = new ResourceID(rIdTwo);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role roleTwo = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("PO"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        LocalDate startTwo = LocalDate.of(2022, 01, 01);
        LocalDate endTwo = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        Period dateTwo = Period.between(startTwo, endTwo);
        double percentage = 80.0;
        double percentageTwo = 20.0;

        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        LimitedPercentage alocationTwo = LimitedPercentage.percentage(percentageTwo);

        int pId = 1;
        int pIdTwo = 2;
        ProjectID projectID = new ProjectID(pId);
        ProjectID projectIDTwo = new ProjectID(pIdTwo);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Monetary costTwo = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        Resource resourceTwo = new Resource(accountIDTwo, resourceIDTwo, roleTwo, dateTwo, alocationTwo, projectIDTwo, costTwo);


        //Act
        boolean result = resource.equals(resourceTwo);
        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsObjectNotNull() {

        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        //Act
        boolean result = resource.equals(null);
        //Assert
        assertNotNull(result);
    }

    @Test
    void testEqualsDiferentObjetcs() {

        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        int rIdTwo = 1;
        ResourceID resourceIDTwo = new ResourceID(rIdTwo);
        //Act
        boolean result = resource.equals(resourceIDTwo);
        //Assert
        assertFalse(result);
    }

    @Test
    void testHashCodeSameIDAreEquals() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        Email emailTwo = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        AccountID accountIDTwo = AccountID.of(emailTwo);
        int rId = 1;
        int rIdTwo = 1;
        ResourceID resourceID = new ResourceID(rId);
        ResourceID resourceIDTwo = new ResourceID(rIdTwo);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role roleTwo = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        LocalDate startTwo = LocalDate.of(2022, 01, 01);
        LocalDate endTwo = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        Period dateTwo = Period.between(startTwo, endTwo);
        double percentage = 80.0;
        double percentageTwo = 80.0;

        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        LimitedPercentage alocationTwo = LimitedPercentage.percentage(percentageTwo);

        int pId = 1;
        int pIdTwo = 1;
        ProjectID projectID = new ProjectID(pId);
        ProjectID projectIDTwo = new ProjectID(pIdTwo);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Monetary costTwo = Monetary.of(15.5, Currency.getInstance("EUR"));


        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        Resource resourceTwo = new Resource(accountIDTwo, resourceIDTwo, roleTwo, dateTwo, alocationTwo, projectIDTwo, costTwo);

        //Assert
        assertEquals(resource.hashCode(), resourceTwo.hashCode());

    }

    @Test
    void testHasCodeDiferentID() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        Email emailTwo = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(email);
        AccountID accountIDTwo = AccountID.of(emailTwo);
        int rId = 1;
        int rIdTwo = 2;
        ResourceID resourceID = new ResourceID(rId);
        ResourceID resourceIDTwo = new ResourceID(rIdTwo);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role roleTwo = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("PO"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        LocalDate startTwo = LocalDate.of(2022, 01, 01);
        LocalDate endTwo = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        Period dateTwo = Period.between(startTwo, endTwo);
        double percentage = 80.0;
        double percentageTwo = 20.0;

        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        LimitedPercentage alocationTwo = LimitedPercentage.percentage(percentageTwo);

        int pId = 1;
        int pIdTwo = 2;
        ProjectID projectID = new ProjectID(pId);
        ProjectID projectIDTwo = new ProjectID(pIdTwo);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Monetary costTwo = Monetary.of(15.5, Currency.getInstance("EUR"));


        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        Resource resourceTwo = new Resource(accountIDTwo, resourceIDTwo, roleTwo, dateTwo, alocationTwo, projectIDTwo, costTwo);

        assertNotEquals(resource.hashCode(), resourceTwo.hashCode());
    }

    @Test
    void getProjectIDTest() {
        AccountID accountID = mock(AccountID.class);
        ResourceID resourceID = mock(ResourceID.class);
        Role role = mock(Role.class);
        Period date = mock(Period.class);
        LimitedPercentage allocation = mock(LimitedPercentage.class);
        ProjectID projectID = mock(ProjectID.class);
        Monetary cost = mock(Monetary.class);
        ProjectID expected = mock(ProjectID.class);
        when(projectID.clone()).thenReturn(expected);
        Resource resource = new Resource(accountID, resourceID, role, date, allocation, projectID, cost);

        ProjectID actual = resource.getProjectID();

        assertSame(expected, actual);

    }

    @Test
    void ensureGetProjectIDRespectsEncapsulation() {
        AccountID accountID = mock(AccountID.class);
        ResourceID resourceID = mock(ResourceID.class);
        Role role = mock(Role.class);
        Period date = mock(Period.class);
        LimitedPercentage allocation = mock(LimitedPercentage.class);
        ProjectID unexpected = mock(ProjectID.class);
        Monetary cost = mock(Monetary.class);
        Resource resource = new Resource(accountID, resourceID, role, date, allocation, unexpected, cost);

        ProjectID actual = resource.getProjectID();

        assertNotSame(unexpected, actual);

    }


    @Test
    void getAssociatedRole() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role expected = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member -Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(accountID, resourceID, expected, date, alocation, projectID, cost);

        Role result = resource.getAssociatedRole();

        assertEquals(expected, result);
        assertNotSame(expected, result);

    }


    @Test
    void getAssociatedAccount() {
        Email email = Email.of("luis@gmail.com");
        AccountID expected = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member -Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(expected, resourceID, role, date, alocation, projectID, cost);

        AccountID result = resource.getAssociatedAccount();

        assertEquals(expected, result);
        assertNotSame(expected, result);

    }

    @Test
    void getResourceID() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID expected = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member -Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(accountID, expected, role, date, alocation, projectID, cost);

        ResourceID result = resource.getResourceID();

        assertEquals(expected, result);
        assertNotSame(expected, result);

    }

    @Test
    void getAllocationPeriodWithEndDate() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID id = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member -Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Period expected = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(accountID, id, role, expected, alocation, projectID, cost);

        Period result = resource.getAllocationPeriod();

        assertEquals(expected, result);
        assertNotSame(expected, result);

    }

    @Test
    void getAllocationPeriodWithoutEndDate() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID id = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member - Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        Period expected = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(accountID, id, role, expected, alocation, projectID, cost);

        Period result = resource.getAllocationPeriod();

        assertEquals(expected, result);
        assertNotSame(expected, result);

    }

    @Test
    void getPercentageOfAllocationTest() {

        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID id = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member - Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        Period period = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage expected = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(accountID, id, role, period, expected, projectID, cost);

        LimitedPercentage result = resource.getPercentageOfAllocation();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    void getCostPerHour() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID id = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member - Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        Period period = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage allocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary expected = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(accountID, id, role, period, allocation, projectID, expected);

        Monetary result = resource.getCostPerHour();

        assertEquals(expected, result);
        assertNotSame(expected, result);
    }

    @Test
    void hasEmail() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID id = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member - Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        Period period = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage allocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary expected = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(accountID, id, role, period, allocation, projectID, expected);

        boolean result = resource.hasEmail(email);

        assertTrue(result);

    }

    @Test
    void doesNotHaveEmailTest() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID id = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member - Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        Period period = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage allocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary expected = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(accountID, id, role, period, allocation, projectID, expected);
        Email newEmail = Email.of("andreia@gmail.com");

        boolean result = resource.hasEmail(newEmail);

        assertFalse(result);
    }
    @Test
    void doesNotHaveEmailTestNullEmail() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID id = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member - Developer"));
        LocalDate start = LocalDate.of(2022, 01, 01);
        Period period = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage allocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary expected = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource resource = new Resource(accountID, id, role, period, allocation, projectID, expected);

        boolean result = resource.hasEmail(null);

        assertFalse(result);
    }

    @Test
    void isActive() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(1);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);

        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        //Act
        boolean result = newResource.isActive();

        //Act
        assertTrue(result);
    }

    @Test
    void isInactiveTest() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().plusDays(10);
        LocalDate end = LocalDate.now().plusDays(15);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);

        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        //Act
        boolean result = newResource.isActive();

        assertFalse(result);
    }
    @Test
    void isActiveTestNullEndDate() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().minusDays(10);
        Period date = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);

        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        //Act
        boolean result = newResource.isActive();

        assertTrue(result);
    }

    @Test
    void isInactiveTestNullEndDate() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().plusDays(10);
        Period date = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);

        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));

        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        //Act
        boolean result = newResource.isActive();

        assertFalse(result);
    }

    @Test
    void hasRole() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().plusDays(10);
        Period date = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        Role roleTest = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));

        //Act
        boolean result = newResource.hasRole(roleTest);

        assertTrue(result);
    }
    @Test
    void doesntHaveRole() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().plusDays(10);
        Period date = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        Role roleTest = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Developer"));

        //Act
        boolean result = newResource.hasRole(roleTest);

        assertFalse(result);
    }

    @Test
    void hasProjectID() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().plusDays(10);
        Period date = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);


        //Act
        boolean result = newResource.hasProjectID(projectID);

        assertTrue(result);
    }
    @Test
    void doesNotHaveProjectID() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().plusDays(10);
        Period date = Period.starting(start);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        ProjectID otherID = new ProjectID(2);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);


        //Act
        boolean result = newResource.hasProjectID(otherID);

        assertFalse(result);
    }

    @Test
    void changeEndDateSuccessTest() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(15);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        boolean result = newResource.changeEndDate(LocalDate.now().plusDays(10));

        assertTrue(result);

    }
    @Test
    void changeEndDateUnsuccessfulTest() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().plusDays(10);
        LocalDate end = LocalDate.now().plusDays(15);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        boolean result = newResource.changeEndDate(LocalDate.now());

        assertFalse(result);
    }

    @Test
    void isActiveInDateTest() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().plusDays(10);
        LocalDate end = LocalDate.now().plusDays(15);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        boolean result = newResource.isActiveInDate(LocalDate.now().plusDays(10));

        assertTrue(result);
    }
    @Test
    void isNotActiveInDateTest() {
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.now().plusDays(10);
        LocalDate end = LocalDate.now().plusDays(15);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR"));
        Resource newResource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);

        boolean result = newResource.isActiveInDate(LocalDate.now());

        assertFalse(result);
    }
}