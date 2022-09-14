package switchtwentyone.project.interfaceAdapters.repositories;

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
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.UserStoryJPARepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.datamodel.UserStoryJPA;
import switchtwentyone.project.datamodel.assembler.UserStoryDomainDataAssembler;
import switchtwentyone.project.interfaceAdapters.repositories.repositories.UserStoryRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserStoryRepositoryTest {

    @Mock
    UserStoryJPARepository usJPARepo;
    @Mock
    UserStoryDomainDataAssembler usDomaintoData;

    @InjectMocks
    private UserStoryRepositoryImpl usRepo;



    @Test
    void saveUSSuccessfull() {
        UserStory us3 = mock(UserStory.class);
        UserStoryJPA usJPA3 = mock(UserStoryJPA.class);

        when(usDomaintoData.toData(us3)).thenReturn(usJPA3);
        when(usJPARepo.save(usJPA3)).thenReturn(usJPA3);

        //act
        boolean result = usRepo.saveUS(us3);;

        //Assert
        assertTrue(result);
    }


    @Test
    void findAllUSByProjectID() {
        //Arrange
        List<UserStoryJPA> listUSJPA = new ArrayList<>();
        UserStoryJPA usJPA1 = mock(UserStoryJPA.class);
        UserStoryJPA usJPA2 = mock(UserStoryJPA.class);
        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);

        listUSJPA.add(usJPA1);
        listUSJPA.add(usJPA2);
        ProjectID projectID = new ProjectID(1);
        when(usJPARepo.findByProjectID(projectID.getProjectID())).thenReturn(listUSJPA);
        when(usDomaintoData.toDomain(usJPA1)).thenReturn(us1);
        when(usDomaintoData.toDomain(usJPA2)).thenReturn(us2);

        List<UserStory> expected = new ArrayList<>();
        expected.add(us1);
        expected.add(us2);

        //Act
        List<UserStory> result = usRepo.findAllUSByProjectID(projectID);

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void findByIdExistingUserStory() {
       //Arrange
        UserStory us4 = mock(UserStory.class);
        UserStoryJPA usJPA4 = mock(UserStoryJPA.class);
        Optional<UserStoryJPA> opUSJPA4 = Optional.of(usJPA4);
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        when(usJPARepo.findById(usID)).thenReturn(opUSJPA4);
        when(usDomaintoData.toDomain(usJPA4)).thenReturn(us4);

        //Act
        Optional<UserStory> found = usRepo.findByID(usID);

        assertEquals(us4, found.get());

    }

    @Test
    void findByIdNotExistingUserStory() {
        //Arrange
        UserStory us5 = mock(UserStory.class);
        Optional<UserStoryJPA> opUSJPA5 = Optional.empty();
        UserStoryID usID = UserStoryID.ofDouble(1.2);
        when(usJPARepo.findById(usID)).thenReturn(opUSJPA5);

        //Act
        Optional<UserStory> found = usRepo.findByID(usID);

        assertEquals(Optional.empty(), found);

    }


    @Test
    void updateTestOne() {
        //Arrange
        UserStory us6 = mock(UserStory.class);
        UserStoryJPA usJPA6 = mock(UserStoryJPA.class);
        when(usDomaintoData.toData(us6)).thenReturn(usJPA6);
        when(usJPARepo.save(usJPA6)).thenReturn(usJPA6);
        when(usDomaintoData.toDomain(usJPA6)).thenReturn(us6);

        //Act
        UserStory us7 = usRepo.save(us6);

        //Assert
        assertEquals(us6, us7);
    }


    @Test
    void saveAllSuccess() {
        UserStory us8 = mock(UserStory.class);
        UserStory us9 = mock(UserStory.class);

        List<UserStory> listUS = new ArrayList<>();
        listUS.add(us8);
        listUS.add(us9);

        UserStoryJPA usJPA8 = mock(UserStoryJPA.class);
        UserStoryJPA usJPA9 = mock(UserStoryJPA.class);

        when(usDomaintoData.toData(us8)).thenReturn(usJPA8);
        when(usDomaintoData.toData(us9)).thenReturn(usJPA9);

        List<UserStoryJPA> listUSJPA = new ArrayList<>();
        listUSJPA.add(usJPA8);
        listUSJPA.add(usJPA9);
        when(usJPARepo.saveAll(listUSJPA)).thenReturn(listUSJPA);

        //act
        boolean result = usRepo.saveAll(listUS);

        //Assert
        assertTrue(result);
    }

}
