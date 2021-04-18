package za.co.anycompany.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.anycompany.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
