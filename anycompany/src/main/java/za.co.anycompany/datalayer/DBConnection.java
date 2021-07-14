package main.java.za.co.anycompany.datalayer;

import main.java.za.co.anycompany.framework.ServicesProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(ServicesProperties.getDbDriver());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(ServicesProperties.getDbConnection(), ServicesProperties.getDbUser(), ServicesProperties.getDbPassword());
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

//    public static void closeConnection(Connection connection) {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}
