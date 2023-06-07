package za.co.anycompany.datalayer;

import za.co.anycompany.common.DatabaseManager;
import za.co.anycompany.common.exception.NullConnectionException;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: CustomerRepository</p>
 * <p/>
 * <p>Description: This class is responsible for all customer related database queries. /</p>
 * <p/>
 * <p>Company: AnyCompany</p>
 *
 * @author Chizeba Maulu
 * @version 1.0
 */
public class CustomerRepository {

    /**
     * Constructor, this class contains only static methods and hence should not be instantiated.
     **/
    private CustomerRepository() {
        throw new IllegalStateException("Utility class, do not instantiate");
    }

    /**
     * This method will find a customer using their customer id
     *
     * @param customerId the customer id
     * @return the customer linked to the requested id
     **/
    public static Customer findCustomerById(int customerId) throws NullConnectionException, SQLException {
        Connection connection = DatabaseManager.getInstance().getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = new Customer();
        if (connection == null) {
            throw new NullConnectionException("Could not establish database connection");
        }

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE customer_id = ?");
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("full_name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customer.setOrderList(findOrderByCustomerId(customerId));
            }
        }
        catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            handleConnection(preparedStatement, resultSet, connection);
        }
        return customer;
    }

    /**
     * This method will return a list of all customers and their associated orders
     *
     * @return the list of customers
     **/
    public static List<Customer> retrieveAllCustomers() throws NullConnectionException, SQLException {
        Connection connection = DatabaseManager.getInstance().getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Customer> customerList = new ArrayList<>();
        if (connection == null) {
            throw new NullConnectionException("Could not establish database connection");
        }

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM customer");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("full_name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customer.setOrderList(findOrderByCustomerId(customer.getCustomerId()));
                customerList.add(customer);
            }
        }
        catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            handleConnection(preparedStatement, resultSet, connection);
        }
        return customerList;
    }

    /**
     * Finds an order or orders by the customer id linked to the order
     *
     * @param customerId the customer id
     * @return the list of orders
     **/
    public static List<Order> findOrderByCustomerId(int customerId) throws NullConnectionException, SQLException {
        Connection connection = DatabaseManager.getInstance().getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orderList = new ArrayList<>();
        if (connection == null) {
            throw new NullConnectionException("Could not establish database connection");
        }

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM order WHERE customer_id = ?");
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setAmount(resultSet.getDouble("amount"));
                order.setVat(resultSet.getDouble("vat"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                orderList.add(order);
            }
        }
        catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            handleConnection(preparedStatement, resultSet, connection);
        }
        return orderList;
    }

    /**
     * Handles the connection and closes it. Also closes any open statement and resultSets.
     *
     * @param preparedStatement the prepared statement
     * @param resultSet         the result set
     * @param connection        the connection
     **/
    private static void handleConnection(PreparedStatement preparedStatement, ResultSet resultSet, Connection connection) throws SQLException {
        connection.close();
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }
}
