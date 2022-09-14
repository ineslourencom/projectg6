package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.dto.BusinessSectorDTO;

import java.util.ArrayList;
import java.util.List;

public class BusinessSectorMapper {

    public static List<BusinessSectorDTO> toDTO(List<Business> listOfBusiness) {
        List<BusinessSectorDTO> listOfDTOs = new ArrayList<>();
        for (int i = 0; i < listOfBusiness.size(); i++) {
            BusinessSectorDTO businessDTO = new BusinessSectorDTO();
            businessDTO.code = listOfBusiness.get(i).getIDAsString();
            businessDTO.description = listOfBusiness.get(i).getDescriptionAsString();
            listOfDTOs.add(businessDTO);
        }
        return listOfDTOs;
    }

    public static BusinessSectorDTO toSingleDTO(Business business) {
            BusinessSectorDTO businessDTO = new BusinessSectorDTO();
            businessDTO.code = business.getIDAsString();
            businessDTO.description = business.getDescriptionAsString();

        return businessDTO;
    }
}
