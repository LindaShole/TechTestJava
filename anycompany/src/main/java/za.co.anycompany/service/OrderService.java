package main.java.za.co.anycompany.service;

import main.java.za.co.anycompany.framework.ServicesProperties;
import main.java.za.co.anycompany.datalayer.CustomerRepository;
import main.java.za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.util.List;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);

        if (order.getAmount() == 0)
            return false;

        if (customer.getCountry() == ServicesProperties.getCOUNTRY())
            order.setVAT(0.2d);
        else
            order.setVAT(0);

        orderRepository.save(order, customerId);

        return true;
    }

    public List<Order> loadCustomerOrders(int customerId){
         return orderRepository.loadCustomerOrders(customerId);
    }
}
