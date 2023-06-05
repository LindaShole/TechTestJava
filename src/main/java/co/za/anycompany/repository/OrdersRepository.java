package co.za.anycompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.za.anycompany.model.Order;

@Repository
public interface OrdersRepository extends JpaRepository <Order, String>{
	
	@Query(value = "SELECT * from ORDERS"
			+ "WHERE orderId = :orderId ", 
			nativeQuery = true)
	List<Order> findOrdersByCutsomerId(String cutsomerId);
}
