package za.co.investec.test.application;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.investec.test.domain.Customer;
import za.co.investec.test.domain.CustomerRepository;
import za.co.investec.test.domain.Order;
import za.co.investec.test.domain.OrderRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class  OrderService {

    private static final String COUNTRY_UK = "UK";
    private static final BigDecimal VAT_UK = new BigDecimal("0.20");
    private static final BigDecimal VAT_NON_UK = new BigDecimal("0.00");

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Order create(Order order) {
        Customer customer = findCustomer(order.getCustomerId());
        if (order.getAmount().signum() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (customer.getCountry().equals(COUNTRY_UK)) {
            order.setVat(VAT_UK);
        } else {
            order.setVat(VAT_NON_UK);
        }
        return orderRepository.save(order);
    }

    private Customer findCustomer(Long customerId) {
        return Optional.ofNullable(customerId).flatMap(customerRepository::findById)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer id"));
    }
}
