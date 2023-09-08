package za.co.anycompany.service;

import za.co.anycompany.dto.CustomerDTO;
import za.co.anycompany.dto.NewCustomerRequestDTO;
import za.co.anycompany.persistence.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    UUID createCustomer(NewCustomerRequestDTO request);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomer(UUID id);

    Optional<CustomerEntity> getCustomerEntity(UUID id);
}
