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

    public List<Customer> getAll(){
        ResultSet resultSetCustomer = null;
        ResultSet resultSetOrder = null;
        List<Customer> customers = new ArrayList<>();
        try(Connection connection = getDBConnection();
            Statement statement = connection.createStatement();
            PreparedStatement prpstmtOrder = connection.prepareStatement("SELECT * FROM ORDER WHERE customerId = ?")) {

            resultSetCustomer = statement.executeQuery("SELECT * FROM CUSTOMER");
            Order order = null;
            Customer customer = null;
            while (resultSetCustomer.next()) {
                customer = new Customer();
                customer.setId(resultSetCustomer.getInt("id"));
                customer.setName(resultSetCustomer.getString("NAME"));
                customer.setCountry(resultSetCustomer.getString("COUNTRY"));
                customer.setDateOfBirth(resultSetCustomer.getDate("DATE_OF_BIRTH"));

                prpstmtOrder.setInt(1, customer.getId());
                resultSetOrder = prpstmtOrder.executeQuery();
                while (resultSetOrder.next()) {
                    order = new Order();
                    order.setOrderId(resultSetOrder.getInt("orderId"));
                    order.setAmount(resultSetOrder.getDouble("amount"));
                    order.setVAT(resultSetOrder.getDouble("vat"));
                    order.setCustomerId(resultSetOrder.getInt("customerId"));
                    customer.getOrders().add(order);
                }
                customers.add(customer);
            }
        }
        catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        }
        finally {
            try{
                if(resultSetCustomer != null)
                    resultSetCustomer.close();

                if(resultSetOrder != null)
                    resultSetOrder.close();
            }
            catch (SQLException e){
                Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
            }
        }

        return customers;
    }

    private static Connection getDBConnection() {
        return ConnectionFactory.getDBConnection();
    }
}
