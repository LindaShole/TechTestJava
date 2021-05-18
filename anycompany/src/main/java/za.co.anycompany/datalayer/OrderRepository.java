package datalayer;

import model.Order;
import util.DatabaseConnection;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepository.class);

    public void save(Order order) {

        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ORDERS (orderId int primary key, amount decimal(10,2), vat decimal(3,2),customerId int," +
                    "foreign key (customerId) references CUSTOMER (customerId))");

            try( PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ORDERS (orderId, amount, vat, customerId) VALUES(?,?,?,?)")) {
                preparedStatement.setInt(1, order.getOrderId());
                preparedStatement.setDouble(2, order.getAmount());
                preparedStatement.setDouble(3, order.getVAT());
                preparedStatement.setInt(4, order.getCustomerId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static List<Order> loadAll() {
        List<Order> orderList = new ArrayList<>();
        Order order;
        try (Connection con = DatabaseConnection.getConnection();
             Statement statement = con.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select * from ORDERS")) {
                while (resultSet.next()) {
                    order = new Order();
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setAmount(resultSet.getDouble("amount"));
                    order.setVAT(resultSet.getDouble("vat"));
                    order.setCustomerId(resultSet.getInt("customerId"));
                    orderList.add(order);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return orderList;
    }

    public static Order load(int orderId) {
        Order order = new Order();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("select * from ORDERS where orderId = ?")) {
            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setAmount(resultSet.getDouble("amount"));
                    order.setVAT(resultSet.getDouble("vat"));
                    order.setCustomerId(resultSet.getInt("customerId"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return order;
    }

    public static List<Order> loadAllByCustomerId(int customerId) {
       
        List<Order> orderList = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("select * from ORDERS where customerId = ?")) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                     Order order = new Order();
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setAmount(resultSet.getDouble("amount"));
                    order.setVAT(resultSet.getDouble("vat"));
                    order.setCustomerId(resultSet.getInt("customerId"));
                    orderList.add(order);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return orderList;
    }
    
    public static void dropTable(){
        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()) {
               statement.executeUpdate("DROP TABLE ORDERS");
           } catch (SQLException e) {
               LOGGER.error(e.getMessage(), e);
           }
    }
}
