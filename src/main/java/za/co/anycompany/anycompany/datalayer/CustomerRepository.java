package za.co.anycompany.anycompany.datalayer;


import org.springframework.data.repository.CrudRepository;
import za.co.anycompany.anycompany.model.Customer;

import java.sql.*;
import java.util.Optional;


public class CustomerRepository implements CrudRepository<Customer, Integer> {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    //  private static final String DB_CONNECTION = "jdbc:h2:~\\Azure\\Java_DEV\\TechTestJava\\src\\main\\resources\\testdb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static Customer load(int customerId) {
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Statement statement = null;
        Customer customer = new Customer();
        try {
            statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMER(CUSTOMERID INT PRIMARY KEY NOT NULL, CUSTOMER_NAME VARCHAR(50), COUNTRY VARCHAR(50), DATE_OF_BIRTH DATE);");
            con.prepareStatement("INSERT INTO CUSTOMER (CUSTOMERID, CUSTOMER_NAME, COUNTRY, DATE_OF_BIRTH) VALUES (1,'Xolisani','South Africa', '2002-11-15');");
            prpstmt = con.prepareStatement("select * from CUSTOMER where customerId = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
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

    @Override
    public <S extends Customer> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Customer> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Customer> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Customer> findAll() {
        return null;
    }

    @Override
    public Iterable<Customer> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Customer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Customer> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
