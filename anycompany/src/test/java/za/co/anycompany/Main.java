package za.co.anycompany;

import model.CustomerAccount;
import service.OrderServiceInterface;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.OrderService;


// you can also test with JUNIT

import java.util.*;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static PrintWriter printWriter = new PrintWriter(System.out,true);
    public static Scanner consoleInput = new Scanner(System.in);

    public static OrderServiceInterface orderService = new OrderService();

    public static void main(String[] args) {

        printWriter.println("testing place order capability....");
        if(orderService.placeOrder(new Order(1,1,100.00,15)))
            printWriter.println("Place order capability works!");
        else
            printWriter.println("Place order capability does not work!");


        printWriter.println("testing retrieve a customer with their linked orders....");
        orderService.placeOrder(new Order(2,1,200.00,30));
        CustomerAccount customerAccount = orderService.getCustomerData(1); // get orders for customer with id 1
        printWriter.println("Customer data for customer with id=1, is: "+ customerAccount.toString());

        printWriter.println("testing retrieve all customers and their linked orders....");
        orderService.placeOrder(new Order(3,2,400.00,60));
        orderService.placeOrder(new Order(4,3,800.00,120));
        List<CustomerAccount> customerAccounts = orderService.getAllCustomerData();
        for(CustomerAccount custAcc: customerAccounts)
            printWriter.println(custAcc.toString());



    }
}
