package za.co.anycompany.model;

public class Order {

    private int orderId;
    private double amount;
    private double VAT;
    private int customerId;
    private String orderDate;
    
    public String getOrderDate() {
    	return orderDate;
    }
    
    public void setOrderDate(String orderDate) {
    	this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId =customerId;
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
