package za.co.anycompany.datalayer;

import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    private static Connection connection = DatalayerConfig.getDBConnection();

    public static Customer load(int customerId) throws SQLException {
        String SelectQuery = "select * from CUSTOMER where customerId = ?";
        PreparedStatement selectPreparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = new Customer();
        try {
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            selectPreparedStatement.setInt(1, customerId);
            assert selectPreparedStatement != null;
            resultSet = selectPreparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setCustomerId(resultSet.getInt("customerId"));
                customer.setName(resultSet.getString("name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setDateOfBirth(resultSet.getDate("dateOfBirth"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.close();
        }
        return customer;
    }


    public static List<Customer> loadAllCustomers() throws SQLException {
        PreparedStatement selectPreparedStatement = null;
        String SelectQuery = "select * from CUSTOMER";
        List<Customer> customers = new ArrayList<>();
        try {
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            assert selectPreparedStatement != null;
            ResultSet rs = selectPreparedStatement.executeQuery();
            Customer customer = new Customer();
            while (rs.next()) {
                customer.setCustomerId(rs.getInt("customerId"));
                customer.setName(rs.getString("name"));
                customer.setCountry(rs.getString("country"));
                customer.setDateOfBirth(rs.getDate("dateOfBirth"));
                customers.add(customer);
                customer = new Customer();
            }
            selectPreparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.close();
        }
        return customers;
    }

    public void saveCustomer(Customer customer) throws SQLException {
        PreparedStatement insertPreparedStatement = null;
        String InsertQuery = "INSERT INTO CUSTOMER(customerId, name, country, dateOfBirth) VALUES(?,?,?,?)";

        try {
            connection.setAutoCommit(false);
            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setInt(1, customer.getCustomerId());
            insertPreparedStatement.setString(2, customer.getName());
            insertPreparedStatement.setString(3, customer.getCountry());
            insertPreparedStatement.setDate(4, customer.getDateOfBirth());
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
