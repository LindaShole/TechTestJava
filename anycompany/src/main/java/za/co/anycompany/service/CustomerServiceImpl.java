package za.co.anycompany.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.model.Customer;
import za.co.anycompany.persistance.dao.CustomerDao;

/**
 * This is where the customer-related business logic and business rules are 
 * enforced/applied
 * 
 * @author v-nchatitai
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    CustomerDao customerDao;
    
    /**
     * 
     * 
     * @return
     * @throws IOException 
     */
    @Override
    public List<Customer> getCustomers() throws IOException {
        return customerDao.getCustomers(); 
    }
    
    /**
     * Return customer whose ID matches the supplied number
     * 
     * @param customerId
     * @return
     * @throws IOException 
     */
    @Override
    public Customer getCustomer(int customerId) throws IOException {
        
        return customerDao.getCustomerById(customerId);
    }
    
    /**
     * An order cannot exist without an associated customer, this method will
     * return the customer details for the customer associated with the supplied
     * orderID 
     * 
     * @param orderId
     * @return
     * @throws IOException 
     */
    @Override
    public Customer getCustomerByOrderId(int orderId) throws IOException{
        
        return customerDao.getCustomerByOrderId(orderId);
    }
}
