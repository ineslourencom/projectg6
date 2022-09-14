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
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyFactory;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProjectTypologyDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectTypologyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ProjectTypologyControllerIntegrationTest {

    @Autowired
    ProjectTypologyFactory projTypFac;

    @Autowired
    ProjectTypologyRepository projTypRep;

    @Autowired
    ApplicationServiceCreateProjectTypology applicationServiceCreateProjectTypology;

    @Autowired
    ProjectTypologyController projectTypologyController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createProjectTyp_Success() throws Exception {

        ProjectTypologyDTO projectTypInfo = new ProjectTypologyDTO();
        projectTypInfo.description = "option";
        projectTypInfo.name = "example";

        String expected = objectMapper.writeValueAsString(projectTypInfo);

        String result = mockMvc
                .perform(MockMvcRequestBuilders.post("/projectTypologies/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(projectTypInfo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);

    }

    @Test
    void getAllProjectTypologies_integrationTest() throws Exception {

        NoNumberNoSymbolString nameProjTyp = NoNumberNoSymbolString.of("project typology");
        Describable description = Text.write("description");
        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(nameProjTyp);
        ProjectTypology projectTypology = projTypFac.createProjectTypology(projectTypologyID, description);
        projTypRep.saveProjectTypology(projectTypology);
        ProjectTypologyDTO projectTypologyDTO = new ProjectTypologyDTO();
        projectTypologyDTO.name = nameProjTyp.getValue();
        projectTypologyDTO.description = description.getValue();

        NoNumberNoSymbolString nameProjTypTwo = NoNumberNoSymbolString.of("project typology Two");
        Describable descriptionTwo = Text.write("descriptionTwo");
        ProjectTypologyID projectTypologyIDTwo = ProjectTypologyID.of(nameProjTypTwo);
        ProjectTypology projectTypologyTwo = projTypFac.createProjectTypology(projectTypologyIDTwo, descriptionTwo);
        projTypRep.saveProjectTypology(projectTypologyTwo);
        ProjectTypologyDTO projectTypologyDTOTwo = new ProjectTypologyDTO();
        projectTypologyDTOTwo.name = nameProjTypTwo.getValue();
        projectTypologyDTOTwo.description = descriptionTwo.getValue();

        ProjectTypologyDTO[] listOfProjectTypologies = new ProjectTypologyDTO[2];
        listOfProjectTypologies[0] = projectTypologyDTO;
        listOfProjectTypologies[1] = projectTypologyDTOTwo;

        String expected = objectMapper.writeValueAsString(listOfProjectTypologies);

        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/projectTypologies/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);

    }
}
