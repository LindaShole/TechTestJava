package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static Customer load(int customerId) {
        Connection con = getDBConnection();  
        PreparedStatement prpstmt = null; 
        ResultSet resultSet = null;
        Customer customer = new Customer();
        try {
            prpstmt = con.prepareStatement("select * from CUSTOMER where CUSTOMER_ID = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                customer.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
			
        } finally {
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return customer;
    }


    public static List<Customer> loadAll() {
        Connection con = getDBConnection();  
        PreparedStatement prpstmt = null; 
        ResultSet resultSet = null;
        
        List<Customer> list = new ArrayList<Customer>();
        
        try {
            prpstmt = con.prepareStatement("select * from CUSTOMER");
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {

                Customer customer = new Customer(); 
                customer.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                list.add(customer);                
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
			
        } finally {
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }

    public static Customer saveCustomer(Customer customer) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER(NAME, COUNTRY, DATE_OF_BIRTH) VALUES(?,?,?)");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getCountry());
            preparedStatement.setDate(3, new java.sql.Date(customer.getDateOfBirth().getTime()));
            preparedStatement.executeUpdate(); 

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();  
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return customer;
    }

    public static Customer editCustomer(Customer customer) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET NAME=?, COUNTRY=?, DATE_OF_BIRTH=? WHERE CUSTOMER_ID=?");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getCountry());
            preparedStatement.setDate(3, new java.sql.Date(customer.getDateOfBirth().getTime()));
            preparedStatement.setInt(4, customer.getCustomerId());
            preparedStatement.executeUpdate(); 

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return customer;
    }

    public static void deleteCustomer(int customerId) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?");
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate(); 

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
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
