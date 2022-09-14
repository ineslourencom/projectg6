package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateBusinessSector;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.businesSector.BusinessSectorFactory;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.BusinessSectorDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.BusinessSectorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class BusinessSectorControllerIntegrationTest {

    @Autowired
    BusinessSectorFactory businessSectorFactory;

    @Autowired
    BusinessSectorRepository businessSectorRepository;

    @Autowired
    ApplicationServiceCreateBusinessSector applicationServiceCreateBusinessSector;

    @Autowired
    BusinessSectorController businessSectorController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBussSect_Success() throws Exception {

        BusinessSectorDTO businessSectorInfo = new BusinessSectorDTO();
        businessSectorInfo.description = "option";
        businessSectorInfo.code = "00000";

        String expected = objectMapper.writeValueAsString(businessSectorInfo);

        String result = mockMvc
                .perform(MockMvcRequestBuilders.post("/businessSectors/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(businessSectorInfo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);

    }

    @Test
    void getAllBusinessSectors_integrationTest() throws Exception {

        CAE cae = CAE.of("00000");
        Nameable nameBuss = NoNumberNoSymbolString.of("example");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business business = businessSectorFactory.createBusinessSector(businesSectorID, nameBuss);
        businessSectorRepository.saveBusinessSector(business);
        BusinessSectorDTO businessSectorDTO = new BusinessSectorDTO();
        businessSectorDTO.code = cae.getCAEAsString();
        businessSectorDTO.description = nameBuss.getValue();

        CAE caeTwo = CAE.of("00001");
        Nameable nameBussTwo = NoNumberNoSymbolString.of("exampleTwo");
        BusinesSectorID businesSectorIDTwo = BusinesSectorID.of(caeTwo);
        Business businessTwo = businessSectorFactory.createBusinessSector(businesSectorIDTwo, nameBussTwo);
        businessSectorRepository.saveBusinessSector(businessTwo);
        BusinessSectorDTO businessSectorDTOTwo = new BusinessSectorDTO();
        businessSectorDTOTwo.code = caeTwo.getCAEAsString();
        businessSectorDTOTwo.description = nameBussTwo.getValue();

        Object[] listOfBusinessSectors = new BusinessSectorDTO[2];
        listOfBusinessSectors[0] = businessSectorDTO;
        listOfBusinessSectors[1] = businessSectorDTOTwo;


        String expected = objectMapper.writeValueAsString(listOfBusinessSectors);

        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/businessSectors/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        assertEquals(expected, result);
    }

}
