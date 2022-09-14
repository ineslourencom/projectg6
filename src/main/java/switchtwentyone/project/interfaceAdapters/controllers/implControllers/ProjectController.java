package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.*;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.dto.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@Controller
@RestController
public class ProjectController {

    @Autowired
    ApplicationServiceCreateProject appServiceCreateProj;

    @Autowired
    ApplicationServiceProject appService;

    @Autowired
    ApplicationServiceListProjects appServiceListAllProjects;

    @PostMapping("/projects")
    public ResponseEntity<Object> createProject(@RequestBody ProjectInfoNecessaryToCreateDTO info) {
        try {
            ProjectInfoReturnedWhenCreatedDTO projectInfo = appServiceCreateProj.createProject(info);

            return new ResponseEntity<>(projectInfo, HttpStatus.CREATED);

        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<Object> getProjectDetails(@PathVariable int id) {
        ResponseEntity<Object> response;

        try {
            Optional<ProjectInfoDTO> result = appService.getProjectDetails(id);
            if (result.isPresent()) {
                response = new ResponseEntity<>(result.get(), HttpStatus.OK);

            } else {
                response = new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
        return response;
    }

    @CrossOrigin
    @PatchMapping(value = "/projects/{id}")
    public ResponseEntity<Object> editProject(@PathVariable int id, @RequestBody ProjectInfoDTO dto) {
        ResponseEntity<Object> response;
        try {
            Optional<ProjectInfoDTO> result = appService.editProject(id, dto);
            if (result.isPresent()) {
                response = new ResponseEntity<>(result.get(), HttpStatus.OK);

            } else {
                response = new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
        return response;
    }


    @GetMapping(value = "/projects", params = {"uid", "role"})
    public ResponseEntity<Object> getListOfProjectsByEmailAndRole(@RequestParam String uid, @RequestParam String role) {

        ResponseEntity response;
        try {
            List<ProjectDTO> result = appServiceListAllProjects.getListOfAllocatedProjects(uid, role);
            if(result.isEmpty()){
                response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

            }else{
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }

        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            response = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);

        }
        return response;
    }

    @GetMapping("/projects/user/{email}")
    public ResponseEntity<Object> getListOfProjectsByUser(@PathVariable String email) {
        ResponseEntity response;
        try {
            Email userEmail = Email.of(email);
            List<ProjectDTO> result = appServiceListAllProjects.getListOfAllocatedProjects(userEmail);
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Exception e){
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping(value = "/projects", params = {"uid"})
    public ResponseEntity<Object> getListOfAllProjects(@RequestParam String uid) {
        ResponseEntity response;
        try {
            List<ProjectDTO> result = appServiceListAllProjects.getAllProjects(uid);
            if(result.isEmpty()){
                response = new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

            }else{
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            response = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    @GetMapping(value = "/local/projects")
    public ResponseEntity<Object> getListOfAllProjects() {
        ResponseEntity response;
        try {
            List<ProjectDTO> result = appServiceListAllProjects.getAllProjects();

            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            response = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }
}
