package za.co.anycompany.datalayer;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import za.co.anycompany.model.Customer;
import za.co.anycompany.responses.SaveCustomerResponse;


import java.util.Date;

public class CustomerRepositoryTest {
    Customer customer = null;
    Customer customer2 = null;
    Customer customer3 = null;
    CustomerRepository customerRepository = null;
    @Before
    public void setUp(){

        customerRepository = new CustomerRepository();

        //Customer 1
        customer = new Customer();
        customer.setCustomerId(0);
        customer.setName("Miyelani Maluleke");
        customer.setCountry("South Africa");
        customer.setDateOfBirth(new Date(1992, 03, 22));

        //Customer 2
        customer2 = new Customer();
        customer2.setCustomerId(1);
        customer2.setName("Steven Moetsela");
        customer2.setCountry("South Africa");
        customer2.setDateOfBirth(new Date(1992, 03, 22));

        //Customer 3
        customer3 = new Customer();
        customer3.setCustomerId(3);
        customer3.setName("Samuel Johnes");
        customer3.setCountry("South Africa");
        customer3.setDateOfBirth(new Date(1992, 03, 22));

    }


    @Test
    public void testSaveCustomer(){

        //Create and save customer

        customerRepository.save(customer);
        customerRepository.save(customer2);
        //save, load and test customer
        Customer savedCustomer = CustomerRepository.load(customer.getCustomerId());
        assertEquals(0, savedCustomer.getCustomerId());
        assertEquals("Miyelani Maluleke", savedCustomer.getName());
        assertEquals("South Africa", savedCustomer.getCountry());

        //save, load and test customer 2
        Customer savedCustomer2 = CustomerRepository.load(customer2.getCustomerId());
        assertEquals(1, savedCustomer2.getCustomerId());
        assertEquals("Steven Moetsela", savedCustomer2.getName());
        assertEquals("South Africa", savedCustomer2.getCountry());
    }

    @Test
    public void testCustomerAlreadyExist(){
        //Save customer 3
        SaveCustomerResponse saveCustomerResponse = customerRepository.save(customer3);
        assertTrue(saveCustomerResponse.isStatus());
        assertEquals("Customer saved Successfully", saveCustomerResponse.getMessage());
        //Save customer 3 again
        saveCustomerResponse = customerRepository.save(customer3);
        //Saving customer 3 is expected to have failed due to primary key violation
        assertFalse(saveCustomerResponse.isStatus());
    }

    @Test
    public void testNoneLoadExistingCustomer(){
        Customer noneExistingCustomer = CustomerRepository.load(customer2.getCustomerId());
    }
}
