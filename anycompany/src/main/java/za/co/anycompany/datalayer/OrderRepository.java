package za.co.anycompany.datalayer;

import za.co.anycompany.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
 
    public void save(Order order) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;
 
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO ORDERS(AMOUNT, VAT, CUSTOMER_ID) VALUES(?,?,?)");
            preparedStatement.setDouble(1, order.getAmount());
            preparedStatement.setDouble(2, order.getVAT());
            preparedStatement.setInt(3, order.getCustomerId());
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

    public static Order load(Integer orderId) {
        Connection con = getDBConnection();  
        PreparedStatement prpstmt = null; 
        ResultSet resultSet = null;
        
        Order order = new Order(); 
        
        try {
            prpstmt = con.prepareStatement("select * from ORDERS where ORDER_ID= ?");
            prpstmt.setInt(1, orderId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {

                order.setOrderId(resultSet.getInt("ORDER_ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setVAT(resultSet.getDouble("VAT"));             
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
        return order;
    }

    public static List<Order> loadAll() {
        Connection con = getDBConnection();  
        PreparedStatement prpstmt = null; 
        ResultSet resultSet = null;
        
        List<Order> list = new ArrayList<Order>();
        
        try {
            prpstmt = con.prepareStatement("select * from ORDERS");
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {

                Order order = new Order(); 
                order.setOrderId(resultSet.getInt("ORDER_ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setVAT(resultSet.getDouble("VAT"));   
                list.add(order);                
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


    public static List<Order> customerOrders(Integer customerId) {
        Connection con = getDBConnection();  
        PreparedStatement prpstmt = null; 
        ResultSet resultSet = null;
        
        List<Order> list = new ArrayList<Order>();
        
        try {
            prpstmt = con.prepareStatement("select * from ORDERS where CUSTOMER_ID=?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {

                Order order = new Order(); 
                order.setOrderId(resultSet.getInt("ORDER_ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setVAT(resultSet.getDouble("VAT"));   
                list.add(order);                
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

    public static void deleteOrder(int orderId) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM ORDERS WHERE ORDER_ID=?");
            preparedStatement.setInt(1, orderId);
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
