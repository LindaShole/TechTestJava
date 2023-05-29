package za.co.anycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
