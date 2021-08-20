package main.java.za.co.anycompany.datalayer;

import main.java.za.co.anycompany.model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.za.co.anycompany.utils.DBUtils.getDBConnection;

public class OrderRepository {
    public static void save(Order order) throws SQLException {
        // Do not insert the ORDERID. This field will be an identity column in DB
        String query = "INSERT INTO ORDER(AMOUNT, VAT, CUSTOMERID) VALUES(?,?,?)";

        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, order.getAmount());
            preparedStatement.setDouble(2, order.getVAT());
            preparedStatement.setLong(3, order.getCustomerId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static List<Order> getOrdersForCustomer(int customerId) throws SQLException {
        String query = "select ORDERID, AMOUNT, VAT, CUSTOMERID from ORDERS where CUSTOMERID = ?";
        List<Order> orderList = new ArrayList<>();
        ResultSet resultSet;

        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Order order = new Order();
                order.setOrderId(resultSet.getInt("ORDERID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setVAT(resultSet.getDouble("VAT"));
                order.setCustomerId(resultSet.getInt("CUSTOMERID"));
                orderList.add(order);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            throw ex;
        }
        return orderList;
    }
}
