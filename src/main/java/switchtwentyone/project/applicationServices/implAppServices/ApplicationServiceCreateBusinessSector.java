package switchtwentyone.project.applicationServices.implAppServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.businesSector.BusinessSectorFactory;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.BusinessSectorDTO;
import switchtwentyone.project.dto.mapper.BusinessSectorMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.BusinessSectorRepository;

import java.util.Optional;


@Service
public class ApplicationServiceCreateBusinessSector {

    @Autowired
    BusinessSectorFactory businessSectorFactory;

    @Autowired
    BusinessSectorRepository businessSectorRepository;

    public BusinessSectorDTO createAndSaveBusinessSector(String cae, String description) {
        Nameable descriptionVO = NoNumberNoSymbolString.of(description);
        CAE caeVO = CAE.of(cae);
        BusinesSectorID businesSectorID = BusinesSectorID.of(caeVO);
        Optional<Business> businessSectorRepeated = businessSectorRepository.findBusinessSectorByID(businesSectorID);
        Business businessSectorSaved;

        if (businessSectorRepeated.isEmpty()) {
            Business businessCreated = businessSectorFactory.createBusinessSector(businesSectorID, descriptionVO);
            businessSectorSaved = businessSectorRepository.saveBusinessSector(businessCreated);
        } else {
            throw new IllegalArgumentException("Business Sector already exists");
        }
        return BusinessSectorMapper.toSingleDTO(businessSectorSaved);
    }
}
