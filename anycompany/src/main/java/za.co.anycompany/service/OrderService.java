package service;

import datalayer.CustomerRepository;
import datalayer.OrderRepository;
import model.Customer;
import model.Order;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);

        if (order.getAmount() == 0)
            return false;

        if (customer.getCountry() == "UK")
            order.setVAT(0.2d);
        else
            order.setVAT(0);
        
        order.setCustomerId(customer.getCustomerId());

        orderRepository.save(order);

        return true;
    }
}
