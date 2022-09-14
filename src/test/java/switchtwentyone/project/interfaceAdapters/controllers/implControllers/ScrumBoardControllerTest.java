package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceUpdateUsStatus;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceViewScrumBoard;
import switchtwentyone.project.dto.ChangeUsCategoryDTO;
import switchtwentyone.project.dto.ScrumBoardDTO;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ScrumBoardControllerTest {

    @Mock
    ApplicationServiceUpdateUsStatus applicationServiceUpdateUsStatus;

    @Mock
    private ApplicationServiceViewScrumBoard applicationServiceViewScrumBoard;

    @InjectMocks
    ScrumBoardController scrumBoardController;


    @Test
    void updateUsStatus() {

        int projID=1;
        double usID=1.2;

        ChangeUsCategoryDTO categoryDTO=mock(ChangeUsCategoryDTO.class);
        when(applicationServiceUpdateUsStatus.updateUsStatus(projID,usID,categoryDTO)).thenReturn(true);

        ResponseEntity result= scrumBoardController.updateUsStatus(projID,usID,categoryDTO);

        ResponseEntity expected=  new ResponseEntity<>("User Story status updated!", HttpStatus.OK);

        assertEquals(result,expected);
    }

    @Test
    void doNotUpdateUsStatus() {

        int projID=1;
        double usID=1.2;

        ChangeUsCategoryDTO categoryDTO=mock(ChangeUsCategoryDTO.class);
        when(applicationServiceUpdateUsStatus.updateUsStatus(projID,usID,categoryDTO)).thenReturn(false);

        ResponseEntity result= scrumBoardController.updateUsStatus(projID,usID,categoryDTO);

        ResponseEntity expected=  new ResponseEntity<>("There is no running sprint.",HttpStatus.NOT_FOUND);

        assertEquals(result,expected);
    }


    @Test
    void viewScrumBoard_withUSExistent() {

        ScrumBoardDTO usInfoStatus1 = mock(ScrumBoardDTO.class);
        ScrumBoardDTO usInfoStatus2 = mock(ScrumBoardDTO.class);
        ScrumBoardDTO usInfoStatus3 = mock(ScrumBoardDTO.class);

        List<ScrumBoardDTO> usInfoListDTO = new ArrayList<>();
        usInfoListDTO.add(usInfoStatus1);
        usInfoListDTO.add(usInfoStatus2);
        usInfoListDTO.add(usInfoStatus3);


        ResponseEntity expected = new ResponseEntity<>(usInfoListDTO, HttpStatus.OK);
        when(applicationServiceViewScrumBoard.viewScrumBoard(1)).thenReturn(usInfoListDTO);

        ResponseEntity result =  scrumBoardController.viewScrumBoard(1);


        assertEquals(expected, result);
    }

    @Test
    void viewScrumBoard_withNoUS() {
        List<ScrumBoardDTO> usInfoListDTO = new ArrayList<>();

        ResponseEntity expected = new ResponseEntity<>(usInfoListDTO, HttpStatus.OK);
        when(applicationServiceViewScrumBoard.viewScrumBoard(1)).thenReturn(usInfoListDTO);

        ResponseEntity result =  scrumBoardController.viewScrumBoard(1);


        assertEquals(expected, result);
    }

    @Test
    void viewScrumBoard_UnprocessableEntity(){
        String message = "No Project was found";
        when(applicationServiceViewScrumBoard.viewScrumBoard(2)).thenThrow(new IllegalArgumentException(message));
        BusinessErrorMessage msg = new BusinessErrorMessage(message, BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        ResponseEntity<Object> result = scrumBoardController.viewScrumBoard(2);

        assertEquals(expected.getStatusCode(), result.getStatusCode());

    }

    @Test
    void viewScrumBoard_NotFound() {
        String message = "No Project was not found";
        when(applicationServiceViewScrumBoard.viewScrumBoard(2)).thenThrow(new EntityNotFoundException(message));
        BusinessErrorMessage msg = new BusinessErrorMessage(message, BusinessErrorMessage.NOT_FOUND);
        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        ResponseEntity<Object> result = scrumBoardController.viewScrumBoard(2);

        assertEquals(expected.getStatusCode(), result.getStatusCode());
    }
}