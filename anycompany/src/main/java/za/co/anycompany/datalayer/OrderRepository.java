package za.co.anycompany.datalayer;

import java.util.ArrayList;
import java.util.List;
import za.co.anycompany.model.Order;

import java.sql.*;
import za.co.anycompany.model.Customer;

public class OrderRepository {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public void save(Order order) {
        Connection connection = getDBConnection();
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            statement = connection.createStatement();
//            statement.executeUpdate("CREATE TABLE ORDER (oderId int primary key not null, amount number(10,2), vat number (3,1), customerId int");
            connection.prepareStatement("INSERT INTO ORDERS(oderId, amount, vat, customerId) VALUES(?,?,?)");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDouble(3, order.getVAT());
            preparedStatement.setInt(3, order.getCustomer().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

        } finally {
            try {
                statement.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Order load(int orderId) {
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Order order = new Order();
        try {
            prpstmt = con.prepareStatement("select * from ORDERS where orderId = ?");
            prpstmt.setInt(1, orderId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                order.setAmount(resultSet.getInt("AMOUNT"));
                order.setVAT(resultSet.getInt("VAT"));
                Customer customer =  new Customer();
                customer.setId(resultSet.getInt("CUSTOMERID"));
                order.setCustomer(customer);
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

    public static List<Order> loadAllForCustomer(int customerId){
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Order order = null;
        List<Order> orders =  new ArrayList<>();
        try {
            prpstmt = con.prepareStatement("select * from ORDER where customerId = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                order =  new Order();
                order.setAmount(resultSet.getInt("AMOUNT"));
                order.setVAT(resultSet.getInt("VAT"));
                Customer customer =  new Customer();
                customer.setId(resultSet.getInt("CUSTOMERID"));
                order.setCustomer(customer);
                orders.add(order);
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
        return orders;
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
