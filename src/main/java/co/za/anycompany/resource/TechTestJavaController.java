package co.za.anycompany.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.za.anycompany.model.Customer;
import co.za.anycompany.model.Order;
import co.za.anycompany.service.CustomerService;
import co.za.anycompany.service.OrderService;
import io.swagger.annotations.ApiOperation;

/** @author SiBhengu*/

@RestController
@RequestMapping("/anycompany")
public class TechTestJavaController {
	
	private static final Logger log = LoggerFactory.getLogger(TechTestJavaController.class);
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderService;
	
	@ApiOperation(value = "Ping", notes = "Ping", response = String.class, tags = "Anycompany Entertainment")
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String ping() {
		log.info("Ping request: {}", "PONG");
		return "PONG!!!!";
	}
	
	@ApiOperation(value = "Create customer", notes = "Creates customer and returns customer object with customerId for placingOrder", response = Customer.class, tags = "Anycompany Entertainment")
	@RequestMapping(value = "/createCustomer", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	ResponseEntity<?> createCustomer(@RequestBody Customer createCustomerRequest) throws Exception {

		Customer createCustomerResponse = customerService.createCustomer(createCustomerRequest);

		log.info("Create customer response", createCustomerResponse);
		return new ResponseEntity<>(createCustomerResponse, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Place order", notes = "Place order, linked to a customer", response = Order.class, tags = "Anycompany Entertainment")
	@RequestMapping(value = "/placeOrder/{customerId}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	ResponseEntity<?> placeOrder(@RequestBody Order orderRequest, @PathVariable(value = "customerId") String customerId) throws Exception {

		Boolean placeOrderResponse = orderService.placeOrder(orderRequest, customerId);

		log.info("Place order response", placeOrderResponse);
		return new ResponseEntity<>(placeOrderResponse, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get all orders", notes = "Returns all customers with their linked order(s)", response = Order.class, tags = "Anycompany Entertainment")
	@RequestMapping(value = "/getAllOrders", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	ResponseEntity<?> getAllOrders() throws Exception {

		List<Order> getOrders = orderService.findAllOrders();

		log.info("Get all orders response", getOrders);
		return new ResponseEntity<>(getOrders, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get customer orders", notes = "Get customer with their linked order(s)", response = Order.class, tags = "Anycompany Entertainment")
	@RequestMapping(value = "/getCustomerOrders/{customerId}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	ResponseEntity<?> getOrder(@PathVariable(value = "customerId") String customerId) throws Exception {

		List<Order> getOrders = orderService.findOrdersByCutsomerId(customerId);

		log.info("Get customer orders response", getOrders);
		return new ResponseEntity<>(getOrders, HttpStatus.OK);
	}

}
