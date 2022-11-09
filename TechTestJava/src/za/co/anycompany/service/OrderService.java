package za.co.anycompany.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);
        if (customer == null) {
    		System.out.println("Customer Doesn't exist with id "+customerId);
    		return false;    	
	    }
        if (order.getAmount() == 0)
            return false;

        if (customer.getCountry() == "UK")
            order.setVAT(0.2d);
        else
            order.setVAT(0);

        order.setCustomerId(customerId);
        orderRepository.save(order);
        
        return true;
    }
    
    public Customer retrieveCustomerById(int customerId) {
    	Customer customer = CustomerRepository.load(customerId);
    	if (customer == null) {
    		System.out.println("Customer Doesn't exist with id "+customerId);
    		return customer;    	
	    }
    	OrderRepository orderRepository = new OrderRepository();
    	List<Order> ordersList = orderRepository.load(customerId);
    	customer.setOrders(ordersList);
    	return customer;
    	
    }
    
    public List<Customer> retrieveAllCustomerWithOrders() {
    	List<Customer> customers = CustomerRepository.load();
    	
    	if(!customers.isEmpty()) {
    		OrderRepository orderRepository = new OrderRepository();
    		for (Customer cust : customers) {
    			List<Order> orders = orderRepository.load(cust.getId());
    			cust.setOrders(orders);
    		}
    	}
    	return customers;
    	
    }
}
