package switchtwentyone.project.domain.aggregates.project;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectFactoryTest {

    @Test
    void ensureInstanceIsAlwaysTheSame() {
        ProjectFactory instanceOne = ProjectFactory.getInstance();
        ProjectFactory instanceTwo = ProjectFactory.getInstance();

        assertTrue(instanceOne == instanceTwo);

    }

    @Test
    void ensureInstanceIsNeverNull() {
        ProjectFactory instance = ProjectFactory.getInstance();

        assertNotNull(instance);

    }

    @Test
    void ensureCreationSucceeds() {
        ProjectID id = mock(ProjectID.class);
        Nameable name = mock(Nameable.class);
        Describable description = mock(Describable.class);
        Period period = mock(Period.class);
        PositiveNumber number = mock(PositiveNumber.class);
        CustomerID customer = mock(CustomerID.class);
        BusinesSectorID business = mock(BusinesSectorID.class);
        ProjectTypologyID tid = mock(ProjectTypologyID.class);

        Project result = ProjectFactory
                .getInstance()
                .createProject(id, name, description, period, number, number, customer, business, tid);

        assertNotNull(result);
    }


}