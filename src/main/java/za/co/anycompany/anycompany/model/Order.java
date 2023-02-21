package za.co.anycompany.anycompany.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ORDERS")
public class Order {
    @Id
    private int orderId;
    private double amount;
    private double VAT;

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
