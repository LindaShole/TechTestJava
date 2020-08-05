package za.co.anycompany.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.anycompany.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
