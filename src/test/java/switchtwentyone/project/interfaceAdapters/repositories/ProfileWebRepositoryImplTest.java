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
import switchtwentyone.project.externaldto.ExternalProfileDTO;
import switchtwentyone.project.infrastructure.webclient.irestrepositories.IProfileRestRepository;
import switchtwentyone.project.interfaceAdapters.repositories.webrepositories.ProfileWebRepositoryImpl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileWebRepositoryImplTest {

    @Mock
    IProfileRestRepository restRepository;

    @InjectMocks
    ProfileWebRepositoryImpl repository;

    @Test
    void getProfilesTest() throws URISyntaxException {


        ExternalProfileDTO dto = new ExternalProfileDTO();
        ExternalProfileDTO dto2 = new ExternalProfileDTO();
        List<ExternalProfileDTO> expected = new ArrayList<>();
        expected.add(dto);
        expected.add(dto2);
        CollectionModel<ExternalProfileDTO> externalProfileDTOS = CollectionModel.of(expected);
        when(restRepository.getAllProfiles()).thenReturn(externalProfileDTOS);

        List<ExternalProfileDTO> result =  repository.getProfiles();

        assertEquals(expected, result);

    }
}