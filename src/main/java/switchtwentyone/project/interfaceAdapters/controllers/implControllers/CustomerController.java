package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateCustomer;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetAllCustomers;
import switchtwentyone.project.dto.CustomerDTO;

import java.util.List;

@Controller
@RestController
public class CustomerController {

    @Autowired
    ApplicationServiceCreateCustomer applicationServiceCreateCustomer;

    @Autowired
    ApplicationServiceGetAllCustomers appServiceGetCustomers;

    @PostMapping("/customers/")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDTO info) {
        try {
            CustomerDTO customerCreated =
                    applicationServiceCreateCustomer.createAndSaveCustomer(info.NIF, info.name);
            return new ResponseEntity<>(customerCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/customers/")
    @ResponseBody
    public ResponseEntity<Object[]> getAllCustomers() {

        List<CustomerDTO> listOfCustomers = appServiceGetCustomers.getAllCustomers();
        CustomerDTO[] arrayListOfCustomers = new CustomerDTO[listOfCustomers.size()];
        listOfCustomers.toArray(arrayListOfCustomers);
        return new ResponseEntity<>(arrayListOfCustomers, HttpStatus.OK);
    }

}
