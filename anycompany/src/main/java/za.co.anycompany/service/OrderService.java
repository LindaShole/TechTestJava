package main.java.za.co.anycompany.service;

import main.java.za.co.anycompany.datalayer.CustomerRepository;
import main.java.za.co.anycompany.datalayer.OrderRepository;
import main.java.za.co.anycompany.model.Customer;
import main.java.za.co.anycompany.model.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class OrderService {
    private static final Logger logger = Logger.getLogger(OrderService.class.getName());

    public boolean placeOrder(Order order) {
        String correlationId = UUID.randomUUID().toString();
        logger.info(String.format("Request - correlationId: %s - CustomerId = %d ...", correlationId, order.getCustomerId()));

        try{
            Customer customer = CustomerRepository.load(order.getCustomerId());

            if (order.getAmount() == 0)
                return false;

            if (customer.getCountry().equals("UK"))
                order.setVAT(0.2d);
            else
                order.setVAT(0);

            OrderRepository.save(order);
        }catch (Exception ex){
            logger.severe(String.format("Response - correlationId: + %s saving failed", correlationId));
            return false;
        }

        logger.info(String.format("Response - correlationId: + %s saved successfully", correlationId));
        return true;
    }

    public Customer getCustomerById(int customerId) throws IOException, SQLException {
        String correlationId = UUID.randomUUID().toString();
        logger.info("Request...");

        Customer customer;

        try{
            customer = CustomerRepository.load(customerId);
            customer.setOrders(OrderRepository.getOrdersForCustomer(customerId));
        }catch (Exception ex){
            logger.severe("Response...");
            throw ex;
        }

        logger.info("Response...");
        return customer;
    }

    public List<Customer> getAllCustomers() throws IOException, SQLException {
        String correlationId = UUID.randomUUID().toString();
        logger.info("Request...");

        List<Customer> customerList;

        try{
            customerList = CustomerRepository.load();

            for (Customer customer : customerList) {
                customer.setOrders(OrderRepository.getOrdersForCustomer(customer.getCustomerId()));
            }
        }catch (Exception ex){
            logger.severe("Response...");
            throw ex;
        }

        logger.info("Response...");
        return customerList;
    }
}
