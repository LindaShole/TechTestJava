package za.co.anycompany.service;

import java.util.Set;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public interface OrderService {

	boolean placeOrder(Order order, int customerId);
	Customer getCustomerWithOrders(int customerId);
	Set<Customer> getAllCustomersWithOrders();
}