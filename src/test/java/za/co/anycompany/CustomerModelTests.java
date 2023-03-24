package za.co.anycompany;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import za.co.anycompany.datalayer.CustomerRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import za.co.anycompany.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerModelTests {

    //@Autowired
    private CustomerRepository customerRepository = new CustomerRepository();
    @BeforeEach
    void setupBefore(){

    }

    @Test
    void setsTheCustomerDetailsCorrectly(){
        Customer customer = new Customer();
        customer.setName("Xolisani");
        customer.setCountry("SA");
        customer.setCustomerid(1);

        assertEquals("Xolisani",customer.getName());
        assertEquals("SA",customer.getCountry());
        assertEquals(1,customer.getCustomerid());

    }

    @Test
    void setsTheCustomerCorrectlyUsingTheLoadMethod(){
        Customer customer = new Customer();

        customer.setName("Xolisani");
        customer.setCountry("SA");
        customer.setCustomerid(1);

        Customer cstmer = customerRepository.load(25);
        //Customer customersList = cstmer.getCustomers();
       // assertEquals(customer.getName(), cstmer.getName());
     //   assertEquals(customer.getCountry(), cstmer.getCountry());
     //   assertEquals(customer.getId(), cstmer.getId());
    }

    @Test
    void getsAllTheCustomersStoredInTheDB(){
        List<Customer> customers = new ArrayList<Customer>();
        customers = customerRepository.getAll();

        assertEquals(0, customers.size());
    }

    @AfterEach
    void setupAfter(){

    }

}
