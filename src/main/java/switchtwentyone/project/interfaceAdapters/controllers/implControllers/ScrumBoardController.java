package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceUpdateUsStatus;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceViewScrumBoard;
import switchtwentyone.project.dto.ChangeUsCategoryDTO;
import switchtwentyone.project.dto.ScrumBoardDTO;
import switchtwentyone.project.dto.USDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RestController
@CrossOrigin

public class ScrumBoardController {

    @Autowired
    ApplicationServiceUpdateUsStatus applicationServiceUpdateUsStatus;

    @Autowired
    ApplicationServiceViewScrumBoard applicationServiceViewScrumBoard;

    @CrossOrigin
    @PatchMapping("/project/{projID}/scrumBoard/userStory/{usID}")
    public ResponseEntity<Object> updateUsStatus(@PathVariable int projID, @PathVariable double usID, @RequestBody ChangeUsCategoryDTO categoryDTO){
        try{
        if(applicationServiceUpdateUsStatus.updateUsStatus(projID,usID,categoryDTO)){
            return new ResponseEntity<>("User Story status updated!", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("There is no running sprint.",HttpStatus.NOT_FOUND);
        }
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @CrossOrigin
    @GetMapping("/project/{projID}/scrumBoard/userStory/{usID}")
    public ResponseEntity<Object> getUSStatus(@PathVariable int projID, @PathVariable double usID){
        //TODO later
        List<USDTO> result = null;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/projects/{pid}/scrumBoard")
    public ResponseEntity<Object> viewScrumBoard(@PathVariable int pid) {
        ResponseEntity<Object> response;

        try {
            List<ScrumBoardDTO> result = applicationServiceViewScrumBoard.viewScrumBoard(pid);
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            BusinessErrorMessage message = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.NOT_FOUND);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            response = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }
}
