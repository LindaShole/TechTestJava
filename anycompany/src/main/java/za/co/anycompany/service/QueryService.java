package za.co.anycompany.service;

import za.co.anycompany.common.DatabaseManager;
import za.co.anycompany.common.exception.NullConnectionException;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * <p>Title: QueryService</p>
 *
 * <p>Description:  Service that provide functionality to query customers</p>
 *
 * <p>Company: AnyCompany</p>
 *
 * @author Chizeba Maulu
 */
public class QueryService {
    private static final Logger LOG = Logger.getLogger(QueryService.class.getName());

    /**
     * Constructor
     **/
    public QueryService() {
        DatabaseManager.getInstance().createOrderTableIfNotExist();
        DatabaseManager.getInstance().createCustomerTableIfNotExist();
    }

    /**
     * Find customer by their customer id. Returns null in the event of a failure and an empty Customer object
     * if no results are found.
     *
     * @param customerId the customer id
     * @return the customer
     **/
    public Customer findCustomer(int customerId) {
        Customer customer;
        try {
            customer = CustomerRepository.findCustomerById(customerId);
        }
        catch (NullConnectionException | SQLException e) {
            LOG.warning("Could not find customer for id " + customerId + "\n" + e.getMessage());
            return null;
        }
        return customer;
    }

    /**
     * Find all customers, returns an empty list if no results are found. Otherwise, null is returned in the even of a failure
     *
     * @return the customers
     **/
    public List<Customer> findAllCustomers() {
        List<Customer> customerList;
        try {
            customerList = new ArrayList<>(CustomerRepository.retrieveAllCustomers());
        }
        catch (NullConnectionException | SQLException e) {
            LOG.warning("Could not find customers: " + e.getMessage());
            return null;
        }
        return customerList;
    }
}
