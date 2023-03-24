package za.co.anycompany.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table("ORDERS")
public class Order {
    @Id
    private int orderId;
    private double amount;
    private double VAT;
    private int customerId;

    public Order(int orderId, double amount, double VAT, int customerId, Customer customer) {
        this.orderId = orderId;
        this.amount = amount;
        this.VAT = VAT;
        this.customerId = customerId;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Order(int orderId, double amount, double VAT) {
        this.orderId = orderId;
        this.amount = amount;
        this.VAT = VAT;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }
}
