package za.co.anycompany.service;

import model.CustomerAccount;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import service.OrderServiceInterface;

import java.util.*;

public class OrderService implements OrderServiceInterface {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    public OrderService()
    {
        this.orderRepository = new OrderRepository();
        this.orderRepository.initRepository();
        this.customerRepository = new CustomerRepository();
        this.customerRepository.initRepository();
    }

    public boolean placeOrder(Order order)
    {
        Customer customer = CustomerRepository.load(order.getCustomerId());

        if (order.getAmount() == 0)
            return false;

        if (customer.getCountry() == "UK")
            order.setVAT(0.2d);
        else if (customer.getCountry() == "RSA")
            order.setVAT(0.15d);
        else
            order.setVAT(0);

        orderRepository.save(order);

        return true;
    }

    public List<CustomerAccount> getAllCustomerData()
    {
        List<CustomerAccount> customerAccounts = new ArrayList<>();

        List<Customer> customers = CustomerRepository.getAllCustomers();

        for(Customer customer: customers)
        {
            CustomerAccount customerAccount = getCustomerData(customer.getCustomerId());
            customerAccounts.add(customerAccount);
        }

        return customerAccounts;
    }

    public CustomerAccount getCustomerData(int customerId)
    {
        CustomerAccount customerAccount = new CustomerAccount();

        customerAccount.setCustomer(getCustomer(customerId));
        customerAccount.setCustomerOrders(getCustomerOrders(customerId));

        return customerAccount;
    }

    private List<Order> getCustomerOrders(int customerId)
    {
        List<Order> customerOrders = OrderRepository.getOrdersByCustomerId(customerId);

        return customerOrders;

    }

    private Customer getCustomer(int customerId)
    {
        Customer customer = CustomerRepository.load(customerId);

        return customer;
    }


}
