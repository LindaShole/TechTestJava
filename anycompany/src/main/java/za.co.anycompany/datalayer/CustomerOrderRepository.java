package za.co.anycompany.datalayer;

import za.co.anycompany.databaseConnection.DataBaseConnection;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.*;

public class CustomerOrderRepository {

public List<Customer> getAll 
{
    Connection connection = getDBConnection();
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    try {
        statement = connection.createStatement();
        connection.prepareStatement("SELECT * FROM CustomerOrder WHERE customerId = ? & orderId = ?");
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
    
    return customers
}

  private static Connection getDBConnection() {
        return databaseConnection.getDBConnection();
  }
}