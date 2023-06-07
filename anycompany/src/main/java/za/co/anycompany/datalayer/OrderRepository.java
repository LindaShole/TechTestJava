package za.co.anycompany.datalayer;

import za.co.anycompany.common.DatabaseManager;
import za.co.anycompany.common.exception.NullConnectionException;
import za.co.anycompany.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>Title: OrderRepository</p>
 * <p/>
 * <p>Description: This class is responsible for persisting order information linked to a customer/</p>
 * <p/>
 * <p>Company: AnyCompany</p>
 *
 * @author Chizeba Maulu
 * @version 1.0
 */
public class OrderRepository {

    /**
     * Persist the customers order into the database
     *
     * @param order      the order
     * @param customerId the customers id
     **/
    public void save(Order order, int customerId) throws NullConnectionException, SQLException {
        Connection connection = DatabaseManager.getInstance().getDBConnection();

        if (connection == null) {
            throw new NullConnectionException("Could not establish database connection");
        }

        try (connection; PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer_order(order_id, amount, vat, customer_id) VALUES(?,?,?,?)")) {
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDouble(3, order.getVat());
            preparedStatement.setInt(3, customerId);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
