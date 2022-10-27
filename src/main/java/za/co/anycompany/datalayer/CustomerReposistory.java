package za.co.anycompany.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.anycompany.model.Customer;

@Repository
public interface CustomerReposistory extends JpaRepository<Customer, Integer> {

}
