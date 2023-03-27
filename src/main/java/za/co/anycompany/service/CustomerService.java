package za.co.anycompany.service;

import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    // 1.
    public List<Customer> getAllCustomers() {
        List<Customer> customers =  customerRepository.getAll();

        if (customers.size() == 0)
            return null;

        else
            return customers;
    }

    // 2.
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    // 3.
    public void saveOrUpdate(Customer customer) {
        customerRepository.save(customer);
    }

    // 4.
    public void update(Customer customer, int id){
        customerRepository.save(customer);
    }

    // 5.
    public Customer getCustomerById(int id){
        Customer customer = customerRepository.load(id);

        if(null==customer.getId())
            return null;

        else {
            return customer;
        }
    }
}
