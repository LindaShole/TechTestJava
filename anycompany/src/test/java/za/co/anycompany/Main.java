package za.co.anycompany;


import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.CustomerService;
import za.co.anycompany.service.OrderService;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        CustomerService customerService = new CustomerService();

        // Place an order, linked to a customer
        Customer customer = new Customer(1, "John", "SA", LocalDate.now());
        Customer savedCustomer = customerService.save(customer);
        Order order = new Order(1234, 200, 15.5, savedCustomer.getId());
        Order placedOrder = orderService.placeOrder(order, customer.getCustomerId());
        System.out.println(placedOrder);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        // Retrieve a customer with their linked order(s)
        Customer customer1 = customerService.findByCustomerId(1);
        Order order1 = new Order(12345, 200, 15.5, customer1.getId());
        Order placedOrder1 = orderService.placeOrder(order1, customer.getCustomerId());
        System.out.println(customer1);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        // Load all customers and their linked orders
        Customer customer2 = new Customer(2, "Jane", "UK", LocalDate.now());
        customerService.save(customer2);
        List<Customer> customers = customerService.findAll();
        System.out.println(customers);
        System.out.println("-----------------------------------------------------------------------------------------");

    }
}
