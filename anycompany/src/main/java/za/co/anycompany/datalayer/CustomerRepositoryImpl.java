package za.co.anycompany.datalayer;

import java.util.List;

import za.co.anycompany.exception.CustomerDataException;
import za.co.anycompany.model.Customer;

public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerDAO customerDAO;

    public CustomerRepositoryImpl() {
        // Could have injected a framework such JPA or Spring but due to constraints on JDBC API
        customerDAO = DatabaseFactoryImpl.getCustomerDAO();
    }

    @Override
    public void add(Customer customer) throws CustomerDataException {
        try {
            customerDAO.create(customer);
        }
        catch(Exception e) {
            throw new CustomerDataException(e.getMessage());
        }
    }

    @Override
    public List<Customer> getAllCustomersWithOrders() throws CustomerDataException {
        try {
            return customerDAO.getAllCustomersWithOrders();
        }
        catch(Exception e) {
            throw new CustomerDataException(e.getMessage());
        }        
    }

    @Override
    public Customer getCustomerWithOrders(int customerId) throws CustomerDataException {
        try {
            return customerDAO.getCustomerWithOrders(customerId);
        }
        catch(Exception e) {
            throw new CustomerDataException(e.getMessage());
        }        
    }   

    public static Customer load(int customerId) throws CustomerDataException {
        try {
            return CustomerDAOImpl.load(customerId);
        }
        catch(Exception e) {
            throw new CustomerDataException(e.getMessage());
        }        
    }
}
