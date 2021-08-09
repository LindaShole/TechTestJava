package za.co.anycompany.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Customer;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findCustomerById(String id);
}
