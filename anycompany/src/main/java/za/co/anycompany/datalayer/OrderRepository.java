package za.co.anycompany.datalayer;

import za.co.anycompany.exception.OrderException;
import za.co.anycompany.model.Order;
import za.co.anycompany.model.Customer;

public interface OrderRepository {
    void add(Order order, Customer customer) throws OrderException;
}