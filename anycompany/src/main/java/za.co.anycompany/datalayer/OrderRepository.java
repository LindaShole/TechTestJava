package za.co.anycompany.datalayer;

import za.co.anycompany.model.Order;

import java.sql.*;

@Repository
public class OrderRepository extends JpaRepository<Booking, Integer> {
 

    @Query(value = "SELECT * FROM Order co WHERE " +
            "EXISTS (SELECT 1 FROM customer c WHERE co.name = c.id ) " +
            "AND EXISTS (SELECT 1 FROM Order o WHERE o.orderId = o.orderId AND c.name IN :name)",
            nativeQuery = true)
    List<Order> queryBy(@Param("name") String name,
                            @Param("amount") List<String> amount);
}
