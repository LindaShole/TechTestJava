package za.co.anycompany;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import za.co.anycompany.model.Customer;
import java.sql.Date;

import static org.junit.Assert.*;

public class CustomerControllerTests extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void loadCustomersList() throws Exception {
        String uri = "http://localhost:8080/api/customers";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);
    }

    @Test
    public void createCustomer() throws Exception {

		String uri = "http://localhost:8080/api/customers";
		Customer customer = new Customer();
		customer.setCustomerId(3);
		customer.setName("Gershom");
		customer.setCountry("UK");
        customer.setDateOfBirth(new Date(333737383));
		String inputJson = super.mapToJson(customer);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "true");

    }
}
