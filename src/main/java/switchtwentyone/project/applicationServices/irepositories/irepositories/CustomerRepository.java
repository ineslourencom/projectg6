package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findCustomerByID(CustomerID customerID);

    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();
}
