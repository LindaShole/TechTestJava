package za.co.anycompany.datalayer;

import za.co.anycompany.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer>{
}