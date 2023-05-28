package za.co.anycompany;

import za.co.anycompany.config.PersistenceManager;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.CustomerService;
import za.co.anycompany.service.OrderService;

import java.sql.Connection;
import java.util.Date;

public class MainApp {

 //Cant close the connection or request new connection instance cause because we are using an in
 // memory database so if i close the connection it will return a new instance of the  database


  public static void main(String[] args){

   Connection connection =  PersistenceManager.getDBConnection();
   final OrderRepository orderRepository = new OrderRepository(connection);
   final CustomerRepository customerRepository = new CustomerRepository(connection);

   CustomerService customerService = new CustomerService(customerRepository);
   OrderService orderService = new OrderService(orderRepository);


   PersistenceManager.createTables(connection);

    Customer customer = new Customer(1,"John","RSA",new Date());
   customerService.save(customer);
    System.out.println("CustomerRepository.load(1) = " + customerService.find(1));

    // ================= Place an order, linked to a customer ===========================
   orderService.placeOrder(new Order(1,50,"Kota",0,customer));
   orderService.placeOrder(new Order(2,550,"Smart TV",20,customer));
   System.out.println("-------------------------------------------------------------------------------------");

   System.out.println("============== RETRIEVE A CUSTOMER WITH THEIR LINKED ORDER(S) =========== " +
           "\n");
   customerService.retrieveCustomerWithOrders(customer.getCustomerId());

   System.out.println("============== LOAD ALL CUSTOMERS AND THEIR LINKED ORDER(S) ==============" +
           "\n");
   customerService.loadCustomersWithOrders();


  }
}
