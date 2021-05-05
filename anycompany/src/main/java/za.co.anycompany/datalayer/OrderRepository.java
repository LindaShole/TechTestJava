package za.co.anycompany.datalayer;

import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class OrderRepository {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static final Logger logger = Logger.getLogger(String.valueOf(OrderRepository.class));

    public boolean save(Order order) {
        boolean isSaved = false;

        String query = "INSERT INTO ORDERS(customerId, item, amount, vat) VALUES(?,?,?,?)";

        try (Connection db = DBConfig.getDBConnection()){
             try(PreparedStatement  preparedStatement = db.prepareStatement(query)) {

                 preparedStatement.setInt(1, order.getCustomerId());
                 preparedStatement.setString(2, order.getItem());
                 preparedStatement.setDouble(3, order.getAmount());
                 preparedStatement.setDouble(4, order.getVAT());

                 isSaved = preparedStatement.executeUpdate() > 0 ? true: false;
             }
        }
        catch (Exception e)
        {
            logger.log(Level.ALL,e.getMessage());
        }

        return isSaved;
    }

    public Order findOrderByOrderId(int customerId) {

        ResultSet resultSet = null;
        Order output = new Order();

        String query = "select orderId, customerId, item, amount, vat from ORDER where orderId = ? LIMIT 1";

        try (Connection db = DBConfig.getDBConnection()) {
            try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
                preparedStatement.setInt(1, customerId);
                try (ResultSet results = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        output.setOrderId(resultSet.getInt("orderId"));
                        output.setCustomerId(resultSet.getInt("customerId"));
                        output.setItem(resultSet.getString("item"));
                        output.setAmount(resultSet.getDouble("amount"));
                        output.setVAT(resultSet.getDouble("vat"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.log(Level.ALL,e.getMessage());
        }
        return output;
    }

    public List<Order> findOrdersByCustomerId(int customerId) {

        List<Order>  output = new ArrayList<>();

        String query = "select orderId, customerId, item, amount, vat from ORDERS where customerId = ? ";

        try (Connection db = DBConfig.getDBConnection()) {
            try (PreparedStatement preparedStatement = db.prepareStatement(query)) {
                preparedStatement.setInt(1, customerId);
                try (ResultSet results = preparedStatement.executeQuery()) {
                    while (results.next()) {
                        Order tmp = new Order();

                        tmp.setOrderId(results.getInt("orderId"));
                        tmp.setCustomerId(results.getInt("customerId"));
                        tmp.setItem(results.getString("item"));
                        tmp.setAmount(results.getDouble("amount"));
                        tmp.setVAT(results.getDouble("vat"));

                        output.add(tmp);
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.log(Level.ALL,e.getMessage());
        }
        return output;
    }
}
