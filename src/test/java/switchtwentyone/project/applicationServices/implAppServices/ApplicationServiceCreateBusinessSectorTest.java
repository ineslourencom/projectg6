package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceCreateBusinessSectorTest {

    @Mock
    BusinessSectorRepository businessSectorRepository;
    @Mock
    BusinessSectorFactory businessSectorFactory;
    @InjectMocks
    ApplicationServiceCreateBusinessSector applicationServiceCreateBusinessSector;

    @Test
    void createAndSaveProjectTypology() {

        String stringCAE = "00000";
        CAE cae = CAE.of(stringCAE);
        MockedStatic<CAE> caeClass = mockStatic(CAE.class);
        caeClass.when(() -> CAE.of(stringCAE)).thenReturn(cae);

        String nameString = "description";
        Nameable name = NoNumberNoSymbolString.of(nameString);
        MockedStatic<NoNumberNoSymbolString> descriptionClass = mockStatic(NoNumberNoSymbolString.class);
        descriptionClass.when(() -> NoNumberNoSymbolString.of(nameString)).thenReturn(name);

        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        MockedStatic<BusinesSectorID> businesSectorIDMockedStatic = mockStatic(BusinesSectorID.class);
        businesSectorIDMockedStatic.when(() -> BusinesSectorID.of(cae)).thenReturn(businesSectorID);


        Business business = mock(Business.class);
        when(businessSectorFactory.createBusinessSector(businesSectorID, name)).thenReturn(business);
        Business businessSaved = mock(Business.class);
        when(businessSectorRepository.saveBusinessSector(business)).thenReturn(businessSaved);
        BusinessSectorDTO expected = BusinessSectorMapper.toSingleDTO(businessSaved);

        BusinessSectorDTO optResult = applicationServiceCreateBusinessSector.createAndSaveBusinessSector(stringCAE, nameString);

        caeClass.close();
        descriptionClass.close();
        businesSectorIDMockedStatic.close();

        assertEquals(expected, optResult);
    }

    @Test
    void createAndSaveProjectTypology_Failure() {

        String stringCAE = "00000";
        CAE cae = CAE.of(stringCAE);

        String nameString = "description";

        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);

        Business business = mock(Business.class);

        when(businessSectorRepository.findBusinessSectorByID(businesSectorID)).thenReturn(Optional.of(business));

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateBusinessSector.createAndSaveBusinessSector(stringCAE, nameString));


    }



}