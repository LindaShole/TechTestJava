package za.co.anycompany.service;

import org.springframework.stereotype.Component;
import za.co.anycompany.model.Order;

@Component
public interface OrderService {
    public Order placeOrder(Order order, long customerId);
}
