package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerRepository {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@//127.0.0.1:1521/cdm.co.za";
    private static final String DB_USER = "LANG";
    private static final String DB_PASSWORD = "Kiran#123";

    public static Customer load(int customerId) {
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Customer customer = null;
        try {
            prpstmt = con.prepareStatement("SELECT * FROM CUSTOMER WHERE ID = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
            	customer = new Customer();
            	customer.setId(resultSet.getInt("ID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                break;
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

    public static List<Customer> load() {
        Connection con = getDBConnection();
        Statement stmt = null;
        ResultSet resultSet = null;
        List<Customer> customerList = new ArrayList();
        try {
        	String query = new String("SELECT * FROM CUSTOMER");        	
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(query);            
            while (resultSet.next()) {
            	Customer customer = new Customer();
            	customer.setId(resultSet.getInt("ID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
                customerList.add(customer);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return customerList;
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
