package za.co.anycompany.mappers;

import za.co.anycompany.dtos.CustomerDTO;
import za.co.anycompany.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public CustomerDTO fromEntityToDTO(Customer customer)
    {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setCountry(customer.getCountry());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());

        return customerDTO;
    }

    public List<CustomerDTO> fromEntitiesToDTOs(List<Customer> customers)
    {
        List<CustomerDTO> customerDTOs = new ArrayList<>();

        for(Customer customer : customers){
            customerDTOs.add(fromEntityToDTO(customer));
        }

        return customerDTOs;
    }
}
