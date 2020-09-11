package za.co.anycompany.dto;



public class OrderDTO {
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

    @Override
    public String toString(){
        String st = "OrderDTO{ orderId : "+orderId+",\n"+"amount : "+amount+",\n"+"VAT : "+VAT+"}";
        return st;
    }
}
