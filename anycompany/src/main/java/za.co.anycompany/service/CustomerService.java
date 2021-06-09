package za.co.anycompany.service;

import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;

import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository = new CustomerRepository();
    private final OrderRepository orderRepository = new OrderRepository();

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findByCustomerId(int customerId) {
        Customer customer = CustomerRepository.load(customerId);
        if (customer != null) {
            customer.setOrders(orderRepository.findByCustomerId(customer.getCustomerId()));
        }
        return customer;
    }

    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(customer -> customer.setOrders(orderRepository.findByCustomerId(customer.getCustomerId())));
        return customers;
    }
}
