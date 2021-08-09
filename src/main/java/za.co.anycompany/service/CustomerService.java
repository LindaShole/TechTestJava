package za.co.anycompany.service;

import org.springframework.stereotype.Component;
import za.co.anycompany.model.Customer;

import java.util.List;

@Component
public interface CustomerService {

    public Customer getCustomer(long customerId);

    public List<Customer> getCustomerList();

    public void saveCustomer(Customer customer);
}
