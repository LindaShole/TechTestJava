package za.co.anycompany.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {



}
