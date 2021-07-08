package za.co.anycompany.datalayer;

import za.co.anycompany.model.Customer;

import java.sql.*;


public class CustomerOrderRepository {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private Connection con;
    private PreparedStatement prpstmt = null;
    private ResultSet resultSet = null;  
	private Order order = new Order();
	private	CustomerOrder customerOrder = new CustomerOrder();
	
	public CustomerOrderRepository(){
		con = getDBConnection();
		Order order = new Order();
		CustomerOrder customerOrder = new CustomerOrder();
	}

    public static CustomerOrder load(int customerId) { 

        try {
            prpstmt = con.prepareStatement("select * from ORDER customerId = ?");
            prpstmt.setInt(1, customerId);
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
				order.setInt(resultSet.getInt("ORDERID");
				order.setDouble(resultSet.getDouble("AMOUNT"));
				order.setDouble(resultSet.getDouble("VAT"));
				
				Customer customerInfo = CustomerRepository.load(customerId);
				order.setCustomer(customerInfo);
				customerOrder.setOrder(order);
				
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
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
        return customerOrder;
    }

   public static List<CustomerOrder> loadAll() {
			List<CustomerOrder> CustomerOrderList = null;
        try {
            prpstmt = con.prepareStatement("select * from ORDER");           
            resultSet = prpstmt.executeQuery();
            while (resultSet.next()) {
				order.setInt(resultSet.getInt("ORDERID");
				order.setDouble(resultSet.getDouble("AMOUNT"));
				order.setDouble(resultSet.getDouble("VAT"));
				
				Customer customerInfo = CustomerRepository.load(resultSet.getInt("CUSTOMERID"));
				order.setCustomer(customerInfo);
				customerOrder.setOrder(order);
				CustomerOrderList.add(customerOrder);
				
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
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
        return CustomerOrderList;
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


}
