package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.anycompany.exception.ErrorResponse;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

@Service
public class OrderService  {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    private final String COUNTRY = "UK";

    public Order placeOrder(Order order, long customerId) {
        Customer customer = customerRepository.findById(customerId).get();

        if (order.getAmount() < 1)

            throw new ErrorResponse("insufficient order amount");

        if (COUNTRY.equals(customer.getCountry()))
            order.setVAT(0.2d);
        else
            order.setVAT(0);
            order.setCustomer(customer);
        return orderRepository.save(order);

    }


}
