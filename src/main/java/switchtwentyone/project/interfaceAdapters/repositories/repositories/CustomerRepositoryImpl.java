package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.CustomerRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.ICustomerJPARepository;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.datamodel.CustomerJPA;
import switchtwentyone.project.datamodel.assembler.CustomerDomainDataAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
   /* List<Customer> listOfCustomers;

    public CustomerRepository() {
        this.listOfCustomers = new ArrayList<>();
    }

    public Optional<Customer> findCustomerByID(CustomerID customerID) {
        Optional<Customer> customerFound = Optional.empty();
        for (int i = 0; i < this.listOfCustomers.size(); i++) {
            if (this.listOfCustomers.get(i).getIDAsInt() == customerID.getCustomerIDAsInt()) {
                customerFound = Optional.of(this.listOfCustomers.get(i));
            }
        }
        return customerFound;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(this.listOfCustomers);
    }

    public Optional<Customer> saveCustomer(Customer customer) {
        boolean customerRepeated = false;
        Optional<Customer> customerSaved = Optional.empty();

        for (int i = 0; i < this.listOfCustomers.size(); i++) {
            if (this.listOfCustomers.get(i).equals(customer)) {
                customerRepeated = true;
            }
        }

        if (!customerRepeated) {
            this.listOfCustomers.add(customer);
            customerSaved = Optional.of(customer);
        }
        return customerSaved;
    }
*/
    @Autowired
    ICustomerJPARepository iCustomerJPARepository;
    @Autowired
    CustomerDomainDataAssembler customerDomainDataAssembler;


    @Override
    public Optional<Customer> findCustomerByID(CustomerID customerID) {
        Optional<CustomerJPA> customerJPAFound = iCustomerJPARepository.findById(customerID);
        Optional<Customer> customerFound = Optional.empty();
        if (customerJPAFound.isPresent()) {
            customerFound = Optional.of(customerDomainDataAssembler.toDomain(customerJPAFound.get()));
        }
        return customerFound;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerJPA customerJPA = customerDomainDataAssembler.toData(customer);
        CustomerJPA customerJPASaved = iCustomerJPARepository.save(customerJPA);
        return customerDomainDataAssembler.toDomain(customerJPASaved);
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<CustomerJPA> listCustomerJPA = (List<CustomerJPA>) iCustomerJPARepository.findAll();
        List<Customer> listCustomer = new ArrayList<>();

        for (int i = 0; i < listCustomerJPA.size(); i++) {
            Customer customer = customerDomainDataAssembler.toDomain(listCustomerJPA.get(i));
            listCustomer.add(customer);
        }
        return listCustomer;
    }

}


