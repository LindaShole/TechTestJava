package com.test.ztest.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
@Entity
public class Customer {

	@Id
	@GeneratedValue(generator = "sequence-generator")
	@GenericGenerator(
			name = "sequence-generator",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@Parameter(name = "sequence_name", value = "customer_sequence"),
					@Parameter(name = "initial_value", value = "101"),
					@Parameter(name = "increment_size", value = "1")})
	private Integer customerId;
	private String name;
	private String country;
	private LocalDate dob;

	@OneToMany(fetch=FetchType.EAGER, mappedBy = "customer")
	private Set<OrdersByCustomer> orders;
	
	protected Customer() {

	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", country=" + country
				+ ", dob=" + dob + "]";
	}
	
	public Customer(String name, String country, LocalDate dob) {
		super();
		this.name = name;
		this.country = country;
		this.dob = dob;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}
	
	public String getCountry() {
		return country;
	}
	
	public LocalDate getDob() {
		return dob;
	}

	public Set<OrdersByCustomer> getOrders() {
		return orders;
	}

}
