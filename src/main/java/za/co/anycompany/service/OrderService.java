package za.co.anycompany.service;

import java.math.BigDecimal;

import za.co.anycompany.datalayer.repo.CustomerRepository;
import za.co.anycompany.datalayer.repo.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);
        if (order.getAmount().equals(new BigDecimal("0"))) {
            return false;
        }

        if (customer.getCountry().equalsIgnoreCase("UK")){
            order.setVAT(new BigDecimal("0.2"));
        }else {
            order.setVAT(new BigDecimal("0"));
        }
        orderRepository.save(order);
        return true;
    }
}
