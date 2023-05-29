package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.repository.CustomerRepository;
import za.co.anycompany.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public boolean placeOrder(Order order, Long customerId) {

        Customer customer = customerRepository.findById(customerId).get();

        if (order.getAmount() == 0) {
            return false;
        }

        if (customer.getCountry() == "UK") {
            order.setVAT(0.2d);
        } else {
            order.setVAT(0);
        }

        orderRepository.save(order);

        return true;
    }


}
