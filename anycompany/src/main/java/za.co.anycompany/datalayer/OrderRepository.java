package za.co.anycompany.datalayer;

import za.co.anycompany.common.DatabaseHelper;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    public void save(Order order) {
        Connection connection = DatabaseHelper.getDBConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO ORDER(orderId, amount, vat, customerId) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDouble(3, order.getVAT());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public List<Order> findByCustomerId(int customerId) {

        Connection connection = DatabaseHelper.getDBConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("select * from ORDER where customerId = ?");
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setAmount(resultSet.getDouble("amount"));
                order.setVAT(resultSet.getDouble("vat"));
                order.setCustomerId(resultSet.getInt("customerId"));

                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
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
                throw new RuntimeException(e);
            }
        }
        return orders;
    }
}
