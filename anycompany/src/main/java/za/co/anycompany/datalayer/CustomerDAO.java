package za.co.anycompany.datalayer;

import java.sql.SQLException;
import java.util.List;

import za.co.anycompany.model.Customer;

public interface CustomerDAO {
    void create(Customer customer) throws SQLException, ClassNotFoundException;
    Customer getCustomerWithOrders(int id) throws SQLException, ClassNotFoundException;
    List<Customer> getAllCustomersWithOrders() throws SQLException, ClassNotFoundException;
    void setupCustomerSchema() throws SQLException, ClassNotFoundException;
}
