package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.dto.CustomerDTO;
import switchtwentyone.project.dto.ProjectTypologyDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerMapperTest {

    @Test
    void toDTO() {
        List<CustomerDTO> expected = new ArrayList<>();
        CustomerDTO customerDTOOne = new CustomerDTO();
        customerDTOOne.name = "Pirata";
        customerDTOOne.NIF = 257715347;
        CustomerDTO customerDTOTwo = new CustomerDTO();
        customerDTOTwo.name = "Pescador";
        customerDTOTwo.NIF = 506943119;
        expected.add(customerDTOOne);
        expected.add(customerDTOTwo);
        Customer customerOne = mock(Customer.class);
        when(customerOne.getNameAsString()).thenReturn("Pirata");
        when(customerOne.getIDAsInt()).thenReturn(257715347);
        Customer customerTwo = mock(Customer.class);
        when(customerTwo.getNameAsString()).thenReturn("Pescador");
        when(customerTwo.getIDAsInt()).thenReturn(506943119);
        List<Customer> listC = new ArrayList<>();
        listC.add(customerOne);
        listC.add(customerTwo);
        List<CustomerDTO> result = CustomerMapper.toDTO(listC);

        assertEquals(expected, result);
    }
}