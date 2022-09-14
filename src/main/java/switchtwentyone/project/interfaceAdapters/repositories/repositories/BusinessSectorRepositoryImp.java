package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.BusinessSectorRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IBusinessSectorJPARepository;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.datamodel.BusinessSectorJPA;
import switchtwentyone.project.datamodel.assembler.BusinessSectorDomainDataAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BusinessSectorRepositoryImp implements BusinessSectorRepository {

    @Autowired
    IBusinessSectorJPARepository iBusinessSectorJPARepository;

    @Autowired
    BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler;

    @Override
    public Optional<Business> findBusinessSectorByID(BusinesSectorID businesSectorID) {
        Optional<BusinessSectorJPA> optionalBusinessSectorJPA = iBusinessSectorJPARepository.findById(businesSectorID);
        Optional<Business> businessFound = Optional.empty();

        if (optionalBusinessSectorJPA.isPresent()) {
            businessFound = Optional.of(businessSectorDomainDataAssembler.toDomain(optionalBusinessSectorJPA.get()));
        }
        return businessFound;
    }

    @Override
    public List<Business> getAllBusinessSectors() {
        List<BusinessSectorJPA> listBusinessSectorJPA = (List<BusinessSectorJPA>) iBusinessSectorJPARepository.findAll();
        List<Business> listBusinessSector = new ArrayList<>();

        for (int i = 0; i < listBusinessSectorJPA.size(); i++) {
            Business business = businessSectorDomainDataAssembler.toDomain(listBusinessSectorJPA.get(i));
            listBusinessSector.add(business);
        }
        return listBusinessSector;
    }

    @Override
    public Business saveBusinessSector(Business business){
        BusinessSectorJPA businessSectorJPATobeSaved = businessSectorDomainDataAssembler.toData(business);
        BusinessSectorJPA businessSectorJPASaved = iBusinessSectorJPARepository.save(businessSectorJPATobeSaved);
        return businessSectorDomainDataAssembler.toDomain(businessSectorJPASaved);
    }

}
