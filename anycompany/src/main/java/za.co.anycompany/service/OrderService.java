package za.co.anycompany.service;

import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.validations.Screen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();
    private CustomerRepository customerRepository = new CustomerRepository();
    private static final Logger logger = Logger.getLogger(String.valueOf(CustomerRepository.class));


    public boolean placeOrder(Order order, int customerId)
    {
        try {

            if(!Screen.isValidInt(customerId) || null ==  order)
                return false;

            Customer customer = customerRepository.load(customerId);

            if(null == customer)
            {
                return  false;
            }

            if (!Screen.isValidDouble(order.getAmount()))
                return false;

            if (customer.getCountry() == "UK")
                order.setVAT(0.2d);
            else
                order.setVAT(0);

            return orderRepository.save(order);

        }
        catch (Exception e)
        {
            logger.log(Level.ALL,e.getMessage());
        }

        return false;
    }

    public Order findOrderByOrderId(int customerId) {
        Order orders = new Order();
        if(Screen.isValidInt(customerId))
        {
            orders =  orderRepository.findOrderByOrderId(customerId);
        }
        return orders;
    }

    public List<Order> findOrderByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        if(Screen.isValidInt(customerId))
        {
            try {
                Customer customer = customerRepository.getCustomer(customerId);
                orders =  orderRepository.findOrdersByCustomerId(customerId);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return orders;
    }
}
