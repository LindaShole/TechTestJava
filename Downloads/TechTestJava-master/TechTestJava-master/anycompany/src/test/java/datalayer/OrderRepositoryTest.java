package za.co.anycompany.datalayer;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.responses.SaveCustomerResponse;
import za.co.anycompany.responses.SaveOrderResponse;

import java.util.Date;

public class OrderRepositoryTest {

    OrderRepository orderRepository = null;
    CustomerRepository customerRepository = null;
    Customer customer = null;
    Order order1;
    @Before
    public void setUp(){
        orderRepository = new OrderRepository();
        customerRepository = new CustomerRepository();
        order1 = new Order(0, 200, 0, 1);

        customer = new Customer();
        customer.setCustomerId(5);
        customer.setName("Bruce Williums");
        customer.setCountry("South Africa");
        customer.setDateOfBirth(new Date(1992, 03, 22));
    }

    @Test
    public void testSaveCustomerOrder(){
        SaveCustomerResponse saveCustomerResponse = customerRepository.save(customer);
        System.out.println("saveCustomerResponse.toString(): " + saveCustomerResponse.toString());
        order1.setCustomerId(customer.getCustomerId());
        SaveOrderResponse saveOrderResponse = orderRepository.save(order1);

        System.out.println(saveOrderResponse.toString());
    }
}
