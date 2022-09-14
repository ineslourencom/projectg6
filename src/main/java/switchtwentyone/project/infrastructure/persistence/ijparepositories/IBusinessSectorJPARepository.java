package switchtwentyone.project.infrastructure.persistence.ijparepositories;

import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.datamodel.BusinessSectorJPA;

public interface IBusinessSectorJPARepository extends CrudRepository<BusinessSectorJPA, BusinesSectorID> {
}
