package za.co.anycompany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "customerId")
    private int customerId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "vat")
    private double VAT;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
 
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "Order [VAT=" + VAT + ", amount=" + amount + ", customerId=" + customerId + ", orderId=" + orderId + "]";
    }
    
}
