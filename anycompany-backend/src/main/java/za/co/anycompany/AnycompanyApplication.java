package za.co.anycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import za.co.anycompany.datalayer.DatalayerConfig;
import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class AnycompanyApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnycompanyApplication.class, args);
	}
}

@Component
class AppInitializator {

	private static Connection connection = DatalayerConfig.getDBConnection();

	@PostConstruct
	private void init() throws SQLException {
		PreparedStatement customerCreatePreparedStatement = null;
		String customerCreateQuery = ("CREATE TABLE CUSTOMER (customerId int primary key not null, name varchar(255), country varchar(255), dateOfBirth date)");
		connection.setAutoCommit(false);
		customerCreatePreparedStatement = connection.prepareStatement(customerCreateQuery);
		customerCreatePreparedStatement.executeUpdate();
		customerCreatePreparedStatement.close();
		connection.commit();

		PreparedStatement orderCreatePreparedStatement = null;
		String orderCreateQuery = "CREATE TABLE ORDERS(oderId int NOT NULL, customerId int, amount numeric(10, 2), vat numeric(3, 1), primary key (oderId));";
		connection.setAutoCommit(false);
		orderCreatePreparedStatement = connection.prepareStatement(orderCreateQuery);
		orderCreatePreparedStatement.executeUpdate();
		orderCreatePreparedStatement.close();
		connection.commit();
	}
}
