package switchtwentyone.project.domain.aggregates.customer;

import switchtwentyone.project.domain.shared.Entity;
import switchtwentyone.project.domain.shared.Nameable;

import java.util.Objects;

public class Customer implements Entity<Customer> {

    private Nameable name;
    /**
     * customer ID is composed by NIF
     */
    private CustomerID customerID;


    protected Customer(CustomerID customerID, Nameable name) {
        this.name = name;
        this.customerID = customerID;
    }

    public int getIDAsInt(){
        return this.customerID.getCustomerIDAsInt();
    }

    public String getNameAsString(){
        return this.name.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return this.sameIdentityAs(customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }


    @Override
    public boolean sameIdentityAs(Customer other) {
        return other != null
                && this.customerID.equals(other.customerID);
    }
}

