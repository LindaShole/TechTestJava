package za.co.anycompany.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table

public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable=false, nullable=false)
    private Long id;

    @Column(name = "name", updatable=false, nullable=false)
    private String name;

    @Column(name = "country", updatable=false, nullable=false)
    private String country;

    @Column(name = "dateOfBirth", updatable=false, nullable=false)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "id")
    private Set<Order> order;
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
