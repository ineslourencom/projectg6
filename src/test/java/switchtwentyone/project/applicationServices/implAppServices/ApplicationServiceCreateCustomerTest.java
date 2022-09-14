package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerFactory;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.CustomerDTO;
import switchtwentyone.project.dto.mapper.CustomerMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.CustomerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceCreateCustomerTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerFactory customerFactory;
    @InjectMocks
    ApplicationServiceCreateCustomer applicationServiceCreateCustomer;

    @Test
    void createAndSaveCustomer() {

        int intNIF = 257715347;
        NIF nif = NIF.of(intNIF);
        MockedStatic<NIF> nifClass = mockStatic(NIF.class);
        nifClass.when(() -> NIF.of(intNIF)).thenReturn(nif);

        String nameString = "description";
        Nameable name = NoNumberNoSymbolString.of(nameString);
        MockedStatic<NoNumberNoSymbolString> descriptionClass = mockStatic(NoNumberNoSymbolString.class);
        descriptionClass.when(() -> NoNumberNoSymbolString.of(nameString)).thenReturn(name);

        CustomerID customerID = CustomerID.of(nif);
        MockedStatic<CustomerID> customerIDMockedStatic = mockStatic(CustomerID.class);
        customerIDMockedStatic.when(() -> CustomerID.of(nif)).thenReturn(customerID);


        Customer customer = mock(Customer.class);
        when(customerFactory.createCustomer(customerID, name)).thenReturn(customer);
        Customer customerSaved = mock(Customer.class);
        when(customerRepository.saveCustomer(customer)).thenReturn(customerSaved);
        CustomerDTO expected = CustomerMapper.toSingleDTO(customerSaved);

        CustomerDTO optResult = applicationServiceCreateCustomer.createAndSaveCustomer(intNIF, nameString);

        nifClass.close();
        descriptionClass.close();
        customerIDMockedStatic.close();

        assertEquals(expected, optResult);


    }

    @Test
    void createAndSaveCustomer_Failure() {

        int intNIF = 257715347;
        NIF nif = NIF.of(intNIF);
        MockedStatic<NIF> nifClass = mockStatic(NIF.class);
        nifClass.when(() -> NIF.of(intNIF)).thenReturn(nif);

        String nameString = "description";
        Nameable name = NoNumberNoSymbolString.of(nameString);
        MockedStatic<NoNumberNoSymbolString> descriptionClass = mockStatic(NoNumberNoSymbolString.class);
        descriptionClass.when(() -> NoNumberNoSymbolString.of(nameString)).thenReturn(name);

        CustomerID customerID = CustomerID.of(nif);
        MockedStatic<CustomerID> customerIDMockedStatic = mockStatic(CustomerID.class);
        customerIDMockedStatic.when(() -> CustomerID.of(nif)).thenReturn(customerID);

        Customer customer = mock(Customer.class);
        Optional<Customer> optCust = Optional.of(customer);
        when(customerRepository.findCustomerByID(customerID)).thenReturn(optCust);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateCustomer.createAndSaveCustomer(intNIF, nameString));

        nifClass.close();
        descriptionClass.close();
        customerIDMockedStatic.close();

    }
}