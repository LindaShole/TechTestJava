package za.co.anycompany.model;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Table(name = "orders")
@Entity
@RestResource(path = "orders",rel = "orders")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private double amount;
    private double VAT;
    private String product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order(){
    }

    public Order(double amount, double VAT, String product, Customer customer){
        this.amount = amount;
        this.VAT = VAT;
        this.product = product;
        this.customer = customer;
    }

    public String getProduct(){
        return product;
    }

    public void setProduct(String product){
        this.product = product;
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

    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    @Override
    public String toString(){
        return "Order{" +
                "orderId=" + orderId +
                ", amount=" + amount +
                ", VAT=" + VAT +
                ", product='" + product + '\'' +
                ", customer=" + customer +
                '}';
    }
}
