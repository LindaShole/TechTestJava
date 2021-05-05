package za.co.anycompany.datalayer;

import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class CustomerRepository {

    private static final Logger logger = Logger.getLogger(String.valueOf(CustomerRepository.class));

    public  Customer load(int customerId) throws SQLException {
        ResultSet resultSet = null;
        Customer customer = new Customer();

        String query = "select NAME, COUNTRY, DATE_OF_BIRTH from CUSTOMER where customerId = ? LIMIT 1";

        try (Connection db = DBConfig.getDBConnection()) {
            try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
                preparedStatement.setInt(1, customerId);
                try (ResultSet results = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        customer.setName(resultSet.getString("NAME"));
                        customer.setCountry(resultSet.getString("COUNTRY"));
                        customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.log(Level.ALL,e.getMessage());
        }
        return customer;
    }

    public  List<Customer> getCustomers() throws SQLException {
        List<Customer> output = new ArrayList<>();
        String query = "select customerId, name, country, dateOfBirth from CUSTOMER ";

        try (Connection db = DBConfig.getDBConnection()) {
            try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
                try (ResultSet results = preparedStatement.executeQuery()) {
                    while (results.next()) {
                        Customer customer = new Customer();

                        customer.setCustomerId(results.getInt("customerId"));
                        customer.setName(results.getString("name"));
                        customer.setCountry(results.getString("country"));
                        customer.setDateOfBirth(results.getDate("dateOfBirth"));

                        output.add(customer);
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.log(Level.ALL,e.getMessage());
        }
        return output;
    }

    public  Customer getCustomer(int customerId) throws SQLException {
        String query = "select customerId, name, country, dateOfBirth from CUSTOMER where customerId = ? limit 1";
        Customer customer = new Customer();

        try (Connection db = DBConfig.getDBConnection()) {
            try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
                preparedStatement.setInt(1,customerId);
                try (ResultSet results = preparedStatement.executeQuery()) {
                    if (results.next()) {

                        customer.setCustomerId(results.getInt("customerId"));
                        customer.setName(results.getString("name"));
                        customer.setCountry(results.getString("country"));
                        customer.setDateOfBirth(results.getDate("dateOfBirth"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.log(Level.ALL,e.getMessage());
        }
        return customer;
    }


    public boolean save(Customer customer) {
        boolean isSaved = false;

        String query = "INSERT INTO CUSTOMER(name, country, dateOfBirth) VALUES(?,?,?)";

        try (Connection db = DBConfig.getDBConnection()){
            try(PreparedStatement  preparedStatement = db.prepareStatement(query)) {

                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getCountry());
                preparedStatement.setDate(3, customer.getDateOfBirth());

                isSaved = preparedStatement.executeUpdate() > 0 ? true: false;
            }
        }
        catch (Exception e)
        {
            logger.log(Level.ALL,e.getMessage());
        }

        return isSaved;
    }
}
