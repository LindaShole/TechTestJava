package za.co.anycompany.datalayer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


}

//import java.sql.*;
//
//
//public class CustomerRepository {
//
//    private static final String DB_DRIVER = "org.h2.Driver";
//    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
//    private static final String DB_USER = "";
//    private static final String DB_PASSWORD = "";
//    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
//
//    public static Customer load(int customerId) {
//        Connection con = getDBConnection();
//        PreparedStatement prpstmt = null;
//        ResultSet resultSet = null;
//        Customer customer = new Customer();
//        try {
//            prpstmt = con.prepareStatement("select * from CUSTOMER where customerId = ?");
//            prpstmt.setInt(1, customerId);
//            resultSet = prpstmt.executeQuery();
//            while (resultSet.next()) {
//                customer.setName(resultSet.getString("NAME"));
//                customer.setCountry(resultSet.getString("COUNTRY"));
//                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
//            }
//
//        } catch (SQLException e) {
//            logger.error(String.format("Failed SQL Result  %s ",  e.getMessage()));
//        } finally {
//            try {
//                if (prpstmt != null)
//                    prpstmt.close();
//                if (resultSet != null)
//                    resultSet.close();
//                if (con != null)
//                    con.close();
//            } catch (SQLException e) {
//                logger.error(String.format("Failed SQL Result  %s ",  e.getMessage()));
//            }
//        }
//        return customer;
//    }
//
//
//    private static Connection getDBConnection() {
//        Connection dbConnection = null;
//        try {
//            Class.forName(DB_DRIVER);
//        } catch (ClassNotFoundException e) {
//            logger.error(String.format("Failed to get the DB DRIVER  %s ",  e.getMessage()));
//        }
//        try {
//            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
//            return dbConnection;
//        } catch (SQLException e) {
//            logger.error(String.format("Failed SQL Result  %s ",  e.getMessage()));
//        }
//        return dbConnection;
//    }
//
//}
