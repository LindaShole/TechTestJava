package za.co.investec.test.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.co.investec.test.application.CustomerService;
import za.co.investec.test.domain.Customer;
import za.co.investec.test.domain.CustomerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void whenFindingValidCustomer_expectSuccess() throws Exception {
        Customer customer = mockCustomer();

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customer)));
    }

    @Test
    public void whenFindingInValidCustomer_expectNotFound() throws Exception {
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/customers/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenFindingAllCustomers_expectSuccess() throws Exception {
        List<Customer> customers = List.of(mockCustomer());

        when(customerRepository.findAll()).thenReturn(customers);

        mockMvc.perform(get("/api/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customers)));
    }

    @Test
    public void whenCreatingValidCustomer_expectSuccess() throws Exception {
        Customer customer = mockCustomer();

        when(customerService.create(any())).thenReturn(mockCustomer());

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(customer)));
    }

    @Test
    public void whenCreatingInValidCustomer_expectBadRequest() throws Exception {
        Customer customer = mockCustomer();

        when(customerService.create(any()))
                .thenThrow(new IllegalArgumentException("Invalid Customer Name"));

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid Customer Name"));
    }


    private Customer mockCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test Test");
        customer.setCountry("UK");
        customer.setDateOfBirth(LocalDate.of(1980, 1, 1));
        return customer;
    }
}
