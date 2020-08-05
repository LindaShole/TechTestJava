package za.co.anycompany.datalayer;

import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private static Connection connection = DatalayerConfig.getDBConnection();
    public void save(Order order) throws SQLException {
        PreparedStatement insertPreparedStatement = null;

        String InsertQuery = "INSERT INTO ORDERS(oderId, customerId, amount, vat) VALUES(?,?,?,?)";

        try {
            connection.setAutoCommit(false);
            insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setInt(1, order.getOrderId());
            insertPreparedStatement.setInt(2, order.getCustomerId());
            insertPreparedStatement.setDouble(3, order.getAmount());
            insertPreparedStatement.setDouble(4, order.getVAT());
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public List<Order> findOrdersByOrderId(int customerId) throws SQLException {
        PreparedStatement selectPreparedStatement = null;
        String SelectQuery = "select * from ORDERS";
        List<Order> orders = new ArrayList<>();
        try {
            selectPreparedStatement = connection.prepareStatement(SelectQuery);
            assert selectPreparedStatement != null;
            ResultSet rs = selectPreparedStatement.executeQuery();
            Order order = new Order();
            while (rs.next()) {
                if(rs.getInt("customerId") == customerId) {
                    order.setOrderId(rs.getInt("oderId"));
                    order.setCustomerId(rs.getInt("customerId"));
                    order.setAmount(rs.getInt("amount"));
                    order.setVAT(rs.getInt("vat"));
                    orders.add(order);
                    order = new Order();
                }
            }
            selectPreparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            connection.close();
        }
        return orders;
    }
}
