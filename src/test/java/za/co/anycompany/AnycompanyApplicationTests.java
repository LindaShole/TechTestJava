package za.co.anycompany;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;


import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Order;


//@SpringBootTest
class AnycompanyApplicationTests {
	private CustomerRepository customerRepository = new CustomerRepository();
	private OrderRepository orderRepository = new OrderRepository();

	// REQ1 - Place an order, linked to a customer
    // REQ2 - Retrieve a customer with their linked order(s)
 	// REQ3 - Load all customers and their linked orders

	@BeforeAll
	static void beforeTesting(){
		System.out.println("Before All The Tests Run");
	}
	@BeforeEach
	void setup(){

	}


	@Test
	public void contextLoads() throws Exception{
	}

	// REQ 1
	@Test
	void orderCanBePlacedForExistingCustomer(){
	//	Customer customer = customerRepository.load(1);
	//	assertThat(customer.getName()).startsWith("Xolis");
	}

	@Test
	void userIsBlocked(){
	//	List<Customer> customers = customerRepository.getAll();
	//	assertThat(customers.size())
	//			.as("Array size is %s ", 0)
	//			.isOne();
	}

	@Test
	public void returnsNullIfTheCustomerDoesNotExist() throws Exception{

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

	@AfterEach
	void cleanup(){

	}

	@AfterAll
	static void afterTesting(){
		System.out.println("After All The Tests Run");
	}

	// assertEquals
	// assertTrue
	// assertNull
	// assertArrayEquals
	// mock(Interafce / class)
}
