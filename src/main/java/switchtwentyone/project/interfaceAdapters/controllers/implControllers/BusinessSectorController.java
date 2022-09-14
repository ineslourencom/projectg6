package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateBusinessSector;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetAllBusinessSectors;
import switchtwentyone.project.dto.BusinessSectorDTO;

import java.util.List;

@Controller
@RestController
public class BusinessSectorController {

    @Autowired
    ApplicationServiceCreateBusinessSector applicationServiceCreateBusinessSector;

    @Autowired
    ApplicationServiceGetAllBusinessSectors appServiceGetBussinessSectors;

    @PostMapping("/businessSectors/")
    public ResponseEntity<Object> createBusinessSector(@RequestBody BusinessSectorDTO info) {
        try {
            BusinessSectorDTO businessSectorCreated =
                    applicationServiceCreateBusinessSector.createAndSaveBusinessSector(info.code, info.description);
            return new ResponseEntity<>(businessSectorCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/businessSectors/")
    @ResponseBody
    public ResponseEntity<Object[]> getAllBusinessSectors() {

        List<BusinessSectorDTO> listOfBusinessSectors = appServiceGetBussinessSectors.getAllBusinessSectors();
        BusinessSectorDTO[] arrayListOfBusinessSectors = new BusinessSectorDTO[listOfBusinessSectors.size()];
        listOfBusinessSectors.toArray(arrayListOfBusinessSectors);
        return new ResponseEntity<>(arrayListOfBusinessSectors, HttpStatus.OK);
    }
}
