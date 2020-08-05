package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public boolean placeOrder(Order order, int customerId)
    {
        try {
            Customer customer = null;
            customer = CustomerRepository.load(customerId);
            if (order.getAmount() == 0)
                return false;

            if (customer.getCountry() == "UK")
                order.setVAT(0.2d);
            else
                order.setVAT(0);
            orderRepository.save(order);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public List<Order> findOrderByOrderId(int customerId) {
        List<Order> orders = new ArrayList<>();
        try {
            orders =  orderRepository.findOrdersByOrderId(customerId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
}
