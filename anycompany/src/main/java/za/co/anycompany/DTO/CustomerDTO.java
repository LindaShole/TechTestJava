package za.co.anycompany.DTO;

import java.util.Date;
import java.util.List;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public class CustomerDTO {

    private int customerId;
    private String name;
    private String country;
    private Date dateOfBirth;
    private List<Order> orders;

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

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.customerId = customer.getId();
        this.name = customer.getName();
        this.country = customer.getCountry();
        this.dateOfBirth =  customer.getDateOfBirth();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
