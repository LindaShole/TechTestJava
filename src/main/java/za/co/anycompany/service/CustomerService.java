package za.co.anycompany.service;

import za.co.anycompany.dto.CustomerDTO;
import za.co.anycompany.dto.NewCustomerRequestDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    UUID createCustomer(NewCustomerRequestDTO request);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomer(UUID id);
}
