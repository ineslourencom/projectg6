package switchtwentyone.project.interfaceAdapters.controllers.implControllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.irepositories.irepositories.BusinessSectorRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.CustomerRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectTypologyRepository;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.businesSector.BusinessSectorFactory;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerFactory;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectFactory;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyFactory;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.NewSprintInfoDTO;
import switchtwentyone.project.dto.SprintDTO;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class SprintControllerIntegrationTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired
    SprintController controller;
    @Autowired BusinessSectorFactory businessSectorFactory;
    @Autowired CustomerFactory cusFac;
    @Autowired ProjectTypologyFactory projTypFac;
    @Autowired ProjectFactory projFac;
    @Autowired
    BusinessSectorRepository busRep;
    @Autowired
    CustomerRepository cusRep;
    @Autowired
    ProjectTypologyRepository projTypRep;
    @Autowired
    ProjectRepository projectRepository;

    @BeforeEach
    public  void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSprintControllerSuccessful() throws Exception {
         //Arrange
        Nameable name = NoNumberNoSymbolString.of("customer");
        NIF nif = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(nif);
        Customer customer = cusFac.createCustomer(customerID, name);
        cusRep.saveCustomer(customer);

        NoNumberNoSymbolString nameProjTyp = NoNumberNoSymbolString.of("project typology");
        Describable description = Text.write("description");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(nameProjTyp);
        ProjectTypology projectTypology = projTypFac.createProjectTypology(projectTypologyID, description);
        projTypRep.saveProjectTypology(projectTypology);

        CAE cae = CAE.of("00000");
        Nameable nameBuss = NoNumberNoSymbolString.of("example");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business business = businessSectorFactory.createBusinessSector(businesSectorID, nameBuss);
        busRep.saveBusinessSector(business);


        ProjectID projectID = new ProjectID(1);
        NoNumberNoSymbolString projectName = NoNumberNoSymbolString.of("Banana");
        Period period = Period.between(LocalDate.of(2002, 8, 24), LocalDate.of(2002, 10, 24));
        PositiveNumber sprintDuration = PositiveNumber.of(2);
        PositiveNumber budget = PositiveNumber.of(50000);

        Project project = new Project(projectID, projectName, description, period, sprintDuration, budget, customerID, businesSectorID, projectTypologyID);
        projectRepository.save(project);

        LocalDate startDate =LocalDate.of(2022,9,1);
        NewSprintInfoDTO newSprintInfoDTO = new NewSprintInfoDTO(startDate);

        SprintDTO expected = new SprintDTO();
        expected.sprintNumber = 1;
        expected.sprintDuration = 2;
        expected.sprintID = 1.001;
        expected.projectID = 1;
        expected.startDate = LocalDate.of(2022, 9, 1);
        expected.sprintBacklogItemDTOList = new ArrayList<>();

        mockMvc
                .perform(MockMvcRequestBuilders.post("/projects/1/sprints")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newSprintInfoDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sprintID").exists());
    }


}

