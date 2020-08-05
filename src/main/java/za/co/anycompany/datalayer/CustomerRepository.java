package za.co.anycompany.datalayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.co.anycompany.model.Customer;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where c.id = :id")
    public Customer load(@Param("id") int customerId);

    @Query("select c from Customer c join Order o on c.id = o.customer.id")
    public List<Customer> getAllCustomers();
}
