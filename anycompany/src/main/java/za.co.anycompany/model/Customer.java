package za.co.anycompany.model;

import java.util.Date;

public class Customer {
	//modified
	private int customerId;
    private String name;
    private String country;
    private Date dateOfBirth;
	
	//modified
	private Order order;
	
    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
	
    public int getCustomerId() {
        return CustomerId;
    }

//modified
    public void setOrder(Order order) {
        this.order = order;
    }
	
    public Order getOrder() {
        return order;
    }	
}
