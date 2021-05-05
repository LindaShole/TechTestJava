package za.co.anycompany.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import za.co.anycompany.Application;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class OrderControllerTest {

    MockMvc mvc;

    @Autowired( required = true )
    private CustomerRepository userRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public void addCustomer() throws Exception {

        Customer customer = new Customer();

        customer.setName("sfiso");
        customer.setCountry("RZA");
        customer.setDateOfBirth(new Date(5555));

        String json = mapper.writeValueAsString(customer);

        ResultActions resultActions = this.mvc.perform(post("/api/customers").content(json).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());//

        System.out.println(resultActions.toString());
    }

    @Test
    public void testAddOrder() throws Exception {

        addCustomer();
        Order order = new Order();

        order.setVAT(0.14);
        order.setAmount(45.78);
        order.setItem("Pizza");
        order.setCustomerId(1);

        String json = mapper.writeValueAsString(order);

        ResultActions resultActions = this.mvc.perform(post("/api/orders").content(json).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());//

        System.out.println(resultActions.toString());

        ResultActions resultActions2 = this.mvc.perform(get("/api/customers/1"))
                .andDo(print())
                .andExpect(status().isOk());//

        System.out.println(resultActions2.toString());

    }
}
