package za.co.anycompany;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.Date;

import static org.junit.Assert.*;

public class OrderControllerTests extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getOrderList() throws Exception {
        String customerUri = "http://localhost:8080/api/customers";
        Customer customer = new Customer();
        customer.setCustomerId(3);
        customer.setName("Gershom");
        customer.setCountry("UK");
        customer.setDateOfBirth(new Date(333737383));
        String customerInputJson = super.mapToJson(customer);
        MvcResult customerMvcResult = mvc.perform(MockMvcRequestBuilders.post(customerUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(customerInputJson)).andReturn();

        String OrderUri = "http://localhost:8080/api/orders";
        Order order = new Order();
        order.setCustomerId(3);
        order.setAmount(140);
        order.setOrderId(3);
        String orderInputJson = super.mapToJson(customer);
        MvcResult orderMvcResult = mvc.perform(MockMvcRequestBuilders.post(customerUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(orderInputJson)).andReturn();

        String uri = "http://localhost:8080/api/customers/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);
    }

    @Test
    public void placeOrder() throws Exception {

        String customerUri = "http://localhost:8080/api/customers";
        Customer customer = new Customer();
        customer.setCustomerId(3);
        customer.setName("Gershom");
        customer.setCountry("UK");
        customer.setDateOfBirth(new Date(333737383));
        String inputJson = super.mapToJson(customer);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(customerUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "true");

        String OrderUri = "http://localhost:8080/api/orders";
        Order order = new Order();
        order.setCustomerId(3);
        order.setAmount(140);
        order.setOrderId(3);
        String orderInputJson = super.mapToJson(customer);
        MvcResult orderMvcResult = mvc.perform(MockMvcRequestBuilders.post(customerUri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(orderInputJson)).andReturn();

        int orderStatus = orderMvcResult.getResponse().getStatus();
        assertEquals(200, orderStatus);
        String orderContent = orderMvcResult.getResponse().getContentAsString();
        assertEquals(orderContent, "true");

    }
}
