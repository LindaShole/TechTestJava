package util;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConnection.class);
    
    private static String databaseDriver = ResourceLoader.configs().get("DB_DRIVER");
    private static String url = ResourceLoader.configs().get("DB_CONNECTION");
    private static String user = ResourceLoader.configs().get("DB_USER");
    private static String pass = ResourceLoader.configs().get("DB_PASSWORD");

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(databaseDriver);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
        	LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }

}
