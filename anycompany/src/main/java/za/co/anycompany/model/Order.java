package za.co.anycompany.model;

import java.io.Serializable;

/**
 * No need for JPA entities: Our mappings are in the XML files
 * 
 * @author v-nchatitai
 */
public class Order implements Serializable {

    private int orderId;
    private double amount;
    private double VAT;
    private int customerId;

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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Because we'll probably want to debug the details especially in pre-PROD 
     * environments
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", amount=" + amount + ", VAT=" + VAT + ", customerId=" + customerId + '}';
    }
}
