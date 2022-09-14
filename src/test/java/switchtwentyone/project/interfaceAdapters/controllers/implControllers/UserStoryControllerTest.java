package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentyone.project.applicationServices.implAppServices.*;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserStoryControllerTest {

    @Mock
    ApplicationServiceChangeUserStoryPriority applicationServiceChangeUserStoryPriority;
    @Mock
    ApplicationServiceCreateUserStory appServcCreateUS;
    @Mock
    ApplicationServiceDecomposeUserStory appService;
    @Mock
    ApplicationServiceAddUSToSprintBacklog appServcAddUS;
    @Mock
    private ApplicationServiceGetAllUserStoryStatus appServiceGetAllUserStoryStatus;
    @InjectMocks
    UserStoryController userStoryController;

    @Test
    public void createUserStorySuccessful() {
        // Arrange
        UserStory usMock = mock(UserStory.class);
        Optional<UserStory> optionalUS = Optional.of(usMock);

        USDTO newUSDTO = mock(USDTO.class);

        Text statement = Text.write("statement");
        Text detail = Text.write("detail");
        ProjectID projID = new ProjectID(1);

        NewUserStoryInfoDTO newUSInfo = mock(NewUserStoryInfoDTO.class);
        when(newUSInfo.getDetail()).thenReturn("detail");
        when(newUSInfo.getStatement()).thenReturn("statement");

        when(appServcCreateUS.createUserStory(statement, detail, projID)).thenReturn(newUSDTO);

        // Act
        ResponseEntity<Object> responseEntity = userStoryController.createUserStory(1, newUSInfo);

        // Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void createUserStoryUnsuccessful() {
        // Arrange
        UserStory usMock = mock(UserStory.class);
        Optional<UserStory> optionalUS = Optional.of(usMock);

        NewUserStoryInfoDTO newUSInfo = mock(NewUserStoryInfoDTO.class);
        when(newUSInfo.getDetail()).thenReturn("");
        when(newUSInfo.getStatement()).thenReturn("statement");

        // Act
        ResponseEntity<Object> responseEntity = userStoryController.createUserStory(1, newUSInfo);

        // Assert
        assertEquals(422, responseEntity.getStatusCodeValue());
    }



    @Test
    void getUserStoryById() {
        // Arrange
        UserStory us = mock(UserStory.class);
        Optional<UserStory> optionalUS = Optional.of(us);
        ProjectID projectID = new ProjectID(1);
        UserStoryID usID = UserStoryID.ofDouble(1.001);
        Text newText = Text.write("sd");
        when( appServcCreateUS.findUSByID(usID)).thenReturn(optionalUS);
        when(us.getProjectID()).thenReturn(projectID);
        when(us.getPriority()).thenReturn(1);
        when(us.getParent()).thenReturn(usID);
        when(us.getStoryNumber()).thenReturn(PositiveNumber.of(1));
        when(us.getIsDecomposed()).thenReturn(false);
        when(us.getStatement()).thenReturn(newText);
        when(us.getDetail()).thenReturn(newText);
        when(us.getUsID()).thenReturn(UserStoryID.ofDouble(1.001));

        // Act
        ResponseEntity<Object> responseEntity = userStoryController.getUserStoryById(1, 1.001);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void getUserStoryByIdNotFound() {
        // Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        when( appServcCreateUS.findUSByID(usID)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Object> responseEntity = userStoryController.getUserStoryById(1, 1.1);

        // Assert
        assertEquals(404, responseEntity.getStatusCodeValue());
    }


    @Test
    void createUserStory_NOTFOUND() {
        NewUserStoryInfoDTO newUSInfo = mock(NewUserStoryInfoDTO.class);
        when(newUSInfo.getDetail()).thenReturn("sd");
        when(newUSInfo.getStatement()).thenReturn("sd");
        ProjectID projectID = new ProjectID(1);
        UserStoryID usID = UserStoryID.ofDouble(1.001);
        Text newText = Text.write("sd");
        String message = "Project not found";
        when(appServcCreateUS.createUserStory(newText, newText, projectID)).thenThrow(new EntityNotFoundException(message));
        BusinessErrorMessage msg = new BusinessErrorMessage(message, BusinessErrorMessage.NOT_FOUND);
        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        ResponseEntity<Object> result = userStoryController.createUserStory(1, newUSInfo);

        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }


    @Test
    void changePriorityOfUserStory() {
        int projID = 1;
        double usID = 1.001;
        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.newPriority = 2;
        USWithPriorityDTO usWithPriorityDTO = new USWithPriorityDTO();
        when(applicationServiceChangeUserStoryPriority.changePriorityOfUserStory(projID, usID, priorityDTO.newPriority)).thenReturn(usWithPriorityDTO);
        ResponseEntity expected = new ResponseEntity(usWithPriorityDTO, HttpStatus.OK);

        ResponseEntity result = userStoryController.changePriorityOfUserStory(projID, usID, priorityDTO);

        assertEquals(expected, result);
    }

    @Test
    void changePriorityOfUserStory_Failure() {
        int projID = 1;
        double usID = 1.001;
        PriorityDTO priorityDTO = new PriorityDTO();
        priorityDTO.newPriority = 2;
        USWithPriorityDTO usWithPriorityDTO = new USWithPriorityDTO();
        when(applicationServiceChangeUserStoryPriority.changePriorityOfUserStory(projID, usID, priorityDTO.newPriority)).thenThrow(new IllegalArgumentException("Banana"));
        BusinessErrorMessage msg = new BusinessErrorMessage("Banana", BusinessErrorMessage.UNPROCESSABLE_ENTITY);

        ResponseEntity expected = new ResponseEntity(msg, HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity result = userStoryController.changePriorityOfUserStory(projID, usID, priorityDTO);

        assertEquals(expected, result);
    }

    /*
    @Test
    void testGetUserStoryById_Exception() {
        // Arrange
        UserStoryID usID = UserStoryID.ofDouble(1.1);
        when( appServcCreateUS.findUSByID(usID)).thenThrow(new IllegalArgumentException("ups"));

        // Act
        ResponseEntity<Object> responseEntity = createUserStoryController.getUserStoryById(1, 1.1);

        // Assert
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    }*/


    @Test
    void decomposeUserStorySuccessTest() {

        //Arrange
        List<ChildUserStoryDTO> dtoList = mock(List.class);
        double id = 1.1;
        int projectID = 1;
        List<USDTO> changedUS = new ArrayList<>();
        USDTO usdto1 = mock(USDTO.class);
        USDTO usdto2 = mock(USDTO.class);
        USDTO usdto3 = mock(USDTO.class);
        changedUS.add(usdto1);
        changedUS.add(usdto2);
        changedUS.add(usdto3);

        when(appService.decomposeUserStory(id, dtoList, projectID)).thenReturn(changedUS);

        //Act
        ResponseEntity<Object> result = userStoryController.decomposeUserStory(dtoList, id, projectID);
        //Assert
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);


    }

    @Test
    void decomposeUserStoryNotFoundTest() {

        //Arrange
        List<ChildUserStoryDTO> dtoList = mock(List.class);
        double id = 1.1;
        int projectID = 1;
        String message = "User Story not found";
        when(appService.decomposeUserStory(id, dtoList, projectID)).thenThrow(new EntityNotFoundException("User Story not found"));
        BusinessErrorMessage msg = new BusinessErrorMessage(message, BusinessErrorMessage.NOT_FOUND);
        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        //Act
        ResponseEntity<Object> result = userStoryController.decomposeUserStory(dtoList, id, projectID);

        //Assert
        assertEquals(expected, result);

    }
    @Test
    void decomposeUserStoryBadRequestTest() {

        //Arrange
        List<ChildUserStoryDTO> dtoList = mock(List.class);
        double id = 1.1;
        int projectID = 1;
        String message = "Something Went Wrong";
        when(appService.decomposeUserStory(id, dtoList, projectID)).thenThrow(new IllegalArgumentException(message));
        BusinessErrorMessage msg = new BusinessErrorMessage(message, BusinessErrorMessage.INVALID_ENTRY);
        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        //Act

        ResponseEntity<Object> result = userStoryController.decomposeUserStory(dtoList, id, projectID);
        //Assert

        assertEquals(expected, result);
    }


    @Test
    public void createSprintBacklogItemSuccessful() {
        // Arrange
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO(1.1, 1);
        when(appServcAddUS.addUSToSprintBacklog(1, dto)).thenReturn(true);

        // Act
        ResponseEntity<Object> responseEntity = userStoryController.addUSToSprintBacklog(1, dto);

        // Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void createSprintBacklogItemUnSuccessful() {
        // Arrange
        // Arrange
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO(1.1, 1);
        when(appServcAddUS.addUSToSprintBacklog(1, dto)).thenThrow(new IllegalArgumentException("There isn't a sprint currently running."));

        // Act
        ResponseEntity<Object> responseEntity = userStoryController.addUSToSprintBacklog(1, dto);

        // Assert
        assertEquals(422, responseEntity.getStatusCodeValue());
    }

    @Test
    void createSprintBacklogItemEntityNotFoundException() {
        String message = "No Project was not found";
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO(1.1, 1);
        when(appServcAddUS.addUSToSprintBacklog(1, dto)).thenThrow(new EntityNotFoundException(message));
        BusinessErrorMessage msg = new BusinessErrorMessage(message, BusinessErrorMessage.NOT_FOUND);
        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        ResponseEntity<Object> result = userStoryController.addUSToSprintBacklog(1, dto);

        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }

    @Test
    void createSprintBacklogItemException() {
        String message = "No Project was not found";
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO(1.1, 1);
        when(appServcAddUS.addUSToSprintBacklog(1, dto)).thenThrow(new IllegalArgumentException(message));
        BusinessErrorMessage msg = new BusinessErrorMessage(message, BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        ResponseEntity<Object> result = userStoryController.addUSToSprintBacklog(1, dto);

        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }


    @Test
    void viewUSStatusFromProject_withUSExistent() {

        USInfoAndStatusDTO usInfoStatus1 = mock(USInfoAndStatusDTO.class);
        USInfoAndStatusDTO usInfoStatus2 = mock(USInfoAndStatusDTO.class);
        USInfoAndStatusDTO usInfoStatus3 = mock(USInfoAndStatusDTO.class);

        List<USInfoAndStatusDTO> usInfoListDTO = new ArrayList<>();
        usInfoListDTO.add(usInfoStatus1);
        usInfoListDTO.add(usInfoStatus2);
        usInfoListDTO.add(usInfoStatus3);



        ResponseEntity expected = new ResponseEntity<>(usInfoListDTO, HttpStatus.OK);
        when(appServiceGetAllUserStoryStatus.getAllUserStoryStatus(1)).thenReturn(usInfoListDTO);

        ResponseEntity result =  userStoryController.viewUSStatusFromProject(1);


        assertEquals(expected, result);
    }

    @Test
    void viewUSStatusFromProject_withNoUS() {
        List<USInfoAndStatusDTO> usInfoListDTO = new ArrayList<>();

        ResponseEntity expected = new ResponseEntity<>("No User Stories found in this project", HttpStatus.OK);
        when(appServiceGetAllUserStoryStatus.getAllUserStoryStatus(1)).thenReturn(usInfoListDTO);

        ResponseEntity result =  userStoryController.viewUSStatusFromProject(1);


        assertEquals(expected, result);
    }

    @Test
    void viewUSStatusFromProject_BadRequest(){
        String message = "No Project was found";
        when(appServiceGetAllUserStoryStatus.getAllUserStoryStatus(2)).thenThrow(new IllegalArgumentException(message));
        BusinessErrorMessage msg = new BusinessErrorMessage(message, BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        ResponseEntity<Object> result = userStoryController.viewUSStatusFromProject(2);

        assertEquals(expected.getStatusCode(), result.getStatusCode());

    }
}