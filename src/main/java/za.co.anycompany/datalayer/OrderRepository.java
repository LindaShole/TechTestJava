package za.co.anycompany.datalayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.anycompany.model.Order;

import java.sql.*;

public class OrderRepository extends GenericRepository {

    private static final Logger LOG = LoggerFactory.getLogger(OrderRepository.class);

    public int save(Order order) {
        Connection connection = getDBConnection();
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        int orderId = 0;

        try{
            PreparedStatement stat;
            ResultSet rs;
            String sql = "SELECT MAX(order_id) AS max_id FROM investec.order";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            if (rs.next()) {
                orderId = rs.getInt("max_id") + 1;
            }
        }catch (Exception e){
            LOG.error("Error occur " + e.getMessage(), e);
        }

        try {
            statement = connection.createStatement();
            PreparedStatement prpstmt = connection.prepareStatement("INSERT INTO investec.order ( order_id, amount, vat) VALUES(?,?,?)");
            prpstmt.setInt(1, orderId);
            prpstmt.setDouble(2, order.getAmount());
            prpstmt.setDouble(3, order.getVAT());
            prpstmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Error occur " + e.getMessage(), e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                LOG.error("Error occur " + e.getMessage(), e);
            }
        }

        LOG.info("saved order id : "+orderId);

        return orderId;
    }


    public static Order load(int orderId) {

        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Order order = new Order();

        try {
            prpstmt = con.prepareStatement("select order_id, amount, vat from investec.order where order_id = ?");
            prpstmt.setInt(1, orderId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                order.setOrderId(resultSet.getInt("order_id"));
                order.setAmount(resultSet.getDouble("amount"));
                order.setVAT(resultSet.getDouble("vat"));
            }

        } catch (SQLException e) {
            LOG.error("Error occur " + e.getMessage(), e);
        } finally {
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOG.error("Error occur " + e.getMessage(), e);
            }
        }

        LOG.info("Order info : "+order);

        return order;
    }
}
