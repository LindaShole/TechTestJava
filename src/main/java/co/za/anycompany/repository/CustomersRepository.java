package co.za.anycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.za.anycompany.model.Customer;

@Repository
public interface CustomersRepository extends JpaRepository <Customer, String>{
	@Query(value = "SELECT * from CUSTOMER"
			+ "WHERE customerId = :customerId ", 
			nativeQuery = true)
	Customer findCustomerByCutsomerId(String cutsomerId);
}
