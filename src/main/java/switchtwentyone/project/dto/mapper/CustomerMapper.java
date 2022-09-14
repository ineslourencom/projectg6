package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.dto.CustomerDTO;
import switchtwentyone.project.dto.ProjectTypologyDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public static List<CustomerDTO> toDTO(List<Customer> listOfCustomer) {
        List<CustomerDTO> listOfDTOs = new ArrayList<>();
        for (int i = 0; i < listOfCustomer.size(); i++) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.NIF = listOfCustomer.get(i).getIDAsInt();
            customerDTO.name = listOfCustomer.get(i).getNameAsString();
            listOfDTOs.add(customerDTO);
        }
        return listOfDTOs;
    }

    public static CustomerDTO toSingleDTO(Customer customer) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.NIF = customer.getIDAsInt();
            customerDTO.name = customer.getNameAsString();
        return customerDTO;
    }
}
