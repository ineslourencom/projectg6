package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.dto.CustomerDTO;
import switchtwentyone.project.dto.mapper.CustomerMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ApplicationServiceGetAllCustomersTest {

    @Mock
    CustomerRepository custRep;
    @InjectMocks
    ApplicationServiceGetAllCustomers appCust;

    @Test
    void getAllCustomers() {

        Customer customerOne = mock(Customer.class);
        List<Customer> listOfCustomers = new ArrayList<>();
        listOfCustomers.add(customerOne);
        when(custRep.getAllCustomers()).thenReturn(listOfCustomers);
        List<CustomerDTO> expected = CustomerMapper.toDTO(listOfCustomers);

        List<CustomerDTO> result = appCust.getAllCustomers();

        assertEquals(expected, result);
    }
}