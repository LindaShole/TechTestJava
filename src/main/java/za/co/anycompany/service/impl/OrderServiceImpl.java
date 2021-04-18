package za.co.anycompany.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.repository.CountryRepository;
import za.co.anycompany.repository.CustomerRepository;
import za.co.anycompany.repository.OrderRepository;
import za.co.anycompany.service.OrderService;

@Component
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderRepository orderRepository;
	@Autowired
    private CustomerRepository customerRepository;
	@Autowired
    private CountryRepository countryRepository;

    @Override
	public boolean placeOrder(Order order, int customerId)
    {
		log.info("customerRepository count: {}", customerRepository.count());
		log.info("countryRepository count: {}", countryRepository.count());
        Customer customer = customerRepository.findById(customerId).get();
		log.info("customer: {}", customer);
		log.info("customer country: {}", customer.getCountry());
		order.setCustomer(customer);

		if (order.getAmount() == 0)
            return false;

		order.setVAT(customer.getCountry()==null ? 0 : customer.getCountry().getVAT());

		order = orderRepository.save(order);

		log.info("Order placed: {}", order);
        return true;
    }

	@Override
	public Customer getCustomerWithOrders(int customerId) {
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		if (customerOpt.isPresent()) {
			Customer customer = customerOpt.get();
			customer.getOrders();
			log.info("getCustomerWithOrders: {}", customer);
			return customer;
		}
		return null;
	}

	@Override
	public Set<Customer> getAllCustomersWithOrders() {
		Iterable<Customer> customers = customerRepository.findAll();
		Set<Customer> loadedCustomers = new HashSet<Customer>();
		for (Customer customer : customers) {
			customer.getOrders();
			loadedCustomers.add(customer);
			log.info("getAllCustomersWithOrders: {}", customer);
		}
		return loadedCustomers;
	}

}
