package za.co.anycompany.datalayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.anycompany.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerRepository extends GenericRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerRepository.class);

    public static Customer load(int customerId) {
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Customer customer = new Customer();
        try {
            prpstmt = con.prepareStatement("select customer_id, name, country, date_of_birth from investec.customer where customer_id = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
            }

        } catch (SQLException e) {
            LOG.error("Error occur " + e.getMessage(), e);
        } finally {
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOG.error("Error occur " + e.getMessage(), e);
            }
        }

        LOG.info("Customer info : "+customer);

        return customer;
    }

    public static List<Customer> load() {
        Connection con = getDBConnection();
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;

        List<Customer> customerList = new ArrayList<Customer>();
        try {
            prpstmt = con.prepareStatement("select customer_id, name, country, date_of_birth from investec.customer");
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customerList.add(customer);
            }

        } catch (SQLException e) {
            LOG.error("Error occur " + e.getMessage(), e);
        } finally {
            try {
                if (prpstmt != null)
                    prpstmt.close();
                if (resultSet != null)
                    resultSet.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                LOG.error("Error occur " + e.getMessage(), e);
            }
        }

        LOG.info("customer list : "+customerList);
        return customerList;
    }
}
