package main.java.za.co.anycompany.datalayer;

import main.java.za.co.anycompany.model.Customer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static main.java.za.co.anycompany.utils.DBUtils.getDBConnection;

public class CustomerRepository {
    public static Customer load(int customerId) throws SQLException {
        String query = "select CUSTOMERID, NAME, COUNTRY, DATE_OF_BIRTH from CUSTOMER where CUSTOMERID = ?";
        ResultSet resultSet;
        Customer customer = new Customer();

        try (Connection con = getDBConnection();
             PreparedStatement prpstmt = con.prepareStatement(query)) {
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();

            if (resultSet.next()) {
                customer.setCustomerId(resultSet.getInt("CUSTOMERID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return customer;
    }

    public static List<Customer> load() throws SQLException {
        String query = "select CUSTOMERID, NAME, COUNTRY, DATE_OF_BIRTH from CUSTOMER";
        ResultSet resultSet;
        List<Customer> customerList = new ArrayList<>();

        try (Connection con = getDBConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("CUSTOMERID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                customerList.add(customer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return customerList;
    }
}
