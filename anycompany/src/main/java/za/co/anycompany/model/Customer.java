package za.co.anycompany.model;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: Customer</p>
 * <p/>
 * <p>Description: Customer domain class representing a customer of AnyCompany/</p>
 * <p/>
 * <p>Company: AnyCompany</p>
 *
 * @author Chizeba Maulu
 * @version 1.0
 */
public class Customer {
    private int customerId;
    private String name;
    private String country;
    private Date dateOfBirth;
    private List<Order> orderList;

    /**
     * Get customer id
     *
     * @return the customer id
     **/
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Set customer id
     *
     * @param customerId the new value for customer id
     **/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Get name
     *
     * @return the name
     **/
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     * @param name the new value for name
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get country
     *
     * @return the country
     **/
    public String getCountry() {
        return country;
    }

    /**
     * Set country
     *
     * @param country the new value for country
     **/
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get date of birth
     *
     * @return the date of birth
     **/
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Set date of birth
     *
     * @param dateOfBirth the new value for date of birth
     **/
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get order list
     *
     * @return the order list
     **/
    public List<Order> getOrderList() {
        return orderList;
    }

    /**
     * Set order list
     *
     * @param orderList the new value for order list
     **/
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
