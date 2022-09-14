package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateSprint;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetRunningSprint;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetUserStoriesNotDoneFromRunningSprint;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.dto.*;
import switchtwentyone.project.dto.mapper.SprintMapper;
import switchtwentyone.project.interfaceAdapters.controllers.iControllers.ICreateSprintController;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Controller
@RestController
public class SprintController implements ICreateSprintController {

    @Autowired
    private ApplicationServiceCreateSprint appServcCreateSprint;
    @Autowired
    ApplicationServiceGetRunningSprint applicationServiceGetRunningSprint;
    @Autowired
    ApplicationServiceGetUserStoriesNotDoneFromRunningSprint applicationServiceGetUserStoriesNotDoneFromRunningSprint;


    @PostMapping("/projects/{pid}/sprints")
    public ResponseEntity<Object> createSprint(@PathVariable int pid, @RequestBody NewSprintInfoDTO info) {
        try {
            ProjectID projID = new ProjectID(pid);
            LocalDate startDate = info.getStartDate();

            SprintDTO result = appServcCreateSprint.createSprint(startDate, projID);
            Link link = linkTo(methodOn(SprintController.class).getSprintById(result.sprintID)).withSelfRel();
            result.add(link);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/projects/{pid}/sprints/{id}")
    @ResponseBody
    public ResponseEntity<Object> getSprintById(@PathVariable final Double id) {
        SprintID sprintID = SprintID.ofDouble(id);
        Optional<Sprint> opSprint = appServcCreateSprint.findSprintByID(sprintID);
        BusinessErrorMessage msg = new BusinessErrorMessage("Sprint not found", BusinessErrorMessage.NOT_FOUND);

        if (opSprint.isPresent()) {
            Sprint sprint = opSprint.get();
            SprintDTO sprintDTO = SprintMapper.toDTO(sprint);
            return new ResponseEntity<>(sprintDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping("/projects/{pid}/sprints")
    public ResponseEntity<Object> getAllSprintsByProjectID(@PathVariable int pid) {
        List<SprintTableDTO> result = appServcCreateSprint.getAllSprintsByProjectID(pid);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("/projects/{pid}/runningSprint")
    public ResponseEntity<Object> gerRunningSprintByProjectID(@PathVariable int pid) {
        SprintInfoForTaskDTO sprintInfoForTaskDTO = applicationServiceGetRunningSprint.getRunningSprint(pid);
        if (sprintInfoForTaskDTO != null) {
            return new ResponseEntity<>(sprintInfoForTaskDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(sprintInfoForTaskDTO, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PatchMapping("/projects/{pid}/sprints/{id}")
    public ResponseEntity<Object> setNewStartDate(@PathVariable int pid, @PathVariable int id, @RequestBody NewSprintInfoDTO info) {
        ProjectID projID = new ProjectID(pid);
        LocalDate startDate = info.getStartDate();

        Optional<SprintDTO> opt = appServcCreateSprint.setNewStartDate(startDate, projID, id);

        if (opt.isPresent()) {
            return new ResponseEntity<>(opt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(opt, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @GetMapping("/projects/{id}/runningSprint/userStoriesDone")
    public ResponseEntity<Object> getUserStoriesDoneInRunningSprint(@PathVariable int id) {
        try {
            List<USMinimalInfoDTO> listDTOs = applicationServiceGetUserStoriesNotDoneFromRunningSprint.getUSNotDoneFromRunningSprint(id);
            return new ResponseEntity<>(listDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}





