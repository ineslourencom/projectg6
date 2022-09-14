package switchtwentyone.project.domain.aggregates.customer;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.shared.Nameable;

@Component
public class CustomerFactory implements CustomerCreatable {

    @Override
    public Customer createCustomer(CustomerID customerID, Nameable name) {
        return new Customer(customerID, name);
    }
}
