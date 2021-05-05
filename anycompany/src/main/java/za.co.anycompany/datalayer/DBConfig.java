package za.co.anycompany.datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConfig {

    private static final String DB_DRIVER = System.getProperty("DB_DRIVER","org.h2.Driver");
    private static final String DB_CONNECTION = System.getProperty("DB_CONNECTION","jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    private static final String DB_USER = System.getProperty("DB_USER", "");
    private static final String DB_PASSWORD = System.getProperty("DB_PASSWORD","");

    private static final Logger logger = Logger.getLogger(String.valueOf(DBConfig.class));


    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.ALL,e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.ALL,e.getMessage());
        }
        return dbConnection;
    }
}
