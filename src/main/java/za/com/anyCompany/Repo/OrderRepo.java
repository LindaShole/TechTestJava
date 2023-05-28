package za.com.anyCompany.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.com.anyCompany.model.OrderModel;
@Repository
public interface OrderRepo extends JpaRepository<OrderModel,Long> {
}
