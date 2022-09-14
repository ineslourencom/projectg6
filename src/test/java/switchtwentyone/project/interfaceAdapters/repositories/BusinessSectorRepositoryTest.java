package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.applicationServices.irepositories.irepositories.BusinessSectorRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IBusinessSectorJPARepository;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.datamodel.BusinessSectorJPA;
import switchtwentyone.project.datamodel.assembler.BusinessSectorDomainDataAssembler;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class BusinessSectorRepositoryTest {

    @MockBean
    IBusinessSectorJPARepository iBusinessSectorJPARepository;

    @MockBean
    BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler;

    @Autowired
    BusinessSectorRepository businessSectorRepository;


    @Test
    void findBusinessSectorByID_Success() {
        BusinesSectorID businesSectorID = mock(BusinesSectorID.class);
        BusinessSectorJPA businessSectorJPA = mock(BusinessSectorJPA.class);
        Optional<BusinessSectorJPA> optionalBusinessSectorJPA = Optional.of(businessSectorJPA);
        when(iBusinessSectorJPARepository.findById(businesSectorID)).thenReturn(optionalBusinessSectorJPA);
        Business business = mock(Business.class);
        when(businessSectorDomainDataAssembler.toDomain(businessSectorJPA)).thenReturn(business);
        Optional<Business> expected = Optional.of(business);

        Optional<Business> result = businessSectorRepository.findBusinessSectorByID(businesSectorID);

        assertEquals(expected, result);
    }

    @Test
    void findBusinessSectorByID_Unsuccess() {
        BusinesSectorID businesSectorID = mock(BusinesSectorID.class);
        when(iBusinessSectorJPARepository.findById(businesSectorID)).thenReturn(Optional.empty());
        Optional<Business> expected = Optional.empty();

        Optional<Business> result = businessSectorRepository.findBusinessSectorByID(businesSectorID);

        assertEquals(expected, result);
    }


    @Test
    void testGetAllBusinessSectors() {
        List<BusinessSectorJPA> list = new ArrayList<>();
        BusinessSectorJPA businessSectorJPAOne = mock(BusinessSectorJPA.class);
        BusinessSectorJPA businessSctorJPATwo = mock(BusinessSectorJPA.class);
        list.add(businessSectorJPAOne);
        list.add(businessSctorJPATwo);
        Iterable<BusinessSectorJPA> iterable = list;
        when(iBusinessSectorJPARepository.findAll()).thenReturn(iterable);
        Business businessOne = mock(Business.class);
        Business businessTwo = mock(Business.class);
        List<Business> businessList = new ArrayList<>();
        businessList.add(businessOne);
        businessList.add(businessTwo);
        when(businessSectorDomainDataAssembler.toDomain(businessSectorJPAOne)).thenReturn(businessOne);
        when(businessSectorDomainDataAssembler.toDomain(businessSctorJPATwo)).thenReturn(businessTwo);

        List<Business> result = businessSectorRepository.getAllBusinessSectors();

        assertEquals(businessList, result);
    }

    @Test
    void saveBusinessSector() {
        Business businessOne = mock(Business.class);
        BusinessSectorJPA businessSectorJPAOne = mock(BusinessSectorJPA.class);
        when(businessSectorDomainDataAssembler.toData(businessOne)).thenReturn(businessSectorJPAOne);
        BusinessSectorJPA businessSectorJPAOneSaved = mock(BusinessSectorJPA.class);
        when(iBusinessSectorJPARepository.save(businessSectorJPAOne)).thenReturn(businessSectorJPAOneSaved);
        Business businessSaved = mock(Business.class);
        when(businessSectorDomainDataAssembler.toDomain(businessSectorJPAOneSaved)).thenReturn(businessSaved);

        Business result = businessSectorRepository.saveBusinessSector(businessOne);

        assertEquals(result, businessSaved );

    }
}