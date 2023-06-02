package za.co.anycompany.datalayer;

import za.co.anycompany.common.DatabaseHelper;
import za.co.anycompany.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerRepository {

    public static List<Customer> findAll() {
        Connection connection = DatabaseHelper.getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Customer> customers = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from CUSTOMER");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customerId"));
                customer.setName(resultSet.getString("name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
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
                throw new RuntimeException(e);
            }
        }
        return customers;
    }
    public static Customer findById(int customerId) {
        Connection connection = DatabaseHelper.getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = new Customer();
        try {
            preparedStatement = connection.prepareStatement("select * from CUSTOMER where customerId = ?");
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
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
                throw new RuntimeException(e);
            }
        }
        return customer;
    }

}
