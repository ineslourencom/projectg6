package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateCustomer;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetAllCustomers;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.dto.CustomerDTO;
import switchtwentyone.project.dto.mapper.CustomerMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    ApplicationServiceCreateCustomer applicationServiceCreateCustomer;

    @Mock
    ApplicationServiceGetAllCustomers applicationServiceGetAllCustomers;

    @InjectMocks
    CustomerController customerController;

    @Test
    void createCustomer_CustomerAlreadyExists() {

        CustomerDTO customerInfo = mock(CustomerDTO.class);
        customerInfo.NIF = 257715347;
        customerInfo.name = "example";

        String msg = "Customer already exists";

        when(applicationServiceCreateCustomer.createAndSaveCustomer(customerInfo.NIF, customerInfo.name)).thenThrow(new IllegalArgumentException("Customer already exists"));

        ResponseEntity<Object> result = customerController.createCustomer(customerInfo);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(msg);

        assertEquals(expected, result);

    }

    @Test
    void createProjectTyp_Success() {

        CustomerDTO customerInfo = mock(CustomerDTO.class);
        customerInfo.NIF = 257715347;
        customerInfo.name = "example";

        Customer customer = mock(Customer.class);
        CustomerDTO customerDTO = CustomerMapper.toSingleDTO(customer);
        when(applicationServiceCreateCustomer.createAndSaveCustomer(customerInfo.NIF, customerInfo.name)).thenReturn(customerDTO);

        ResponseEntity<Object> result = customerController.createCustomer(customerInfo);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);

        assertEquals(expected, result);

    }

    @Test
    void getAllCustomers() {

        CustomerDTO customerDTOOne = mock(CustomerDTO.class);
        CustomerDTO customerDTOTwo = mock(CustomerDTO.class);

        List<CustomerDTO> listCustomerDTO = new ArrayList<>();
        listCustomerDTO.add(customerDTOOne);
        listCustomerDTO.add(customerDTOTwo);
        CustomerDTO[] customerDTOS = new CustomerDTO[2];
        listCustomerDTO.toArray(customerDTOS);

        when(applicationServiceGetAllCustomers.getAllCustomers()).thenReturn(listCustomerDTO);

        ResponseEntity<Object[]> result = customerController.getAllCustomers();
        ResponseEntity<Object[]> expected = ResponseEntity.status(HttpStatus.OK).body(customerDTOS);

        assertEquals(expected, result);

    }

}