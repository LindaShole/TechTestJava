package co.za.anycompany.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * To do, add relationship between Customer table and Order table.
 */
@Entity
@Table(name = "customer")
public class Customer {
	@Id
	private String cutsomerId;
    private String name;
    private String country;
    private Date dateOfBirth;

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

	public String getCutsomerId() {
		return cutsomerId;
	}

	public void setCutsomerId(String cutsomerId) {
		this.cutsomerId = cutsomerId;
	}
}
