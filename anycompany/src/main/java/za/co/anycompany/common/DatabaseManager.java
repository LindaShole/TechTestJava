package za.co.anycompany.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * <p>Title: DatabaseManager</p>
 *
 * <p>Description:  DatabaseManager, responsible for establishing connections with the DB and creating the necessary tables
 * if they do not exist</p>
 *
 * <p>Company: AnyCompany</p>
 *
 * @author Chizeba Maulu
 */
public class DatabaseManager {
    private static final Logger LOG = Logger.getLogger(DatabaseManager.class.getName());
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String SYSTEM_PROP_CONNECTION = "database.connection";
    private static final String SYSTEM_PROP_PASSWORD = "database.password";
    private static final String SYSTEM_PROP_USER = "database.user";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    /**
     * Get instance
     *
     * @return the instance
     **/
    public static DatabaseManager getInstance() {
        if (System.getProperty(SYSTEM_PROP_USER) == null) {
            System.setProperty(SYSTEM_PROP_USER, DB_USER);
        }

        if (System.getProperty(SYSTEM_PROP_PASSWORD) == null) {
            System.setProperty(SYSTEM_PROP_PASSWORD, DB_PASSWORD);
        }

        if (System.getProperty(SYSTEM_PROP_CONNECTION) == null) {
            System.setProperty(SYSTEM_PROP_CONNECTION, DB_CONNECTION);
        }
        return new DatabaseManager();
    }

    /**
     * Establish a database connection
     *
     * @return the db connection
     **/
    public Connection getDBConnection() {
        try {
            String user = System.getProperty(SYSTEM_PROP_USER);
            String password = System.getProperty(SYSTEM_PROP_PASSWORD);
            String dbConnection = System.getProperty(SYSTEM_PROP_CONNECTION);
            return DriverManager.getConnection(dbConnection, user, password);
        }
        catch (SQLException e) {
            LOG.warning(e.getMessage());
        }
        return null;
    }

    /**
     * Create order table if it does not already exist
     **/
    public void createOrderTableIfNotExist() {
        Connection connection = this.getDBConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS customer_order (order_id INT PRIMARY KEY NOT NULL, amount NUMBER(10,2), vat NUMBER(3,1), customer_id INT FOREIGN KEY REFERENCES customer(customer_id))");
        }
        catch (SQLException e) {
            LOG.warning(e.getMessage());
        }
        finally {
            handleConnection(connection, statement);
        }
    }

    /**
     * Create customer table if it does not already exist
     **/
    public void createCustomerTableIfNotExist() {
        Connection connection = this.getDBConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS customer (customer_id int primary key not null, full_name varchar, date_of_birth date, country varchar)");
        }
        catch (SQLException e) {
            LOG.warning(e.getMessage());
        }
        finally {
            handleConnection(connection, statement);
        }
    }

    /**
     * Handle connection
     *
     * @param connection the connection
     * @param statement  the statement
     **/
    private void handleConnection(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException e) {
            LOG.warning(e.getMessage());
        }
    }
}
