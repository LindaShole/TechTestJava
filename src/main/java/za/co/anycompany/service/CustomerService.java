package za.co.anycompany.service;

import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.getAllCustomers();
    }

    public Customer getCustomer(int customerId) {
        return customerRepository.load(customerId);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
