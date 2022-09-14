package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceViewProductBacklog;
import switchtwentyone.project.dto.USShortDTO;

import java.util.List;

@RestController
public class ProductBacklogController {

    @Autowired
    ApplicationServiceViewProductBacklog service;

    @CrossOrigin
    @GetMapping("/projects/{id}/productBacklog")
    public ResponseEntity<Object> getProductBacklog(@PathVariable int id, @RequestParam String email) {
        ResponseEntity<Object> response;
        try {
            List<USShortDTO> result = service.getProductBacklog(id, email);
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
