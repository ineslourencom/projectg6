package switchtwentyone.project.interfaceAdapters.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IProfileJPARepository;
import switchtwentyone.project.domain.aggregates.profile.*;
import switchtwentyone.project.datamodel.ProfileJPA;
import switchtwentyone.project.datamodel.assembler.ProfileDomainDataAssembler;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.ProfileRepositoryImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileRepositoryTest {

    @Mock
    IProfileJPARepository profileJPARepository;

    @Mock
    ProfileDomainDataAssembler profileAssembler;

    @InjectMocks
    private ProfileRepositoryImpl profRepo;


    @AfterEach
    public void destroyAll(){
        profRepo.deleteAll();
    }

    /*
    @Test
    void save() {
        Profile prfOne = mock(Profile.class);

        ProfileJpa prfOneJpa = mock(ProfileJpa.class);
        when(profRepoJPA.save(prfOneJpa)).thenReturn(prfOneJpa);

        Profile profile = profRepo.save(prfOne);


        assertEquals(prfOne, profile);
    }


     */

/*
    @ParameterizedTest
    @CsvSource({"true, false, false",
            "false, true, true",
            "true, true, false"})
    void findProfileById(boolean hasId1, boolean hasId2, boolean hasId3){
        Profile prf1 = mock(Profile.class);
        Profile prf2 = mock(Profile.class);
        Profile prf3 = mock(Profile.class);
        ProfileID id = mock(ProfileID.class);

        profRepo.save(prf1);
        profRepo.save(prf2);
        profRepo.save(prf3);

        when(prf1.hasID(id)).thenReturn(hasId1);
        when(prf2.hasID(id)).thenReturn(hasId2);
        when(prf3.hasID(id)).thenReturn(hasId3);


        Optional<Profile> found = profRepo.findProfileById(id);

        assertTrue(found.isPresent());
    }


 */
@Test
void findProfileByIdTrue(){
    Profile prf1 = mock(Profile.class);
    ProfileJPA prfJPA = mock(ProfileJPA.class);
    ProfileID id = ProfileID.ofProfileType("test");
    Optional<ProfileJPA> optPrfJPA1 = Optional.of(prfJPA);
    when(profileJPARepository.findById(id)).thenReturn(optPrfJPA1);
    when(profileAssembler.toDomain(prfJPA)).thenReturn(prf1);

    Optional<Profile> found = profRepo.findProfileById(id);

    assertEquals(Optional.of(prf1), found);
}

    @Test
    void findProfileByIdFalse(){
        Profile prf1 = mock(Profile.class);
        ProfileID id = ProfileID.ofProfileType("test");
        Optional<ProfileJPA> optPrfJPA1 = Optional.empty();
        when(profileJPARepository.findById(id)).thenReturn(optPrfJPA1);

        Optional<Profile> found = profRepo.findProfileById(id);

        assertEquals(Optional.empty(), found);
    }

}