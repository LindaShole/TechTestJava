package za.co.anycompany.anycompany.service;


import org.springframework.stereotype.Service;
import za.co.anycompany.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.anycompany.datalayer.OrderRepository;
import za.co.anycompany.anycompany.model.Customer;
import za.co.anycompany.anycompany.model.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    private Map<Integer, Order> db = new HashMap<>(){{
        put(1, new Order(1, 200.00, 1.15));
    }};
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

        orderRepository.save(order);

        return true;
    }

    public Order get(Integer id) {
        return db.get(id);
    }

    public Collection<Order> get() {
        return db.values();
    }

    public Order remove(Integer id) {
        return db.remove(id);
    }

    public Order save(int orderId, Order order) {

        return order;
    }
}
