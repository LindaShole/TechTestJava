package za.co.anycompany.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

	@Id
	@SequenceGenerator(name = "order_details_sequence", sequenceName = "order_details_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "order_details_sequence")
	@Column(name = "orderid")
	private int orderId;

	@Column(name = "amount")
	private double amount;

	@Column(name = "vat")
	private double vat;

	@Column(name = "createddate")
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "updateddate")
	@UpdateTimestamp
	private Date updatedDate;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "customerId")
	private Customer customer;

}
