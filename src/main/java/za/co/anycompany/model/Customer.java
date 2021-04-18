package za.co.anycompany.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "countryId", nullable = true)
    @ToString.Exclude 
    private Country country;
    
    private Date dateOfBirth;

}
