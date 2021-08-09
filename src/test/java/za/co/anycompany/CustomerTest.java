package za.co.anycompany;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.CustomerServiceImpl;
import za.co.anycompany.service.OrderServiceImpl;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private OrderServiceImpl orderService;

    @Before
    public void loadCustomer(){
        Customer cust1 = new Customer();
        cust1.setName("customer1");
        cust1.setCountry("ZA");
        cust1.setDateOfBirth(new Date());
        customerService.saveCustomer(cust1);

        Customer cust2 = new Customer();
        cust2.setName("customer2");
        cust2.setCountry("US");
        cust2.setDateOfBirth(new Date());
        customerService.saveCustomer(cust2);

        Customer cust3 = new Customer();
        cust3.setName("customer3");
        cust3.setCountry("UK");
        cust3.setDateOfBirth(new Date());
        customerService.saveCustomer(cust3);

    }

    @Test
    public void customer_placing_order_test(){
        Order order = new Order();
        order.setVAT(2.3d);
        order.setAmount(500);

        Order order1 = orderService.placeOrder(order, 1);
        Assert.assertEquals(order.getAmount(),order1.getAmount(),1.4);

    }

    @Test
    public void retrieve_customer_with_their_order_linked_test(){
        Customer customer = customerService.getCustomer(1);
        Assert.assertNotNull(customer);

    }

    @Test
    public void load_customers_with_their_order_linked_test(){
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(3,customerList.size());

    }




}
