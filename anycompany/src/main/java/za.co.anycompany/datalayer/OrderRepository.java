package za.co.anycompany.datalayer;

import za.co.anycompany.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            statement.executeUpdate("CREATE TABLE ORDER (oderId int primary key not null, customerId int, amount number(10,2), vat number (3,1))");
            connection.prepareStatement("INSERT INTO ORDER(oderId, customerId, amount, vat) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setDouble(3, order.getAmount());
            preparedStatement.setDouble(4, order.getVAT());
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

    public List<Order> load(int customerId) {
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Order order = new Order();
        List<Order> orders = new ArrayList<>();
        try {
            prpstmt = con.prepareStatement("select * from ORDER where customerId = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                order.setOrderId(resultSet.getInt("oderId"));
                order.setCustomerId(resultSet.getInt("customerId"));
                order.setAmount(resultSet.getDouble("amount"));
                order.setVAT(resultSet.getDouble("vat"));
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
