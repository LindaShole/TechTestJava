package service;

import model.CustomerAccount;
import za.co.anycompany.model.Order;

import java.util.List;

public interface OrderServiceInterface {

    public boolean placeOrder(Order order);
    public List<CustomerAccount> getAllCustomerData();
    public CustomerAccount getCustomerData(int customerId);
}
