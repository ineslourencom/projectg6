package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateResource;
import switchtwentyone.project.dto.NewResourceDTO;
import switchtwentyone.project.dto.NewProductOwnerDTO;
import switchtwentyone.project.dto.NewScrumMasterDTO;
import switchtwentyone.project.dto.ResourceDTO;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceControllerTest {

    @Mock
    ApplicationServiceCreateResource appService;

    @InjectMocks
    ResourceController controller;

    @Test
    void createResourceSuccess() {
        NewResourceDTO dto = new NewResourceDTO();
        ResourceDTO response = new ResourceDTO();
        when(appService.createResource(dto, 1)).thenReturn(response);
        ResponseEntity<Object> expected = new ResponseEntity<>(response, HttpStatus.CREATED);

        ResponseEntity<Object> result = controller.createResource(dto, 1);

        assertEquals(expected, result);
    }

    @Test
    void createResourceUnsuccessful() {
        NewResourceDTO dto = new NewResourceDTO();
        String message = "An exception ocurred";
        BusinessErrorMessage bem = new BusinessErrorMessage(message, BusinessErrorMessage.INVALID_ENTRY);

        when(appService.createResource(dto, 1)).thenThrow(new IllegalArgumentException(message));
        ResponseEntity<Object> expected = new ResponseEntity<>(bem, HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> result = controller.createResource(dto, 1);

        assertEquals(expected, result);


    }

    @Test
    void defineProductOwnerTest() {
        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        ResourceDTO response = new ResourceDTO();
        int projectID = 1;
        when(appService.defineProductOwner(dto, projectID)).thenReturn(response);
        ResponseEntity<Object> expected = new ResponseEntity<>(response, HttpStatus.CREATED);
        ResponseEntity<Object> result = controller.defineProductOwner(dto, projectID);
        assertEquals(expected, result);
    }
    @Test
    void defineProductOwnerNotFoundTest() {
        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        int projectID = 1;
        String message = "An exception ocurred";
        when(appService.defineProductOwner(dto, projectID)).thenThrow(new EntityNotFoundException(message));
        BusinessErrorMessage errorMessage = new BusinessErrorMessage(message, BusinessErrorMessage.NOT_FOUND);
        ResponseEntity<Object> expected = new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

        ResponseEntity<Object> result = controller.defineProductOwner(dto, projectID);

        assertEquals(expected, result);
    }
    @Test
    void defineProductOwnerBadInputTest() {
        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        int projectID = 1;
        String message = "An exception ocurred";
        when(appService.defineProductOwner(dto, projectID)).thenThrow(new IllegalArgumentException(message));
        BusinessErrorMessage errorMessage = new BusinessErrorMessage(message, BusinessErrorMessage.INVALID_ENTRY);
        ResponseEntity<Object> expected = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> result = controller.defineProductOwner(dto, projectID);

        assertEquals(expected, result);
    }

    @Test
    void newScrumMaster_Created() {
        NewScrumMasterDTO dto = new NewScrumMasterDTO();
        dto.associatedAccount = "luis@isep.ipp.pt";
        dto.startDate = "22-06-01";
        dto.endDate = "22-06-07";
        dto.percentageOfAllocation =0.5;
        int projID = 1;

        ResourceDTO newScrumMaster = new ResourceDTO();

        when(appService.updateScrumMaster(dto,projID)).thenReturn(newScrumMaster);

        ResponseEntity<Object>expected = new ResponseEntity<>(newScrumMaster,HttpStatus.CREATED);

        ResponseEntity<Object>result = controller.newScrumMaster(dto,projID);

        assertEquals(expected,result);
    }

    @Test
    void newScrumMaster_NotFound() {
        NewScrumMasterDTO dto = new NewScrumMasterDTO();
        dto.associatedAccount = "luis@isep.ipp.pt";
        dto.startDate = "22-06-01";
        dto.endDate = "22-06-07";
        dto.percentageOfAllocation =0.5;
        int projID = 1;

        String message = "Project Not Found";

        when(appService.updateScrumMaster(dto,projID)).thenThrow(new EntityNotFoundException(message));
        BusinessErrorMessage notFound = new BusinessErrorMessage(message,BusinessErrorMessage.NOT_FOUND);


        ResponseEntity<Object>expected = new ResponseEntity<>(notFound,HttpStatus.NOT_FOUND);

        ResponseEntity<Object>result = controller.newScrumMaster(dto,projID);

        assertEquals(expected,result);

    }

    @Test
    void newScrumMaster_BAD_REQUEST() {
        NewScrumMasterDTO dto = new NewScrumMasterDTO();
        dto.associatedAccount = "luis@isep.ipp.pt";
        dto.startDate = "22-06-01";
        dto.endDate = "22-06-07";
        dto.percentageOfAllocation =0.5;
        int projID = 1;

        String exception = "An exception ocurred";

        when(appService.updateScrumMaster(dto,projID)).thenThrow(new IllegalArgumentException(exception));
        BusinessErrorMessage errorMessage = new BusinessErrorMessage(exception,BusinessErrorMessage.INVALID_ENTRY);


        ResponseEntity<Object>expected = new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);

        ResponseEntity<Object>result = controller.newScrumMaster(dto,projID);

        assertEquals(expected,result);

    }

    @Test
    void getResourcesByProjectID_All() {

        int pid = 1;
        String type = "all";

        ResourceDTO mockDevDTO=mock(ResourceDTO.class);
        ResourceDTO mockScrumDTO= mock(ResourceDTO.class);
        ResourceDTO mockProjectManager = mock(ResourceDTO.class);
        List<ResourceDTO> mocklist = new ArrayList<>();
        mocklist.add(mockDevDTO);
        mocklist.add(mockProjectManager);
        mocklist.add(mockScrumDTO);

        when(appService.getAllResourcesByProjectID(pid)).thenReturn(mocklist);

        ResponseEntity<Object>expected = new ResponseEntity<>(mocklist,HttpStatus.OK);

        ResponseEntity<Object>result = controller.getResourcesByProjectID(pid,type);

        assertEquals(expected, result);

    }

    @Test
    void getResourcesByProjectID_ActiveResources() {

        int pid = 1;
        String type = "active";

        ResourceDTO mockDevDTO=mock(ResourceDTO.class);
        ResourceDTO mockScrumDTO= mock(ResourceDTO.class);
        ResourceDTO mockProjectManager = mock(ResourceDTO.class);
        List<ResourceDTO> mocklist = new ArrayList<>();
        mocklist.add(mockDevDTO);
        mocklist.add(mockProjectManager);
        mocklist.add(mockScrumDTO);

        when(appService.getAllActiveResourcesByProjectID(pid, LocalDate.now())).thenReturn(mocklist);

        ResponseEntity<Object>expected = new ResponseEntity<>(mocklist,HttpStatus.OK);

        ResponseEntity<Object>result = controller.getResourcesByProjectID(pid,type);

        assertEquals(expected, result);

    }

    @Test
    void getResourcesByProjectID_badQuery() {

        int pid = 1;
        String type = "acve";
        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid data");

        ResponseEntity result = controller.getResourcesByProjectID(1,type);

        assertEquals(expected, result);

    }
}