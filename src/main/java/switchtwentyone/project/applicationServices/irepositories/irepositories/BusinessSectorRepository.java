package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;

import java.util.List;
import java.util.Optional;

public interface BusinessSectorRepository {
    Optional<Business> findBusinessSectorByID(BusinesSectorID businesSectorID);

    List<Business> getAllBusinessSectors();

    Business saveBusinessSector(Business business);
}
