package za.co.investec.test.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.investec.test.domain.Customer;
import za.co.investec.test.domain.CustomerRepository;
import za.co.investec.test.domain.Order;
import za.co.investec.test.domain.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void givenInvalidCustomerId_whenCreatingOrder_expectFailure() {
        Order order = new Order();
        order.setCustomerId(0L);
        order.setAmount(new BigDecimal("1.00"));

        when(customerRepository.findById(0L)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderService.create(order));
    }

    @Test
    public void givenInvalidAmount_whenCreatingOrder_expectFailure() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setAmount(new BigDecimal("0.00"));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer()));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> orderService.create(order));
    }

    @Test
    public void givenValidOrder_whenCreatingOrder_expectSuccess() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setAmount(new BigDecimal("1.00"));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer()));

        orderService.create(order);

        verify(orderRepository, times(1)).save(any(Order.class));
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
