package com.test.ztest.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class OrdersByCustomer {
	
	@Id
	@GeneratedValue(generator = "sequence-generator")
	@GenericGenerator(
			name = "sequence-generator",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@Parameter(name = "sequence_name", value = "order_sequence"),
					@Parameter(name = "initial_value", value = "1001"),
					@Parameter(name = "increment_size", value = "1")})
	private Long orderId;
	private double vat;
	private double amount;
	
	@ManyToOne
	@JoinColumn(name="customerId", nullable=false)
	private Customer customer;

	protected OrdersByCustomer() {
		
	}
	public OrdersByCustomer(double vat, double amount, Customer customer) {
		super();
		this.vat = vat;
		this.amount = amount;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "OrdersByCustomer [orderId=" + orderId + ", vat=" + vat + ", amount=" + amount + ", customerId=" + customer.getCustomerId()
				+ "]";
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public double getVat() {
		return vat;
	}

	public double getAmount() {
		return amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	
}
