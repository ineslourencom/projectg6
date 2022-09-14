package switchtwentyone.project.domain.aggregates.customer;

import switchtwentyone.project.domain.shared.Nameable;

public interface CustomerCreatable {

    Customer createCustomer(CustomerID customerID, Nameable name);
}
