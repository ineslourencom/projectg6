package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.dto.CustomerDTO;
import switchtwentyone.project.dto.mapper.CustomerMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.CustomerRepository;

import java.util.List;

@Service
public class ApplicationServiceGetAllCustomers {

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {
        return CustomerMapper.toDTO(customerRepository.getAllCustomers());
    }

}
