package switchtwentyone.project.interfaceAdapters.controllers.implControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateProjectTypology;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetAllProjectTypologies;
import switchtwentyone.project.dto.ProjectTypologyDTO;

import java.util.List;

@Controller
@RestController
public class ProjectTypologyController {

    @Autowired
    ApplicationServiceCreateProjectTypology applicationServiceCreateProjectTypology;

    @Autowired
    ApplicationServiceGetAllProjectTypologies appServiceGetProjectTypologies;

    @PostMapping("/projectTypologies")
    public ResponseEntity<Object> createProjectTypology(@RequestBody ProjectTypologyDTO info) {
        try {
            ProjectTypologyDTO projectTypologyCreated =
                    applicationServiceCreateProjectTypology.createAndSaveProjectTypology(info.name, info.description);
            return new ResponseEntity<>(projectTypologyCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/projectTypologies/")
    @ResponseBody
    public ResponseEntity<Object[]> getAllProjectTypologies() {

        List<ProjectTypologyDTO> listOfProjectTypologies = appServiceGetProjectTypologies.getProjectAllTypologies();
        ProjectTypologyDTO[] arrayListOfProjectTypologies = new ProjectTypologyDTO[listOfProjectTypologies.size()];
        listOfProjectTypologies.toArray(arrayListOfProjectTypologies);
        return new ResponseEntity<>(arrayListOfProjectTypologies, HttpStatus.OK);
    }
}
