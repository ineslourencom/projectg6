package switchtwentyone.project.interfaceAdapters.controllers.implControllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateSprint;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetRunningSprint;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetUserStoriesNotDoneFromRunningSprint;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.dto.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@AutoConfigureMockMvc
@SpringBootTest
public class SprintControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ApplicationServiceCreateSprint appServcCreateSprint;

    @Autowired
    SprintController sprintController;

    @MockBean
    ApplicationServiceGetRunningSprint applicationServiceGetRunningSprint;

    @MockBean
    ApplicationServiceGetUserStoriesNotDoneFromRunningSprint applicationServiceGetUserStoriesNotDoneFromRunningSprint;


    @Test
    public void createSprintControllerSuccessful() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Sprint sprintMock = mock(Sprint.class);
        Optional<Sprint> optionalSprint = Optional.of(sprintMock);

        SprintDTO newSprintDTO = mock(SprintDTO.class);

        ProjectID projID = new ProjectID(1);
        LocalDate startDate = LocalDate.of(2022, 04, 26);
        PositiveNumber sprintDuration = PositiveNumber.of(8);

        NewSprintInfoDTO newSprintInfo = mock(NewSprintInfoDTO.class);
        when(newSprintInfo.getStartDate()).thenReturn(startDate);

        when(appServcCreateSprint.createSprint(startDate, projID)).thenReturn(newSprintDTO);

        // Act
        ResponseEntity<Object> responseEntity = sprintController.createSprint(1, newSprintInfo);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    @Test
    public void createSprintUnsuccessful() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        LocalDate startDate = LocalDate.of(2022, 04, 26);

        NewSprintInfoDTO newSprintInfo = mock(NewSprintInfoDTO.class);
        when(newSprintInfo.getStartDate()).thenReturn(startDate);

        // Act
        ResponseEntity<Object> responseEntity = sprintController.createSprint(1, newSprintInfo);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 422);
    }

    @Test
    void getSprintById() {
        // Arrange
        Sprint sprint = mock(Sprint.class);
        Optional<Sprint> optionalSprint = Optional.of(sprint);

        SprintID sprintID = SprintID.ofDouble(1.001);
        when(appServcCreateSprint.findSprintByID(sprintID)).thenReturn(optionalSprint);
        when(sprint.getSprintID()).thenReturn(sprintID);
        when(sprint.getSprintNumber()).thenReturn(1);
        when(sprint.getProjectID()).thenReturn(1);
        when(sprint.getStartDate()).thenReturn(LocalDate.of(2022, 5, 1));
        when(sprint.getSprintDuration()).thenReturn(PositiveNumber.of(2));
        when(sprint.getSprintBacklogItems()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<Object> responseEntity = sprintController.getSprintById(1.001);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    void getSprintByIdNotFound() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        SprintID sprintID = SprintID.ofDouble(1.1);
        when(appServcCreateSprint.findSprintByID(sprintID)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Object> responseEntity = sprintController.getSprintById(1.1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 404);
    }

    @Test
    void setNewStartDate() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Sprint sprintMock = mock(Sprint.class);

        SprintDTO newSprintDTO = mock(SprintDTO.class);
        int sprintNumber = 1;
        ProjectID projID = new ProjectID(1);
        LocalDate startDate = LocalDate.of(2022, 04, 26);


        NewSprintInfoDTO newSprintInfo = mock(NewSprintInfoDTO.class);
        when(newSprintInfo.getStartDate()).thenReturn(startDate);

        when(appServcCreateSprint.setNewStartDate(startDate, projID, sprintNumber)).thenReturn(Optional.of(newSprintDTO));

        // Act
        ResponseEntity<Object> responseEntity = sprintController.setNewStartDate(1, 1, newSprintInfo);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    void gerRunningSprintByProjectID() {
        SprintInfoForTaskDTO sprintInfoForTaskDTO = mock(SprintInfoForTaskDTO.class);

        when(applicationServiceGetRunningSprint.getRunningSprint(1)).thenReturn(sprintInfoForTaskDTO);

        ResponseEntity expected = new ResponseEntity(sprintInfoForTaskDTO, HttpStatus.OK);

        ResponseEntity result = sprintController.gerRunningSprintByProjectID(1);

        assertEquals(expected, result);

    }
    @Test
    void gerRunningSprintByProjectID_NoRunningSprint() {
        SprintInfoForTaskDTO sprintInfoForTaskDTO = null;

        when(applicationServiceGetRunningSprint.getRunningSprint(1)).thenReturn(null);

        ResponseEntity expected = new ResponseEntity(sprintInfoForTaskDTO, HttpStatus.NOT_FOUND);

        ResponseEntity result = sprintController.gerRunningSprintByProjectID(1);

        assertEquals(expected, result);

    }


    @Test
    void getUserStoriesDoneInRunningSprint() {
        List<USMinimalInfoDTO> list = new ArrayList<>();

        when(applicationServiceGetUserStoriesNotDoneFromRunningSprint.getUSNotDoneFromRunningSprint(1)).thenReturn(list);

        ResponseEntity expected = new ResponseEntity(list, HttpStatus.OK);

        ResponseEntity result = sprintController.getUserStoriesDoneInRunningSprint(1);

        assertEquals(expected, result);
    }


    @Test
    void getUserStoriesDoneInRunningSprint_exception() {
        List<USMinimalInfoDTO> list = new ArrayList<>();

        when(applicationServiceGetUserStoriesNotDoneFromRunningSprint.getUSNotDoneFromRunningSprint(1)).thenThrow(new IllegalArgumentException("Banana"));

        ResponseEntity expected = new ResponseEntity("Banana", HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity result = sprintController.getUserStoriesDoneInRunningSprint(1);

        assertEquals(expected, result);
    }

    @Test
    void getAllSprintsByProjectID() {
        int projectID = 5;
        List<SprintTableDTO> listSprints = new ArrayList<>();
        when(appServcCreateSprint.getAllSprintsByProjectID(projectID)).thenReturn(listSprints);
        ResponseEntity expected = new ResponseEntity(listSprints, HttpStatus.OK);

        ResponseEntity result = sprintController.getAllSprintsByProjectID(projectID);

        assertEquals(expected, result);

    }


    @Test
    void setNewStartDate_Failure() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Sprint sprintMock = mock(Sprint.class);

        SprintDTO newSprintDTO = mock(SprintDTO.class);
        int sprintNumber = 1;
        ProjectID projID = new ProjectID(1);
        LocalDate startDate = LocalDate.of(2022, 04, 26);


        NewSprintInfoDTO newSprintInfo = mock(NewSprintInfoDTO.class);
        when(newSprintInfo.getStartDate()).thenReturn(startDate);

        when(appServcCreateSprint.setNewStartDate(startDate, projID, sprintNumber)).thenReturn(Optional.empty());

        ResponseEntity expected = new ResponseEntity(Optional.empty(), HttpStatus.NOT_FOUND);


        // Act
        ResponseEntity<Object> responseEntity = sprintController.setNewStartDate(1, 1, newSprintInfo);

        // Assert
        assertEquals(expected, responseEntity);
    }
}
