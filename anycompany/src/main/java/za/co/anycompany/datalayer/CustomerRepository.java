package za.co.anycompany.datalayer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.anycompany.model.Customer;
public interface CustomerRepository  extends JpaRepository<Customer, Long> {
    Page<Customer> findByNameContaining(String name, Pageable pageable);
}
