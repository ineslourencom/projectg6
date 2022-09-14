package switchtwentyone.project.datamodel;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class ResourceJPATest {

    @Test
    void getResourceID() {

        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        ResourceID expected = resourceID;
        ResourceID result = resourceJPA.getResourceID();

        assertEquals(expected, result);
    }

    @Test
    void getRole() {

        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        String expected = role;
        String result = resourceJPA.getRole();

        assertEquals(expected, result);
    }

    @Test
    void getRoleDescription() {

        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        String expected = associatedRoleDes;
        String result = resourceJPA.getRoleDescription();

        assertEquals(expected, result);
    }

    @Test
    void getStartDate() {

        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        LocalDate expected = start;
        LocalDate result = resourceJPA.getStartDate();

        assertEquals(expected, result);
    }

    @Test
    void getEndDate() {
        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        LocalDate expected = end;
        LocalDate result = resourceJPA.getEndDate();

        assertEquals(expected, result);
    }

    @Test
    void getPercentageOfAllocation() {
        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        Double expected = percent;
        Double result = resourceJPA.getPercentageOfAllocation();

        assertEquals(expected, result);
    }

    @Test
    void getProjectID() {
        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        ProjectID expected = projectID;
        ProjectID result = resourceJPA.getProjectID();

        assertEquals(expected, result);
    }

    @Test
    void getCostPerHour() {
        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        Double expected = cost;
        Double result = resourceJPA.getCostPerHour();

        assertEquals(expected, result);
    }

    @Test
    void getCurrency() {
        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        Currency expected = eur;
        Currency result = resourceJPA.getCurrency();

        assertEquals(expected, result);
    }

    @Test
    void getAssociatedAccount() {
        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);
        AccountID expected = accountID;
        AccountID result = resourceJPA.getAssociatedAccount();

        assertEquals(expected, result);
    }

    @Test
    void testEquals_ToItself() {
        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);

        Boolean result = resourceJPA.equals(resourceJPA);
        assertTrue(result);
    }

    @Test
    void testEquals_EqualObjects() {
        ResourceID resourceID = ResourceID.of(1);
        String role = "Developer";
        String associatedRoleDes = "Developer";
        LocalDate start = LocalDate.of(2022, 01, 01);
        LocalDate end = LocalDate.of(2022, 02, 25);
        Double percent = 0.5;
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);
        Double cost = 12.5;
        Currency eur = Currency.getInstance("EUR");

        Email emailsecond = Email.of("lui@gmail.com");
        AccountID accountID = AccountID.of(emailsecond);

        ResourceJPA resourceJPA = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);

        ResourceJPA resourceJPATwo = new ResourceJPA(resourceID, role, associatedRoleDes, start, end, percent, projectID, cost, eur, accountID);

        Boolean result = resourceJPA.equals(resourceJPATwo);
        assertTrue(result);
    }

}

