package za.co.anycompany.datalayer;

import za.co.anycompany.database.ConnectionFactory;
import za.co.anycompany.model.Order;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderRepository {

    public void save(Order order) {
        try(Connection connection = getDBConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ORDER(orderId, amount, vat, customerId) VALUES(?,?,?,?)")) {

            statement.executeUpdate("CREATE TABLE ORDER (orderId int primary key not null, amount number(10,2), vat number (3,1)), customerId int not null");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDouble(3, order.getVAT());
            preparedStatement.setInt(4, order.getCustomerId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        }
    }

    private static Connection getDBConnection() {
        return ConnectionFactory.getDBConnection();
    }
}
