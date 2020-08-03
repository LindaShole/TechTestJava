package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;

import java.sql.*;

@Repository
public class CustomerRepository extends JpaRepository<Booking, Integer> {
    
@PersistenceContext
    private EntityManager entityManager;
    
@Transactional
public void insertWithQuery(Person person) {
    entityManager.createNativeQuery("INSERT INTO Customer (customerId, name, country) VALUES (?,?,?)")
      .setParameter(1, person.getCustomerId())
      .setParameter(2, person.getName())
      .setParameter(3, person.getCountry())
      .executeUpdate();
    }

}
