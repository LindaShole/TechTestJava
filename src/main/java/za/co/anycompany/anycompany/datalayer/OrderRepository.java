package za.co.anycompany.anycompany.datalayer;

import za.co.anycompany.anycompany.model.Order;

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
            statement.executeUpdate("CREATE TABLE ORDERS (oderId int primary key not null, amount number(10,2), vat number (3,1))");
            connection.prepareStatement("INSERT INTO ORDERS(oderId, amount, vat) VALUES(?,?,?)");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDouble(3, order.getVAT());
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
        return order;
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<Order>();
        Order order = new Order();
        Connection connection = getDBConnection();
        try {
            Statement statement = connection.createStatement();
            String select = "Select orderId, amount, VAT, customerId from ORDERS";
            //statement.executeUpdate("CREATE TABLE ORDERS (oderId int primary key not null, amount number(10,2), vat number (3,1))");
            //connection.prepareStatement("INSERT INTO ORDERS(oderId, amount, vat) VALUES(?,?,?)");
            //preparedStatement.setInt(1, order.getOrderId());
            //preparedStatement.setDouble(2, order.getAmount());
            //preparedStatement.setDouble(3, order.getVAT());
            //preparedStatement.executeUpdate();
            ResultSet rows;
            rows = statement.executeQuery(select);
            while (rows.next()) {
                order.setCustomerId(rows.getInt(1));
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
}
