package za.co.anycompany.model;

public class Order {

    private int orderId;
    private int customerId;
    private double amount;
    private double VAT;

    public Order()
    {}

    public Order(int orderId, int customerId, double amount, double VAT)
    {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.VAT = VAT;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString()
    {
        return "Order = {orderId=" + this.orderId +
                ", customerId=" + this.customerId +
                ", amount=" + this.amount +
                ", vat=" + this.VAT + "}";
    }
}
