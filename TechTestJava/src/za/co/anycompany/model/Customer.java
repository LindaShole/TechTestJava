package za.co.anycompany.model;

import java.util.Date;
import java.util.List;

public class Customer {
	private int id;
    private String name;
    private String country;
    private Date dateOfBirth;

    private List<Order> orders = null;
    
    public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", country=" + country + ", dateOfBirth=" + dateOfBirth
				+ ", orders=" + orders + "]";
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

    public void setId(int id) {
    	this.id = id;
    }
    
	public int getId() {		
		return id;
	}
}
