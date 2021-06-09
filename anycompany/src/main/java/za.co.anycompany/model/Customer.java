package za.co.anycompany.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer extends GenericEntity {
    private int customerId;
    private String name;
    private String country;
    private LocalDate dateOfBirth;
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(int customerId, String name, String country, LocalDate dateOfBirth) {
        this.customerId = customerId;
        this.name = name;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer(int customerId, String name, String country, LocalDate dateOfBirth, List<Order> orders) {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", customerId=" + customerId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", orders=" + orders +
                '}';
    }
}
