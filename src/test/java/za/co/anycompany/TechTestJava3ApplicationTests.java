package za.co.anycompany;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Set;

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
	void testGetAllCustomerWithLinkedOrders() {
		Country country = createCountry("UK", 0.2);
		
		Customer customer = createCustomer("testGetAllCustomerWithLinkedOrders3", country);
		Order order = placeOrder(1, 0.15d, customer);
		Order order2 = placeOrder(2, 0.16d, customer);
		
		Customer customer2 = createCustomer("testGetAllCustomerWithLinkedOrders4", country);
		Order order3 = placeOrder(3, 0.17d, customer2);
		Order order4 = placeOrder(4, 0.18d, customer2);
		
		Set<Customer> fetchedCustomers = orderService.getAllCustomersWithOrders();
		log.info("testGetAllCustomerWithLinkedOrders: fetchedCustomers.size() = {}", fetchedCustomers.size());
		assertTrue(fetchedCustomers.size()>=2);
		for (Customer fetchedCustomer : fetchedCustomers) {
			if (fetchedCustomer.getId()==customer.getId() || fetchedCustomer.getId()==customer2.getId()) {
				assertTrue(fetchedCustomer.getOrders().size()==2);		
			}
		}
	}
	
	@Test
	void testRetrieveCustomerWithLinkedOrders() {
		Country country = createCountry("UK", 0.2);
		
		Customer customer = createCustomer("testRetrieveCustomerWithLinkedOrders1", country);
		Order order = placeOrder(1, 0.15d, customer);
		Order order2 = placeOrder(2, 0.16d, customer);
		
		Customer customer2 = createCustomer("testRetrieveCustomerWithLinkedOrders2", country);
		Order order3 = placeOrder(3, 0.17d, customer2);
		Order order4 = placeOrder(4, 0.18d, customer2);
		
		Customer fetchedCustomer = orderService.getCustomerWithOrders(customer.getId());
		assertTrue(fetchedCustomer.getOrders().size()==2);		
	}
	
	@Test
	void testPlaceOrderUK() {
		Country country = createCountry("UK", 0.2);
		Customer customer = createCustomer("testPlaceOrderUK", country);
		Order order = placeOrder(1, 0.15d, customer);
	}

	@Test
	void testPlaceOrderNoCountry() {
		Customer customer = createCustomer("testPlaceOrderNoCountry", null);
		Order order = placeOrder(1, 0.15d, customer);
	}
	
	@Test
	void testPlaceOrderAmount0() {
		Country country = createCountry("UK", 0.2);
		Customer customer = createCustomer("testPlaceOrderAmount0", country);
		boolean result = orderService.placeOrder(new Order(0, 0.15d), customer.getId());
		assertFalse(result, "Amount 0 failed");
	}
	
	private Country createCountry(String id, double vat) {
		Country country = new Country(id, vat);
		country = countryRepository.save(country);
		log.debug("createCountry done: " + country);
		return country;
	}
	
	private Customer createCustomer(String name, Country country) {
		Customer customer = new Customer(name, country, Calendar.getInstance().getTime());
		customer = customerRepository.save(customer);
		log.debug("createCustomer done: " + customer);
		return customer;
	}
	
	private Order placeOrder(double amount, double VAT, Customer customer) {
		Order order = new Order(amount, VAT);
		boolean result = orderService.placeOrder(order, customer.getId());
		assertTrue(result);
		return order;
	}

}
