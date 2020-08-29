package za.co.anycompany;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.repository.CustomerRepository;
import za.co.anycompany.repository.OrderRepository;
import za.co.anycompany.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootConfiguration
@SpringBootTest(classes = Application.class)
class ApplicationTests {

	@Autowired
	private OrderService orderService;
	@MockBean
	CustomerRepository customerRepository;

	@MockBean
	OrderRepository orderRepository;


	@Test
	void retrieveCustomerOrderTest() {

		Customer customer = new Customer();
		Order orderNumberOne = new Order();
		Order orderNumberTwo = new Order();

		List<Customer> cusList = new ArrayList<>();
		List<Order> OrdList = new ArrayList<>();

		customer.setCustomerId(1);
		customer.setCountry("America");
		customer.setName("Lufuno");

		customer.setDateOfBirth(new Date());
		orderNumberOne.setAmount(22.03);
		orderNumberOne.setOrderId(1);
		orderNumberOne.setVat(0.14);

		orderNumberTwo.setOrderId(2);
		orderNumberTwo.setVat(0.15);
		orderNumberTwo.setAmount(20.5);

		OrdList.add(orderNumberOne);
		OrdList.add(orderNumberTwo);
		cusList.add(customer);

		when(customerRepository.findAll()).thenReturn((cusList));

		assertEquals(1, orderService.loadCustomerOrders().size());


	}

}
