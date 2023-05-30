package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.repository.CustomerRepository;
import za.co.anycompany.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public boolean placeOrder(Order order, Long customerId) {

        Customer customer = null;

        if (customerRepository.findById(customerId).isPresent()) {
            customer = customerRepository.findById(customerId).get();
        } else {
            throw new RuntimeException("Customer not present");
        }

        if (!isOrderValid(order)) {
            return false;
        }

        if (customer.getCountry().equalsIgnoreCase("UK")) {
            order.setVAT(0.2d);
        } else {
            order.setVAT(0);
        }

        orderRepository.save(order);

        return true;
    }


    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    private boolean isOrderValid(Order order) {
        return !(order.getVAT() <= 0 || order.getAmount() <= 0);
    }
}
