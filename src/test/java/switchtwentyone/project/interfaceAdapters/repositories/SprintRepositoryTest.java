package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.ISprintJPARepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.datamodel.SprintJPA;
import switchtwentyone.project.datamodel.assembler.SprintDomainDataAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class SprintRepositoryTest {

    @Autowired
    SprintRepository sprintRepo;

    @MockBean
    ISprintJPARepository sprintJPARepository;
    @MockBean
    SprintDomainDataAssembler sprintDomainDataAssembler;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void saveSprintSuccessful() {
        Sprint spt = mock(Sprint.class);
        SprintJPA jpa = mock(SprintJPA.class);

        when(sprintDomainDataAssembler.toData(spt)).thenReturn(jpa);
        when(sprintJPARepository.save(jpa)).thenReturn(jpa);

        //act
        boolean result =  sprintRepo.saveSprint(spt);

        //Assert
        assertTrue(result);
    }

    @Test
    void findById() {
        Sprint spt = mock(Sprint.class);
        SprintJPA jpa = mock(SprintJPA.class);
        SprintID id = SprintID.ofDouble(1.001);

        when(sprintDomainDataAssembler.toData(spt)).thenReturn(jpa);
        when(sprintJPARepository.save(jpa)).thenReturn(jpa);
        when(spt.getSprintID()).thenReturn(id);

        Sprint spt1 = mock(Sprint.class);
        SprintJPA jpa1 = mock(SprintJPA.class);
        SprintID id1 = SprintID.ofDouble(1.002);

        when(sprintDomainDataAssembler.toData(spt1)).thenReturn(jpa1);
        when(sprintJPARepository.save(jpa1)).thenReturn(jpa1);
        when(spt1.getSprintID()).thenReturn(id1);
        sprintRepo.saveSprint(spt);
        sprintRepo.saveSprint(spt1);
        when(sprintJPARepository.findBySprintID(id1)).thenReturn(Optional.of(jpa1));
        when(sprintDomainDataAssembler.toDomain(jpa1)).thenReturn(spt1);

        //act
        Optional<Sprint> found = sprintRepo.findByID(id1);

        //assert
        assertEquals(spt1, found.get());
    }

    @Test
    void findByIdUnsuccessful() {
        Sprint spt = mock(Sprint.class);
        SprintJPA jpa = mock(SprintJPA.class);
        SprintID id = SprintID.ofDouble(2.001);

        when(sprintDomainDataAssembler.toData(spt)).thenReturn(jpa);
        when(sprintJPARepository.save(jpa)).thenReturn(jpa);
        when(spt.getSprintID()).thenReturn(id);

        Sprint spt1 = mock(Sprint.class);
        SprintJPA jpa1 = mock(SprintJPA.class);
        SprintID id1 = SprintID.ofDouble(2.002);

        when(sprintDomainDataAssembler.toData(spt1)).thenReturn(jpa1);
        when(sprintJPARepository.save(jpa1)).thenReturn(jpa1);
        when(spt1.getSprintID()).thenReturn(id1);
        sprintRepo.saveSprint(spt);
        sprintRepo.saveSprint(spt1);
        when(sprintJPARepository.findBySprintID(id1)).thenReturn(Optional.of(jpa1));
        when(sprintDomainDataAssembler.toDomain(jpa1)).thenReturn(spt1);

        SprintID id2 = SprintID.ofDouble(2.003);

        //act
        Optional<Sprint> found = sprintRepo.findByID(id2);

        //assert
        assertTrue(found.isEmpty());
    }

    @Test
    void findAllSprintByProjectID() {
        Sprint spt = mock(Sprint.class);
        SprintJPA jpa = mock(SprintJPA.class);
        SprintID id = SprintID.ofDouble(3.001);

        when(sprintDomainDataAssembler.toData(spt)).thenReturn(jpa);
        when(sprintJPARepository.save(jpa)).thenReturn(jpa);
        when(spt.getSprintID()).thenReturn(id);

        Sprint spt2 = mock(Sprint.class);
        SprintJPA jpa2 = mock(SprintJPA.class);
        SprintID id2 = SprintID.ofDouble(2.003);

        when(sprintDomainDataAssembler.toData(spt2)).thenReturn(jpa2);
        when(sprintJPARepository.save(jpa2)).thenReturn(jpa2);
        when(spt2.getSprintID()).thenReturn(id2);

        Sprint spt1 = mock(Sprint.class);
        SprintJPA jpa1 = mock(SprintJPA.class);
        SprintID id1 = SprintID.ofDouble(3.002);

        when(sprintDomainDataAssembler.toData(spt1)).thenReturn(jpa1);
        when(sprintJPARepository.save(jpa1)).thenReturn(jpa1);
        when(spt1.getSprintID()).thenReturn(id1);

        sprintRepo.saveSprint(spt);
        sprintRepo.saveSprint(spt1);

        List<SprintJPA> jpaList = new ArrayList<>();
        jpaList.add(jpa);
        jpaList.add(jpa1);

        when(sprintDomainDataAssembler.toDomain(jpa)).thenReturn(spt);
        when(sprintDomainDataAssembler.toDomain(jpa1)).thenReturn(spt1);
        when(sprintDomainDataAssembler.toDomain(jpa2)).thenReturn(spt2);

        when(sprintJPARepository.findAllByProjectID(3)).thenReturn(jpaList);

        //Act
        List<Sprint> result = sprintRepo.findAllSprintsByProjectID(new ProjectID(3));
        List<Sprint> expected = new ArrayList<>();
        expected.add(spt);
        expected.add(spt1);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void findAllSprintByProjectIDEmptyResult() {
        Sprint spt = mock(Sprint.class);
        SprintJPA jpa = mock(SprintJPA.class);
        SprintID id = SprintID.ofDouble(4.001);

        when(sprintDomainDataAssembler.toData(spt)).thenReturn(jpa);
        when(sprintJPARepository.save(jpa)).thenReturn(jpa);
        when(spt.getSprintID()).thenReturn(id);

        Sprint spt2 = mock(Sprint.class);
        SprintJPA jpa2 = mock(SprintJPA.class);
        SprintID id2 = SprintID.ofDouble(4.003);

        when(sprintDomainDataAssembler.toData(spt2)).thenReturn(jpa2);
        when(sprintJPARepository.save(jpa2)).thenReturn(jpa2);
        when(spt2.getSprintID()).thenReturn(id2);

        Sprint spt1 = mock(Sprint.class);
        SprintJPA jpa1 = mock(SprintJPA.class);
        SprintID id1 = SprintID.ofDouble(4.002);

        when(sprintDomainDataAssembler.toData(spt1)).thenReturn(jpa1);
        when(sprintJPARepository.save(jpa1)).thenReturn(jpa1);
        when(spt1.getSprintID()).thenReturn(id1);

        sprintRepo.saveSprint(spt);
        sprintRepo.saveSprint(spt1);

        List<SprintJPA> jpaList = new ArrayList<>();
        when(sprintDomainDataAssembler.toDomain(jpa)).thenReturn(spt);
        when(sprintDomainDataAssembler.toDomain(jpa1)).thenReturn(spt1);
        when(sprintDomainDataAssembler.toDomain(jpa2)).thenReturn(spt2);

        when(sprintJPARepository.findAllByProjectID(5)).thenReturn(jpaList);

        //Act
        List<Sprint> result = sprintRepo.findAllSprintsByProjectID(new ProjectID(3));
        List<Sprint> expected = new ArrayList<>();

        //Assert
        assertEquals(expected, result);
    }



    @Test
    void findRunningSprintByProjectID() {
        Sprint spt = mock(Sprint.class);
        SprintJPA jpa = mock(SprintJPA.class);
        SprintID id = SprintID.ofDouble(6.001);

        when(sprintDomainDataAssembler.toData(spt)).thenReturn(jpa);
        when(sprintJPARepository.save(jpa)).thenReturn(jpa);
        when(spt.getSprintID()).thenReturn(id);

        Sprint spt2 = mock(Sprint.class);
        SprintJPA jpa2 = mock(SprintJPA.class);
        SprintID id2 = SprintID.ofDouble(6.003);

        when(sprintDomainDataAssembler.toData(spt2)).thenReturn(jpa2);
        when(sprintJPARepository.save(jpa2)).thenReturn(jpa2);
        when(spt2.getSprintID()).thenReturn(id2);

        Sprint spt1 = mock(Sprint.class);
        SprintJPA jpa1 = mock(SprintJPA.class);
        SprintID id1 = SprintID.ofDouble(6.002);

        when(sprintDomainDataAssembler.toData(spt1)).thenReturn(jpa1);
        when(sprintJPARepository.save(jpa1)).thenReturn(jpa1);
        when(spt1.getSprintID()).thenReturn(id1);

        sprintRepo.saveSprint(spt);
        sprintRepo.saveSprint(spt1);

        List<SprintJPA> jpaList = new ArrayList<>();
        jpaList.add(jpa);
        jpaList.add(jpa1);

        when(sprintDomainDataAssembler.toDomain(jpa)).thenReturn(spt);
        when(sprintDomainDataAssembler.toDomain(jpa1)).thenReturn(spt1);
        when(sprintDomainDataAssembler.toDomain(jpa2)).thenReturn(spt2);

        when(sprintJPARepository.findAllByProjectID(6)).thenReturn(jpaList);

        when(spt.isRunning()).thenReturn(true);
        when(spt1.isRunning()).thenReturn(false);

        //Act
        Optional<Sprint> result = sprintRepo.findRunningSprintByProjectID(new ProjectID(6));
        Optional<Sprint> expected = Optional.of(spt);

        //Assert
        assertEquals(expected, result);
    }

}


