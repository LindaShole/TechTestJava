package za.co.anycompany.service;

import za.co.anycompany.common.DatabaseHelper;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.util.List;

public class CustomerOrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public CustomerOrderService() {
        DatabaseHelper.createTableIfDoNotExist();
    }

    public boolean placeOrder(Order order, int customerId)
    {
        Customer customer = CustomerRepository.findById(customerId);

        if (order.getAmount() == 0) {
            return false;
        }

        if (customer.getCountry() == "UK") {
            order.setVAT(0.2d);
        }
        else {
            order.setVAT(0);
        }

        order.setCustomerId(customerId);
        orderRepository.save(order);

        return true;
    }

    public List<Customer> findAllCustomers(){
        List<Customer> customers = CustomerRepository.findAll();

        customers.forEach(customer -> {
            List<Order> orders = orderRepository.findByCustomerId(customer.getCustomerId());
            customer.setOrders(orders);
        });

        return customers;
    }

    public Customer findCustomer(int customerId){
        Customer customer = CustomerRepository.findById(customerId);
        List<Order> orders = orderRepository.findByCustomerId(customer.getCustomerId());
        customer.setOrders(orders);
        return customer;
    }

}
