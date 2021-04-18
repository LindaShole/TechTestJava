package za.co.anycompany.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Data
@EqualsAndHashCode
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "countryId", nullable = true)
    @ToString.Exclude 
    @EqualsAndHashCode.Exclude
    private Country country;
    
    private Date dateOfBirth;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @ToString.Exclude 
    @EqualsAndHashCode.Exclude
    private Set<Order> orders;
    
    public Customer(String name, Country country, Date dateOfBirth) {
    	this.name = name;
    	this.country = country;
    	this.dateOfBirth = dateOfBirth;
    }

}
