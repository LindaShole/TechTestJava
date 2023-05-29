package za.co.anycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
