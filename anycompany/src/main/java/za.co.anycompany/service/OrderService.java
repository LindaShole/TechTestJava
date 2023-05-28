package za.co.anycompany.service;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.util.Objects;

public class OrderService {


    private final  OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public boolean placeOrder(Order order)
    {
        Customer customer = order.getCustomer();

        if (order.getAmount() == 0)
            return false;

        if (Objects.equals(customer.getCountry(), "UK"))
            order.setVAT(0.2d);
        else
            order.setVAT(0);

        orderRepository.save(order);

        return true;
    }
}
