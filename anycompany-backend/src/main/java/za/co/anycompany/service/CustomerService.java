package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer load(int customerId) {
        Customer customer = new Customer();
        try {
            customer =  CustomerRepository.load(customerId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }



    public boolean saveCustomer(Customer customer) {

        if (customer.getCountry().isEmpty() || customer.getDateOfBirth() == null || customer.getName().isEmpty()) {
            return false;
        }
        try {
            customerRepository.saveCustomer(customer);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public List<Customer> loadAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            customers =  CustomerRepository.loadAllCustomers();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }
}
