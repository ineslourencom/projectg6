package switchtwentyone.project.datamodel.assembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.businesSector.BusinessSectorFactory;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.datamodel.BusinessSectorJPA;

@Service
public class BusinessSectorDomainDataAssembler {
    @Autowired
    BusinessSectorFactory businessSectorFactory;

    public BusinessSectorJPA toData (Business business){
        BusinesSectorID businesSectorID = business.getID();
        String name = business.getDescriptionAsString();
        return  new BusinessSectorJPA(businesSectorID, name);
    }

    public Business toDomain (BusinessSectorJPA businessSectorJPA){
        BusinesSectorID businesSectorID = businessSectorJPA.getBusinesSectorID();
        Nameable name = NoNumberNoSymbolString.of(businessSectorJPA.getName());
        return businessSectorFactory.createBusinessSector(businesSectorID, name);
    }

}
