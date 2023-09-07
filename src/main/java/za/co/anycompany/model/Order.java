package za.co.anycompany.model;

/**
 * @deprecated Use the OrderDTO under the dto package
 */
@Deprecated(forRemoval = true)
public class Order {

    private int orderId;
    private double amount;
    private double VAT;

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
