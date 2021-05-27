package model;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.util.*;

public class CustomerAccount {

    private Customer customer;
    private List<Order> customerOrders;

    public CustomerAccount() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Order> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<Order> customerOrders) {
        this.customerOrders = customerOrders;
    }

    @Override
    public String toString() {

        String temp = "CustomerAccount = { customer =" + this.customer.toString() + ", orders = {";

        for (Order order : this.customerOrders)
            temp = temp + order.toString();
        temp = temp + "}";

        return temp;
    }
}
