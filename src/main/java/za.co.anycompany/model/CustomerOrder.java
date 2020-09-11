package za.co.anycompany.model;

public class CustomerOrder {
    private int customerOrderId;
    private int customerId;
    private int orderId;

    public int getCustomerOrderId(){
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
    }

    @Override
    public String toString(){
        String st = "CustomerOrder{ customerOrderId : "+customerOrderId+",\n"+"customerId : "+customerId+",\n"+"orderId : "+orderId+"}";
        return st;
    }
}
