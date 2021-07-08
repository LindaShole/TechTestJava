package za.co.anycompany.model;

public class Order {

    private int orderId;
    private double amount;
    private double VAT;
	//modified
	private Customer customer;

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
	//modified
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
	
	public Customer getCustomer(){
		return customer;
	}
}
