package za.co.anycompany.datalayer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Order;

import java.sql.*;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);

}
//public class OrderRepository {
//
//    private static final String DB_DRIVER = "org.h2.Driver";
//    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
//    private static final String DB_USER = "";
//    private static final String DB_PASSWORD = "";
//    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
//
//    public void save(Order order) {
//        Connection connection = getDBConnection();
//        Statement statement = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            statement = connection.createStatement();
//            statement.executeUpdate("CREATE TABLE ORDER (oderId int primary key not null, amount number(10,2), vat number (3,1))");
//            connection.prepareStatement("INSERT INTO ORDER(oderId, amount, vat) VALUES(?,?,?)");
//            preparedStatement.setInt(1, order.getOrderId());
//            preparedStatement.setDouble(2, order.getAmount());
//            preparedStatement.setDouble(3, order.getVAT());
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            logger.error(String.format("Failed SQL Result  %s ",  e.getMessage()));
//        } finally {
//            try {
//                statement.close();
//                preparedStatement.close();
//                connection.close();
//            } catch (SQLException e) {
//                logger.error(String.format("Failed SQL Result  %s ", e.getMessage()));
//
//            }
//        }
//    }
//
//    private static Connection getDBConnection() {
//        Connection dbConnection = null;
//        try {
//            Class.forName(DB_DRIVER);
//        } catch (ClassNotFoundException e) {
//            logger.error(String.format("Failed to get the DB Driver name  %s ",  e.getMessage()));
//        }
//        try {
//            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
//            return dbConnection;
//        } catch (SQLException e) {
//            logger.error(String.format("Failed to connect to the DB %s ",  e.getMessage()));
//        }
//        return dbConnection;
//    }
//}
