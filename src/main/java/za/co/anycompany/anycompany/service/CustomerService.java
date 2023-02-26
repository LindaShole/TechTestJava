package za.co.anycompany.anycompany.service;

import org.springframework.stereotype.Service;
import za.co.anycompany.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.anycompany.model.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();
/*
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

 */
//    private Customer customer;

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findAll().forEach(customers1 -> customers.add(customers1));
        return customers;
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
