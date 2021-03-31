package za.co.investec.test.infrastructure.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.investec.test.domain.Customer;
import za.co.investec.test.domain.CustomerRepository;

@Primary
@Repository
@ConditionalOnExpression("${persistence.use-jpa}")
public interface JpaCustomerRepository extends CustomerRepository, JpaRepository<Customer, Long> {

}
