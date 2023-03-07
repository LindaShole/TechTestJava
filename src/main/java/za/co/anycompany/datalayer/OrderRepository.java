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
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ORDERS (orderId int primary key not null, amount number(10,2), vat number (3,1))");
           /* statement.executeUpdate("INSERT INTO ORDERS(amount, vat) VALUES(299.65,2)");
            statement.executeUpdate("INSERT INTO ORDERS(amount, vat) VALUES(299.65,1.5)");
            statement.executeUpdate("INSERT INTO ORDERS(amount, vat) VALUES(299.65,1.2)");
            connection.prepareStatement("INSERT INTO ORDERS(orderId, amount, vat) VALUES(4,299.65,2)");*/
            preparedStatement = connection.prepareStatement("INSERT INTO ORDERS(amount, vat, customerId) VALUES(?,?,?)");
            /*preparedStatement.setInt(1, order.getOrderId());*/
            preparedStatement.setDouble(1, order.getAmount());
            preparedStatement.setDouble(2, order.getVAT());
            preparedStatement.setInt(3, order.getCustomerId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {

        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
                if(connection != null)
                    connection.close();
                if(statement != null)
                    statement.close();
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

    public Order findById(Integer id) {
       Order order = new Order();

        Connection connection = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
          //  String select = "Select orderId, amount, VAT, customerId from ORDERS order by orderId asc";
            prpstmt = connection.prepareStatement("select orderId, amount, VAT, customerId from ORDERS where orderId = ?");
            prpstmt.setInt(1, id);
            resultSet = prpstmt.executeQuery();

            while (resultSet.next()) {
                order.setOrderId(resultSet.getInt(1));
                order.setAmount(resultSet.getDouble(2)) ;
                order.setVAT(resultSet.getDouble(3));
                order.setCustomerId(resultSet.getInt(4));
            }

            return order;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<Order>();
   //     Order order = new Order();
        Connection connection = getDBConnection();
        try {
            Statement statement = connection.createStatement();
            String select = "Select orderId, amount, VAT, customerId from ORDERS order by orderId asc";
            ResultSet rows;
            rows = statement.executeQuery(select);
            while (rows.next()) {
                Order order = new Order();
                order.setOrderId(rows.getInt(1));
                order.setAmount(rows.getDouble(2)) ;
                order.setVAT(rows.getDouble(3));
                order.setCustomerId(rows.getInt(4));
                orders.add(order);
            }

            return orders;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Order> getOrdersByCustomerId(Integer customerId) {
        Order order = new Order();
        List<Order> orders = new ArrayList<Order>();

        Connection connection = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            //  String select = "Select orderId, amount, VAT, customerId from ORDERS order by orderId asc";
            prpstmt = connection.prepareStatement("select orderId, amount, VAT, customerId from ORDERS where customerId = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();

            while (resultSet.next()) {
                order.setOrderId(resultSet.getInt(1));
                order.setAmount(resultSet.getDouble(2)) ;
                order.setVAT(resultSet.getDouble(3));
                order.setCustomerId(resultSet.getInt(4));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public Order remove(Integer id) {
        Order order = new Order();
        return order;
    }
}
