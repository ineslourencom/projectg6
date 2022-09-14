package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateProjectTypology;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetAllProjectTypologies;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.dto.ProjectTypologyDTO;
import switchtwentyone.project.dto.mapper.ProjectTypologyMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectTypologyControllerTest {
    @Mock
    ApplicationServiceCreateProjectTypology applicationServiceCreateProjectTypology;

    @Mock
    ApplicationServiceGetAllProjectTypologies applicationServiceGetAllProjectTypologies;

    @InjectMocks
    ProjectTypologyController projectTypologyController;


    @Test
    void createProjectTyp_ProjectTypAlreadyExists() {

        ProjectTypologyDTO projectTypInfo = mock(ProjectTypologyDTO.class);
        projectTypInfo.description = "option";
        projectTypInfo.name = "example";

        String msg = "ProjectTypology already exists";


        when(applicationServiceCreateProjectTypology.createAndSaveProjectTypology(projectTypInfo.name, projectTypInfo.description)).thenThrow(new IllegalArgumentException("ProjectTypology already exists"));

        ResponseEntity<Object> result = projectTypologyController.createProjectTypology(projectTypInfo);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(msg);

        assertEquals(expected, result);

    }

    @Test
    void createProjectTyp_Success() {

        ProjectTypologyDTO projectTypInfo = mock(ProjectTypologyDTO.class);
        projectTypInfo.description = "option";
        projectTypInfo.name = "example";


        ProjectTypology projectTypology = mock(ProjectTypology.class);
        ProjectTypologyDTO projectTypologyDTO = ProjectTypologyMapper.toSingleDTO(projectTypology);
        when(applicationServiceCreateProjectTypology.createAndSaveProjectTypology(projectTypInfo.name, projectTypInfo.description)).thenReturn(projectTypologyDTO);

        ResponseEntity<Object> result = projectTypologyController.createProjectTypology(projectTypInfo);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.CREATED).body(projectTypologyDTO);


        assertEquals(expected, result);

    }

    @Test
    void getAllProjectTypologies() {


        ProjectTypologyDTO projectTypologyDTOOne = mock(ProjectTypologyDTO.class);
        ProjectTypologyDTO projectTypologyDTOTwo = mock(ProjectTypologyDTO.class);

        List<ProjectTypologyDTO> listProjectTypologyDTO = new ArrayList<>();
        listProjectTypologyDTO.add(projectTypologyDTOOne);
        listProjectTypologyDTO.add(projectTypologyDTOTwo);
        ProjectTypologyDTO[] projectTypologyDTOS = new ProjectTypologyDTO[2];
        listProjectTypologyDTO.toArray(projectTypologyDTOS);


        when(applicationServiceGetAllProjectTypologies.getProjectAllTypologies()).thenReturn(listProjectTypologyDTO);

        ResponseEntity<Object[]> result = projectTypologyController.getAllProjectTypologies();
        ResponseEntity<Object[]> expected = ResponseEntity.status(HttpStatus.OK).body(projectTypologyDTOS);

        assertEquals(expected, result);

    }

}