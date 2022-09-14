package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.*;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.account.Password;
import switchtwentyone.project.domain.aggregates.account.Photo;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AccountController {

    @Autowired
    ApplicationServiceListAccountsAndStatus appServiceListAllAccounts;
    @Autowired
    private ApplicationServiceCreateAccount appServiceCreateAccount;
    @Autowired
    private ApplicationServiceGetAccount serviceLong;
    @Autowired
    private ApplicationServiceGetEditableAccount serviceShort;
    @Autowired
    private ApplicationServiceUpdateAccount applicationServiceUpdateAccount;

    @CrossOrigin
    @PostMapping("/accounts")
    public ResponseEntity<Object> createAccount(@RequestBody NewAccountInfoDTO info) {
        BusinessErrorMessage msg = new BusinessErrorMessage("Account data are not valid", BusinessErrorMessage.NOT_FOUND);
        try {
            Email email = Email.of(info.getEmail());
            NoNumberNoSymbolString name = NoNumberNoSymbolString.of(info.getName());
            NoNumberNoSymbolString function = NoNumberNoSymbolString.of(info.getFunction());
            Photo photo = Photo.of(info.getPhoto());
            Password password = Password.of(info.getPassword(), 1);

            Optional<AccountDTO> result = appServiceCreateAccount.createNewAccount(email, name, function, photo, password);


            ResponseEntity<Object> response = new ResponseEntity<>(result, HttpStatus.CREATED);
            return response;

        } catch (Exception e) {
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/accounts/{email}")
    @ResponseBody
    public ResponseEntity<Object> findAccountInfo(@PathVariable String email, @RequestParam String type) {
        ResponseEntity<Object> response;
        Optional<AccountShortDTO> resultShort = Optional.empty();
        Optional<AccountDTO> resultLong = Optional.empty();
        boolean badQuery = false;

        try {
            switch (type) {
                case "short":
                    resultShort = serviceShort.getAccountShortInfo(email);
                    break;
                case "long":
                    resultLong = serviceLong.getAccountInfo(email);
                    break;
                default:
                    badQuery = true;
            }
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            if (resultShort.isPresent()) {
                response = new ResponseEntity<>(resultShort.get(), HttpStatus.OK);
            }
            if (resultLong.isPresent()) {
                response = new ResponseEntity<>(resultLong.get(), HttpStatus.OK);
            }
            if (badQuery) {
                response = new ResponseEntity<>("code #001", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }


    @CrossOrigin
    @PutMapping(value = "/accounts/{email}")
    public ResponseEntity<Object> updateAccount(@PathVariable String email, @RequestBody AccountShortDomainDTO newDetailsDTO, @RequestParam String type) {
        ResponseEntity<Object> response;
        try {
            if (!email.equals(newDetailsDTO.email)) throw new IllegalAccessException("Security error");
            Optional<AccountShortDTO> resultOpt = applicationServiceUpdateAccount.updateAccount(newDetailsDTO);
            if (resultOpt.isPresent()) {
                AccountShortDTO result = resultOpt.get();
                Link selfLink = linkTo(methodOn(AccountController.class).findAccountInfo(result.email, type)).withSelfRel();
                result.add(selfLink);
                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "/accountsInfo")
    public ResponseEntity<Object> getListOfAllAccountsAndStatus() {

        List<AccountAndStatusDTO> result = appServiceListAllAccounts.getAllAccountsAndStatus();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/accounts")
    public ResponseEntity<Object> getAllAccounts() {
        //TODO - implement this controller on next Sprint
        List<AccountShortDTO> result = null;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}





