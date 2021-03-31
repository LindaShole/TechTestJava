package za.co.investec.test.application;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.investec.test.domain.Customer;
import za.co.investec.test.domain.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer create(Customer customer) {
        // add additional validations
        if (StringUtils.isBlank(customer.getName())) {
            throw new IllegalArgumentException("Invalid customer name");
        }
        return customerRepository.save(customer);
    }
}
