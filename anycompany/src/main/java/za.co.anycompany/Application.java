package za.co.anycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.co.anycompany.datalayer.DBConfig;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class Application {

    private static final Logger logger = Logger.getLogger(String.valueOf(Application.class));

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void setUpENV()
    {
        System.setProperty("DB_CONNECTION","jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        System.setProperty("DB_DRIVER","org.h2.Driver");
        System.setProperty("DB_USER", "");
        System.setProperty("DB_PASSWORD","");

        Connection connection = DBConfig.getDBConnection();

        try {
            PreparedStatement customerCreatePreparedStatement = null;
            String customerCreateQuery = ("CREATE TABLE CUSTOMER (customerId int auto_increment primary key not null, name varchar(255), country varchar(255), dateOfBirth date)");
            connection.setAutoCommit(false);
            customerCreatePreparedStatement = connection.prepareStatement(customerCreateQuery);
            customerCreatePreparedStatement.executeUpdate();
            customerCreatePreparedStatement.close();
            connection.commit();

            PreparedStatement orderCreatePreparedStatement = null;
            String orderCreateQuery = "CREATE TABLE ORDERS(orderId int auto_increment primary key not null, customerId int, item varchar(255), amount numeric(10, 2), vat numeric(3, 1), CONSTRAINT FK_ORDERS_CUSTOMER FOREIGN KEY (customerId) REFERENCES CUSTOMER(customerId))";
            connection.setAutoCommit(false);
            orderCreatePreparedStatement = connection.prepareStatement(orderCreateQuery);
            orderCreatePreparedStatement.executeUpdate();
            orderCreatePreparedStatement.close();
            connection.commit();
        }
        catch (Exception e)
        {
            logger.log(Level.ALL,e.getMessage());

        }
    }

}
