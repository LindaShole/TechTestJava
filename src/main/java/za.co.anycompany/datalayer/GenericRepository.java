package za.co.anycompany.datalayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class GenericRepository {

    private static final Logger LOG = LoggerFactory.getLogger(GenericRepository.class);

    private static final String USERNAME = "investec";
    private static final String PASSWORD = "investec";
    private static final String URL = "jdbc:postgresql://localhost:4040/investec";

    public static Connection getDBConnection() {
        Connection dbConnection = null;

        try {
            dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            LOG.error("Error occur while trying to create connection string : "+e.getMessage(), e);
        }
        return dbConnection;
    }
}
