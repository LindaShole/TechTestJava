package za.co.anycompany.service;


import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import static org.junit.Assert.*;

public class OrderServiceTest {

    private OrderService orderService;

    private Mockery mockery;

   // private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        orderService = new OrderService();
    }

    @Test
    public void placeOrderTest() {
        int customerId = 1;
        assertTrue(orderService.placeOrder(getOrder(), customerId));
    }

    @Test
    public void failToPlaceOrderTest() {
        int customerId = 1;
        assertFalse(orderService.placeOrder(getOrder1(), customerId));
    }

    @Test
    public void loadCustomersTest() {
        assertTrue(orderService.loadCustomers().size()>0);
    }

    @Test
    public void getCustomerTest() {
        assertTrue(orderService.getCustomer(1).getName().equalsIgnoreCase("Samu"));
    }

    private Order getOrder(){
        Order order = new Order();
        order.setAmount(10.8d);
        order.setOrderId(1);
        return order;
    }

    private Order getOrder1(){
        Order order = new Order();
        order.setAmount(0);
        order.setOrderId(1);
        return order;
    }

    private Customer getCustomer(){
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCountry("UK");
        customer.setName("Samu");

        return customer;
    }


}