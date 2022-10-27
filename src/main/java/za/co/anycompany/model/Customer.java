package za.co.anycompany.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
	@Column(name = "customerid")
	private int customerId;

	@Column(name = "name")
	private String name;

	@Column(name = "country")
	private String country;

	@Column(name = "dob")
	private Date dateOfBirth;

	@Column(name = "createddate")
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "updateddate")
	@UpdateTimestamp
	private Date updatedDate;

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "updatedby")
	private String updatedBy;

	@OneToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER, mappedBy = "customer")
	private List<Order> orders;

}
