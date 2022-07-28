package za.co.anycompany.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Customer {
    private int customerId;
    private String name;
    private String country;
    private Date dateOfBirth;
    private List<Order> orders;

    public Customer() {
        this.orders = new ArrayList<>();
    }
    
    public Customer(int customerId, String name, String country, Date dateOfBirth) {
        this.customerId = customerId;
        this.name = name;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.orders = new ArrayList<>();
    }

    public Customer(int customerId, String name, String country, Date dateOfBirth, List<Order> orders) {
        this.customerId = customerId;
        this.name = name;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.orders = orders;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        return String.format("Name: %s , Country: %s, Orders: %s", name, country, orders == null? 0 : orders.size());
    }   
}
