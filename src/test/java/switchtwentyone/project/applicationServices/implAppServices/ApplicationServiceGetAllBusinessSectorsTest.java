package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.dto.BusinessSectorDTO;
import switchtwentyone.project.dto.mapper.BusinessSectorMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.BusinessSectorRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceGetAllBusinessSectorsTest {

    @Mock
    BusinessSectorRepository busSecRep;
    @InjectMocks
    ApplicationServiceGetAllBusinessSectors applicationServiceGetAllBusinessSectors;

    @Test
    void getAllBusinessSectors() {

        Business businessOne = mock(Business.class);
        List<Business> listOfBusiness = new ArrayList<>();
        listOfBusiness.add(businessOne);
        when(busSecRep.getAllBusinessSectors()).thenReturn(listOfBusiness);
        List<BusinessSectorDTO> expected = BusinessSectorMapper.toDTO(listOfBusiness);

        List<BusinessSectorDTO> result = applicationServiceGetAllBusinessSectors.getAllBusinessSectors();

        assertEquals(expected, result);
    }
}