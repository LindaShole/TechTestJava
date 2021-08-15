package za.co.anycompany;

import org.junit.Assert;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.CustomerService;
import za.co.anycompany.service.OrderService;

import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnyCompanyApplicationTest {

   @Autowired
   CustomerService customerService;

   @Autowired
   OrderService orderService;

    @Test
    public void contextLoads() {
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void add_customer(){

        Customer customer = new Customer();
        customer.setName("customer");
        customer.setCountry("UK");
        customer.setDateOfBirth(new Date());

        Assert.assertEquals(customerService.saveCustomer(customer).getId().longValue(),1);

    }


    @Test
    @org.junit.jupiter.api.Order(2)
    public void place_order_And_validate_UK_Vat(){

        Order order = new Order();
        order.setAmount(500);
        order.setVAT(15.0d);
        Order createdOrder = orderService.placeOrder(order, 1L);
        Assert.assertEquals(createdOrder.getVAT(),0.2d,0.2);

    }

    @Test(expected = ErrorResponse.class)
    @org.junit.jupiter.api.Order(3)
    public void validate_order_insufficient_amount(){
        Order order = new Order();
        order.setAmount(0);
        order.setVAT(0);
        Order createdOrder = orderService.placeOrder(order, 1L);

    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void getAllCustomers(){
        Assert.assertEquals(customerService.customerList().size(),1L);
    }

}
