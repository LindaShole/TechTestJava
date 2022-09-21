package za.co.anycompany.service;

import java.io.IOException;
import java.util.List;
import za.co.anycompany.model.Customer;

/**
 *
 * @author v-nchatitai
 */
public interface CustomerService {
    
    public List<Customer> getCustomers() throws IOException;

    public Customer getCustomer(int customerId) throws IOException;

    public Customer getCustomerByOrderId(int orderId) throws IOException;
}
