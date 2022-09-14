package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.customer.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {


    @Test
    void CustomerDTOWithSameAtributesAreEqual() {
        CustomerDTO customerOne = new CustomerDTO();
        customerOne.NIF = 257715347;
        customerOne.name = "pistolas";
        CustomerDTO customerTwo = new CustomerDTO();
        customerTwo.NIF = 257715347;
        customerTwo.name = "pistolas";

        assertEquals(customerOne, customerTwo);
    }

    @Test
    void CustomerDTOWithDifferentNIFAreDifferent() {

        CustomerDTO customerOne = new CustomerDTO();
        customerOne.NIF = 257715347;
        customerOne.name = "pistolas";
        CustomerDTO customerTwo = new CustomerDTO();
        customerTwo.NIF = 506943119;
        customerTwo.name = "pistolas";

        assertNotEquals(customerOne, customerTwo);
    }

    @Test
    void CustomerDTOWithDifferentNameAreDifferent() {

        CustomerDTO customerOne = new CustomerDTO();
        customerOne.NIF = 506943119;
        customerOne.name = "pistolas";
        CustomerDTO customerTwo = new CustomerDTO();
        customerTwo.NIF = 506943119;
        customerTwo.name = "espadas";

        assertNotEquals(customerOne, customerTwo);
    }

    @Test
    void CustomerDTOWithSameAtributesHaveSameHashCode() {

        CustomerDTO customerOne = new CustomerDTO();
        customerOne.NIF = 506943119;
        customerOne.name = "pistolas";
        CustomerDTO customerTwo = new CustomerDTO();
        customerTwo.NIF = 506943119;
        customerTwo.name = "pistolas";

        assertEquals(customerOne.hashCode(), customerTwo.hashCode());
    }

    @Test
    void CustomerWithDifferentAtributesHaveDifferentHashCode() {

        CustomerDTO customerOne = new CustomerDTO();
        customerOne.NIF = 506943119;
        customerOne.name = "pistolas";
        CustomerDTO customerTwo = new CustomerDTO();
        customerTwo.NIF = 506943119;
        customerTwo.name = "espadas";

        assertNotEquals(customerOne.hashCode(), customerTwo.hashCode());
    }

    @Test
    void ProjectTypologyDTONotEqualsWithDifferentObjects() {
        CustomerDTO customerOne = new CustomerDTO();
        customerOne.NIF = 506943119;
        customerOne.name = "pistolas";
        int comparator = 5;

        assertNotEquals(customerOne, comparator);
    }

    @Test
    void ProjectTypologyEqualsNotNull() {
        CustomerDTO customerOne = new CustomerDTO();
        customerOne.NIF = 506943119;
        customerOne.name = "pistolas";

        assertNotEquals(customerOne, null);
    }

}