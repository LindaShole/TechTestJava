package za.co.investec.test.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.co.investec.test.application.OrderService;
import za.co.investec.test.domain.Order;
import za.co.investec.test.domain.OrderRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void whenFindingValidOrder_expectSuccess() throws Exception {
        Order order = mockOrder();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/api/orders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(order)));
    }

    @Test
    public void whenFindingInValidOrder_expectNotFound() throws Exception {
        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/orders/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenFindingAllOrders_expectSuccess() throws Exception {
        List<Order> orders = List.of(mockOrder());

        when(orderRepository.findAll()).thenReturn(orders);

        mockMvc.perform(get("/api/orders"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(orders)));
    }

    @Test
    public void whenCreatingValidOrder_expectSuccess() throws Exception {
        Order order = mockOrder();

        when(orderService.create(any())).thenReturn(mockOrder());

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(order)));
    }

    @Test
    public void whenCreatingInValidOrder_expectBadRequest() throws Exception {
        Order order = mockOrder();

        when(orderService.create(any()))
                .thenThrow(new IllegalArgumentException("Invalid Customer id"));

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid Customer id"));
    }


    private Order mockOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomerId(1L);
        order.setVat(new BigDecimal("0.00"));
        order.setAmount(new BigDecimal("1.00"));
        return order;
    }
}
