package switchtwentyone.project.infrastructure.persistence.ijparepositories;

import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.datamodel.CustomerJPA;

public interface ICustomerJPARepository extends CrudRepository<CustomerJPA, CustomerID> {
}
