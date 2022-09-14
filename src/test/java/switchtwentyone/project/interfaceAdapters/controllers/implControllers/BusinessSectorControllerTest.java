package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateBusinessSector;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetAllBusinessSectors;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.dto.BusinessSectorDTO;
import switchtwentyone.project.dto.mapper.BusinessSectorMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BusinessSectorControllerTest {

    @Mock
    ApplicationServiceCreateBusinessSector applicationServiceCreateBusinessSector;

    @Mock
    ApplicationServiceGetAllBusinessSectors applicationServiceGetAllBusinessSectors;

    @InjectMocks
    BusinessSectorController businessSectorController;

    @Test
    void createBussSect_BussSectAlreadyExists() {

        BusinessSectorDTO businessSectorInfo = mock(BusinessSectorDTO.class);
        businessSectorInfo.description = "option";
        businessSectorInfo.code = "00000";

        String msg = "BusinessSector already exists";

        Optional<BusinessSectorDTO> optBuss = Optional.empty();
        when(applicationServiceCreateBusinessSector.createAndSaveBusinessSector(businessSectorInfo.code, businessSectorInfo.description
        )).thenThrow(new IllegalArgumentException("BusinessSector already exists"));

        ResponseEntity<Object> result = businessSectorController.createBusinessSector(businessSectorInfo);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(msg);

        assertEquals(expected, result);

    }

    @Test
    void createBussSect_Success() {

        BusinessSectorDTO businessSectorInfo = mock(BusinessSectorDTO.class);
        businessSectorInfo.description = "option";
        businessSectorInfo.code = "00000";

        Business businessSector = mock(Business.class);
        BusinessSectorDTO businessSectorDTO = BusinessSectorMapper.toSingleDTO(businessSector);
        when(applicationServiceCreateBusinessSector.createAndSaveBusinessSector(businessSectorInfo.code, businessSectorInfo.description)).thenReturn(businessSectorDTO);

        ResponseEntity<Object> result = businessSectorController.createBusinessSector(businessSectorInfo);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.CREATED).body(businessSectorDTO);


        assertEquals(expected, result);

    }

    @Test
    void getAllBusinessSectors() {

        BusinessSectorDTO businessSectorDTOOne = mock(BusinessSectorDTO.class);
        BusinessSectorDTO businessSectorDTOTwo = mock(BusinessSectorDTO.class);

        List<BusinessSectorDTO> listBusinessSectorDTO = new ArrayList<>();
        listBusinessSectorDTO.add(businessSectorDTOOne);
        listBusinessSectorDTO.add(businessSectorDTOTwo);
        BusinessSectorDTO[] businessSectorDTOS = new BusinessSectorDTO[2];
        listBusinessSectorDTO.toArray(businessSectorDTOS);

        when(applicationServiceGetAllBusinessSectors.getAllBusinessSectors()).thenReturn(listBusinessSectorDTO);

        ResponseEntity<Object[]> result = businessSectorController.getAllBusinessSectors();
        ResponseEntity<Object[]> expected = ResponseEntity.status(HttpStatus.OK).body(businessSectorDTOS);

        assertEquals(expected, result);

    }

}