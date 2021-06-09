package za.co.anycompany.datalayer;

import za.co.anycompany.model.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    static {
        Connection connection = getDBConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `order` (id BIGINT PRIMARY KEY AUTO_INCREMENT, order_id INT UNIQUE NOT NULL, amount NUMBER(10,2), vat NUMBER(3,1), customer_id BIGINT);");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Order save(Order order) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Order newOrder = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `order` (order_id, amount, vat, customer_id) VALUES(?,?,?,?);");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDouble(3, order.getVAT());
            preparedStatement.setLong(4, order.getCustomerId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT * FROM `order` WHERE order_id=?;");
            preparedStatement.setInt(1, order.getOrderId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newOrder = new Order();
                newOrder.setId(resultSet.getLong("id"));
                newOrder.setOrderId(resultSet.getInt("order_id"));
                newOrder.setAmount(resultSet.getDouble("amount"));
                newOrder.setVAT(resultSet.getDouble("vat"));
                newOrder.setCustomerId(resultSet.getLong("customer_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return newOrder;
    }

    public List<Order> findByCustomerId(int customerId) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `order` WHERE customer_id=?;");
            preparedStatement.setLong(1, customerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setOrderId(resultSet.getInt("order_id"));
                order.setAmount(resultSet.getDouble("amount"));
                order.setVAT(resultSet.getDouble("vat"));
                order.setCustomerId(resultSet.getLong("customer_id"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (resultSet != null)
                    resultSet.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return orders;
    }

    public Order findById(Long id) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Order order = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `order` WHERE id=?;");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setOrderId(resultSet.getInt("order_id"));
                order.setAmount(resultSet.getDouble("amount"));
                order.setVAT(resultSet.getDouble("vat"));
                order.setCustomerId(resultSet.getLong("customer_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (resultSet != null)
                    resultSet.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return order;
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
