package za.co.anycompany.datalayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.anycompany.model.CustomerOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderRepository extends GenericRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerOrderRepository.class);

    public static List<CustomerOrder> load(int customerId) {
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;

        List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
        try {
            prpstmt = con.prepareStatement("select customer_order_id, customer_id, order_id from investec.customer_order where customer_id = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setCustomerOrderId(resultSet.getInt("customer_order_id"));
                customerOrder.setOrderId(resultSet.getInt("order_id"));
                customerOrder.setCustomerId(resultSet.getInt("customer_id"));
                customerOrders.add(customerOrder);
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

        LOG.info("customer order info: "+customerOrders);

        return customerOrders;
    }

    public boolean save(CustomerOrder customerOrder) {
        Connection connection = getDBConnection();
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        boolean isSavedSuccessfully = false;

        int returned = 0;
        try{
            PreparedStatement stat;
            ResultSet rs;
            String sql = "SELECT MAX(customer_order_id) AS max_id FROM investec.customer_order";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            if (rs.next()) {
                returned = rs.getInt("max_id") + 1;
            }
        }catch (Exception e){
            LOG.error("Error occur " + e.getMessage(), e);
        }

        try {
            statement = connection.createStatement();
            PreparedStatement prpstmt =  connection.prepareStatement("INSERT INTO investec.customer_order ( customer_order_id, customer_id, order_id ) VALUES(?,?,?)");
            prpstmt.setInt(1, returned);
            prpstmt.setInt(2, customerOrder.getCustomerId());
            prpstmt.setInt(3, customerOrder.getOrderId());
            prpstmt.executeUpdate();

            isSavedSuccessfully = true;

        } catch (SQLException e) {
            LOG.error("Error occur " + e.getMessage(), e);
        } finally {
            try {
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                LOG.error("Error occur " + e.getMessage(), e);
            }
        }
        return isSavedSuccessfully;
    }

}
