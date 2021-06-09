package za.co.anycompany.service;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository = new OrderRepository();

    public Order placeOrder(Order order, int customerId) {
        Customer customer = CustomerRepository.load(customerId);

        if (order.getAmount() == 0) {
            return null;
        }
        if ("UK".equals(customer.getCountry())) {
            order.setVAT(0.2d);
        } else {
            order.setVAT(0);
        }

        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }
}
