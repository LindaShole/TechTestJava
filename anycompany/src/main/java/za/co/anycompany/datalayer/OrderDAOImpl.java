package za.co.anycompany.datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class OrderDAOImpl implements OrderDAO {
    
    // System wouldn't take ORDER as table name hence CUSTOMER_ORDER
    private static final String name = "CUSTOMER_ORDER";
    // Added customerId foreign key
    // Update the casing to what seems to be upper case standard
    private static final String CREATE_ORDER_SCHEMA = "CREATE TABLE CUSTOMER_ORDER (ORDERID INT PRIMARY KEY NOT NULL, AMOUNT NUMERIC(10,2), VAT NUMERIC(3,1), CUSTOMERID INT NOT NULL, FOREIGN KEY (CUSTOMERID) REFERENCES CUSTOMER(CUSTOMERID))";

    private final DatabaseFactory database;

    public OrderDAOImpl() {
        database = DatabaseFactoryImpl.getInstance();        
    }

    @Override
    public void create(Order order, Customer customer) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = database.getConnection();
            
            preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER_ORDER(ORDERID, CUSTOMERID, AMOUNT, VAT) VALUES(?,?,?, ?)");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, customer.getCustomerId());
            preparedStatement.setDouble(3, order.getAmount());
            preparedStatement.setDouble(4, order.getVAT());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    public void setupOrderSchema() throws SQLException, ClassNotFoundException{
        try {
            if(!database.schemaExist(name)){
                database.createSchema(CREATE_ORDER_SCHEMA);
            }
        }
        catch(SQLException | ClassNotFoundException e) {
            throw e;
        }
    }
}
