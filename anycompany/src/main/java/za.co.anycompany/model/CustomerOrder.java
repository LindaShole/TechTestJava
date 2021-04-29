package za.co.anycompany.model;

public class CustomerOrder {
	private int customerOrderID;
	
	private int customerId;
	
	private int orderId;
	
	public int getCustomerOrderID() {
        return customerOrderID;
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
   
}