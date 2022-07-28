package za.co.anycompany.service;

import java.util.List;

import za.co.anycompany.exception.CustomerDataException;
import za.co.anycompany.exception.InvalidCustomerException;
import za.co.anycompany.exception.InvalidOrderException;
import za.co.anycompany.exception.OrderException;
import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;

public interface OrderService {
    boolean placeOrder(Order order, int customerId) throws OrderException, CustomerDataException, InvalidOrderException, InvalidCustomerException;
    List<Customer> listCustomersOrders() throws CustomerDataException;
    Customer getCustomerOrders(int customerId) throws CustomerDataException;
}