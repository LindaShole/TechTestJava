package za.co.anycompany;


import java.util.Date;

import datalayer.CustomerRepository;
import datalayer.OrderRepository;
import model.Customer;
import model.Order;
import service.CustomerService;
import service.OrderService;

public class Main {

    public static void main(String[] args) {

        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        
        // I drop the tables every time this class is executed so that the below steps execute as expected
        
        CustomerRepository.dropTable();
        OrderRepository.dropTable();
        
        
        //Create dummy customers and persist them
        
        Customer customerVince = new Customer();
        customerVince.setName("Vincent");
        customerVince.setCountry("US");
        customerVince.setDateOfBirth(new java.sql.Date(new Date().getTime()));
        customerService.save(customerVince);
        
        Customer customerNdivhu = new Customer();
        customerNdivhu.setName("Ndivhuwo");
        customerNdivhu.setCountry("UK");
        customerNdivhu.setDateOfBirth(new java.sql.Date(new Date().getTime()));
        customerService.save(customerNdivhu);
        
        Customer customerMatidze = new Customer();
        customerMatidze.setName("Matidze");
        customerMatidze.setCountry("ZA");
        customerMatidze.setDateOfBirth(new java.sql.Date(new Date().getTime()));
        customerService.save(customerMatidze);
        
        Customer customerJack = new Customer();
        customerJack.setName("Jack");
        customerJack.setCountry("EU");
        customerJack.setDateOfBirth(new java.sql.Date(new Date().getTime()));
        customerService.save(customerJack);
        


        //Place an order(s) while linking them to the dummy customer's
        
        Order order = new Order();
        order.setOrderId(1);
        order.setAmount(239);
        order.setVAT(6.3d);
        orderService.placeOrder(order,1);
        
        Order order2 = new Order();
        order2.setOrderId(2);
        order2.setAmount(120);
        orderService.placeOrder(order2,1);
        
        Order order3 = new Order();
        order3.setOrderId(3);
        order3.setAmount(637);
        order3.setVAT(6.9d);
        orderService.placeOrder(order3,2);
        
        Order order4 = new Order();
        order4.setOrderId(4);
        order4.setAmount(836);
        order4.setVAT(2.9d);
        orderService.placeOrder(order4,3);

       //Load all customers and their linked orders
        
        System.out.println();
        System.out.println("########################## Load all customers and their linked orders ###############################");
        for (Customer customers: customerService.getCustomerList()){
        	System.out.println();
            System.out.println(customers);
            System.out.println();
        }

        // Retrieve a customer with their linked order(s)
        
        Customer returnedCustomer = customerService.getCustomer(3);
        System.out.println();
        System.out.println("########################## Retrieve a customer with their linked order(s) ###############################");
        System.out.println();
        System.out.println(returnedCustomer);

    }
}
