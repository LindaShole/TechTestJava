package za.co.anycompany.service;

import java.io.IOException;
import java.util.List;
import za.co.anycompany.model.Order;

public interface OrderService {
    
    public boolean placeOrder(Order order, int customerId) throws IOException;
    
    public List<Order> getOrdersByCustomerID(int customerId) throws IOException;
}
