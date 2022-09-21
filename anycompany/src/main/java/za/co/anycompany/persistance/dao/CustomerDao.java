package za.co.anycompany.persistance.dao;

import java.io.IOException;
import java.util.List;
import za.co.anycompany.model.Customer;

/**
 * 
 *
 * @author v-nchatitai
 */
public interface CustomerDao {

    public List<Customer> getCustomers() throws IOException;
    
    public Customer getCustomerById(int customerId) throws IOException;
    
    public Customer getCustomerByOrderId(int orderId) throws IOException;
}
