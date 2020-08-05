package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = customerRepository.load(customerId);

        if (order.getAmount() == 0)
            return false;

        if ("UK".equals(customer.getCountry()))
            order.setVAT(0.2d);
        else
            order.setVAT(0);

        order.setCustomer(customer);
        orderRepository.save(order);

        return true;
    }
}
