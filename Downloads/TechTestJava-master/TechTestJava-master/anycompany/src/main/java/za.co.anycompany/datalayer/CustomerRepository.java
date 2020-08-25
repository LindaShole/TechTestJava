package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.responses.SaveCustomerResponse;
import java.sql.*;


public class CustomerRepository {

	private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static Connection connection = null;
    private static Statement statement = null;

    static{
        connection = getDBConnection();
        statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE CUSTOMER (CUSTOMER_ID int primary key not null, " +
                    "NAME VARCHAR(45), COUNTRY VARCHAR(45), DATE_OF_BIRTH DATE)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Customer load(int customerId) {
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        PreparedStatement prpstmtOrders = null;
        ResultSet resultSet = null;
        Customer customer  =null;
        try {
            prpstmt = con.prepareStatement("select * from CUSTOMER where CUSTOMER_ID = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));


            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return customer;
    }



    public SaveCustomerResponse save(Customer customer) {


        PreparedStatement preparedStatement = null;

        try {
            connection = getDBConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER(CUSTOMER_ID, NAME, " +
                    "COUNTRY, DATE_OF_BIRTH) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setDate(4, new Date(customer.getDateOfBirth().getYear(),customer.getDateOfBirth().getMonth(),
                    customer.getDateOfBirth().getDay()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            return SaveCustomerResponse.builder().status(false).message(e.getLocalizedMessage()).build();

        } finally {
            try {
                statement.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return SaveCustomerResponse.builder().status(false).message(e.getLocalizedMessage()).build();
            }
        }
        return SaveCustomerResponse.builder().status(true).message("Customer saved Successfully").build();
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
