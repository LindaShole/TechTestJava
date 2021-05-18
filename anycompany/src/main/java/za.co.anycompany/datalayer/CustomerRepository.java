package datalayer;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import model.Customer;
import util.DatabaseConnection;


public class CustomerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRepository.class);

    public static Customer load(int customerId) {
        Customer customer = new Customer();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("select * from CUSTOMER where customerId = ?");
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    customer.setCustomerId(resultSet.getInt("CUSTOMERID"));
                    customer.setName(resultSet.getString("NAME"));
                    customer.setCountry(resultSet.getString("COUNTRY"));
                    customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
       
        return customer;
    }
    
    public static Customer loadWithOrders(int customerId) {
        Customer customer = new Customer();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("select * from CUSTOMER where customerId = ?");
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    customer.setCustomerId(resultSet.getInt("CUSTOMERID"));
                    customer.setName(resultSet.getString("NAME"));
                    customer.setCountry(resultSet.getString("COUNTRY"));
                    customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                    customer.setOrderList(OrderRepository.loadAllByCustomerId(customer.getCustomerId()).size()>0 ? OrderRepository.loadAllByCustomerId(customer.getCustomerId()) : java.util.Collections.emptyList() );
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
       
        return customer;
    }

    public static List<Customer> loadAll() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer;
        
        try (Connection con = DatabaseConnection.getConnection();
                Statement statement = con.createStatement()) {
               try (ResultSet resultSet = statement.executeQuery("select * from CUSTOMER")) {
                   while (resultSet.next()) {
                   	  customer = new Customer();
                      customer.setCustomerId(resultSet.getInt("CUSTOMERID"));
                      customer.setName(resultSet.getString("NAME"));
                      customer.setCountry(resultSet.getString("COUNTRY"));
                      customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                      customer.setOrderList(OrderRepository.loadAllByCustomerId(customer.getCustomerId()));
                      customerList.add(customer);
                   }
               }

           } catch (SQLException e) {
               LOGGER.error(e.getMessage(), e);
           }
       
        return customerList;
    }

    public static void save(Customer customer) {

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMER (customerId int auto_increment primary key, name varchar, country varchar, date_of_birth DATE)");
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER(name, country, date_of_birth) VALUES(?,?,?)")) {
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getCountry());
                preparedStatement.setDate(3, (Date) customer.getDateOfBirth());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    public static void dropTable(){
        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()) {
               statement.executeUpdate("DROP TABLE CUSTOMER");
           } catch (SQLException e) {
               LOGGER.error(e.getMessage(), e);
           }
    }
}
