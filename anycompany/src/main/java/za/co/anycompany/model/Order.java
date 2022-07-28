package za.co.anycompany.model;

public class Order {

    private int orderId;
    private double amount;
    private double VAT;

    public Order() {}

    public Order(int orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public Order(int orderId, double amount, double vat) {
        this.orderId = orderId;
        this.amount = amount;
        this.VAT = vat;
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
