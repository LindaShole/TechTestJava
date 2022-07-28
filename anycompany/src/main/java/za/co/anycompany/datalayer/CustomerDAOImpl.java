package za.co.anycompany.datalayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class CustomerDAOImpl implements CustomerDAO {
    
    // 
    private static final String name = "CUSTOMER";
    // Added customerId foreign key
    // Update the casing to what seems to be upper case standard
    private static final String CREATE_CUSTOMER_SCHEMA = "CREATE TABLE CUSTOMER (CUSTOMERID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255) NOT NULL, COUNTRY VARCHAR(100), DATE_OF_BIRTH DATE)";

    private final DatabaseFactory database;

    public CustomerDAOImpl() {
        database = DatabaseFactoryImpl.getInstance();  
    }

    @Override
    public void create(Customer customer) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER(CUSTOMERID, NAME, COUNTRY, DATE_OF_BIRTH) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setDate(4, new Date(customer.getDateOfBirth().getTime()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    @Override
    public List<Customer> getAllCustomersWithOrders() throws SQLException, ClassNotFoundException {

        Connection con = database.getConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;

        Map<Integer, Customer> map = new HashMap<Integer, Customer>();

        try {
            prpstmt = con.prepareStatement("SELECT c.CUSTOMERID, c.NAME, c.COUNTRY, c.DATE_OF_BIRTH, o.ORDERID, o.AMOUNT, o.VAT FROM CUSTOMER AS c LEFT JOIN CUSTOMER_ORDER AS o ON c.CUSTOMERID = o.CUSTOMERID");
            
            resultSet = prpstmt.executeQuery();

            // Results could be mapped to an object using specialised Mapper class but for this
            // exercise, decided against it 
            while (resultSet.next()) {
                int customerId = resultSet.getInt("CUSTOMERID");
                Customer customer = map.get(customerId);

                if(customer == null) {
                    customer = new Customer();
                    customer.setCustomerId(customerId);
                    customer.setName(resultSet.getString("NAME"));
                    customer.setCountry(resultSet.getString("COUNTRY"));
                    customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                    
                    map.put(customerId, customer);
                }

                Order order = new Order();
                order.setOrderId(resultSet.getInt("ORDERID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setVAT(resultSet.getDouble("VAT"));

                if(order.getOrderId() > 0)
                    customer.addOrder(order);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw e;
            }
        }

        return new ArrayList<Customer>(map.values());
    }

    @Override
    public Customer getCustomerWithOrders(int id) throws SQLException, ClassNotFoundException {
        Connection con = database.getConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;

        Map<Integer, Customer> map = new HashMap<Integer, Customer>();
        Customer customer = null;

        try {
            prpstmt = con.prepareStatement("SELECT c.CUSTOMERID, c.NAME, c.COUNTRY, c.DATE_OF_BIRTH, o.ORDERID, o.AMOUNT, o.VAT FROM CUSTOMER AS c LEFT JOIN CUSTOMER_ORDER AS o ON c.CUSTOMERID = o.CUSTOMERID WHERE c.CUSTOMERID = ?");
            prpstmt.setInt(1, id);
            resultSet = prpstmt.executeQuery();

            // Results could be mapped to an object using specialised Mapper class but for this
            // exercise, decided against it 
            while (resultSet.next()) {
                int customerId = resultSet.getInt("CUSTOMERID");
                customer = map.get(customerId);

                if(customer == null) {
                    customer = new Customer();
                    customer.setCustomerId(customerId);
                    customer.setName(resultSet.getString("NAME"));
                    customer.setCountry(resultSet.getString("COUNTRY"));
                    customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                    
                    map.put(customerId, customer);
                }

                Order order = new Order();
                order.setOrderId(resultSet.getInt("ORDERID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setVAT(resultSet.getDouble("VAT"));

                if (order.getOrderId() > 0)
                    customer.addOrder(order);
            }
        } 
        catch (SQLException e) {
            throw e;
        } 
        finally {
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } 
            catch (SQLException e) {
                throw e;
            }
        }

        return customer;
    } 

    public static Customer load(int customerId) throws SQLException, ClassNotFoundException {
        Connection con = DatabaseFactoryImpl.getInstance().getConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Customer customer = new Customer();

        try {
            prpstmt = con.prepareStatement("select * from CUSTOMER where CUSTOMERID = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();

            while (resultSet.next()) {
                customer.setCustomerId(resultSet.getInt("CUSTOMERID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                throw e;
            }
        }

        return customer;
    }

    public void setupCustomerSchema() throws SQLException, ClassNotFoundException {
        try {
            if(!database.schemaExist(name)){
                database.createSchema(CREATE_CUSTOMER_SCHEMA);
            }
        }
        catch(SQLException | ClassNotFoundException e) {
            throw e;
        }
    }    
}
