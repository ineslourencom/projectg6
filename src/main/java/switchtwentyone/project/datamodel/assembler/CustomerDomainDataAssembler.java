package switchtwentyone.project.datamodel.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerFactory;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.datamodel.CustomerJPA;


@Service
public class CustomerDomainDataAssembler {
    @Autowired
    CustomerFactory customerFactory;

    public CustomerJPA toData(Customer customer) {
        CustomerID nif = CustomerID.of(NIF.of(customer.getIDAsInt()));
        String name = customer.getNameAsString();
        return new CustomerJPA(nif, name);
    }


    public Customer toDomain(CustomerJPA customerJPA) {
        CustomerID nif = customerJPA.getNIF();
        Nameable name = NoNumberNoSymbolString.of(customerJPA.getName());
        return customerFactory.createCustomer(nif, name);
    }


}
