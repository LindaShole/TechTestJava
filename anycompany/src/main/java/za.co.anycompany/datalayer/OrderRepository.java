package za.co.anycompany.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {


}
