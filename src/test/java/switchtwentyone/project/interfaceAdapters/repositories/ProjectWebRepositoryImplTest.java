package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import switchtwentyone.project.externaldto.ExternalProjectDTO;
import switchtwentyone.project.infrastructure.webclient.irestrepositories.IProjectRestRepository;
import switchtwentyone.project.interfaceAdapters.repositories.webrepositories.ProjectWebRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectWebRepositoryImplTest {

    @Mock
    IProjectRestRepository restRepository;

@InjectMocks
ProjectWebRepositoryImpl repository;

    @Test
    void getProjects() {
        ExternalProjectDTO dto = new ExternalProjectDTO();
        ExternalProjectDTO dto2 = new ExternalProjectDTO();
        List<ExternalProjectDTO> expected = new ArrayList<>();
        expected.add(dto);
        expected.add(dto2);
        CollectionModel<ExternalProjectDTO> externalProjectDTOS = CollectionModel.of(expected);
    when(restRepository.getAllProjects()).thenReturn(externalProjectDTOS);

   List<ExternalProjectDTO> result= repository.getProjects();

   assertEquals(expected, result);
    }
}