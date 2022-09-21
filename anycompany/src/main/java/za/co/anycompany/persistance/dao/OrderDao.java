package za.co.anycompany.persistance.dao;

import java.io.IOException;
import java.util.List;
import za.co.anycompany.model.Order;

/**
 *
 * @author v-nchatitai
 */
public interface OrderDao {
    
    public List<Order> getOrders() throws IOException;
    public void insertNewOrder(Order order) throws IOException;
    public List<Order> getOrdersByCustomerId(int customerId) throws IOException;
}
