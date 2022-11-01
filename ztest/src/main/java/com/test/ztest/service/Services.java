package com.test.ztest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.ztest.entity.Customer;
import com.test.ztest.entity.OrdersByCustomer;
import com.test.ztest.repository.CustomerRepository;
import com.test.ztest.repository.OrderRepository;

@Service
public class Services {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private OrderRepository orderRepo;

	public Integer addCustomer(Customer customer) {
		Integer customerId = 0;
		try {
			customerId = customerRepo.save(customer).getCustomerId();
		}catch(Exception ex) {
			ex.getMessage();
		}
		return customerId;	
	}

	public Long addOrderByCustomer(OrdersByCustomer order) {
		Long orderId = 0l;
		try {
			orderId = orderRepo.save(order).getOrderId();
		}catch(Exception ex) {
			ex.getMessage();
		}
		return orderId;
	}

	public Customer fetchACustomer(Integer customerId) {
		Customer customer = null; 
		try {
			customer = customerRepo.findById(customerId).get();
		}catch(Exception ex) {
			ex.getMessage();
		}
		return customer;
	}

	public Set<OrdersByCustomer> fetchAllOrdersForACustomer(Integer customerId){
		Set<OrdersByCustomer> orders = null;
		try {
			Customer customer = fetchACustomer(customerId);
			if(null != customer) {
				orders = customer.getOrders();
			}
		}catch(Exception ex) {
			ex.getMessage();
		}
		return orders;
	}

	public OrdersByCustomer fetchAnOrder(Long orderId) {
		OrdersByCustomer order = null;
		try {
			order = orderRepo.findById(orderId).get();
		}catch(Exception ex) {
			ex.getMessage();
		}
		return order;
	}
	
	public Map<Customer, Set<OrdersByCustomer>> fetchAllOrdersForAllCustomers(){
		Map<Customer, Set<OrdersByCustomer>> map = new HashMap<Customer, Set<OrdersByCustomer>>();
		try {
			List<Customer> list = customerRepo.findAll();
			if(null != list) {
				list.forEach(c->map.put(c, c.getOrders()));
			}
		}catch(Exception ex) {
			ex.getMessage();
		}
		return map;
	}
}
