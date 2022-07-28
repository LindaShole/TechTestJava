package za.co.anycompany.datalayer;

import java.sql.SQLException;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public interface OrderDAO {
    void create(Order order, Customer customer) throws SQLException, ClassNotFoundException;
    void setupOrderSchema() throws SQLException, ClassNotFoundException;
}