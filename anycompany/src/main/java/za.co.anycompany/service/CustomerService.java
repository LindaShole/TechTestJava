package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.controller.ApplicationException;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.domain.Customer;
import za.co.anycompany.dto.CustomerDto;
import za.co.anycompany.translator.Translator;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDto createCustomer(CustomerDto dto)
    {
        Customer customer = customerRepository.findByIdNo(dto.getIdNo());
        customer = saveCustomer(dto, customer);

        if (customer == null){
            throw new ApplicationException(ApplicationException.Type.unexpected, ApplicationException.CODE_CUSTOMER_ALREADY_EXIST, "Error while creating customer");
        }
        dto.setId(customer.getId());
        return dto;
    }

    private Customer saveCustomer(CustomerDto dto, Customer customer) {
        if (customer == null){
            try {
                customer = customerRepository.save(Translator.toCustomer(dto));
            } catch (Exception e){
                e.printStackTrace();
                throw new ApplicationException(ApplicationException.Type.unexpected, ApplicationException.CODE_UNSPECIFIED, "Error occured while saving customer");
            }
        } else {
            throw new ApplicationException(ApplicationException.Type.entity_already_exist, ApplicationException.CODE_CUSTOMER_ALREADY_EXIST, "Customer already exist");
        }
        return customer;
    }

    public Customer findCustomerById(Long id) {
        Customer customer = customerRepository.findOne(id);
        if (customer == null){
            throw new ApplicationException(ApplicationException.Type.entity_not_found, ApplicationException.CODE_CUSTOMER_ALREADY_EXIST, "Customer does not exist");
        }
        return customer;
    }
}
