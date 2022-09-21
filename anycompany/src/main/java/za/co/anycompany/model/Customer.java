package za.co.anycompany.model;

import java.io.Serializable;
import java.util.Date;

/**
 * No need for JPA entities: Our mappings are in the XML files
 * 
 * @author v-nchatitai
 */
public class Customer implements Serializable {

    private int customerId;
    private String customerName;
    private String country;
    private Date dateOfBirth;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    /**
     * Because we'll probably want to debug the details especially in pre-PROD 
     * environments
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", customerName=" + customerName + ", country=" + country + ", dateOfBirth=" + dateOfBirth + "}\n";
    }
}
