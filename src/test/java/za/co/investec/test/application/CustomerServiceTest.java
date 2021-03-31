package za.co.investec.test.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.investec.test.domain.Customer;
import za.co.investec.test.domain.CustomerRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void givenInvalidName_whenCreatingCustomer_expectFailure() {
        Customer customer = new Customer();
        customer.setName("");
        customer.setCountry("UK");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> customerService.create(customer));
    }

    @Test
    public void givenValidCustomer_whenCreatingCustomer_expectSuccess() {
        Customer customer = new Customer();
        customer.setName("Test Test");
        customer.setCountry("UK");

        customerService.create(customer);

        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}
