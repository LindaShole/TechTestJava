package za.co.anycompany.datalayer;

import java.util.List;

import za.co.anycompany.exception.CustomerDataException;
import za.co.anycompany.model.Customer;


public interface CustomerRepository {
    void add(Customer customer) throws CustomerDataException;
    Customer getCustomerWithOrders(int customerId) throws CustomerDataException;
    List<Customer> getAllCustomersWithOrders() throws CustomerDataException;
}