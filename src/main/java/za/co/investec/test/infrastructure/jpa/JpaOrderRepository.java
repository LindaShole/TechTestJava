package za.co.investec.test.infrastructure.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.investec.test.domain.Order;
import za.co.investec.test.domain.OrderRepository;

@Primary
@Repository
@ConditionalOnExpression("${persistence.use-jpa}")
public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, Long> {

}
