package za.co.anycompany.service;

import za.co.anycompany.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void createOrder(final Order order);
    void updateOrder(final Order order);

    void deleteOrder(final Order order);
    void deleteOrder(final int orderId);

    Optional<Order> getOrderById(final int orderId);
    List<Order> getAllOrders();


}
