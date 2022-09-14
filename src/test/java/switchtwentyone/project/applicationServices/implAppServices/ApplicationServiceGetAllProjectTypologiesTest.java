package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.dto.ProjectTypologyDTO;
import switchtwentyone.project.dto.mapper.ProjectTypologyMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectTypologyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceGetAllProjectTypologiesTest {

    @Mock
    ProjectTypologyRepository projTypRep;
    @InjectMocks
    ApplicationServiceGetAllProjectTypologies appServ;

    @Test
    void getProjectAllTypologies() {

        ProjectTypology projTypOne = mock(ProjectTypology.class);
        List<ProjectTypology> listOfProjectTypologies = new ArrayList<>();
        listOfProjectTypologies.add(projTypOne);
        when(projTypRep.getAllProjectTypologies()).thenReturn(listOfProjectTypologies);
        List<ProjectTypologyDTO> expected = ProjectTypologyMapper.toDTO(listOfProjectTypologies);

        List<ProjectTypologyDTO> result = appServ.getProjectAllTypologies();

        assertEquals(expected, result);



    }
}