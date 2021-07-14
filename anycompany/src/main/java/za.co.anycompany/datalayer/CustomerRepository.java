package main.java.za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;

import java.sql.*;


public class CustomerRepository {

    public static Customer load(int customerId) {
        Connection con = DBConnection.getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Customer customer = null;
        try {
            prpstmt = con.prepareStatement("select * from CUSTOMER where customerId = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            customer = new Customer();
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

    public static Boolean create(Customer customer){
        Connection connection = DBConnection.getDBConnection();
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMER(customerId int primary key not null, NAME varchar(250), COUNTRY varchar(250), DATE_OF_BIRTH date)");
            preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER(customerId, NAME, COUNTRY, DATE_OF_BIRTH) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setDate(4, (Date) customer.getDateOfBirth());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                statement.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }

        return true;
    }

}
