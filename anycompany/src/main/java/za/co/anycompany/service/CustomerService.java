package za.co.anycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.exception.CustomerNotFoundException;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    public static final String UK = "UK";
    public static final String VAT = "0.2";
    @Autowired
    private CustomerRepository customerRepository;

    public Page<Customer> findAllCustomers(String name, Pageable pageable) {
        if (name != null) {
            return customerRepository.findByNameContaining(name, pageable);
        }
        return customerRepository.findAll(pageable);
    }

    public Customer findCustomer(long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    public List<Customer> addCustomer(List<Customer> customers) {
        List<Customer> customerList = customers.stream()
                .map(customer -> {
                    if (customer.getCountry().equals(UK)) {
                        customer.setTotalAmount(customer.getOrders()
                                .stream()
                                .filter(order -> !order.getAmount().equals(BigDecimal.ZERO))
                                .map(order -> {
                                    order.setVAT(new BigDecimal(VAT));
                                    return order;
                                })
                                .map(Order::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add)
                        );
                    } else {
                        customer.setTotalAmount(customer.getOrders()
                                .stream()
                                .filter(order -> !order.getAmount().equals(BigDecimal.ZERO))
                                .map(order -> {
                                    order.setVAT(BigDecimal.ZERO);
                                    return order;
                                })
                                .map(Order::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add)
                        );
                    }
                    customer.setQuantity(customer.getOrders().size());
                    return customer;
                })
                .collect(Collectors.toList());

        return customerRepository.saveAll(customerList);
    }

    public Customer updateCustomer(long id, Customer customer) {
        Customer savedCustomer = findCustomer(id);
        if (savedCustomer !=  null) {
            Customer customerEntity = savedCustomer;
            customerEntity.setCountry(customer.getCountry());
            customerEntity.setName(customer.getName());
            customerEntity.setDateOfBirth(customer.getDateOfBirth());
            customerEntity.setQuantity(customer.getOrders().size());
            List<Order> orders;
            if (customer.getCountry().equals(UK)) {
                orders = customer.getOrders().stream()
                        .map(order -> {
                            order.setVAT(new BigDecimal(VAT));
                            return order;
                        })
                        .collect(Collectors.toList());
            } else {
                orders = customer.getOrders().stream()
                        .map(order -> {
                            order.setVAT(BigDecimal.ZERO);
                            return order; })
                        .collect(Collectors.toList());
            }
            customerEntity.setOrders(orders);
            BigDecimal total = customer.getOrders().stream()
                    .filter(order -> !order.getAmount().equals(BigDecimal.ZERO))
                    .map(Order::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            customerEntity.setTotalAmount(total);
            return customerRepository.save(customerEntity);
        }
        return customer;
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }
}
