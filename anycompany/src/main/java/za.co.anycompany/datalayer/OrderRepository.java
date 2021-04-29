package za.co.anycompany.datalayer;

import za.co.anycompany.model.Order;
import za.co.anycompany.databaseConnection.DataBaseConnection;

import java.sql.*;

public class OrderRepository {

    public void save(Order order) 
    {
        Connection connection = getDBConnection();
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE ORDER (orderId int primary key not null, amount number(10,2), vat number (3,1),customerID int FOREIGN KEY REFERENCES CUSTOMER(customerId))");
            connection.prepareStatement("INSERT INTO ORDER(orderId, amount, vat) VALUES(?,?,?)");
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
        return databaseConnection.getDBConnection();
}
}
