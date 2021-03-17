package za.co.anycompany.service;

import za.co.anycompany.model.Order;

import java.util.List;
import za.co.anycompany.datalayer.OrderRepository;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public boolean placeOrder(Order order)
    {
        if (order.getAmount() == 0)
            return false;

        if (order.getCustomer().getCountry() == "UK")
            order.setVAT(0.2d);
        else
            order.setVAT(0);

        orderRepository.save(order);

        return true;
    }

    public List<Order> getAllForCustomer(int customerId){
        return orderRepository.loadAllForCustomer(customerId);
    }

    public List<Order> saveAll(List<Order> orders){
        return null;
    }


}
