package za.co.anycompany.model;

import java.util.Date;

/**
 * @deprecated Use the CustomerDTO under the dto package
 */
@Deprecated(forRemoval = true)
public class Customer {
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
}
