package za.co.anycompany.service;

import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

import java.util.List;

@Service
public class CustomerService {
    // 1.
    private CustomerRepository customerRepository = new CustomerRepository();

    // 2.
    public List<Customer> getAllCustomers() {
        List<Customer> customers =  customerRepository.getAll();

        if (customers.size() == 0)
            return null;

        else
            return customers;
    }

    // 3.
    public Customer getCustomerByIdTest(int id) {
        return customerRepository.findById(id).get();
    }

    // 4.
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    // 5.
    public void saveOrUpdate(Customer customer) {
        customerRepository.save(customer);
    }

    // 6.
    public void update(Customer customer, int id){
        customerRepository.save(customer);
    }

    // 7.
    public Customer getCustomerById(int id){
        return customerRepository.load(id);
    }
}
