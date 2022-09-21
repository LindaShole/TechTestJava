package za.co.anycompany.persistence.mapper;

import java.util.List;
import za.co.anycompany.model.Order;

/**
 *
 * @author v-nchatitai
 */
public interface OrderMapper {

    public void save(Order order);

    public List<Order> getOrders();

    public List<Order> getOrdersByCustomerId(int customerId);
}
