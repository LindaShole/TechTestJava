package za.co.anycompany;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Order;

//import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

//@SpringBootTest
class AnycompanyApplicationTests {
	private CustomerRepository customerRepository = new CustomerRepository();
	private OrderRepository orderRepository = new OrderRepository();
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
