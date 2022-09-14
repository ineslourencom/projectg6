package switchtwentyone.project.domain.aggregates.businesSector;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BusinessSectorFactoryTest {

    @Test
    void createBusinessSector() {
        BusinesSectorID businesSectorID = mock(BusinesSectorID.class);
        CAE cae = mock(CAE.class);
        NoNumberNoSymbolString name =mock(NoNumberNoSymbolString.class);
        BusinessSectorFactory businessSectorFactory = new BusinessSectorFactory();

        Business One = businessSectorFactory.createBusinessSector(businesSectorID, name);
        Business Two = businessSectorFactory.createBusinessSector(businesSectorID, name);

        assertEquals(One, Two);

    }
}