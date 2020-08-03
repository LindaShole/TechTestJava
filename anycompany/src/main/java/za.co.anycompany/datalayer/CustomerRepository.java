package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;

import java.sql.*;

@Repository
public class CustomerRepository extends JpaRepository<Booking, Integer> {
    
@PersistenceContext
    private EntityManager entityManager;
    
@Transactional
public void insertWithQuery(Customer c) {
    entityManager.createNativeQuery("INSERT INTO Customer (customerId, name, country) VALUES (?,?,?)")
      .setParameter(1, c.getCustomerId())
      .setParameter(2, c.getName())
      .setParameter(3, c.getCountry())
      .executeUpdate();
    }

}
