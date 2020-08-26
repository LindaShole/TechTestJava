package za.co.anycompany.datalayer.repo;

import za.co.anycompany.datalayer.persistance.PersistanceManager;
import za.co.anycompany.datalayer.persistance.PersistanceRepository;
import za.co.anycompany.model.Customer;

import java.sql.*;


public class CustomerRepository extends PersistanceRepository {

   

    public static Customer load(int customerId) {
        Connection con = PersistanceManager.getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Customer customer = new Customer();
        try {
            prpstmt = con.prepareStatement("select * from CUSTOMER where customerId = ?");
            resultSet =  get(prpstmt, customerId);
            while (resultSet.next()) {
                customer.setName(resultSet.getString("NAME"));
                customer.setCountry(resultSet.getString("COUNTRY"));
                customer.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
            }

        } catch (SQLException e) {
        	//Implement A logger instead
            e.printStackTrace();
        } finally {
        	//Remove the management on closing connections to be managed by phantomreferences if Java 9 is used
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return customer;
    }


    

}
