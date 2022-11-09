package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@//127.0.0.1:1521/cdm.co.za";
    private static final String DB_USER = "LANG";
    private static final String DB_PASSWORD = "Kiran#123";

    public List<Order> load() {
    	Connection con = getDBConnection();
    	Statement statement = null;
        ResultSet resultSet = null;
        List<Order> orderList = new ArrayList<>();
        try {
        	statement = con.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM ORDERS");
            Order order = new Order();
            while (resultSet.next()) {
                order.setId(resultSet.getInt("ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setVAT(resultSet.getDouble("VAT"));
                order.setCustomerId(resultSet.getInt("CUSTOMERID"));
                orderList.add(order);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return orderList;
    }
    
    public void save(Order order) {
        Connection con = getDBConnection();        
        PreparedStatement preparedStmt = null;

        try {
        	con.setAutoCommit(false);
        	preparedStmt = con.prepareStatement("INSERT INTO ORDERS(ID, amount, vat, customerId) VALUES(?,?,?,?)");            
        	preparedStmt.setInt(1, order.getId());
        	preparedStmt.setDouble(2, order.getAmount());
        	preparedStmt.setDouble(3, order.getVAT());
        	preparedStmt.setInt(4, order.getCustomerId());
        	preparedStmt.executeUpdate();
            con.commit();

        } catch (SQLException e) {
        	try {
				con.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
        	e.printStackTrace();
        } finally {
            try {
            	preparedStmt.close();                               
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

	public List<Order> load(int customerId) {
		Connection con = getDBConnection();
    	Statement statement = null;
        ResultSet resultSet = null;
        List<Order> orderList = new ArrayList<>();
        try {
        	statement = con.createStatement();            
            resultSet = statement.executeQuery("SELECT * FROM ORDERS WHERE CUSTOMERID="+customerId);            
            while (resultSet.next()) {
            	Order order = new Order();
                order.setId(resultSet.getInt("ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setVAT(resultSet.getDouble("VAT"));
                order.setCustomerId(resultSet.getInt("CUSTOMERID"));
                orderList.add(order);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return orderList;
	}
	
}
