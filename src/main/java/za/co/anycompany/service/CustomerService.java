package za.co.anycompany.service;

import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    public List<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

    public Customer getCustomerByIdTest(int id) {
        return customerRepository.findById(id).get();
    }

    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    public void saveOrUpdate(Customer customer) {
        customerRepository.save(customer);
    }

    public void update(Customer customer, int id){
        customerRepository.save(customer);
    }

    public Customer getCustomerById(int id){
        return customerRepository.load(id);
    }
}
