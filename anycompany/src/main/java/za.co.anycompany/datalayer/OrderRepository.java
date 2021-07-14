package main.java.za.co.anycompany.datalayer;

import main.java.za.co.anycompany.datalayer.DBConnection;
import za.co.anycompany.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    public void save(Order order, int customerId) {
        Connection connection = DBConnection.getDBConnection();
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ORDERTABLE(orderId int primary key auto_increment, amount number(10,2), vat number (3,1), customerId int)");
            preparedStatement = connection.prepareStatement("INSERT INTO ORDERTABLE(orderId, amount, vat, customerId) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDouble(3, order.getVAT());
            preparedStatement.setInt(4, customerId);
            preparedStatement.executeUpdate();

            //System.out.print(String.valueOf(result));

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

    public List<Order> loadCustomerOrders(int customerId) {
        Connection con = DBConnection.getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        List<Order> customerOrders = new ArrayList<>();
        Order order = null;
        try {
            prpstmt = con.prepareStatement("select * from ORDERTABLE");// "select * from CUSTOMER where customerId = ?"
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();

            while (resultSet.next()) {
                order = new Order();
                order.setAmount(resultSet.getDouble("amount"));
                order.setVAT(resultSet.getDouble("vat"));

                customerOrders.add(order);
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
        return customerOrders;
    }

}
