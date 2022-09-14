package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class ApplicationServiceCreateCustomer {

    @Autowired
    CustomerFactory customerFactory;
    @Autowired
    CustomerRepository customerRepository;


    public CustomerDTO createAndSaveCustomer(int nifInt, String name) {
        Nameable nameVO = NoNumberNoSymbolString.of(name);
        NIF nif = NIF.of(nifInt);
        CustomerID customerID = CustomerID.of(nif);
        Optional<Customer> customerWithSameID = customerRepository.findCustomerByID(customerID);
        Customer customerSaved;

        if (customerWithSameID.isEmpty()) {
            Customer customerCreated = customerFactory.createCustomer(customerID, nameVO);
            customerSaved = customerRepository.saveCustomer(customerCreated);
        } else throw new IllegalArgumentException("Customer already exists");

        return CustomerMapper.toSingleDTO(customerSaved);
    }
}
