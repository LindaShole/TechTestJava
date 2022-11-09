import java.util.Arrays;
import java.util.List;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;

public class Main {

	public static void main(String[] args) {
		
		OrderService os = new OrderService();
		Order order = new Order();
		order.setId(1109);
		order.setAmount(1556.00);
		order.setCustomerId(1);
		
		//task#1
		System.out.println("Task 1 : Place an order and link to a customer");
		boolean success = os.placeOrder(order, 1);
		if (success){
		System.out.println(" order successful and linked to a customer");		
		}else{
			System.out.println("unsuccessful oder");		
		}
		//task#2
		System.out.println("Task 2 : Retrieve a customer with their linked order(s)");
		Customer customer = os.retrieveCustomerById(1);
		System.out.println(customer);
		
		//task#3
		System.out.println("Task 3 : Load all customers and their linked orders");
		List<Customer> customers = os.retrieveAllCustomerWithOrders();
		System.out.println(customers);
		
		
	}

}
