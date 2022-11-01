package com.test.ztest;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.ztest.entity.Customer;
import com.test.ztest.entity.OrdersByCustomer;
import com.test.ztest.service.Services;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ZtestApplicationTests {

	@Autowired
	private Services services;
	
	private static final Logger logger = LoggerFactory.getLogger(ZtestApplicationTests.class);
	
	@BeforeEach
	public void drawALine() {
		logger.info("");
		logger.info("+++++++++++++++++++++");
	}
	
	@Test
	@Order(1)
	void testPlaceAnOrder() {
		logger.info("*** Testing placing an order ***");
		Customer customer = new Customer("First Customer", "India", LocalDate.of(2000, 12, 15));
		OrdersByCustomer order1 = new OrdersByCustomer(650.0d, 900.0d, customer);
		OrdersByCustomer order2 = new OrdersByCustomer(500.0d, 1000.0d, customer);
		Integer customerId = services.addCustomer(customer);
		Long orderId1 = services.addOrderByCustomer(order1);
		Long orderId2 = services.addOrderByCustomer(order2);
		logger.info("Customer ID: "+customerId+" Order ID: "+orderId1+", "+orderId2);
		Assertions.assertTrue(customerId>100 && orderId1>1000 && orderId2>1001);
	}
	
	@Test
	@Order(2)
	void testPlaceAnotherOrder() {
		logger.info("*** Testing placing another order ***");
		Customer customer = new Customer("Second Customer", "US", LocalDate.of(1998, 12, 14));
		OrdersByCustomer order = new OrdersByCustomer(250.0d, 600.0d, customer);
		Integer customerId = services.addCustomer(customer);
		Long orderId = services.addOrderByCustomer(order);
		logger.info("Customer ID: "+customerId+" Order ID: "+orderId);
		Assertions.assertTrue(customerId>101 && orderId>1002);
	}

	@Test
	@Order(3)	
	void testRetrieveAcustomerWithAllOrders() {
		logger.info("*** Testing retrieving all orders for a Customer ***");
		Integer customerId = 101;
		Customer customer = services.fetchACustomer(customerId);
		Set<OrdersByCustomer> orders = customer.getOrders();
		logger.info(customer.toString());
		logger.info(orders.toString());
		Assertions.assertTrue(101 == customer.getCustomerId());
	}
	
	@Test
	@Order(4)
	void testRetrieveAllCustomersWithAllOrders() {
		logger.info("*** Testing retrieving all orders for all Customer ***");
		Map<Customer, Set<OrdersByCustomer>> map = services.fetchAllOrdersForAllCustomers();
		map.entrySet().forEach(c->logger.info(c.toString()));
		Assertions.assertTrue(map.size()>0);
	}

}
