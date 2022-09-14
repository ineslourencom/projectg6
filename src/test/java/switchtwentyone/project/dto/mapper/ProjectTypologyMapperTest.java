package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.dto.ProjectTypologyDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectTypologyMapperTest {

    @Test
    void toDTO() {
        List<ProjectTypologyDTO> expected = new ArrayList<>();
        ProjectTypologyDTO projectTypologyDTOOne = new ProjectTypologyDTO();
        projectTypologyDTOOne.name = "Pirata";
        projectTypologyDTOOne.description = "Perna de Pau";
        ProjectTypologyDTO projectTypologyDTOTwo = new ProjectTypologyDTO();
        projectTypologyDTOTwo.name = "Pescador";
        projectTypologyDTOTwo.description = "Olho de vidro";
        expected.add(projectTypologyDTOOne);
        expected.add(projectTypologyDTOTwo);
        ProjectTypology projectTypologyOne = mock(ProjectTypology.class);
        when(projectTypologyOne.getProjectTypologyIDAsString()).thenReturn("Pirata");
        when(projectTypologyOne.getDescriptionAsString()).thenReturn("Perna de Pau");
        ProjectTypology projectTypologyTwo = mock(ProjectTypology.class);
        when(projectTypologyTwo.getProjectTypologyIDAsString()).thenReturn("Pescador");
        when(projectTypologyTwo.getDescriptionAsString()).thenReturn("Olho de vidro");
        List<ProjectTypology> listPT = new ArrayList<>();
        listPT.add(projectTypologyOne);
        listPT.add(projectTypologyTwo);
        List<ProjectTypologyDTO> result = ProjectTypologyMapper.toDTO(listPT);

        assertEquals(expected, result);
    }
}