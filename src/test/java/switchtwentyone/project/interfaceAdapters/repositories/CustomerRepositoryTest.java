package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.applicationServices.irepositories.irepositories.CustomerRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.ICustomerJPARepository;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.datamodel.CustomerJPA;
import switchtwentyone.project.datamodel.assembler.CustomerDomainDataAssembler;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.CustomerRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryTest {

    @Mock
    CustomerDomainDataAssembler customerDomainDataAssembler;

    @Mock
    ICustomerJPARepository iCustomerJPARepository;

    @InjectMocks
    CustomerRepositoryImpl customerRepository;

    @Test
    void saveCustomerSuccess() {
        Customer customer = mock(Customer.class);
        Customer customerSaved = mock(Customer.class);
        CustomerJPA customerJPA = mock(CustomerJPA.class);
        CustomerJPA customerJPASaved = mock(CustomerJPA.class);
        when(customerDomainDataAssembler.toData(customer)).thenReturn(customerJPA);
        when(iCustomerJPARepository.save(customerJPA)).thenReturn(customerJPASaved);
        when(customerDomainDataAssembler.toDomain(customerJPASaved)).thenReturn(customerSaved);


        Customer result = customerRepository.saveCustomer(customer);

        assertEquals(customerSaved, result);
    }


    @Test
    void findCustomerByIDSuccess() {
        CustomerID customerID = mock(CustomerID.class);
        CustomerJPA customerJPA = mock(CustomerJPA.class);
        Optional<CustomerJPA> optCustomerJPA = Optional.of(customerJPA);
        when(iCustomerJPARepository.findById(customerID)).thenReturn(optCustomerJPA);
        Customer customer = mock(Customer.class);
        when(customerDomainDataAssembler.toDomain(customerJPA)).thenReturn(customer);
        Optional<Customer> optCustomerFound = Optional.of(customer);

        Optional<Customer> result = customerRepository.findCustomerByID(customerID);

        assertEquals(optCustomerFound, result);

    }

    @Test
    void findCustomerByIDUnsuccess() {
        CustomerID customerID = mock(CustomerID.class);
        when(iCustomerJPARepository.findById(customerID)).thenReturn(Optional.empty());
        Optional<Customer> optCustomerFound = Optional.empty();

        Optional<Customer> result = customerRepository.findCustomerByID(customerID);

        assertEquals(optCustomerFound, result);
    }

    @Test
    void getAllBusinessSectors() {

        List<CustomerJPA> list = new ArrayList<>();
        CustomerJPA customerJPAOne = mock(CustomerJPA.class);
        CustomerJPA customerJPATwo = mock(CustomerJPA.class);
        list.add(customerJPAOne);
        list.add(customerJPATwo);
        Iterable<CustomerJPA> iterable = list;
        when(iCustomerJPARepository.findAll()).thenReturn(iterable);
        Customer customerOne = mock(Customer.class);
        Customer customerTwo = mock(Customer.class);
        List<Customer> custumerList = new ArrayList<>();
        custumerList.add(customerOne);
        custumerList.add(customerTwo);
        when(customerDomainDataAssembler.toDomain(customerJPAOne)).thenReturn(customerOne);
        when(customerDomainDataAssembler.toDomain(customerJPATwo)).thenReturn(customerTwo);

        List<Customer> result = customerRepository.getAllCustomers();

        assertEquals(custumerList, result);
    }
}