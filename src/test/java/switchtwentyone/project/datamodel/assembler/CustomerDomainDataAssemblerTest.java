package switchtwentyone.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerFactory;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.datamodel.CustomerJPA;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerDomainDataAssemblerTest {

    @Mock
    CustomerFactory customerFactory;

    @InjectMocks
    CustomerDomainDataAssembler customerDomainDataAssembler;

    @Test
    void toData() {
        Customer customerOne = mock(Customer.class);
        Customer customerTwo = mock(Customer.class);
        when(customerOne.getIDAsInt()).thenReturn(257715347);
        when(customerOne.getNameAsString()).thenReturn("example");
        when(customerTwo.getIDAsInt()).thenReturn(257715347);
        when(customerTwo.getNameAsString()).thenReturn("example");

        CustomerJPA customerJPAOne = customerDomainDataAssembler.toData(customerOne);
        CustomerJPA customerJPATwo = customerDomainDataAssembler.toData(customerTwo);

        assertEquals(customerJPAOne, customerJPATwo);
    }

    @Test
    void toDomain() {
        CustomerJPA customerJPAOne = mock(CustomerJPA.class);
        CustomerJPA customerJPATwo = mock(CustomerJPA.class);
        CustomerID customerID = mock(CustomerID.class);
        when(customerJPAOne.getNIF()).thenReturn(customerID);
        when(customerJPATwo.getNIF()).thenReturn(customerID);
        when(customerJPAOne.getName()).thenReturn("bananaSplit");
        Nameable nameOne = NoNumberNoSymbolString.of("bananaSplit");
        when(customerJPATwo.getName()).thenReturn("bananaSplit");
        Nameable nameTwo = NoNumberNoSymbolString.of("bananaSplit");
        Customer customerOne = mock(Customer.class);
        Customer customerTwo = mock(Customer.class);
        when(customerFactory.createCustomer(customerID, nameOne)).thenReturn(customerOne);
        when(customerFactory.createCustomer(customerID, nameTwo)).thenReturn(customerTwo);

        Customer customerResultOne = customerDomainDataAssembler.toDomain(customerJPAOne);
        Customer customerResultTwo = customerDomainDataAssembler.toDomain(customerJPATwo);

        assertEquals(customerResultOne, customerResultTwo);
    }
}