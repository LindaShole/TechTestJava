package co.za.anycompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.za.anycompany.model.Customer;
import co.za.anycompany.model.Order;
import co.za.anycompany.repository.CustomersRepository;
import co.za.anycompany.repository.OrdersRepository;

/**
 * @author SiBhengu Provides data persistence to the db.
 */

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrdersRepository orderRepository;
	
	@Autowired
	CustomersRepository customerRepository;

	@Override
	public boolean placeOrder(Order customerOrder, String customerId) {

		if (customerOrder.getAmount() == 0) return false;

		Customer customer = customerRepository.findCustomerByCutsomerId(customerId);

		Order order = new Order();

		if (customer != null) {

			order.setOrderId(customer.getCutsomerId());

			if (customer.getCountry().equalsIgnoreCase("UK")) {
				order.setVAT(0.2d);
			} else {
				order.setVAT(0);
			}

			order.setAmount(customerOrder.getAmount());

			orderRepository.save(order);

			return true;

		}

		return false;
	}

	@Override
	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> findOrdersByCutsomerId(String cutsomerId) {
		return orderRepository.findOrdersByCutsomerId(cutsomerId);
	}
    
}
