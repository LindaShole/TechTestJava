package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;

import java.sql.*;

public class CustomerRepository {

    private Connection con = null;

    public CustomerRepository(Connection con){
        this.con = con;
    }

    public Customer load(int customerId) {
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        Customer customer = new Customer();
        try {
            prpstmt = con.prepareStatement("select * from CUSTOMER where customerId = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
                customer.setCustomerId(resultSet.getInt("customerId"));
                customer.setName(resultSet.getString("name"));
                customer.setCountry(resultSet.getString("country"));
                customer.setDateOfBirth(resultSet.getDate("dateOfBirth"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //   finally {
        //            try {
        //                if (prpstmt != null)
        //                    prpstmt.close();
        //                if (resultSet != null)
        //                    resultSet.close();
        //                if (con != null)
        //                    con.close();
        //            } catch (SQLException e) {
        //                System.out.println(e.getMessage());
        //            }
        //        }
        return customer;
    }

    // Load all customers and their linked orders
    public void loadCustomersWithOrders(){
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        try  {
            // Query to retrieve all customers with their linked orders
            String query = "SELECT c.customerId, c.name, c.country, c.dateOfBirth, o.orderId, o.amount, o.product, o.vat " +
                    "FROM CUSTOMER c " +
                    "LEFT JOIN orders o ON c.customerId = o.customerId";
            prpstmt = con.prepareStatement(query);
            resultSet = prpstmt.executeQuery();

            while (resultSet.next()) {
                // Retrieve the data from the result set
                int customerId = resultSet.getInt("customerId");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                Date dateOfBirth = resultSet.getDate("dateOfBirth");
                int orderId = resultSet.getInt("orderId");
                double amount = resultSet.getDouble("amount");
                String product = resultSet.getString("product");
                double vat = resultSet.getDouble("vat");

                // Determine if the customer has orders or not
                if (resultSet.wasNull()) {
                    System.out.println("Customer ID: " + customerId);
                    System.out.println("Name: " + name);
                    System.out.println("Country: " + country);
                    System.out.println("Date of Birth: " + dateOfBirth);
                    System.out.println("No orders found for this customer.");
                    System.out.println(
                            "----------------------------------------------------------");
                } else {
                    // Do something with the retrieved order data
                    System.out.println("Customer ID: " + customerId);
                    System.out.println("Name: " + name);
                    System.out.println("Country: " + country);
                    System.out.println("Date of Birth: " + dateOfBirth);
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Amount: " + amount);
                    System.out.println("Product: " + product);
                    System.out.println("VAT: " + vat);
                    System.out.println(
                            "------------------------------------------------------------");
                }
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        //Cant close the connection cause because we are using an in memory database so if i
        // close the connection it will return a new instance of the database
        //        finally {
        //            try {
        //                if(prpstmt != null)
        //                    prpstmt.close();
        //                if(resultSet != null)
        //                    resultSet.close();
        //                if(con != null)
        //                    con.close();
        //            } catch(SQLException e) {
        //                System.out.println(e.getMessage());
        //            }
        //
        //        }
    }


    //Retrieve a customer with their linked order(s)
    public void retrieveCustomerWithOrders(int customerId){
        PreparedStatement prpstmt = null;
        ResultSet resultSet = null;
        try {
            // Query to retrieve customer with linked orders
            String query = "SELECT c.customerId, c.name, c.country, c.dateOfBirth , o.orderId," +
                    "o.amount, o.product,o.vat FROM CUSTOMER c " +
                    "INNER JOIN orders o ON c.customerId = o.customerId " +
                    "WHERE c.customerId = ?";

            prpstmt = con.prepareStatement(query);
            prpstmt.setInt(1, customerId);

            resultSet = prpstmt.executeQuery();

            // Check if the result set is empty
            if (!resultSet.next()) {
                System.out.println("No orders found for the specified customer.");
            } else {
                // Process the result set
                do{
                    int customerId_ = resultSet.getInt("customerId");
                    String name = resultSet.getString("name");
                    String country = resultSet.getString("country");
                    Date dateOfBirth = resultSet.getDate("dateOfBirth");
                    int orderId = resultSet.getInt("orderId");
                    double amount = resultSet.getDouble("amount");
                    String product = resultSet.getString("product");
                    double vat = resultSet.getDouble("vat");

                    // Do something with the retrieved data
                    System.out.println("Customer ID: " + customerId_);
                    System.out.println("Name: " + name);
                    System.out.println("Country: " + country);
                    System.out.println("Date of Birth: " + dateOfBirth);
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Amount: " + amount);
                    System.out.println("Product: " + product);
                    System.out.println("VAT: " + vat);
                    System.out.println(
                            "----------------------------------------------------------");
                }while (resultSet.next());
            }

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        //        finally {
        //            try {
        //                if(prpstmt != null)
        //                    prpstmt.close();
        //                if(resultSet != null)
        //                    resultSet.close();
        //                if(con != null)
        //                    con.close();
        //            } catch(SQLException e) {
        //                System.out.println(e.getMessage());
        //            }
        //
        //        }
    }

    public void save(Customer customer) {
        try {
            String query = "INSERT INTO CUSTOMER (customerId, name, country, dateOfBirth) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getCountry());
            statement.setDate(4, new java.sql.Date(customer.getDateOfBirth().getTime()));

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer created successfully.");
            } else {
                System.out.println("Failed to create customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
