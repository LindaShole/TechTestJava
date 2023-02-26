package za.co.anycompany.anycompany.model;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

//@Entity
@Table
public class Customer {
    @Id
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String country;
    @Column
    private Date dateOfBirth;
   /* @jakarta.persistence.Id
    private Long id;*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerid() {
        return id;
    }

    public void setCustomerid(int customerid) {
        this.id = customerid;
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

    public Integer getId() {
        return id;
    }
}