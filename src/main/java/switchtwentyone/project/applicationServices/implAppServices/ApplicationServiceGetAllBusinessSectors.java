package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.dto.BusinessSectorDTO;
import switchtwentyone.project.dto.mapper.BusinessSectorMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.BusinessSectorRepository;

import java.util.List;

@Service
public class ApplicationServiceGetAllBusinessSectors {
    @Autowired
    BusinessSectorRepository businessSectorRepository;

    public List<BusinessSectorDTO> getAllBusinessSectors(){
        return BusinessSectorMapper.toDTO(businessSectorRepository.getAllBusinessSectors());
    }

}
