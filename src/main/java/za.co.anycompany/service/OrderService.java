package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.ErrorResponse;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.util.Optional;

@Service
public class OrderService  {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Order placeOrder(Order order, long customerId) {
        Customer customer = customerRepository.findById(customerId).get();

        if (order.getAmount() < 1)
            throw new ErrorResponse("insufficient order amount");
           //log this errror

        if (customer.getCountry() == "UK")
            order.setVAT(0.2d);
        else
            order.setVAT(0);
        order.setCustomer(customer);
        return orderRepository.save(order);


    }


}
