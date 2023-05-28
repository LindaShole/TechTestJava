package za.co.anycompany.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PersistenceManager {

//  private static final Logger LOG = LoggerFactory.getLogger(PersistenceManager.class);
  private static final String DB_DRIVER = "org.h2.Driver";
  private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
  private static final String DB_USER = "";
  private static final String DB_PASSWORD = "";


  public static Connection getDBConnection() {
    Connection dbConnection = null;
    try {
      Class.forName(DB_DRIVER);
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    try {
      dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
      System.out.println("Connection established");
      return dbConnection;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    System.out.println("Connection established");
    return dbConnection;
  }


  public static void createTables(Connection connection){
    customerTable(connection);
    orderTable(connection);

  }

  private static void customerTable(Connection conn){

    try{
    Statement stmt = conn.createStatement();
      String query = "CREATE TABLE CUSTOMER " +
              "(customerId  INTEGER not NULL, " +
              " name VARCHAR(255), " +
              " country VARCHAR(255), " +
              "  dateOfBirth DATE, " +
              " PRIMARY KEY (customerId ))";

      stmt.executeUpdate(query);
      System.out.println("Created customer table in given database...");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  private static void orderTable(Connection conn){
    try{

      Statement stmt = conn.createStatement();
      String query = "CREATE TABLE orders " +
              "(orderId  INTEGER not NULL, " +
              " customerId INTEGER, " +
              " amount DECIMAL(10, 2), " +
              " product VARCHAR(255), " +
              " vat DECIMAL(10, 2), " +
              " createdAt DATE, " +
              " PRIMARY KEY (orderId), " +
              "  FOREIGN KEY (customerId) REFERENCES CUSTOMER(customerId))";

      stmt.executeUpdate(query);
      System.out.println("Created orders table in given database...");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
