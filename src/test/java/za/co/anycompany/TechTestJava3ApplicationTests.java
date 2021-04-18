package za.co.anycompany;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import za.co.anycompany.model.Country;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.repository.CountryRepository;
import za.co.anycompany.repository.CustomerRepository;
import za.co.anycompany.repository.OrderRepository;
import za.co.anycompany.service.OrderService;

@SpringBootTest
@Slf4j
class TechTestJava3ApplicationTests {
	@Autowired
    private OrderRepository orderRepository;
	@Autowired
    private CustomerRepository customerRepository;
	@Autowired
    private CountryRepository countryRepository;
	@Autowired
	private OrderService orderService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void testUKOrder() {
		Country country = new Country("UK", 0.2);
		countryRepository.save(country);
		Customer customer = new Customer(null, "One", country, Calendar.getInstance().getTime());
		customer = customerRepository.save(customer);
		log.info("CustomerRepo save: " + customer);		
		boolean result = orderService.placeOrder(new Order(null, 1, 0.15d), customer.getCustomerId());
		assertTrue(result, "UK Order Successful");
	}
	
	@Test
	void testNoCountryOrder() {
		Customer customer = new Customer(null, "One", null, Calendar.getInstance().getTime());
		customer = customerRepository.save(customer);
		log.info("CustomerRepo save: " + customer);		
		boolean result = orderService.placeOrder(new Order(null, 1, 0.15d), customer.getCustomerId());
		assertTrue(result, "No Country Order Successful");
	}
	
	@Test
	void testAmount0() {
		Customer customer = new Customer(null, "One", null, Calendar.getInstance().getTime());
		customer = customerRepository.save(customer);
		log.info("CustomerRepo save: " + customer);		
		boolean result = orderService.placeOrder(new Order(null, 0, 0.15d), customer.getCustomerId());
		assertFalse(result, "Amount 0 failed");
	}

}
