package za.co.anycompany.service;

import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class OrderService {

    private final OrderRepository orderRepository ;

    public  OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void placeOrder(Order order)
    {
        Customer customer = order.getCustomer();

        if (order.getAmount() == 0)
            return;

        if (customer.getCountry() == "UK")
            order.setVat(0.2d);
        else
            order.setVat(0);

        orderRepository.placeOrder(order);

    }
}
