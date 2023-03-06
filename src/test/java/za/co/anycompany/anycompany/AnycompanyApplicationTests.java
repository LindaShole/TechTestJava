package za.co.anycompany.anycompany;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.anycompany.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.anycompany.datalayer.OrderRepository;
import za.co.anycompany.anycompany.model.Customer;
import za.co.anycompany.anycompany.model.Order;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

//@SpringBootTest
class AnycompanyApplicationTests {
	private CustomerRepository customerRepository = new CustomerRepository();
	private OrderRepository orderRepository = new OrderRepository();

	// REQ1 - Place an order, linked to a customer
    // REQ2 - Retrieve a customer with their linked order(s)
 	// REQ3 - Load all customers and their linked orders
	@Test
	public void contextLoads() throws Exception{
	}

	@Test
	public void returnsNullIfTheCustomerDoesNotExist() throws Exception{
/*

		Customer results = customerRepository.load(1);

		 assertThat(results.getCountry(), is("Xolisani") );
*/
	}


	@Test
	public void returnsNullIfTheOrderDoesNotExist() throws Exception{

		Order order = new Order();
		order.setVAT(2.0);
		order.setOrderId(2);
		order.setAmount(450.23);
/*
		orderRepository.save(order);

		assertThat(order.getAmount(), is(450.23) );*/
	}
}
