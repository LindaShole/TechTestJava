package za.co.anycompany.datalayer;

import za.co.anycompany.database.ConnectionFactory;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerRepository {

    public static Customer load(int customerId) {
        ResultSet resultSet = null;
        Customer customer = new Customer();
        try(Connection con = getDBConnection();
            PreparedStatement prpstmtCustomer = con.prepareStatement("select * from CUSTOMER where customerId = ?");
            PreparedStatement prpstmtOrder = con.prepareStatement("select * from ORDER where customerId = ?");
        ) {

            prpstmtCustomer.setInt(1, customerId);
            resultSet = prpstmtCustomer.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
            }

            prpstmtOrder.setInt(1, customerId);
            resultSet = prpstmtOrder.executeQuery();
            Order order = null;
            Set<Order> customerOrders = new HashSet<>();
            while (resultSet.next()) {
                order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setAmount(resultSet.getDouble("amount"));
                order.setVAT(resultSet.getDouble("vat"));
                order.setCustomerId(customerId);
                customerOrders.add(order);
            }
            customer.setOrders(customerOrders);
        }
        catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        }
        finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            }
            catch (SQLException e) {
                Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
            }
        }
        return customer;
    }

    private static Connection getDBConnection() {
        return ConnectionFactory.getDBConnection();
    }
}
