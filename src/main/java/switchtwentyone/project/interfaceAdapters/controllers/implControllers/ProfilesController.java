package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateProfile;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetProfileList;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceUpdateProfile;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.NewProfileInfoDTO;
import switchtwentyone.project.dto.ProfileDTO;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
public class ProfilesController {

    @Autowired
    private ApplicationServiceCreateProfile appServCreateProfile;
    @Autowired
    private ApplicationServiceUpdateProfile applicationServiceUpdateProfile;
    @Autowired
    private ApplicationServiceGetProfileList applicationServiceGetProfileList;

    @PostMapping("/profiles")
    public ResponseEntity<Object> createProfile(@RequestBody NewProfileInfoDTO newProfileInfoDTO) {

        ProfileType profileType = ProfileType.of(newProfileInfoDTO.getProfileType());
        Text description = Text.write(newProfileInfoDTO.getDescription());

        Optional<ProfileDTO> profileDTO = appServCreateProfile.createAndSaveProfile(profileType, description);
        //see if this is the best solution
        if (profileDTO.isPresent()) {
            ProfileDTO result = profileDTO.get();
            Link link = linkTo(methodOn(ProfilesController.class).findProfileById(result.profileID)).withSelfRel();
            result.add(link);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Profile Type not valid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/profiles/{id}")
    @ResponseBody
    public ResponseEntity<Object> findProfileById(@PathVariable String id){
        ProfileID profId = ProfileID.ofProfileType(id);
        Optional<Profile> optionalProfile = appServCreateProfile.findProfileById(profId);

        if(optionalProfile.isPresent()){
            Profile profile = optionalProfile.get();
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }else
            return new ResponseEntity<>("Profile Not Found", HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/accounts/{id}/profiles")
    public ResponseEntity<Object> updateProfile(@RequestBody ProfileDTO profileDTO, @PathVariable final String id){
        try {
            Email email = Email.of(id);

            ProfileID profileID = ProfileID.ofProfileType(profileDTO.profileID);

            Optional<AccountDTO> response = applicationServiceUpdateProfile.updateProfile(email,profileID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            BusinessErrorMessage msg = new BusinessErrorMessage(e.getMessage(),BusinessErrorMessage.NOT_FOUND);
            return new ResponseEntity<>(msg,HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/profiles")
    @ResponseBody
    public ResponseEntity<Object> getProfilesDTOS(){

        List<ProfileDTO> result = applicationServiceGetProfileList.getProfilesDTOS();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //For external service consumption
    @GetMapping("/local/profiles")
    @ResponseBody
    public ResponseEntity<Object> getLocalProfilesDTOS(){

        List<ProfileDTO> result = applicationServiceGetProfileList.getLocalProfilesDTOS();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
