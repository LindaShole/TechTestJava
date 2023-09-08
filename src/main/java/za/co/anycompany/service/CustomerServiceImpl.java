package za.co.anycompany.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import za.co.anycompany.dto.CustomerDTO;
import za.co.anycompany.dto.NewCustomerRequestDTO;
import za.co.anycompany.mapper.CustomerMapper;
import za.co.anycompany.persistence.entity.CustomerEntity;
import za.co.anycompany.persistence.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public UUID createCustomer(NewCustomerRequestDTO request) {
        try {
            return repository.save(mapper.toEntity(request)).getId();
        } catch (Exception e) {
            log.error("Unexpected error while creating new customer {}", kv("newCustomerRequest", request));
            throw e;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        try {
            return mapper.toDTOs(repository.findAll());
        } catch (Exception e) {
            log.error("Unexpected error while retrieving customer details.", e);
            throw e;
        }
    }

    @Override
    public CustomerDTO getCustomer(UUID id) {
        try {
            return getCustomerEntity(id)
                    .map(mapper::toDTO)
                    .orElse(null);
        } catch (Exception e) {
            log.error("Unexpected error while retrieving details for {}", kv("customerId", id), e);
            throw e;
        }
    }

    @Override
    public Optional<CustomerEntity> getCustomerEntity(UUID id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            log.error("Unexpected error while retrieving details for {}", kv("customerId", id), e);
            throw e;
        }
    }
}
