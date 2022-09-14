package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.datamodel.ResourceJPA;
import switchtwentyone.project.datamodel.assembler.ResourceDomainDataAssembler;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IResourceJPARepository;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.ResourceRepositoryImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceRepositoryImplTest {

    @Mock
    IResourceJPARepository iResourceJPARepository;
    @Mock
    ResourceDomainDataAssembler resourceDomainDataAssembler;
    @InjectMocks
    ResourceRepositoryImpl resourceRepositoryImpl;

    @Test
    void findResourceByID() {
        ResourceID resourceID = mock(ResourceID.class);
        ResourceJPA resourceJPA = mock(ResourceJPA.class);
        Resource resource = mock(Resource.class);
        when(iResourceJPARepository.findById(resourceID)).thenReturn(Optional.of(resourceJPA));
        when(resourceDomainDataAssembler.toDomain(resourceJPA)).thenReturn(resource);

        Resource result = resourceRepositoryImpl.findResourceByID(resourceID).get();

        assertEquals(resource, result);
    }

    @Test
    void findResourceByID_NoResource() {
        ResourceID resourceID = mock(ResourceID.class);
        when(iResourceJPARepository.findById(resourceID)).thenReturn(Optional.empty());

        Optional<Resource> expected = Optional.empty();
        Optional<Resource> result = resourceRepositoryImpl.findResourceByID(resourceID);

        assertEquals(expected, result);
    }
}