package za.co.anycompany.datalayer;

import za.co.anycompany.model.Order;

import java.sql.*;

import static za.co.anycompany.config.PersistenceManager.getDBConnection;

public class OrderRepository {

    private  Connection connection;

    public OrderRepository(){
        this.connection = getDBConnection();
    }

    public OrderRepository(Connection connection){

        //        this.connection = connection;
    }


    // Not being used
    public void save(Order order) {
        Connection connection = getDBConnection();
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE ORDER (oderId int primary key not null, amount number(10,2), vat number (3,1))");
            connection.prepareStatement("INSERT INTO ORDER(oderId, amount, vat) VALUES(?,?,?)");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDouble(3, order.getVat());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                statement.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Place an order, linked to a customer
    public void placeOrder(Order order) {

        try {
            // Insert the order details
            String insertOrderQuery = "INSERT INTO orders (orderId,customerId, amount, product," +
                    "vat,createdAt) VALUES " +
                    "(?,?, ?, ?,?,?)";

            // Prepare the statement for inserting order
            PreparedStatement insertStatement = connection.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getOrderId());
            insertStatement.setInt(2, order.getCustomer().getCustomerId());
            insertStatement.setDouble(3, order.getAmount());
            insertStatement.setString(4, order.getProduct());
            insertStatement.setDouble(5, order.getVat());
            insertStatement.setDate(6, new java.sql.Date(order.getCreatedAt().getTime()));


            // Execute the insert statement
            int affectedRows = insertStatement.executeUpdate();


            if (affectedRows > 0) {
                System.out.println("Order was placed successfully.");
            } else {
                System.out.println("Failed to create customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
