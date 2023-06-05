package co.za.anycompany.service;

import java.util.List;

import co.za.anycompany.model.Order;

public interface OrderService {
	boolean placeOrder(Order order, String customerId);
	List<Order> findAllOrders();
	List<Order> findOrdersByCutsomerId(String cutsomerId);
}
