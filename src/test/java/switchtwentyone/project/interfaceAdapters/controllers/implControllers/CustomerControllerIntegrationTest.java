package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateCustomer;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerFactory;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.CustomerDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.CustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CustomerControllerIntegrationTest {

    @Autowired
    CustomerFactory customerFactory;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ApplicationServiceCreateCustomer applicationServiceCreateCustomer;

    @Autowired
    CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCustomer_Success() throws Exception {

        CustomerDTO customerInfo = new CustomerDTO();
        customerInfo.NIF = 257715347;
        customerInfo.name = "banana";

        String expected = objectMapper.writeValueAsString(customerInfo);

        String result = mockMvc
                .perform(MockMvcRequestBuilders.post("/customers/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customerInfo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);
    }

    @Test
    void getAllCustomers_integrationTest() throws Exception {

        Nameable name = NoNumberNoSymbolString.of("customer");
        NIF nif = NIF.of(257715347);
        CustomerID customerID = CustomerID.of(nif);
        Customer customer = customerFactory.createCustomer(customerID, name);
        customerRepository.saveCustomer(customer);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.name = name.getValue();
        customerDTO.NIF = Integer.parseInt(nif.toString());

        Nameable nameTwo = NoNumberNoSymbolString.of("customerTwo");
        NIF nifTwo = NIF.of(506943119);
        CustomerID customerIDTwo = CustomerID.of(nifTwo);
        Customer customerTwo = customerFactory.createCustomer(customerIDTwo, nameTwo);
        customerRepository.saveCustomer(customerTwo);
        CustomerDTO customerDTOTwo = new CustomerDTO();
        customerDTOTwo.name = nameTwo.getValue();
        customerDTOTwo.NIF = Integer.parseInt(nifTwo.toString());

        Object[] listOfCustomers = new CustomerDTO[2];
        listOfCustomers[0] = customerDTO;
        listOfCustomers[1] = customerDTOTwo;

        /*ResponseEntity<Object[]> result = createProjectController.getAllCustomers();*/

        String expected = objectMapper.writeValueAsString(listOfCustomers);

        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/customers/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(expected, result);
    }
}
