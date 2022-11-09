package za.co.anycompany.model;

public class Order {

    private int id;
    private double amount;
    private double VAT;
    private int customerId;

    @Override
	public String toString() {
		return "Order [id=" + id + ", amount=" + amount + ", VAT=" + VAT + ", customerId=" + customerId + "]";
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}	

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
