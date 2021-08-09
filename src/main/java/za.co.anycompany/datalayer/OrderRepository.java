package za.co.anycompany.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
