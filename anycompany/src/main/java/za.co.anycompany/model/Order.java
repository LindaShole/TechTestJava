package za.co.anycompany.model;

import java.util.Date;

public class Order {


    private int orderId;
    private double amount;
    private String product;
    private  double vat ;

    private final Date createdAt = new Date();
    private Customer customer;

    public Order(){

    }

    public Order(double amount, String product, double vat, Customer customer){
        this.amount = amount;
        this.product = product;
        this.vat = vat;
        this.customer = customer;
    }

    public Order(int orderId, double amount, String product, double vat, Customer customer) {
        this.orderId = orderId;
        this.amount = amount;
        this.product = product;
        this.vat = vat;
        this.customer = customer;
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

    public String getProduct(){
        return product;
    }

    public void setProduct(String product){
        this.product = product;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotal(){
        return amount + vat;
    }

    public double getVat(){
        return vat;
    }

    public void setVat(double vat){
        this.vat = vat;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Date getCreatedAt(){
        return createdAt;
    }
}
