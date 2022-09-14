package switchtwentyone.project.domain.aggregates.resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

@SpringBootTest
class ResourceFactoryTest {

@Autowired ResourceFactory resourceFactory;

    @Test
    void createResource() {
        //Arrange
        Email email = Email.of("luis@gmail.com");
        AccountID accountID = AccountID.of(email);
        int rId = 1;
        ResourceID resourceID = new ResourceID(rId);
        Role role = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 2, 25);
        Period date = Period.between(start, end);
        double percentage = 80.0;
        LimitedPercentage alocation = LimitedPercentage.percentage(percentage);
        int pId = 1;
        ProjectID projectID = new ProjectID(pId);

        Monetary cost = Monetary.of(15.5, Currency.getInstance("EUR") );

        //Act
        Resource resource = new Resource(accountID, resourceID, role, date, alocation, projectID, cost);
        Resource newResource = resourceFactory.createResource(accountID, resourceID, role, date, alocation, projectID, cost);

        //Assert
        assertEquals(resource, newResource);
    }
}