package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.*;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.*;
import switchtwentyone.project.dto.mapper.UserStoryMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RestController
public class UserStoryController {

    @Autowired
    private ApplicationServiceCreateUserStory appServcCreateUS;

    @Autowired
    ApplicationServiceChangeUserStoryPriority applicationServiceChangeUserStoryPriority;

    @Autowired
    ApplicationServiceDecomposeUserStory appService;

    @Autowired
    private ApplicationServiceGetAllUserStoryStatus appServiceGetAllUserStoryStatus;


    @Autowired
    private ApplicationServiceAddUSToSprintBacklog appServcAddUS;


    @CrossOrigin
    @PatchMapping(value = "/projects/{pid}/userStories/{id}")
    public ResponseEntity<Object> changePriorityOfUserStory(@PathVariable int pid, @PathVariable double id, @RequestBody PriorityDTO priority) {
        try {
            USWithPriorityDTO usWithPriorityDTO = applicationServiceChangeUserStoryPriority.changePriorityOfUserStory(pid, id, priority.newPriority);
            return new ResponseEntity<>(usWithPriorityDTO, HttpStatus.OK);
        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @CrossOrigin
    @PostMapping("/projects/{pid}/userStories")
    public ResponseEntity<Object> createUserStory(@PathVariable int pid, @RequestBody NewUserStoryInfoDTO info) {
        try {
            Text newStatement = Text.write(info.getStatement());
            Text newDetail = Text.write(info.getDetail());
            ProjectID projID = new ProjectID(pid);

            USDTO result = appServcCreateUS.createUserStory(newStatement, newDetail, projID);
            Link link = linkTo(methodOn(UserStoryController.class).getUserStoryById(result.projID, result.usID)).withSelfRel();
            result.add(link);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            BusinessErrorMessage message = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.NOT_FOUND);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/projects/{pid}/userStories/{id}")
    @ResponseBody
    public ResponseEntity<Object> getUserStoryById(@PathVariable final int pid, @PathVariable final Double id) {
        UserStoryID usID = UserStoryID.ofDouble(id);
        Optional<UserStory> opUS = appServcCreateUS.findUSByID(usID);
        BusinessErrorMessage msg = new BusinessErrorMessage("User Story not found", BusinessErrorMessage.NOT_FOUND);

        try {
            if (opUS.isPresent()) {
                UserStory us = opUS.get();
                USDTO usdto = UserStoryMapper.toDTO(us);
                return new ResponseEntity<>(usdto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            BusinessErrorMessage msg1 = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            return new ResponseEntity<>(msg1, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //TODO I don't know if using PUT is correct.
    @CrossOrigin
    @PutMapping(value = "/projects/{pid}/userStories/{id}/childs")
    public ResponseEntity<Object> decomposeUserStory(@RequestBody List<ChildUserStoryDTO> newUserStories,
                                                     @PathVariable double id, @PathVariable int pid) {


        try {
            List<USDTO> result = appService.decomposeUserStory(id, newUserStories,pid);

            for (USDTO usdto : result) {
                Link link = linkTo(methodOn(UserStoryController.class).getUserStoryById(usdto.projID, usdto.usID)).withSelfRel();
                usdto.add(link);

            }

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch(EntityNotFoundException e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.NOT_FOUND);
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }

        catch(Exception e){
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.INVALID_ENTRY);
            return  new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/projects/{pid}/sprintBacklogItems")
    public ResponseEntity<Object> addUSToSprintBacklog(@PathVariable int pid, @RequestBody AddUSToSprintInfoDTO info) {
        try {
            appServcAddUS.addUSToSprintBacklog(pid, info);
            return new ResponseEntity<>("US successfully added.", HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            BusinessErrorMessage message = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.NOT_FOUND);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/projects/{pid}/sprintBacklogItems")
    public ResponseEntity<Object> getAllSprintBacklogItemsByProject(@PathVariable int pid) {
        //TODO - implement this controller on next Sprint
        List<ProjectDTO> result = null;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/projects/{id}/userStories")
    public ResponseEntity<Object> viewUSStatusFromProject(@PathVariable int id) {
        ResponseEntity<Object> response;

        try {
            List<USInfoAndStatusDTO> result = appServiceGetAllUserStoryStatus.getAllUserStoryStatus(id);

            if (result.isEmpty()) {
                response = new ResponseEntity<>("No User Stories found in this project", HttpStatus.OK);

            } else {


                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(), BusinessErrorMessage.UNPROCESSABLE_ENTITY);
            response = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);

        }
        return response;
    }

}

