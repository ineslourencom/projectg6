package switchtwentyone.project.domain.aggregates.customer;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;

import static org.junit.jupiter.api.Assertions.*;

class CustomerIDTest {

    @Test
    void CustomerIDWithSameIDAreEquals() {
        NIF nif = NIF.of(257715347);

        CustomerID customerIDOne = CustomerID.of(nif);
        CustomerID customerIDTwo = CustomerID.of(nif);

        assertEquals(customerIDOne, customerIDTwo);
    }

    @Test
    void CustomerIDWithDifferentIDAreNotEquals() {
        NIF nif = NIF.of(257715347);
        NIF nifTwo = NIF.of(506943119);

        CustomerID customerIDOne = CustomerID.of(nif);
        CustomerID customerIDTwo = CustomerID.of(nifTwo);

        assertNotEquals(customerIDOne, customerIDTwo);
    }

    @Test
    void CustomerIDEqualToItself() {
        NIF nif = NIF.of(257715347);

        CustomerID customerIDOne = CustomerID.of(nif);

        assertEquals(customerIDOne, customerIDOne);
    }

    @Test
    void CustomerIDWithSameIDHaveSameHashCode() {
        NIF nif = NIF.of(257715347);

        CustomerID customerIDOne = CustomerID.of(nif);
        CustomerID customerIDTwo = CustomerID.of(nif);

        assertEquals(customerIDOne.hashCode(), customerIDTwo.hashCode());
    }

    @Test
    void CustomerIDWithDifferentIDHaveDifferentHashCode() {
        NIF nif = NIF.of(257715347);
        NIF nifTwo = NIF.of(506943119);
        CustomerID customerIDOne = CustomerID.of(nif);
        CustomerID customerIDTwo = CustomerID.of(nifTwo);

        assertNotEquals(customerIDOne.hashCode(),customerIDTwo.hashCode());
    }

    @Test
    void CustomerIDNotEqualsWithDifferentObjects() {
        NIF nif = NIF.of(257715347);

        CustomerID customerIDOne = CustomerID.of(nif);

        assertNotEquals(customerIDOne, nif);
    }

    @Test
    void CustomerEqualsNotNull() {
        NIF nif = NIF.of(257715347);

        CustomerID customerIDOne = CustomerID.of(nif);

        assertNotEquals(customerIDOne, null);
    }

    @Test
    void CustomerSameIdentity() {
        NIF nif = NIF.of(257715347);

        CustomerID customerIDOne = CustomerID.of(nif);
        CustomerID customerIDTwo = CustomerID.of(nif);

        boolean result = customerIDOne.sameValueAs(customerIDTwo);

        assertTrue(result);
    }


    @Test
    void getCustomerIDAsInt() {
        int NIFNumber = 257715347;
        NIF nif = NIF.of(NIFNumber);
        CustomerID customerID = CustomerID.of(nif);

        int result = customerID.getCustomerIDAsInt();

        assertEquals(NIFNumber, result);
    }
}