package za.co.anycompany.common;

import java.sql.*;

public class DatabaseHelper {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    public static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE IF NOT EXISTS CUSTOMER (customerId int primary key not null, name varchar, dateOfBirth varchar, country varchar)";
    public static final String CREATE_ORDER_TABLE = "CREATE TABLE IF NOT EXISTS ORDER (orderId int primary key not null, amount number(10,2), vat number (3,1), customerId int, FOREIGN KEY (customerId) REFERENCES Customer(customerId))";

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return dbConnection;
    }

    public static void createTableIfDoNotExist() {
        Connection connection = DatabaseHelper.getDBConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_ORDER_TABLE);

            statement = connection.createStatement();
            statement.executeUpdate(CREATE_CUSTOMER_TABLE);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
   }
}
