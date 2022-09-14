package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateResource;
import switchtwentyone.project.dto.NewProductOwnerDTO;
import switchtwentyone.project.dto.NewResourceDTO;
import switchtwentyone.project.dto.NewScrumMasterDTO;
import switchtwentyone.project.dto.ResourceDTO;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin
public class ResourceController {
    @Autowired
    ApplicationServiceCreateResource appService;

    /**
     * Creates a new resource
     *
     * @param dto contains necessary information to create a resource
     * @return Response entity containing ResourceDTO if successful
     */
    @PostMapping("/projects/{pid}/resources")
    public ResponseEntity<Object> createResource(@RequestBody NewResourceDTO dto, @PathVariable int pid) {
        try {
            ResourceDTO
                    response = appService.createResource(dto, pid);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            BusinessErrorMessage message = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.NOT_FOUND);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            BusinessErrorMessage message = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.INVALID_ENTRY);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

    }

   @CrossOrigin
   @GetMapping("/projects/{pid}/resources")
    public ResponseEntity<Object> getResourcesByProjectID(@PathVariable int pid, @RequestParam String type) {

        ResponseEntity<Object> response= new ResponseEntity<>(HttpStatus.NO_CONTENT);

       boolean badQuery = false;
        switch (type) {

                case "active":
                    List<ResourceDTO> resultActive = appService.getAllActiveResourcesByProjectID(pid, LocalDate.now());
                    response = new ResponseEntity<>(resultActive, HttpStatus.OK);
                break;
                case "all":
                    List<ResourceDTO> resultAll = appService.getAllResourcesByProjectID(pid);
                    response = new ResponseEntity<>(resultAll, HttpStatus.OK);
                break;
            default:
                badQuery = true;
            }
            if (badQuery){
                response = new ResponseEntity<>("invalid data", HttpStatus.BAD_REQUEST);
            }

        return response;
    }

    /**
     * Updates the product owner of a project.
     *
     * @param dto contains necessary information to the creation of a resource as Product Owner
     * @param pid the ID of the project
     * @return Response entity containing ResourceDTO if successful
     */
    @PutMapping("/projects/{pid}/resources/productOwner")
    public ResponseEntity<Object> defineProductOwner(@RequestBody NewProductOwnerDTO dto, @PathVariable int pid) {
        try {
            ResourceDTO
                    response = appService.defineProductOwner(dto, pid);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (EntityNotFoundException e) {
            BusinessErrorMessage message = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.NOT_FOUND);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            BusinessErrorMessage message = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.INVALID_ENTRY);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/projects/{pid}/resources/scrumMaster")
    public ResponseEntity<Object> newScrumMaster(@RequestBody NewScrumMasterDTO dto, @PathVariable int pid) {
        try {
            ResourceDTO
                    response = appService.updateScrumMaster(dto, pid);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            BusinessErrorMessage message = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.NOT_FOUND);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            BusinessErrorMessage message = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.INVALID_ENTRY);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

    }

}
