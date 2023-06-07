package za.co.anycompany.model;

public class Order {

    private int orderId;
    private double amount;
    private double vat;
    private int customerId;

    /**
     * Get order id
     *
     * @return the order id
     **/
    public int getOrderId() {
        return orderId;
    }

    /**
     * Set order id
     *
     * @param orderId the new value for order id
     **/
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Get amount
     *
     * @return the amount
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * Set amount
     *
     * @param amount the new value for amount
     **/
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get vat
     *
     * @return the vat
     **/
    public double getVat() {
        return vat;
    }

    /**
     * Set vat
     *
     * @param vat the new value for vat
     **/
    public void setVat(double vat) {
        this.vat = vat;
    }

    /**
     * Get customer id
     *
     * @return the customer id
     **/
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Set customer id
     *
     * @param customerId the new value for customer id
     **/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
