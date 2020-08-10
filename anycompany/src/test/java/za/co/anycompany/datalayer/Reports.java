package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import java.text.NumberFormat;
import java.util.Locale;

import sun.tools.tree.InstanceOfExpression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;  

public class Reports {
	
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    /*private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";*/
    
    public static void getAllCustomersOrders() throws IOException{
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        System.out.println("---------------------------------------------------------------------------------");
        System.out.format("%-100.100s","Orders for all customers"); 
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------");
    	System.out.format("%-25.25s", "Customer Name");
    	System.out.format("%-12.12s", "Order Date");
    	System.out.format("%12.12s", "Amount");
    	System.out.format("%12.12s", "VAT");
    	System.out.format("%15.15s", "Total Inc VAT");
    	System.out.println();
        System.out.println("---------------------------------------------------------------------------------");
        		
        try {
            prpstmt = con.prepareStatement("SELECT customer.name AS 'Customer' , orders.orderDate AS 'Order Date', orders.amount AS 'Amount' , (orders.amount * orders.vat) AS 'VAT', (orders.amount + (orders.amount * orders.vat)) AS 'Total' FROM `orders` LEFT JOIN customer ON orders.customerId = customer.customerId ORDER By customer.NAME , orders.orderDate");
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
            	NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
            	System.out.format("%-25.25s", resultSet.getString("Customer"));
            	System.out.format("%-12.12s", resultSet.getString("Order Date"));
            	System.out.format("%12.12s", defaultFormat.format(resultSet.getDouble("Amount")));
            	System.out.format("%12.12s", defaultFormat.format(resultSet.getDouble("VAT")));
            	System.out.format("%15.15s", defaultFormat.format(resultSet.getDouble("Total")));
            	System.out.println();
            }
        	System.out.println();
        	System.out.println("Press anykey to continue");
        	BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        	reader.readLine();
        	
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
    }
    
    public static void getCustomersOrders(Customer customer) throws IOException{
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        System.out.println("---------------------------------------------------------------------------------");
        System.out.format("%-100.100s","Orders for " + customer.getName()); 
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------");
    	System.out.format("%-12.12s", "Order Date");
    	System.out.format("%12.12s", "Amount");
    	System.out.format("%12.12s", "VAT");
    	System.out.format("%15.15s", "Total Inc VAT");
    	System.out.println();
        System.out.println("---------------------------------------------------------------------------------");
        		
        try {
            prpstmt = con.prepareStatement("SELECT orders.orderDate AS 'Order Date', orders.amount AS 'Amount' , (orders.amount * orders.vat) AS 'VAT', (orders.amount + (orders.amount * orders.vat)) AS 'Total' FROM `orders` LEFT JOIN customer ON orders.customerId = customer.customerId WHERE customer.customerId = ? ORDER By orders.orderDate");
            prpstmt.setInt(1, customer.getId());
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
            	NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
            	System.out.format("%-12.12s", resultSet.getString("Order Date"));
            	System.out.format("%12.12s", defaultFormat.format(resultSet.getDouble("Amount")));
            	System.out.format("%12.12s", defaultFormat.format(resultSet.getDouble("VAT")));
            	System.out.format("%15.15s", defaultFormat.format(resultSet.getDouble("Total")));
            	System.out.println();
            }
        	System.out.println();
        	BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        	try {
        	System.out.println("Press anykey to continue");
        	reader.readLine();
        	}catch (IOException exp) {
        		
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
