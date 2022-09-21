package za.co.anycompany.persistence.mapper;

import java.util.List;
import za.co.anycompany.model.Customer;

/**
 * Customer data access contract
 *
 * @author v-nchatitai
 */
public interface CustomerMapper {
    
    public List<Customer> getCustomers();
    
    public Customer getCustomerById(int customerId);

    public Customer getCustomerByOrderId(int orderId);
}
