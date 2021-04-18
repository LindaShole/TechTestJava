package za.co.anycompany.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.anycompany.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
