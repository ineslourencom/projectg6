package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceCreateUS;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.USDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ApplicationServiceCreateUserStoryTest {

    @Mock
    private ProjectRepository projRepo;
    @Mock
    private UserStoryRepository usRepo;
    @Mock
    private ServiceCreateUS serviceCreateUS;
    @InjectMocks
    ApplicationServiceCreateUserStory appServcCreateUS;

    @Test
    void createUserStory() {
        //Arrange
        UserStory us = mock(UserStory.class);
        Project proj = mock(Project.class);
        List<UserStory> listUS = new ArrayList<>();
        listUS.add(us);

        ProjectID projID = new ProjectID(1);
        Text statement = Text.write("statement");
        Text detail = Text.write("detail");
        PositiveNumber usNumber = PositiveNumber.of(1);
        int priority = 1;
        UserStoryID usID = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);

//        when(us.getStoryNumber()).thenReturn(usNumber);
//        when(us.getPriority()).thenReturn(priority);
//        when(us.getUsID()).thenReturn(usID);
//        when(us.getDetail()).thenReturn(detail);
//        when(us.getIsDecomposed()).thenReturn(false);
//        when(us.getParent()).thenReturn(usID);
//        when(us.getProjectID()).thenReturn(projID);
//        when(us.getStatement()).thenReturn(statement);

        UserStory us2 = mock(UserStory.class);
        PositiveNumber usNumber2 = PositiveNumber.of(2);
        int priority2 = 2;
        UserStoryID usID2 = UserStoryID.ofProjIDAndUsNumber(projID, usNumber2);

        when(us2.getStoryNumber()).thenReturn(usNumber2);
        when(us2.getPriority()).thenReturn(priority2);
        when(us2.getUsID()).thenReturn(usID2);
        when(us2.getDetail()).thenReturn(detail);
        when(us2.getIsDecomposed()).thenReturn(false);
        when(us2.getParent()).thenReturn(usID);
        when(us2.getProjectID()).thenReturn(projID);
        when(us2.getStatement()).thenReturn(statement);

        when(projRepo.findById(projID)).thenReturn(Optional.of(proj));
        when(usRepo.findAllUSByProjectID(projID)).thenReturn(listUS);
        when(serviceCreateUS.generateUSNumber(listUS)).thenReturn(usNumber2);
        when(serviceCreateUS.generateUSID(usNumber2, projID)).thenReturn(usID2);
        when(serviceCreateUS.determineUSPriority(listUS)).thenReturn(priority2);
        when(proj.createUserStory(statement, detail, usID2, priority2, usNumber2)).thenReturn(us2);
        when(usRepo.saveUS(us2)).thenReturn(true);

        USDTO usDTOExpected = UserStoryMapper.toDTO(us2);

        //Act
        USDTO usDTOResult = appServcCreateUS.createUserStory(statement, detail, projID);

        //Assert
        assertEquals(usDTOResult, usDTOExpected);
    }

    @Test
    void findUSByID() {
        //Arrange
        Project proj = mock(Project.class);
        List<UserStory> listUS = new ArrayList<>();

        ProjectID projID = new ProjectID(1);
        Text statement = Text.write("statement");
        Text detail = Text.write("detail");
        UserStory us2 = mock(UserStory.class);
        PositiveNumber usNumber2 = PositiveNumber.of(2);
        int priority2 = 2;
        UserStoryID usID2 = UserStoryID.ofProjIDAndUsNumber(projID, usNumber2);

//        when(us2.getStoryNumber()).thenReturn(usNumber2);
//        when(us2.getPriority()).thenReturn(priority2);
//        when(us2.getUsID()).thenReturn(usID2);
//        when(us2.getDetail()).thenReturn(detail);
//        when(us2.getIsDecomposed()).thenReturn(false);
//        when(us2.getParent()).thenReturn(usID2);
//        when(us2.getProjectID()).thenReturn(projID);
//        when(us2.getStatement()).thenReturn(statement);

//        when(projRepo.findById(projID)).thenReturn(Optional.of(proj));
//        when(usRepo.findAllUSByProjectID(projID)).thenReturn(listUS);
//        when(serviceCreateUS.generateUSNumber(listUS)).thenReturn(usNumber2);
//        when(serviceCreateUS.generateUSID(usNumber2, projID)).thenReturn(usID2);
//        when(serviceCreateUS.determineUSPriority(listUS)).thenReturn(priority2);
//        when(proj.createUserStory(statement, detail, usID2, priority2, usNumber2)).thenReturn(us2);
//        when(usRepo.saveUS(us2)).thenReturn(true);
//        when(us2.hasID(usID2)).thenReturn(true);
        when(usRepo.findByID(usID2)).thenReturn(Optional.of(us2));

        //Act
        Optional<UserStory> foundUS = appServcCreateUS.findUSByID(usID2);

        //Assert
        assertEquals(Optional.of(us2), foundUS);

    }
}