package za.co.anycompany.service.impl;

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

		if (order.getAmount() == 0)
            return false;

		order.setVAT(customer.getCountry()==null ? 0 : customer.getCountry().getVAT());

		order = orderRepository.save(order);

		log.info("Order placed: {}", order);
        return true;
    }
}
