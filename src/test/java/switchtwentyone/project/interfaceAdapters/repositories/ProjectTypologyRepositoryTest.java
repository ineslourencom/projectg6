package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectTypologyRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IProjectTypologyJPARepository;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.datamodel.ProjectTypologyJPA;
import switchtwentyone.project.datamodel.assembler.ProjectTypologyDomainDataAssembler;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.ProjectTypologyRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectTypologyRepositoryTest {

    @Mock
    ProjectTypologyDomainDataAssembler projectTypologyDomainDataAssembler;

    @Mock
    IProjectTypologyJPARepository iProjectTypologyJPARepository;

    @InjectMocks
    ProjectTypologyRepositoryImpl projectTypologyRepository;


    @Test
    void saveProjectTypologySuccess() {
        ProjectTypology projectTypology = mock(ProjectTypology.class);
        ProjectTypology projectTypologySaved = mock(ProjectTypology.class);
        ProjectTypologyJPA projectTypologyJPA = mock(ProjectTypologyJPA.class);
        ProjectTypologyJPA projectTypologyJPASaved = mock(ProjectTypologyJPA.class);
        when(projectTypologyDomainDataAssembler.toData(projectTypology)).thenReturn(projectTypologyJPA);
        when(iProjectTypologyJPARepository.save(projectTypologyJPA)).thenReturn(projectTypologyJPASaved);
        when(projectTypologyDomainDataAssembler.toDomain(projectTypologyJPASaved)).thenReturn(projectTypologySaved);

        ProjectTypology result = projectTypologyRepository.saveProjectTypology(projectTypology);

        assertEquals(projectTypologySaved, result);
    }

    @Test
    void findProjectByIDFail() {
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        when(iProjectTypologyJPARepository.findById(projectTypologyID)).thenReturn(Optional.empty());
        Optional<ProjectTypology> optionalProjectTypologyFound = Optional.empty();

        Optional<ProjectTypology> result = projectTypologyRepository.findProjectTypologyByID(projectTypologyID);

        assertEquals(optionalProjectTypologyFound, result);

    }

    @Test
    void findProjectByIDSuccess() {
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        ProjectTypologyJPA projectTypologyJPA = mock(ProjectTypologyJPA.class);
        Optional<ProjectTypologyJPA> optionalProjectTypologyJPA = Optional.of(projectTypologyJPA);
        when(iProjectTypologyJPARepository.findById(projectTypologyID)).thenReturn(optionalProjectTypologyJPA);
        ProjectTypology projectTypology = mock(ProjectTypology.class);
        when(projectTypologyDomainDataAssembler.toDomain(projectTypologyJPA)).thenReturn(projectTypology);
        Optional<ProjectTypology> optionalProjectTypologyFound = Optional.of(projectTypology);

        Optional<ProjectTypology> result = projectTypologyRepository.findProjectTypologyByID(projectTypologyID);

       assertEquals(optionalProjectTypologyFound,result);

    }

    @Test
    void getAllProjectTypologies() {
        List<ProjectTypologyJPA> list = new ArrayList<>();
        ProjectTypologyJPA projectTypologyJPAOne = mock(ProjectTypologyJPA.class);
        ProjectTypologyJPA projectTypologyJPATwo = mock(ProjectTypologyJPA.class);
        list.add(projectTypologyJPAOne);
        list.add(projectTypologyJPATwo);
        Iterable<ProjectTypologyJPA> iterable = list;
        when(iProjectTypologyJPARepository.findAll()).thenReturn(iterable);
        ProjectTypology projectTypologyOne = mock(ProjectTypology.class);
        ProjectTypology projectTypologyTwo = mock(ProjectTypology.class);
        List<ProjectTypology> projectTypologyList = new ArrayList<>();
        projectTypologyList.add(projectTypologyOne);
        projectTypologyList.add(projectTypologyTwo);
        when(projectTypologyDomainDataAssembler.toDomain(projectTypologyJPAOne)).thenReturn(projectTypologyOne);
        when(projectTypologyDomainDataAssembler.toDomain(projectTypologyJPATwo)).thenReturn(projectTypologyTwo);

        List<ProjectTypology> result = projectTypologyRepository.getAllProjectTypologies();

        assertEquals(projectTypologyList, result);
    }
}
