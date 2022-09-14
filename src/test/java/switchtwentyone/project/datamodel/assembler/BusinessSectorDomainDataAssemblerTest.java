package switchtwentyone.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.businesSector.BusinessSectorFactory;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.datamodel.BusinessSectorJPA;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessSectorDomainDataAssemblerTest {

    @Mock
    BusinessSectorFactory businessSectorFactory;

    @InjectMocks
    BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler;


    @Test
    void toData() {
        Business business = mock(Business.class);
        BusinesSectorID businesSectorID = mock(BusinesSectorID.class);
        when(business.getID()).thenReturn(businesSectorID);

        Business businessTwo = mock(Business.class);
        when(businessTwo.getID()).thenReturn(businesSectorID);

        BusinessSectorJPA resultOne = businessSectorDomainDataAssembler.toData(business);
        BusinessSectorJPA resultTwo = businessSectorDomainDataAssembler.toData(businessTwo);

        assertEquals(resultOne, resultTwo);
    }

    @Test
    void toDomain() {
        BusinessSectorJPA businessSectorJPA = mock(BusinessSectorJPA.class);
        BusinesSectorID businesSectorID = mock(BusinesSectorID.class);
        when(businessSectorJPA.getBusinesSectorID()).thenReturn(businesSectorID);
        when(businessSectorJPA.getName()).thenReturn("RicardoCurado");
        Nameable name = NoNumberNoSymbolString.of("RicardoCurado");
        Business business = mock(Business.class);
        when(businessSectorFactory.createBusinessSector(businesSectorID, name)).thenReturn(business);

        Business result = businessSectorDomainDataAssembler.toDomain(businessSectorJPA);

        assertEquals(business, result);

    }
}