package za.co.anycompany.service;

import za.co.anycompany.model.Order;

public interface OrderService {

	boolean placeOrder(Order order, int customerId);

}