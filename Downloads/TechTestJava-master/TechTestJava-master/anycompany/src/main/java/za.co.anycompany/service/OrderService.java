package za.co.anycompany.service;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);
        if(customer != null) {
            if (order.getAmount() == 0)
                return false;

            if (customer.getCountry() == "UK")
                order.setVAT(0.2d);
            else
                order.setVAT(0);

            orderRepository.save(order);

            return true;
        }
        else{
            return false;
        }
    }
}
