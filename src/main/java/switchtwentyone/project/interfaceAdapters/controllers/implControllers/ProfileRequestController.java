package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateProfileRequest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;
import switchtwentyone.project.dto.NewProfileRequestInfoDTO;
import switchtwentyone.project.dto.ProfileRequestDTO;

import java.util.Optional;

@RestController
public class ProfileRequestController {

    @Autowired
    private ApplicationServiceCreateProfileRequest appSrvCreateProfileRequest;

    @PostMapping("/profileRequests")
    public ResponseEntity<Object> createProfileRequest(@RequestBody String email, String profType) {
        BusinessErrorMessage msg = new BusinessErrorMessage("Profile Request are not valid", BusinessErrorMessage.NOT_FOUND);
        try {
            NewProfileRequestInfoDTO newProfileRequestInfoDTO = new NewProfileRequestInfoDTO(email, profType);
            ProfileRequestDTO result = appSrvCreateProfileRequest.createProfileRequest(newProfileRequestInfoDTO);

            ResponseEntity<Object> response = new ResponseEntity<>(result, HttpStatus.CREATED);

            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/profileRequests/{id}")
    @ResponseBody
    public ResponseEntity<Object> getProfileRequestById(@PathVariable(value = "id") final int id) {
        ProfileRequestID profReqId = ProfileRequestID.createProfileRequestID();
        Optional<ProfileRequest> opProfileRequest = appSrvCreateProfileRequest.findProfileRequestByID(profReqId);
        BusinessErrorMessage msg = new BusinessErrorMessage("Profile Request not found", BusinessErrorMessage.NOT_FOUND);

        if (opProfileRequest.isPresent()) {
            ProfileRequest profileRequest = opProfileRequest.get();
            return new ResponseEntity<>(profileRequest, HttpStatus.OK);
        } else
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}

