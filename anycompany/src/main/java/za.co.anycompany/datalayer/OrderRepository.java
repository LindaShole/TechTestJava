package za.co.anycompany.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Order;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {


  List<Order> findAllOrdersByPlacedAt(Date placedAt);
}
