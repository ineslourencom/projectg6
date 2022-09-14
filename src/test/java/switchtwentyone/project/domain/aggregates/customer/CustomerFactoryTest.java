package switchtwentyone.project.domain.aggregates.customer;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomerFactoryTest {

    @Test
    void createCustomer() {
        CustomerID customerID = mock(CustomerID.class);
        NIF nif = mock(NIF.class);
        NoNumberNoSymbolString name = mock(NoNumberNoSymbolString.class);

        CustomerFactory customerFactory = new CustomerFactory();
        Customer One = customerFactory.createCustomer(customerID, name);
        Customer Two = customerFactory.createCustomer(customerID, name);

        assertEquals(One, Two);
    }
}