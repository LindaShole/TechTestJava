package za.co.anycompany.model;

import java.util.Date;

public class Customer {
    private int customerId;
    private String name;
    private String country;
    private Date dateOfBirth;

    public Customer(){
    }

    public Customer(int customerId, String name, String country, Date dateOfBirth){
        this.customerId = customerId;
        this.name = name;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId){
        this.customerId = customerId;
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

    @Override
    public String toString(){
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
