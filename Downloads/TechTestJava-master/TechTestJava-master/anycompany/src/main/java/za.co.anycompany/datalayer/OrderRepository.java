package za.co.anycompany.datalayer;

import za.co.anycompany.model.Order;
import za.co.anycompany.responses.SaveOrderResponse;
import java.sql.*;

public class OrderRepository {

	private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static Connection connection = null;
    private static Statement statement = null;

    static{
        connection = getDBConnection();
        statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE CUSTOMER_ORDERS (oderId int primary key not null, amount number(10,2), vat number (3,1), customer_id int)");
            statement.executeUpdate("ALTER TABLE CUSTOMER_ORDERS ADD FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public SaveOrderResponse save(Order order) {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER_ORDERS(oderId, amount, vat, customer_id) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDouble(3, order.getVAT());
            preparedStatement.setInt(4, order.getCustomerId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            return SaveOrderResponse.builder().message(e.getMessage()).status(false).build();
        } finally {
            try {
                statement.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return SaveOrderResponse.builder().status(true).message("Successfully Saved Customer Order").build();
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
