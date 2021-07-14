package test.java.za.co.anycompany;


import main.java.za.co.anycompany.datalayer.CustomerRepository;
import main.java.za.co.anycompany.service.OrderService;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String country = "UK";
        List<Integer> custNumbers = Arrays.asList( 1, 2);
        List<Integer> custOrders = Arrays.asList( 1, 2, 3, 4);
        List<String> custNames = Arrays.asList( "Samuel", "John");
        List<Date> custDOBList = Arrays.asList( Date.valueOf("2000-09-09"), Date.valueOf("2000-09-10"));

        createCustomers(custNumbers, custNames, custDOBList, country);

        za.co.anycompany.model.Customer customer = getCustomer(custNumbers.get(0));
        printCustomer(customer);


        OrderService orderService = new OrderService();
        Order order = new Order();
        //order.setOrderId(1);
        order.setAmount(30);
        orderService.placeOrder(order, custNumbers.get(0));
        //order.setOrderId(2);
        //order = new Order();
        order.setAmount(80);
        orderService.placeOrder(order, custNumbers.get(0));
        //order.setOrderId(3);
        order.setAmount(10);
        orderService.placeOrder(order, custNumbers.get(0));

        List<Order> customerOrders = orderService.loadCustomerOrders(custNumbers.get(0));
        printCustomerOrders(customerOrders);
    }

    private static za.co.anycompany.model.Customer getCustomer(int customerId){
         return CustomerRepository.load(customerId);
    }

    private static void printCustomer(za.co.anycompany.model.Customer customer){
        System.out.println("Customer Name: " +customer.getName() +"   Country:" + customer.getCountry() + "   Date Of Birth: " + customer.getDateOfBirth());
    }

    private static void printCustomerOrders(List<za.co.anycompany.model.Order> orders){
        System.out.println("//////ORDERS///////");

        for (Order order:orders
             ) {
            System.out.println("Amount: " + order.getAmount() + "   Vat:" + order.getVAT());
        }

        System.out.println("//////END OF ORDERS///////");
    }

    private static void createCustomer(int customerId, String name, Date dateOfBirth, String country){
        za.co.anycompany.model.Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName(name);
        customer.setCountry(country);
        customer.setDateOfBirth(dateOfBirth);

        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.create(customer);

    }

    private static void createCustomers(List<Integer> custNumbers, List<String> custNames, List<Date> custDOBList, String country){

        for (int i = 0; i < 2; i++) {
            createCustomer(custNumbers.get(i), custNames.get(i), custDOBList.get(i), country);
        }
    }

}
