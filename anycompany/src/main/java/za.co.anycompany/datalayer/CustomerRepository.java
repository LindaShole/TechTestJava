package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerRepository {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    static {
        Connection connection = getDBConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS customer (id BIGINT PRIMARY KEY AUTO_INCREMENT, customer_id INT UNIQUE NOT NULL, name VARCHAR(255), country VARCHAR(255), date_of_birth DATE);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Customer save(Customer customer) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer newCustomer = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO customer(customer_id, name, country, date_of_birth) VALUES(?,?,?,?);");
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setDate(4, Date.valueOf(customer.getDateOfBirth()));
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE customer_id=?;");
            preparedStatement.setInt(1, customer.getCustomerId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newCustomer = new Customer();
                newCustomer.setId(resultSet.getLong("id"));
                newCustomer.setCustomerId(resultSet.getInt("customer_id"));
                newCustomer.setName(resultSet.getString("name"));
                newCustomer.setCountry(resultSet.getString("country"));
                newCustomer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return newCustomer;
    }

    public static Customer load(int customerId) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = new Customer();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE customer_id = ?;");
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getLong("id"));
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
            }
            return customer;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (resultSet != null)
                    resultSet.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public List<Customer> findAll() {
        Connection con = getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Customer> customers = new ArrayList<>();
        try {
            preparedStatement = con.prepareStatement("SELECT * FROM customer;");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return customers;
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

}
