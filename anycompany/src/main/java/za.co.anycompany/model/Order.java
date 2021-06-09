package za.co.anycompany.model;

public class Order extends GenericEntity {
    private int orderId;
    private double amount;
    private double VAT;
    private Long customerId;

    public Order() {
    }

    public Order(int orderId, double amount, double VAT, Long customerId) {
        this.orderId = orderId;
        this.amount = amount;
        this.VAT = VAT;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", VAT=" + VAT +
                ", customerId=" + customerId +
                '}';
    }
}
