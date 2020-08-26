package za.co.anycompany.datalayer.repo;

import za.co.anycompany.datalayer.persistance.PersistanceManager;
import za.co.anycompany.datalayer.persistance.PersistanceRepository;
import za.co.anycompany.model.Order;

import java.sql.*;

public class OrderRepository extends PersistanceRepository {


    public void save(Order order) {
        Connection connection = PersistanceManager.getDBConnection();
        PreparedStatement preparedStatement = null;

        try {
            connection.prepareStatement("INSERT INTO ORDER(oderId, amount, vat) VALUES(?,?,?)");
            super.save(preparedStatement, order.getOrderId() , order.getAmount(), order.getVAT());
        } catch (SQLException e) {

        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    
}
