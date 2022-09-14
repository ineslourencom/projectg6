package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceViewProductBacklog;
import switchtwentyone.project.dto.USShortDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductBacklogControllerTest {

    @Mock
    private ApplicationServiceViewProductBacklog service;

    @InjectMocks
    private ProductBacklogController controller;


    @Test
    void getProductBacklog_Test_Success() {
        String email = "carl.minion@isep.ipp.pt";
        List<USShortDTO> usList = new ArrayList<>();
        USShortDTO dtoOne = mock(USShortDTO.class);
        USShortDTO dtoTwo = mock(USShortDTO.class);
        USShortDTO dtoThree = mock(USShortDTO.class);
        usList.add(dtoOne);
        usList.add(dtoTwo);
        usList.add(dtoThree);
        when(service.getProductBacklog(1, email)).thenReturn(usList);
        ResponseEntity expected = ResponseEntity.status(HttpStatus.OK).body(usList);

        ResponseEntity result = controller.getProductBacklog(1, "carl.minion@isep.ipp.pt");

        assertEquals(expected, result);
    }

    @Test
    void getProductBacklog_Test_Fail_Exception() {
        String email = "carl*minion@isep.ipp.p1t";
        IllegalArgumentException exception = new IllegalArgumentException("Email format is invalid");
        when(service.getProductBacklog(1, email)).thenThrow(exception);
        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email format is invalid");

        ResponseEntity result = controller.getProductBacklog(1, "carl*minion@isep.ipp.p1t");

        assertEquals(expected, result);
    }

}