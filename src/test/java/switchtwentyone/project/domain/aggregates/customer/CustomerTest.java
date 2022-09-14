package switchtwentyone.project.domain.aggregates.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void CustomerWithSameAttributesAreEqual() {
        NIF NIFExample = NIF.of(257715347);
        NoNumberNoSymbolString Name = NoNumberNoSymbolString.of("ExampleOne");
        CustomerID customerID = CustomerID.of(NIFExample);
        Customer customer = new Customer(customerID, Name);
        Customer customerTwo = new Customer(customerID, Name);

        assertEquals(customer, customerTwo);
    }

    @Test
    void CustomerWithDifferentNIFIsDifferent() {
        NIF NIFExample = NIF.of(257715347);
        NIF NIFExampleTwo = NIF.of(506943119);
        CustomerID customerID = CustomerID.of(NIFExample);
        CustomerID customerIDTwo = CustomerID.of(NIFExampleTwo);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("ExampleOne");
        Customer customer = new Customer(customerID, name);
        Customer customerTwo = new Customer(customerIDTwo, name);

        assertNotEquals(customer, customerTwo);
    }

    @Test
    void CustomerEqualsToItself() {
        NIF NIFExample = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(NIFExample);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("ExampleOne");
        Customer customer = new Customer(customerID, name);

        assertEquals(customer, customer);
    }

    @Test
    void CustomerWithSameAttributesHaveSameHashCode() {
        NIF NIFExample = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(NIFExample);
        NoNumberNoSymbolString Name = NoNumberNoSymbolString.of("ExampleOne");
        Customer customer = new Customer(customerID, Name);
        Customer customerTwo = new Customer(customerID, Name);

        assertEquals(customer.hashCode(), customerTwo.hashCode());
    }

    @Test
    void CustomerWithDifferentAttributesHasDifferentHashCode() {
        NIF NIFExample = NIF.of(257715347);
        NIF NIFExampleTwo = NIF.of(506943119);
        CustomerID customerID = CustomerID.of(NIFExample);
        CustomerID customerIDTwo = CustomerID.of(NIFExampleTwo);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("ExampleOne");
        NoNumberNoSymbolString nameTwo = NoNumberNoSymbolString.of("ExampleTwo");
        Customer customer = new Customer(customerID, name);
        Customer customerTwo = new Customer(customerIDTwo, nameTwo);

        assertNotEquals(customer.hashCode(), customerTwo.hashCode());
    }

    @Test
    void CustomerNotEqualsWithDifferentObjects() {
        NIF NIFExample = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(NIFExample);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("ExampleOne");
        Customer customer = new Customer(customerID, name);

        assertNotEquals(customer, NIFExample);
    }

    @Test
    void CustomerNotEqualsNotNull() {
        NIF NIFExample = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(NIFExample);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("ExampleOne");
        Customer customer = new Customer(customerID, name);

        assertNotEquals(customer, null);
    }

    @Test
    void NIFSameIdentity() {
        NIF NIFExample = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(NIFExample);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("ExampleOne");
        Customer customer = new Customer(customerID, name);
        Customer customerTwo = new Customer(customerID, name);

        boolean result = customer.sameIdentityAs(customerTwo);

        assertTrue(result);
    }


    @Test
    void getIDAsInt() {
        int NIFNumber = 257715347;
        NIF nif = NIF.of(NIFNumber);
        CustomerID customerID = CustomerID.of(nif);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("ExampleOne");
        Customer customer = new Customer(customerID, name);

        int result = customer.getIDAsInt();

        assertEquals(NIFNumber, result);
    }

    @Test
    void getNameAsString() {
        int NIFNumber = 257715347;
        NIF nif = NIF.of(NIFNumber);
        CustomerID customerID = CustomerID.of(nif);
        String expected = "ExampleOne";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(expected);
        Customer customer = new Customer(customerID, name);

        String result = customer.getNameAsString();

        assertEquals(expected, result);
    }
}